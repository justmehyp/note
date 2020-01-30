package com.example.spring.event.customEventListener;

import com.example.spring.event.util.LogUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LogUtil.log(String.format("[%s] catch event type [%s]", this.getClass().getSimpleName(), event.getClass().getSimpleName()));
    }
}
