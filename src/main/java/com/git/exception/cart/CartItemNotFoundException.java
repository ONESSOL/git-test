package com.git.exception.cart;

public class CartItemNotFoundException extends RuntimeException {

    private static final String MESSAGE = "장바구니 상품을 찾을 수 없습니다.";

    public CartItemNotFoundException() {
        super(MESSAGE);
    }

    public CartItemNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
