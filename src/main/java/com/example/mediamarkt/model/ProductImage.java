package com.example.mediamarkt.model;

import jakarta.persistence.*;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl; // URL изображения

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;



}
