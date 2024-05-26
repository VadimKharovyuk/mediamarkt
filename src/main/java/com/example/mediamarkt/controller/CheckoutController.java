package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.ShippingAddress;
import com.example.mediamarkt.service.ShippingAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CheckoutController {

    ShippingAddressService shippingAddressService ;

    @GetMapping("/checkout")
    public String showCheckoutForm(Model model) {
        model.addAttribute("shippingAddress", new ShippingAddress());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckoutForm(@ModelAttribute("shippingAddress") ShippingAddress shippingAddress) {
       shippingAddressService.saveShippingAddress(shippingAddress);
        return "checkout-success";
    }
    @GetMapping("/shippingAddresses")
    public String getAllShippingAddresses(Model model) {
        List<ShippingAddress> shippingAddresses = shippingAddressService.getAllShippingAddresses();
        model.addAttribute("shippingAddresses", shippingAddresses);
        return "shipping-addresses";
    }
}

