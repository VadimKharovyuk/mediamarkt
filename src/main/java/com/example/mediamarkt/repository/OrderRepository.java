package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
