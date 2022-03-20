package com.example.user.exception;

public class NotEnoughMoneyException extends ApplicationException {

    public NotEnoughMoneyException(ErrorCode errorCode) {
        super(errorCode);
    }
}
