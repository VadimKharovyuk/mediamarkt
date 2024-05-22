package com.example.mediamarkt.service;
import com.example.mediamarkt.model.Discount;
import com.example.mediamarkt.repository.DiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiscountService {


    private final DiscountRepository discountRepository;

    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public Optional<Discount> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}

