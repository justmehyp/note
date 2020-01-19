package jvm.lab10;

public class JvmLab10 {
    public static void main(String[] args) {
//        Array.newInstance();

        // 数组class对象调用getClassLoader(),返回的是element type 的ClassLoader
        System.out.println(String[].class.getClassLoader());
        System.out.println(JvmLab10[].class.getClassLoader());


        System.out.println(Integer.TYPE.getClassLoader()); // 原生类型没有类加载器
    }
}
