package com.example.product.controller;

import com.example.product.controller.dto.ProductCreateRequest;
import com.example.product.controller.dto.SubtractStockRequest;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/product-service")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        productService.createProduct(productCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/products")
    public ResponseEntity<Void> subtractStockQuantity(@RequestBody SubtractStockRequest subtractStockRequest) {
        productService.subtractStockQuantity(subtractStockRequest);
        return ResponseEntity.ok().build();
    }
}
