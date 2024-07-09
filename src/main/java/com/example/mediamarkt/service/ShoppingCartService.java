//package com.example.mediamarkt.service;
//import com.example.mediamarkt.model.Discount;
//import com.example.mediamarkt.model.Product;
//import com.example.mediamarkt.model.ShoppingCart;
//import com.example.mediamarkt.repository.ShoppingCartRepository;
//import com.example.mediamarkt.repository.UserRepository;
//import jakarta.servlet.http.HttpSession;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Service
//@AllArgsConstructor
//public class ShoppingCartService {
//
//    private final ShoppingCartRepository shoppingCartRepository;
//    private final UserRepository userRepository;
//    private final ProductService productService;
//    private final DiscountService discountService;
//
//    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
//        return shoppingCartRepository.save(shoppingCart);
//    }
//
//    public Optional<ShoppingCart> getShoppingCartById(Long id) {
//        return shoppingCartRepository.findById(id);
//    }
//
//    public List<ShoppingCart> getAllShoppingCarts() {
//        return shoppingCartRepository.findAll();
//    }
//
//    public void deleteShoppingCart(Long id) {
//        shoppingCartRepository.deleteById(id);
//    }
//
//
//
//    public ShoppingCart getCartFromSession(HttpSession session) {
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
//        if (shoppingCart == null) {
//            shoppingCart = new ShoppingCart();
//            session.setAttribute("shoppingCart", shoppingCart);
//        }
//        return shoppingCart;
//    }
//
//    public BigDecimal getTotalPrice(ShoppingCart shoppingCart) {
//        BigDecimal totalPrice = BigDecimal.ZERO;
//
//        for (Product product : shoppingCart.getProducts()) {
//            BigDecimal price = product.getPrice();
//            Long productId = product.getId();
//            Long categoryId = product.getCategory().getId();
//
//            Optional<Discount> productDiscount = discountService.getActiveDiscountForProduct(productId);
//            if (productDiscount.isPresent()) {
//                price = price.subtract(productDiscount.get().getAmount());
//            } else {
//                Optional<Discount> categoryDiscount = discountService.getActiveDiscountForCategory(categoryId);
//                if (categoryDiscount.isPresent()) {
//                    price = price.subtract(categoryDiscount.get().getAmount());
//                }
//            }
//
//            totalPrice = totalPrice.add(price);
//        }
//
//        return totalPrice;
//    }
//
//    private BigDecimal getPriceWithDiscount(Product product) {
//        BigDecimal price = product.getPrice();
//        Long categoryId = product.getCategory().getId();
//
//        return discountService.getActiveDiscountForCategory(categoryId)
//                .map(discount -> price.subtract(discount.getAmount()))
//                .orElse(price);
//    }
//
//
//}
//
//
package com.example.mediamarkt.service;

import com.example.mediamarkt.model.Discount;
import com.example.mediamarkt.model.Product;
import com.example.mediamarkt.model.ShoppingCart;
import com.example.mediamarkt.repository.ShoppingCartRepository;
import com.example.mediamarkt.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final DiscountService discountService;

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    public ShoppingCart getCartFromSession(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        return shoppingCart;
    }

    public BigDecimal getTotalPrice(ShoppingCart shoppingCart) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Product product : shoppingCart.getProducts()) {
            BigDecimal price = product.getPrice();
            Long productId = product.getId();
            Long categoryId = product.getCategory().getId();

            Optional<Discount> productDiscount = discountService.getActiveDiscountForProduct(productId);
            Optional<Discount> categoryDiscount = discountService.getActiveDiscountForCategory(categoryId);

            if (productDiscount.isPresent()) {
                price = price.subtract(productDiscount.get().getAmount());
                System.out.println("Applied product discount for product ID " + productId + ": " + productDiscount.get().getAmount());
            } else if (categoryDiscount.isPresent()) {
                price = price.subtract(categoryDiscount.get().getAmount());
                System.out.println("Applied category discount for category ID " + categoryId + ": " + categoryDiscount.get().getAmount());
            }

            if (price.compareTo(BigDecimal.ZERO) < 0) {
                price = BigDecimal.ZERO;
            }

            totalPrice = totalPrice.add(price);
        }

        return totalPrice;
    }
    public void cleanCard(Product product,ShoppingCart shoppingCart){
        product.setPrice(BigDecimal.ZERO);
        shoppingCart.getProducts().clear();



    }

}
