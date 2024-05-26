package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.ProductAddition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAdditionRepository extends JpaRepository<ProductAddition,Long> {
    ProductAddition findByProductId(Long productId);
}
