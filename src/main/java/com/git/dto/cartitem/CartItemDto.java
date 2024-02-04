package com.git.dto.cartitem;

import com.git.domain.cart.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDto {

    private String itemName;
    private int price;
    private String color;
    private String size;
    private int count;
    private int orderPrice;

    public static CartItemDto toSave(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setItemName(cartItem.getItem().getItemName());
        dto.setPrice(cartItem.getItem().getPrice());
        dto.setColor(cartItem.getColor());
        dto.setSize(cartItem.getSize());
        dto.setCount(cartItem.getCount());
        dto.setOrderPrice(dto.getPrice() * dto.getCount());
        return dto;
    }


}
