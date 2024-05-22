package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
