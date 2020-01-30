package com.example.springboot.enablexxx.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {

    @Bean
    public ApplicationRunner hello() {
        return args -> System.out.println("hello - configuration");
    }
}
