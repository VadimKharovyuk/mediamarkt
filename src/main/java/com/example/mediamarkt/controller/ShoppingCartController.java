package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ShoppingCart;
import com.example.mediamarkt.model.User;
import com.example.mediamarkt.service.ProductService;
import com.example.mediamarkt.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    @GetMapping()
    public String viewShoppingCart(Model model, HttpSession session) {
        ShoppingCart shoppingCart = shoppingCartService.getCartFromSession(session);
        BigDecimal totalPrice = shoppingCartService.getTotalPrice(shoppingCart);

        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("totalPrice", totalPrice);

        return "shopping_cart";
    }

    @PostMapping("/add/{productId}")
    public String addShoppingCart(@PathVariable("productId") Long productId, HttpSession session) {
        ShoppingCart shoppingCart = shoppingCartService.getCartFromSession(session);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }

        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        shoppingCart.getProducts().add(product);
        session.setAttribute("shoppingCart", shoppingCart);

        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeShoppingCart(@PathVariable("id") Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return "redirect:/cart"; // Перенаправляем на страницу корзины
    }
}
