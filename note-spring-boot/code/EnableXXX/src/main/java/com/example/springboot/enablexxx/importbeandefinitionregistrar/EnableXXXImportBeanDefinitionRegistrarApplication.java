package com.example.springboot.enablexxx.importbeandefinitionregistrar;

import org.springframework.boot.SpringApplication;
import com.example.springboot.enablexxx.importbeandefinitionregistrar.EnableHelloOrWorld.HelloOrWorld;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@EnableHelloOrWorld(HelloOrWorld.HELLO)
//@EnableHelloOrWorld(HelloOrWorld.WORLD)
public class EnableXXXImportBeanDefinitionRegistrarApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnableXXXImportBeanDefinitionRegistrarApplication.class);
    }
}
