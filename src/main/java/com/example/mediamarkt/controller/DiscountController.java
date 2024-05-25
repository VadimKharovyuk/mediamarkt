package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Category;
import com.example.mediamarkt.model.Discount;
import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.service.CategoryService;
import com.example.mediamarkt.service.DiscountService;
import com.example.mediamarkt.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discounts")
@AllArgsConstructor
public class DiscountController {

    private final DiscountService discountService;
    private final CategoryService categoryService;
    private final ProductService productService;


    @GetMapping("/new")
    public String createDiscountForm(Model model) {
        model.addAttribute("discount", new Discount());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "discount_form";
    }

    @PostMapping("/save")
    public String saveDiscount(@ModelAttribute Discount discount) {
        discountService.saveDiscount(discount);
        return "redirect:/discounts";
    }

    @GetMapping
    public String viewAllDiscounts(Model model) {
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("discounts", discounts);
        return "discount_list";
    }


    @PostMapping("/delete/{id}")
    public String deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return "redirect:/discounts";
    }


}


