package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Long> {

    @Query("SELECT d FROM Discount d WHERE d.category.id = :categoryId AND :now BETWEEN d.startDate AND d.endDate")
    Optional<Discount> findActiveDiscountForCategory(Long categoryId, LocalDate now);



}

