package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.ProductAddition;
import com.example.mediamarkt.service.ProductAdditionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/popular-products")
public class PopularProductsController {

    private final ProductAdditionService productAdditionService;

    @GetMapping()
    public String viewPopularProducts(Model model) {
        List<ProductAddition> popularProducts = productAdditionService.getMostAddedProducts(10);
        model.addAttribute("popularProducts", popularProducts);
        return "popular_products";
    }
}
