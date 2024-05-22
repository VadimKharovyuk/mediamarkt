package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
}
