package jvm.lab6;

public class JvmLab6 {
    public static void main(String[] args) throws Exception {
//        new Parent(); // 主动使用，不会抛异常， 因为对 A 不是主动使用，运行时可以删掉A.class
//        Class.forName("jvm.lab6.Parent"); // 主动使用，不会抛异常， 因为对 A 不是主动使用A.class

        ClassLoader.getSystemClassLoader().loadClass("jvm.lab6.Parent"); // 只是 加载
    }
}

class A {

}


class Parent {
    public static String str = "hello";

//    public static A a; // 默认值 null, 不会抛异常，不是主动使用
    public static Object a = new A(); // java.lang.NoClassDefFoundError:

    static {
        System.out.println("parent static block");
        System.out.println(a);
    }
}

