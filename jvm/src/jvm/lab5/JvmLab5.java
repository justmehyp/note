package jvm.lab5;

/**
 * 创建类的数组，不是对类的主动使用，不会初始化对应的类
 */
public class JvmLab5 {
    public static void main(String[] args) {
//        Parent parent = new Parent();// 主动使用 Parent

        Parent[] parents = new Parent[1]; // 不是主动使用 Parent，不会初始化 Parent
        System.out.println(parents.getClass()); // class [Ljvm.lab5.Parent;   JVM运行时动态生成的
        System.out.println(parents.getClass().getSuperclass()); // 数组的父类是 java.lang.Object

        System.out.println(parents.getClass().getComponentType()); // class jvm.lab5.Parent   说明这个class加载了，只是没有初始化


        int[] ints = new int[2];
        System.out.println(ints.getClass());

        boolean[] booleans = new boolean[3];
        System.out.println(booleans.getClass());

        char[] chars = new char[1];
        System.out.println(chars.getClass());

        byte[] bytes = new byte[1];
        System.out.println(bytes.getClass());

        short[] shorts = new short[1];
        System.out.println(shorts.getClass());

        int[] ints1 = new int[1];
        System.out.println(ints.getClass());

        long[] longs = new long[1];
        System.out.println(longs.getClass());

        float[] floats = new float[1];
        System.out.println(floats.getClass());

        double[] doubles = new double[1];
        System.out.println(doubles.getClass());

    }
}

class Parent {
    static {
        System.out.println("parent static block");
    }
}
