package com.example.springboot.enablexxx.importbeandefinitionregistrar;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(HelloWorldImportBeanDefinitionRegistrar.class)
public @interface EnableHelloOrWorld {
    HelloOrWorld value();

    enum HelloOrWorld {
        HELLO, WORLD;
    }
}
