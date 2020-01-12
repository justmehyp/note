package jvm.lab1;

/**
 * -XX:+TraceClassLoading 打印虚拟机加载的类
 *
 * -XX:+<option> 开启option选项
 * -XX:-<option> 关闭option选项
 *
 * -XX:<option>=<value> 将option选项的值设置为value
 */
public class JvmLab1 {
    public static void main(String[] args) {
        System.out.println(Child.str); // str 静态字段来自于 Parent, 实际 主动使用 的是 Parent，不会初始化 Child
    }
}

class Parent {
    public static String str = "parent";

    static {
        System.out.println("parent static block");
    }
}

class Child extends Parent {

    static {
        System.out.println("child static block");
    }
}