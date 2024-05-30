package com.example.mediamarkt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import redis.clients.jedis.Jedis;

import java.util.Set;

@SpringBootApplication
@EnableCaching
public class MediamarktApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediamarktApplication.class, args);



    }

    }

