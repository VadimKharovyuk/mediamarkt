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

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            // Получение всех ключей по шаблону "getProductById" и установка TTL на уровне Redis
            for (String key : jedis.keys("getProductById*")) {
                jedis.expire(key, 60); // Установка TTL на 5 минут (300 секунд)
            }
        }




    }

    }

