package com.example.mediamarkt.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int stockQuantity;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    @ManyToMany(mappedBy = "products")
    private List<ShoppingCart> shoppingCarts;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
