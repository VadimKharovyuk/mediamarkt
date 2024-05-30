package com.example.mediamarkt.service;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    @Cacheable(value = "searchProductsByName", key = "#name")
    public List<Product> searchProductsByName(String name) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            for (String key : jedis.keys("searchProductsByName*")) {
                jedis.expire(key, 60); // Установка TTL на 5 минут (300 секунд)
            }
        }
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    @Cacheable(key = "#id", value = "getProductById")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


}
