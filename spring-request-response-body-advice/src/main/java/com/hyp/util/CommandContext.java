package com.hyp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandContext {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new ConcurrentHashMap<>();
        }
    };


    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static void setReqSeq(String reqSeq) {
        THREAD_LOCAL.get().put("reqSeq", reqSeq);
    }

    public static String getReqSeq() {
        return (String) THREAD_LOCAL.get().get("reqSeq");
    }
}
