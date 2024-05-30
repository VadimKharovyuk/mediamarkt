package com.example.mediamarkt;

import com.example.mediamarkt.model.Category;
import com.example.mediamarkt.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MediamarktApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediamarktApplication.class, args);



    }

}
