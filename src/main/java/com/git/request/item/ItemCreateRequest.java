package com.git.request.item;

import com.git.domain.item.ItemCode;
import com.git.domain.item.ItemCondition;
import com.git.dto.item.ItemConditionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemCreateRequest {

    private String itemName;
    private int price;
    private ItemCode itemCode;
    private List<ItemCondition> itemConditions;

}
