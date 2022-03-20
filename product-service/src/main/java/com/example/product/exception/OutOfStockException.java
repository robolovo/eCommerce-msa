package com.example.product.exception;

public class OutOfStockException extends ApplicationException {

    public OutOfStockException(ErrorCode errorCode) {
        super(errorCode);
    }
}
