package jvm.lab2;

public class JvmLab2 {

    public static void main(String[] args) {
        System.out.println(Child.str); // 主动使用 Child, Parent 是 Child 的父类，先初始化 Parent，再初始化 Child 本身
    }
}

class Parent {
    public static String str = "parent";

    static {
        System.out.println("parent static block");
    }
}

class Child extends Parent {
    public static String str = "child";

    static {
        System.out.println("child static block");
    }
}
