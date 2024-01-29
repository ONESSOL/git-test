package com.git.exception.member;

public class CheckNewPasswordException extends RuntimeException {

    private static final String MESSAGE = "비밀번호를 다시 확인해주세요";

    public CheckNewPasswordException() {
        super(MESSAGE);
    }

    public CheckNewPasswordException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
