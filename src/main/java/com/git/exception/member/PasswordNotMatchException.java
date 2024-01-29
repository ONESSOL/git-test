package com.git.exception.member;

public class PasswordNotMatchException extends RuntimeException {

    private static final String MESSAGE = "비밀번호가 틀립니다.";

    public PasswordNotMatchException() {
        super(MESSAGE);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
