package com.example.mediamarkt.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Геттеры и сеттеры
}

