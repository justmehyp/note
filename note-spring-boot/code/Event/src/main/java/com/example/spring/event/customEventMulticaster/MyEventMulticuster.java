package com.example.spring.event.customEventMulticaster;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component("applicationEventMulticaster")
public class MyEventMulticuster extends AbstractApplicationEventMulticaster {

    @Override
    public void multicastEvent(ApplicationEvent event) {
        multicastEvent(event, null);
    }

    @Override
    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
        System.err.println(String.format("[%s] >>> event [%s],  ResolvableType: [%s]", LocalTime.now(), event.getClass().getSimpleName(), eventType));
    }
}
