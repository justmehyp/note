package com.example.springboot.enablexxx.importbeandefinitionregistrar;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class World implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("world - importBeanDefinitionRegistrar");
    }
}
