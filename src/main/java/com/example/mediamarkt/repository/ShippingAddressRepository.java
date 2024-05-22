package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
}
