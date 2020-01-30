package com.example.spring.event.util;

import java.time.LocalTime;

public class LogUtil {

    public static void log(Object msg) {
        System.out.println(String.format("[%s] %s", LocalTime.now(), msg));
    }
}
