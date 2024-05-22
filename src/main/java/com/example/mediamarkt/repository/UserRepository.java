package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
