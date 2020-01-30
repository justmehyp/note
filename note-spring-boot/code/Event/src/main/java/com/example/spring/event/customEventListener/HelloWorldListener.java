package com.example.spring.event.customEventListener;

import com.example.spring.event.util.LogUtil;
import org.springframework.context.ApplicationListener;

public class HelloWorldListener implements ApplicationListener<HelloWorldEvent> {

    @Override
    public void onApplicationEvent(HelloWorldEvent event) {
        Object msg = event.getSource();
        LogUtil.log(String.format("[%s] catch event type [%s], source [%s] of type [%s]", this.getClass().getSimpleName(),
                event.getClass().getSimpleName(), msg, msg.getClass().getSimpleName()));
    }
}
