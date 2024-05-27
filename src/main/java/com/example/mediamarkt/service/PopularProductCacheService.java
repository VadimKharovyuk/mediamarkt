package com.example.mediamarkt.service;

import com.example.mediamarkt.model.ProductAddition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularProductCacheService {

    @Autowired
    private RedisTemplate<String, List<ProductAddition>> redisTemplate;

    private static final String POPULAR_PRODUCTS_KEY = "popularProducts";

    public void cachePopularProducts(List<ProductAddition> popularProducts) {
        redisTemplate.opsForValue().set(POPULAR_PRODUCTS_KEY, popularProducts);
    }

    public List<ProductAddition> getPopularProducts() {
        return redisTemplate.opsForValue().get(POPULAR_PRODUCTS_KEY);
    }
}
