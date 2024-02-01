package com.git.dto.item;

import com.git.domain.item.ItemCondition;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemConditionDto {

    private String color;
    private String size;
    private int quantity;

    public static ItemConditionDto toSave(ItemCondition itemCondition) {
        ItemConditionDto dto = new ItemConditionDto();
        dto.setColor(itemCondition.getColor());
        dto.setSize(itemCondition.getSize());
        dto.setQuantity(itemCondition.getQuantity());
        return dto;
    }
}
