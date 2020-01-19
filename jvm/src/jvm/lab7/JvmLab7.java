package jvm.lab7;


public class JvmLab7 {
    public static void main(String[] args) {
        System.out.println(Child1.str1);
        System.out.println(Child2.str2);
    }
}

class A {
}


// 主动使用类，会加载实现的接口，如果接口的Parent.class不存在，会抛异常
// 但不会初始化实现的接口，把A.class删除，不会报错

interface Parent1 {
    public static final A a = new A();
}
class Child1 implements Parent1 {
    public static String str1 = "hello";
}



// 会初始化父类，如果 A.class 删除，会报错

class Parent2 {
    public static final A a = new A();
}
class Child2 extends Parent2 {
    public static String str2 = "world";
}
