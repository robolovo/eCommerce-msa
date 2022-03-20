package com.example.product.infrastructure.kafka;

import com.example.product.domain.Product;
import com.example.product.domain.ProductRepository;
import com.example.product.exception.ErrorCode;
import com.example.product.exception.OutOfStockException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.service.dto.ProductKafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.product.exception.ErrorCode.PRODUCT_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Transactional
    @KafkaListener(topics = "product-topic")
    public void receive(String message) {
        Map<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(message, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Product product = productRepository.findById(Long.parseLong(map.get("productId")))
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

        product.addStockQuantity(Integer.parseInt(map.get("quantity")));
    }

//    private void sendSuccessMessage(Map<String, String> map) {
//        kafkaProducer.send("user-topic", new ProductKafkaMessage(
//                Long.parseLong(map.get("productId")),
//                Long.parseLong(map.get("userId")),
//                Integer.parseInt(map.get("quantity")),
//                Integer.parseInt(map.get("unitPrice")),
//                "success"
//        ));
//    }
//
//    private void sendFailureMessage(Map<String, String> map) {
//        kafkaProducer.send("user-topic", new ProductKafkaMessage(
//                Long.parseLong(map.get("productId")),
//                Long.parseLong(map.get("userId")),
//                Integer.parseInt(map.get("quantity")),
//                Integer.parseInt(map.get("unitPrice")),
//                "failure"
//        ));
//    }
}
