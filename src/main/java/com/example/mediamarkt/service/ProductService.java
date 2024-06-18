package com.example.mediamarkt.service;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CacheManager cacheManager;

    @Cacheable(value = "searchProductsByName", key = "#name")
    public List<Product> searchProductsByName(String name) {
        try {
           return productRepository.findByNameContainingIgnoreCase(name);
        } catch (DataAccessException e) {
            return productRepository.findByNameContainingIgnoreCase(name);
        }
    }


    public Product saveProduct(Product product) {
        Objects.requireNonNull(cacheManager.getCache("getProductById")).put(product.getId(), product);
        return productRepository.save(product);

    }

    @Cacheable(key = "#id", value = "getProductById")
    public Optional<Product> getProductById(Long id) {
        try {
            return productRepository.findById(id);
        } catch (DataAccessException e) {
            return productRepository.findById(id);
        }
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        Objects.requireNonNull(cacheManager.getCache("getProductById")).evict(id);
        productRepository.deleteById(id);

    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


    public List<Product> findProductsByPriceBetweenAndCategory(BigDecimal startPrice, BigDecimal endPrice, Long categoryId) {
        return productRepository.findByPriceBetweenAndCategoryId(startPrice, endPrice, categoryId);
    }


}
