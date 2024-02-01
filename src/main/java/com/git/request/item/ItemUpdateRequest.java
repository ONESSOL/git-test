package com.git.request.item;

import com.git.domain.item.ItemCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemUpdateRequest {

    private String itemName;
    private int price;
    private List<ItemCondition> itemConditions;
}
