package jvm.lab8;


// 删除Parent.class 会报错，虽然不会初始化 Parent， 但是会加载，
// 因为 Child 本身是要完成 加载 -> 连接 -> 初始化 的，所以需要加载 Parent
public class JvmLab8 {

    public static void main(String[] args) {
        Child.sayHello();

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}

interface Parent {
}

interface Child extends Parent {

    public static void sayHello() {
        System.out.println("hello");
    }
}