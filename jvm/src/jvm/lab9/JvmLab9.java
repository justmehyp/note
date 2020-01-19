package jvm.lab9;


public class JvmLab9 {
    public static void main(String[] args) throws Exception {
        System.out.println(A.str); // 编译时常量，编译时已放入到当前方法所在类的常量池中，和 A 已经没有关系了
        System.out.println("----------");
        System.out.println(Class.forName("jvm.lab9.A").getField("str").get(null)); // 主动使用, 访问 A.str
    }
}

interface A {
    public static final String str = "hello";
}