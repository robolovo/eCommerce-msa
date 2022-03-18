package com.example.product;


import com.example.product.domain.Product;
import com.example.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class DataLoader {

    private final ProductRepository productRepository;

    @Transactional
    public void loadData() {
        productRepository.deleteAll();

        productRepository.save(new Product("Product-001", 100, 1500));
        productRepository.save(new Product("Product-002", 150, 3000));
        productRepository.save(new Product("Product-003", 200, 500));
    }
}
