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

import java.util.List;

@Controller
@AllArgsConstructor
public class MainPage {

    private final CategoryService categoryService;


    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "homePage";
    }
}

