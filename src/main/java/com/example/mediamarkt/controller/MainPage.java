package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Category;
import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.repository.ProductRepository;
import com.example.mediamarkt.service.CategoryService;
import com.example.mediamarkt.service.ProductAdditionService;
import com.example.mediamarkt.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainPage {

    private final CategoryService categoryService;
    private final ProductAdditionService  productAdditionService ;


    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> popularProducts = productAdditionService.getMostAddedProducts(15);


        List<List<Product>> groupedProducts = new ArrayList<>();
        for (int i = 0; i < popularProducts.size(); i += 4) {
            int end = Math.min(i + 4, popularProducts.size());
            groupedProducts.add(popularProducts.subList(i, end));
        }

        model.addAttribute("groupedProducts", groupedProducts);
        model.addAttribute("categories", categories);
        return "homePage";
    }

}

