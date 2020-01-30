package com.example.springboot.enablexxx.importselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@EnableHelloOrWorld(World.class)
public class EnableXXXImportSelectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnableXXXImportSelectorApplication.class);
    }
}
