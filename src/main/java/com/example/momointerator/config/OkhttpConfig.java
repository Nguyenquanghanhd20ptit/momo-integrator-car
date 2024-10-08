package com.example.momointerator.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkhttpConfig {
    @Autowired
    private ResourceProperties resourceProperties;
    @Bean
    public OkHttpClient okHttpClientInit(){
        return new OkHttpClient.Builder()
                .build();
    }
}
