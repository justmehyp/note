package com.example.spring.event.customEventListener;

import com.example.spring.event.util.LogUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        LogUtil.log(String.format("[%s] catch event type [%s], source [%s] of type [%s]",
                this.getClass().getSimpleName(), event.getClass().getSimpleName(), source, source.getClass().getSimpleName()));
    }
}
