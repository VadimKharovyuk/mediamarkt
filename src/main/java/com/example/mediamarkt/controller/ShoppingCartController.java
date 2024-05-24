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

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @GetMapping
    public String viewShoppingCart(Model model, HttpSession session) {
        ShoppingCart shoppingCart = shoppingCartService.getCartFromSession(session);

        // Проверка, что корзина не пуста
        System.out.println("Viewing cart. Cart size: " + (shoppingCart != null ? shoppingCart.getProducts().size() : 0));

        // Передаем корзину в модель для отображения на странице
        model.addAttribute("shoppingCart", shoppingCart);

        // Возвращаем имя представления
        return "shopping_cart";
    }


@PostMapping("/add/{productId}")
public String addProductToCart(@PathVariable("productId") Long productId, HttpSession session) {
    ShoppingCart shoppingCart = shoppingCartService.getCartFromSession(session);

    if (shoppingCart == null) {
        shoppingCart = new ShoppingCart();
        session.setAttribute("shoppingCart", shoppingCart);
    }

    // Убедитесь, что список продуктов инициализирован
    if (shoppingCart.getProducts() == null) {
        shoppingCart.setProducts(new ArrayList<>());
    }

    System.out.println("Before adding product. Cart size: " + shoppingCart.getProducts().size());

    Product product = productService.getProductById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    shoppingCart.getProducts().add(product);

    System.out.println("After adding product. Cart size: " + shoppingCart.getProducts().size());

    session.setAttribute("shoppingCart", shoppingCart);

    System.out.println("After saving to session. Cart size: " + ((ShoppingCart) session.getAttribute("shoppingCart")).getProducts().size());

    return "redirect:/cart";
}







    @PostMapping("/remove/{id}")
    public String removeShoppingCart(@PathVariable("id") Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return "redirect:/cart"; // Перенаправляем на страницу корзины
    }
}
