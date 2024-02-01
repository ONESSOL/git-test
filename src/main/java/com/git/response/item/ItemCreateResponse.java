package com.git.response.item;

import com.git.domain.item.Item;
import com.git.domain.item.ItemCode;
import com.git.domain.item.ItemCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemCreateResponse {

    private String itemName;
    private int price;
    private ItemCode itemCode;
    private List<ItemCondition> itemCondition;

    public static ItemCreateResponse toSave(Item item) {
        ItemCreateResponse response = new ItemCreateResponse();
        response.setItemName(item.getItemName());
        response.setPrice(item.getPrice());
        response.setItemCode(item.getItemCode());
        response.setItemCondition(item.getItemCondition());
        return response;
    }
}
