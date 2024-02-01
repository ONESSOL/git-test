package com.git.controller.item;

import com.git.domain.item.ItemCode;
import com.git.request.item.ItemCreateRequest;
import com.git.request.item.ItemUpdateRequest;
import com.git.response.item.ItemCreateResponse;
import com.git.response.item.ItemDetailResponse;
import com.git.response.item.ItemListResponse;
import com.git.response.item.ItemUpdateResponse;
import com.git.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<ItemCreateResponse> saveItem(@RequestBody ItemCreateRequest request) {
        return ResponseEntity.ok(itemService.saveItem(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @GetMapping("/list/{itemCode}")
    public ResponseEntity<Page<ItemListResponse>> findByItemCode(@PathVariable ItemCode itemCode,
                                                                @PageableDefault(page = 1, sort = "id")Pageable pageable) {
        return ResponseEntity.ok(itemService.findByItemCode(itemCode, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ItemListResponse>> findByItemName(@RequestParam String itemName,
                                                                 @PageableDefault(page = 1, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(itemService.findByItemName(itemName, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemUpdateResponse> update(@PathVariable Long id, @RequestBody ItemUpdateRequest request) {
        return ResponseEntity.ok(itemService.update(id, request));
    }
}




























