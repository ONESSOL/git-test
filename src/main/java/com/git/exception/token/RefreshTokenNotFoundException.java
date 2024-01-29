package com.git.exception.token;

public class RefreshTokenNotFoundException extends RuntimeException {

    private static final String MESSAGE = "리프레시 토큰을 찾을 수 없습니다.";

    public RefreshTokenNotFoundException() {
        super(MESSAGE);
    }

    public RefreshTokenNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
