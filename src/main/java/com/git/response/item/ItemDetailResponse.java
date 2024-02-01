package com.git.response.item;

import com.git.domain.item.Item;
import com.git.domain.item.ItemCode;
import com.git.domain.item.ItemCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemDetailResponse {

    private String itemName;
    private int price;
    private ItemCode itemCode;
    private List<ItemCondition> itemConditions;

    public static ItemDetailResponse toSave(Item item) {
        ItemDetailResponse response = new ItemDetailResponse();
        response.setItemName(item.getItemName());
        response.setPrice(item.getPrice());
        response.setItemCode(item.getItemCode());
        response.setItemConditions(item.getItemCondition());
        return response;
    }
}
