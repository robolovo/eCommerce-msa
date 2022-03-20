package com.example.product.exception;

public enum ErrorCode {

    PRODUCT_NOT_FOUND(404, "P_001", "존재하지 않는 상품입니다."),
    OUT_OF_STOCK(400, "P_002", "해당 상품의 재고가 없습니다."),


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
