package com.example.mediamarkt.repository;

import com.example.mediamarkt.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository  extends JpaRepository<Subscription,Long> {
}
