package com.example.product.service;

import com.example.product.controller.dto.ProductCreateRequest;
import com.example.product.controller.dto.ProductResponse;
import com.example.product.controller.dto.SubtractStockRequest;
import com.example.product.domain.Product;
import com.example.product.domain.ProductRepository;
import com.example.product.exception.ErrorCode;
import com.example.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.product.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductCreateRequest productCreateRequest) {
        productRepository.save(productCreateRequest.toProduct());
    }

    public void subtractStockQuantity(SubtractStockRequest subtractStockRequest) {
        Product product = productRepository.findById(subtractStockRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

        product.subtractStockQuantity(subtractStockRequest.getQuantity());
    }

}
