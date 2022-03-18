package com.example.product.controller.dto;

import com.example.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    private String name;

    private int stockQuantity;

    private int unitPrice;

    public ProductCreateRequest(String name, int stockQuantity, int unitPrice) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
    }

    public Product toProduct() {
        return new Product(name, stockQuantity, unitPrice);
    }
}
