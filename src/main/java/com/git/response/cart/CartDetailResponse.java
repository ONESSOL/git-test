package com.git.response.cart;

import com.git.domain.cart.CartItem;
import com.git.dto.cartitem.CartItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CartDetailResponse {

    private List<CartItemDto> cartItemDtos = new ArrayList<>();
    private int totalPrice;

    public static CartDetailResponse toSave(List<CartItem> cartItems) {
        CartDetailResponse response = new CartDetailResponse();
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        int totalPrice = 0;
        for(CartItem cartItem : cartItems) {
            CartItemDto dto = CartItemDto.toSave(cartItem);
            totalPrice += dto.getOrderPrice();
            cartItemDtoList.add(dto);
        }
        response.setCartItemDtos(cartItemDtoList);
        response.setTotalPrice(totalPrice);
        return response;
    }
}
