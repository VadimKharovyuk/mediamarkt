package com.example.mediamarkt.model;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;



    public ShoppingCart getShoppingCart(HttpServletRequest request) {
        // Получаем текущую сессию
        HttpSession session = request.getSession(false);

        // Проверяем, существует ли сессия и атрибут с корзиной пользователя
        if (session != null && session.getAttribute("shoppingCart") != null) {
            // Преобразуем атрибут сессии в объект ShoppingCart
            return (ShoppingCart) session.getAttribute("shoppingCart");
        }

        // Если сессия не существует или корзина пользователя не найдена в сессии, возвращаем null
        return null;
    }

}

