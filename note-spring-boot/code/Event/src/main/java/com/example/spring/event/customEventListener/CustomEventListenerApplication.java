package com.example.spring.event.customEventListener;

import com.example.spring.event.util.LogUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableAsync
public class CustomEventListenerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

//        ac.addApplicationListener(new MyContextRefreshedListener());
//        ac.addApplicationListener(new HelloWorldListener());
        ac.addApplicationListener(new MyApplicationListener());
        ac.register(CustomEventListenerApplication.class);
//        ac.addApplicationListener(new MySmartApplicationListener());
//        ac.register(CustomEventListenerApplication.class);

        ac.refresh();

//        ac.publishEvent(new HelloWorldEvent<>("Hello world."));
//        ac.publishEvent(new HelloWorldEvent<>(123));
//        ac.publishEvent(new HelloWorldEvent<>(123.456));

        ac.close();
    }

    @EventListener
    public void annotationApplicationListener(ApplicationEvent event) {
        Object source = event.getSource();
        LogUtil.log(String.format("[%s] catch event type [%s], source [%s] of type [%s]",
                "annotationApplicationListener", event.getClass().getSimpleName(), source, source.getClass().getSimpleName()));
    }

    @Async
    @EventListener
    public void annotationHelloWorldListener(HelloWorldEvent<Double> event) {
        // in fact, event will be caught only if type is HelloWorldEvent, source type is ignored.
        Object source = event.getSource();
        LogUtil.log(String.format("[%s - %s] catch event type [%s], source [%s] of type [%s]",
                Thread.currentThread().getName(), "annotationHelloWorldListener",
                event.getClass().getSimpleName(), source, source.getClass().getSimpleName()));
    }
}
