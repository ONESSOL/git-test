package com.git.response.item;

import com.git.domain.item.Item;
import com.git.domain.item.ItemCode;
import com.git.domain.item.ItemCondition;
import com.git.dto.item.ItemConditionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemUpdateResponse {

    private String itemName;
    private int price;
    private ItemCode itemCode;
    private List<ItemConditionDto> itemConditionDtos;

    public static ItemUpdateResponse toSave(Item item) {
        ItemUpdateResponse response = new ItemUpdateResponse();
        response.setItemName(item.getItemName());
        response.setPrice(item.getPrice());
        response.setItemCode(item.getItemCode());
        List<ItemConditionDto> itemConditionDtoList = new ArrayList<>();
        for(ItemCondition itemCondition : item.getItemCondition()) {
            ItemConditionDto dto = ItemConditionDto.toSave(itemCondition);
            itemConditionDtoList.add(dto);
        }
        response.setItemConditionDtos(itemConditionDtoList);
        return response;
    }
}
