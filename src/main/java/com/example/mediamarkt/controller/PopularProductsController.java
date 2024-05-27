package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Category;
import com.example.mediamarkt.model.ProductAddition;
import com.example.mediamarkt.service.CategoryService;
import com.example.mediamarkt.service.PopularProductCacheService;
import com.example.mediamarkt.service.ProductAdditionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/popular-products")

@AllArgsConstructor
public class PopularProductsController {

    private PopularProductCacheService popularProductCacheService;
    private ProductAdditionService productAdditionService;
    private CategoryService categoryService;

    @GetMapping()
    public String viewPopularProducts(Model model) {
        List<ProductAddition> popularProducts = popularProductCacheService.getPopularProducts();

        if (popularProducts == null || popularProducts.isEmpty()) {
            popularProducts = productAdditionService.getMostAddedProducts(10);
            popularProductCacheService.cachePopularProducts(popularProducts);
        }

        model.addAttribute("popularProducts", popularProducts);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "popular_products";
    }
}
