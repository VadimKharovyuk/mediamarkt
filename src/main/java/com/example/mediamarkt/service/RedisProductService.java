//package com.example.mediamarkt.service;
//
//import com.example.mediamarkt.model.Product;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class RedisProductService {
//
//
//    private  final RedisTemplate<String, String> redisTemplate;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public List<Product> getPopularProductsFromRedis() throws IOException {
//        String serializedProducts = redisTemplate.opsForValue().get("popularProducts");
//        if (serializedProducts != null) {
//            return deserializeProductList(serializedProducts);
//        }
//        return new ArrayList<>();
//    }
//
//    public void savePopularProductsToRedis(List<Product> products) throws JsonProcessingException {
//        String serializedProducts = serializeProductList(products);
//        redisTemplate.opsForValue().set("popularProducts", serializedProducts);
//    }
//
//    private String serializeProductList(List<Product> products) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(products);
//    }
//
//    private List<Product> deserializeProductList(String serializedProducts) throws IOException {
//        return objectMapper.readValue(serializedProducts, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
//    }
//}
