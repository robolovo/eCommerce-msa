package com.example.user.exception;

public enum ErrorCode {

    USER_NOT_FOUND(404, "U001", "존재하지 않는 회원입니다."),
    NOT_ENOUGH_MONEY(400, "U002", "잔고가 부족합니다.")
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
