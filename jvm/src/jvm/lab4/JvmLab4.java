package jvm.lab4;

import java.util.UUID;

/**
 * 运行时常量，不会放入引用这个常量的方法所在类的常量池，用到的时候会初始化定义常量的类
 */
public class JvmLab4 {
    public static void main(String[] args) {
        System.out.println(Parent.str);
    }
}

class Parent {
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("parent static block");
    }
}
