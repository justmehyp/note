package com.example.springboot.enablexxx.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import({HelloConfiguration.class, WorldConfiguration.class})
public @interface EnableHelloWorld {
}
