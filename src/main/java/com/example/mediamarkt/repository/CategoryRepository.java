package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
