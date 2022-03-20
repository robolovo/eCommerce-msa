package com.example.product.exception;

public class ProductNotFoundException extends ApplicationException {

    public ProductNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
