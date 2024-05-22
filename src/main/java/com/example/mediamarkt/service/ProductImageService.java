package com.example.mediamarkt.service;
import com.example.mediamarkt.model.ProductImage;
import com.example.mediamarkt.repository.ProductImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductImageService {


    private  final ProductImageRepository productImageRepository;

    public ProductImage saveProductImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    public Optional<ProductImage> getProductImageById(Long id) {
        return productImageRepository.findById(id);
    }

    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    public void deleteProductImage(Long id) {
        productImageRepository.deleteById(id);
    }
}

