package com.example.mediamarkt.service;
import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ProductAddition;
import com.example.mediamarkt.repository.ProductAdditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductAdditionService {

    private final ProductAdditionRepository productAdditionRepository;





    public List<Product> getMostAddedProducts(int limit) {
        // Получаем все дополнения продуктов
        List<ProductAddition> additions = productAdditionRepository.findAll();

        // Группируем по продукту и подсчитываем количество добавлений для каждого продукта
        return additions.stream()
                .collect(Collectors.groupingBy(ProductAddition::getProduct, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // Сортируем по количеству добавлений (по убыванию)
                .limit(limit) // Ограничиваем количество возвращаемых продуктов
                .map(entry -> entry.getKey()) // Преобразуем в список продуктов
                .collect(Collectors.toList());
    }
    public void incrementProductCount(Product product) {
        Optional<ProductAddition> existingAddition = productAdditionRepository.findByProduct(product);

        if (existingAddition.isPresent()) {
            ProductAddition productAddition = existingAddition.get();
            productAddition.setCount(productAddition.getCount() + 1);
            productAdditionRepository.save(productAddition);
        } else {
            ProductAddition newAddition = new ProductAddition();
            newAddition.setProduct(product);
            newAddition.setCount(1);
            productAdditionRepository.save(newAddition);
        }
    }
}