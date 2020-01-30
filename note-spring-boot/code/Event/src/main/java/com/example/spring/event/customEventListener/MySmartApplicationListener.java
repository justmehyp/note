package com.example.spring.event.customEventListener;

import com.example.spring.event.util.LogUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

public class MySmartApplicationListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return HelloWorldEvent.class == eventType;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return Integer.class == sourceType;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        LogUtil.log(String.format("[%s] catch event type [%s], source [%s] of type [%s]", this.getClass().getSimpleName(),
                event.getClass().getSimpleName(), source, source.getClass().getSimpleName()));
    }
}
