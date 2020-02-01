package jvm.gc.lab1;

public class JvmGCLab1 {

    public static void main(String[] args) {
        int m = 1024 * 1024;

//        // 没有 GC
//        byte[] bytes1 = new byte[2 * m];
//        byte[] bytes2 = new byte[2 * m];
//        byte[] bytes3 = new byte[2 * m];
//        System.out.println("hello world");

//        // GC
//        byte[] bytes1 = new byte[2 * m];
//        byte[] bytes2 = new byte[2 * m];
//        byte[] bytes3 = new byte[3 * m];
//        System.out.println(bytes1);
//        System.out.println(bytes2);
//        System.out.println(bytes3);
//        System.out.println("hello world");

        // GC & Full GC
        byte[] bytes1 = new byte[2 * m];
        byte[] bytes2 = new byte[2 * m];
        byte[] bytes3 = new byte[2 * m];
        byte[] bytes4 = new byte[2 * m];
        System.out.println("hello world");

    }
}
