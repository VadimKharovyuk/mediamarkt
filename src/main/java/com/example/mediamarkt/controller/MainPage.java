package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Category;
import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.service.CategoryService;
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
    private final ProductService productService;


    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> productList = productService.getAllProducts();

        List<List<Product>> groupedProducts = new ArrayList<>();
        for (int i = 0; i < productList.size(); i += 4) {
            int end = Math.min(i + 4, productList.size());
            groupedProducts.add(productList.subList(i, end));
        }

        model.addAttribute("groupedProducts", groupedProducts);
        model.addAttribute("categories", categories);
        return "homePage";
    }

}

