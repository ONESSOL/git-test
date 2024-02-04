package com.git.request.cart;

import com.git.domain.item.ItemCondition;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartCreateRequest {

    private Long itemId;
    private int count;
    private String color;
    private String size;
}
