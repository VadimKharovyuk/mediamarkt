package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ShippingAddress;
import com.example.mediamarkt.model.ShoppingCart;
import com.example.mediamarkt.service.ProductService;
import com.example.mediamarkt.service.ShippingAddressService;
import com.example.mediamarkt.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {


    private final ShippingAddressService shippingAddressService;

    @GetMapping
    public String viewOrders(Model model) {
        List<ShippingAddress> orders = shippingAddressService.getAllShippingAddresses();
        model.addAttribute("orders", orders);
        return "orders-List";
    }

    @GetMapping("/new")
    public String showNewOrderForm(Model model) {
        model.addAttribute("shippingAddress", new ShippingAddress());
        return "new_order";
    }

    @PostMapping("/save")
    public String createOrder(@ModelAttribute("shippingAddress") ShippingAddress shippingAddress) {
        // Here you would normally save the shipping address and create an order
        shippingAddressService.saveShippingAddress(shippingAddress);
        // Create and save the order using orderService (this part is omitted for brevity)
        return "redirect:/orders";
    }
}

