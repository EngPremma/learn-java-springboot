package com.example.learnjavaspringboot;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

    @Bean // get injected into the Spring Container using dependency injection
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();

        manager.setAllowNullValues(false); // Do not cache null value
        manager.setCacheNames(List.of("productsCache"));

        return manager;
    }
}
