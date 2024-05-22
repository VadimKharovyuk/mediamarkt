package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
