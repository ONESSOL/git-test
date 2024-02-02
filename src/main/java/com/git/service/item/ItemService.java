package com.git.service.item;

import com.git.domain.item.Item;
import com.git.domain.item.ItemCode;
import com.git.domain.item.ItemCondition;
import com.git.exception.item.ItemNotFoundException;
import com.git.repository.item.ItemConditionRepository;
import com.git.repository.item.ItemRepository;
import com.git.request.item.ItemCreateRequest;
import com.git.request.item.ItemUpdateRequest;
import com.git.response.item.ItemCreateResponse;
import com.git.response.item.ItemDetailResponse;
import com.git.response.item.ItemListResponse;
import com.git.response.item.ItemUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemConditionRepository itemConditionRepository;

    @Transactional
    public ItemCreateResponse saveItem(ItemCreateRequest request) {

        Item item = itemRepository.save(Item.builder()
                .itemName(request.getItemName())
                .price(request.getPrice())
                .itemCode(request.getItemCode())
                .itemCondition(request.getItemConditions())
                .build());

        for(ItemCondition itemCondition : request.getItemConditions()) {
            itemConditionRepository.save(itemCondition);
        }

        return ItemCreateResponse.toSave(item);
    }

    @Transactional(readOnly = true)
    public ItemDetailResponse findById(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        return ItemDetailResponse.toSave(item);
    }

    @Transactional(readOnly = true)
    public Page<ItemListResponse> findByItemCode(ItemCode itemCode, Pageable pageable) {

        int firstPage = pageable.getPageNumber() - 1;
        PageRequest pageRequest = PageRequest.of(firstPage, 10, Sort.by(DESC, "id"));
        Page<Item> itemList = itemRepository.findByItemCode(itemCode, pageRequest);
        return itemList.map(ItemListResponse::toSave);
    }

    @Transactional
    public Page<ItemListResponse> findByItemName(String itemName, Pageable pageable) {

        int firstPage = pageable.getPageNumber() - 1;
        PageRequest pageRequest = PageRequest.of(firstPage, 10, Sort.by(DESC, "id"));
        Page<Item> itemList = itemRepository.findByItemName(itemName, pageRequest);
        return itemList.map(ItemListResponse::toSave);
    }

    @Transactional
    public ItemUpdateResponse update(Long itemId, ItemUpdateRequest request) {

        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        item.getItemCondition().removeAll(item.getItemCondition());

        for(ItemCondition itemCondition : request.getItemConditions()) {
            itemConditionRepository.save(itemCondition);
            item.addItemCondition(itemCondition);
        }
        item.update(request.getItemName(), request.getPrice());
        return ItemUpdateResponse.toSave(item);
    }

    @Transactional
    public void deleteItem(@PathVariable Long itemId) {
        itemRepository.deleteById(itemId);
    }
}

























