package com.git.exception.item;

import lombok.Getter;
import lombok.Setter;

public class ItemNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 상품을 찾을 수 없습니다.";

    public ItemNotFoundException() {
        super(MESSAGE);
    }

    public ItemNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
