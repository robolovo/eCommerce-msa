package com.example.product.service;

import com.example.product.controller.dto.ProductCreateRequest;
import com.example.product.controller.dto.ProductResponse;
import com.example.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductCreateRequest productCreateRequest) {
        productRepository.save(productCreateRequest.toProduct());
    }

//    public List<ProductResponse> findAll() {
//        return productRepository.findAll()
//                .stream()
//                .map(ProductResponse::of)
//                .collect(Collectors.toList());
//    }
}
