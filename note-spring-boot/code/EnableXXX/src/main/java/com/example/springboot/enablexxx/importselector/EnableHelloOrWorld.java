package com.example.springboot.enablexxx.importselector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloOrWorld {
    Class value();
}
