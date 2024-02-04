package com.git.response.cart;

import com.git.domain.cart.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartCreateResponse {

    private String itemName;
    private int price;
    private String color;
    private String size;

    public static CartCreateResponse toSave(CartItem cartItem) {
        CartCreateResponse response = new CartCreateResponse();
        response.setItemName(cartItem.getItem().getItemName());
        response.setPrice(cartItem.getItem().getPrice());
        response.setColor(cartItem.getColor());
        response.setSize(cartItem.getSize());
        return response;
    }
}
