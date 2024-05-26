package com.example.mediamarkt.service;
import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ProductAddition;
import com.example.mediamarkt.repository.ProductAdditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductAdditionService {

    private final ProductAdditionRepository productAdditionRepository;

    public void incrementProductCount(Product product) {
        ProductAddition productAddition = productAdditionRepository.findByProductId(product.getId());
        if (productAddition == null) {
            productAddition = new ProductAddition();
            productAddition.setProduct(product);
            productAddition.setCount(1);
        } else {
            productAddition.setCount(productAddition.getCount() + 1);
        }
        productAdditionRepository.save(productAddition);
    }

    public List<ProductAddition> getMostAddedProducts(int limit) {
        return productAdditionRepository.findAll().stream()
                .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount()))
                .limit(limit)
                .toList();
    }
}
