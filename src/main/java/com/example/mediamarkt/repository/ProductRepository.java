package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :startPrice AND :endPrice AND p.category.id = :categoryId")
    List<Product> findByPriceBetweenAndCategoryId(@Param("startPrice") BigDecimal startPrice,
                                                  @Param("endPrice") BigDecimal endPrice,
                                                  @Param("categoryId") Long categoryId);
}

