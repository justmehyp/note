package com.example.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);

        new AbstractApplicationContext() {
            @Override
            protected void refreshBeanFactory() throws BeansException, IllegalStateException {

            }

            @Override
            public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
                return null;
            }

            @Override
            protected void closeBeanFactory() {

            }
        };
    }

}
