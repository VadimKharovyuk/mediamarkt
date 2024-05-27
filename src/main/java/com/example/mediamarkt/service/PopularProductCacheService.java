package com.example.mediamarkt.service;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ProductAddition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PopularProductCacheService {

 private final RedisTemplate<String, List<Product>> redisTemplate;
    private static final String POPULAR_PRODUCTS_KEY = "popularProducts";


    public void cachePopularProducts(List<Product> popularProducts) {
        redisTemplate.opsForValue().set(POPULAR_PRODUCTS_KEY, popularProducts);
    }

    public List<Product> getPopularProducts() {
        return redisTemplate.opsForValue().get(POPULAR_PRODUCTS_KEY);
    }
}
