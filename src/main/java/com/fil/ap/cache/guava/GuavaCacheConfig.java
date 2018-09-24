package com.fil.ap.cache.guava;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.google.common.cache.CacheBuilder;

@EnableCaching
@Configuration
public class GuavaCacheConfig {

    @Value("${guava.maximumsize}")
    private int maximumSize;
    
    @Value("${guava.expireafterwrite}")
    private int expireAfterWrite;

	@Bean
	public CacheBuilder<Object, Object> cacheBuilder() {

		if (maximumSize <= 0) {
			maximumSize = 1024;
		}
		
		if (expireAfterWrite <= 0) {
			expireAfterWrite = 1800;
		}
		
		return CacheBuilder.newBuilder().maximumSize(maximumSize).expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS);
	}
	
	@DependsOn({"cacheBuilder"})
    @Bean
    public CacheManager cacheManager(CacheBuilder<Object, Object> cacheBuilder) {
		
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(cacheBuilder);
        
        return cacheManager;
    }

}
