package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ProductAddition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAdditionRepository extends JpaRepository<ProductAddition,Long> {
    ProductAddition findByProductId(Long productId);

    Optional<ProductAddition> findByProduct(Product product);

}
