package com.example.mediamarkt.service;

import com.example.mediamarkt.model.ShippingAddress;
import com.example.mediamarkt.repository.ShippingAddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingAddressService {


    private  final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    public Optional<ShippingAddress> getShippingAddressById(Long id) {
        return shippingAddressRepository.findById(id);
    }

    public List<ShippingAddress> getAllShippingAddresses() {
        return shippingAddressRepository.findAll();
    }

    public void deleteShippingAddress(Long id) {
        shippingAddressRepository.deleteById(id);
    }
}

