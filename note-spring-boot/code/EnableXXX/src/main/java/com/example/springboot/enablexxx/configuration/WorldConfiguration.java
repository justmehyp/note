package com.example.springboot.enablexxx.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorldConfiguration {

    @Bean
    public ApplicationRunner world() {
        return args -> System.out.println("world - configuration");
    }
}
