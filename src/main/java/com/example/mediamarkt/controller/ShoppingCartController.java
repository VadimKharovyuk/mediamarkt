package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ShoppingCart;
import com.example.mediamarkt.model.User;
import com.example.mediamarkt.service.ProductAdditionService;
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
    private final ProductAdditionService productAdditionService;

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

        productAdditionService.incrementProductCount(product);

        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeShoppingCart(@PathVariable("id") Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return "redirect:/cart"; // Перенаправляем на страницу корзины
    }


    @PostMapping("/clear")
    public String clearCard(Product product, HttpSession httpSession) {
        ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("shoppingCart");
        if (shoppingCart != null) {
            shoppingCartService.cleanCard(product, shoppingCart);
            httpSession.setAttribute("shoppingCart", shoppingCart);
        }
        return "redirect:/cart";

    }
}
