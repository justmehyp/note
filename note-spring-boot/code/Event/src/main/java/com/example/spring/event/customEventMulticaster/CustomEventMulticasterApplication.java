package com.example.spring.event.customEventMulticaster;

import com.example.spring.event.util.LogUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomEventMulticasterApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyEventMulticuster.class);
        LogUtil.log("Created");
        printState(ac);

        refresh(ac);

        stop(ac);

        start(ac);

        stop(ac);

        close(ac);
    }

    private static void close(AnnotationConfigApplicationContext ac) {
        LogUtil.log("Close ApplicationContext");
        ac.close();
        printState(ac);
    }

    private static void refresh(AnnotationConfigApplicationContext ac) {
        LogUtil.log("Refresh ApplicationContext");
        ac.refresh();
        printState(ac);
    }

    private static void start(AnnotationConfigApplicationContext ac) {
        LogUtil.log("Start ApplicationContext");
        ac.start();
        printState(ac);
    }

    private static void stop(AnnotationConfigApplicationContext ac) {
        LogUtil.log("Stop ApplicationContext");
        ac.stop();
        printState(ac);
    }

    private static void printState(AnnotationConfigApplicationContext ac) {
        LogUtil.log("isActive: " + ac.isActive());
        LogUtil.log("isRunning: " + ac.isRunning());
        System.out.println();
    }


}
