package com.example.spring.event.customEventListener;

import org.springframework.context.ApplicationEvent;

public class HelloWorldEvent<S> extends ApplicationEvent {

    public HelloWorldEvent(S source) {
        super(source);
    }
}
