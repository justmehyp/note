package jvm.lab3;

/**
 * 常量，在编译阶段，会存入到调用这个常量的方法所在的类的常量池中。本质上，调用类并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 *
 * 这里值得是 str 存放到了 JvmLab3 的常量池中，之后 JvmLab3 和 Parent 就没有任何关系了
 * 设置，可以将编译后的 Parent.class文件删除
 */
public class JvmLab3 {
    public static void main(String[] args) {
        System.out.println(Parent.str);
        System.out.println(Parent.i);
        System.out.println(Parent.l);
        System.out.println(Parent.f);
        System.out.println(Parent.d);
    }
}

class Parent {
//    public static final int i = -1; // iconst_m1
//    public static final int i = 0; // iconst_0      0..5


    //    public static final int i = 100; // bipush
//    public static final int i = 128; // sipush

    public static final int i = 32768; // ldc
    public static final float f = 1.2f; // ldc
    public static final String str = "parent"; // ldc


    public static final long l = 2147483648L; // ldc2_w
    public static final double d = 1.2; // ldc2_w

    static {
        System.out.println("parent static block");
    }
}
