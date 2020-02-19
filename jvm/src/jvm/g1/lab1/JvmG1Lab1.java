package jvm.g1.lab1;


// -XX:+PrintGCDetails -XX:+UseG1GC -Xms10m -Xmx10m -Xloggc:gc.txt
public class JvmG1Lab1 {
    public static void main(String[] args) {

        byte[] bytes1 = new byte[200 * 1024];
        byte[] bytes2 = new byte[200 * 1024];
        byte[] bytes3 = new byte[200 * 1024];
        byte[] bytes4 = new byte[200 * 1024];
        byte[] bytes5 = new byte[200 * 1024];

        byte[] bytes6 = new byte[200 * 1024];
        byte[] bytes7 = new byte[200 * 1024];
        byte[] bytes8 = new byte[200 * 1024];
        byte[] bytes9 = new byte[200 * 1024];
        byte[] bytes10 = new byte[200 * 1024];


        byte[] bytes13 = new byte[200 * 1024];
        byte[] bytes14 = new byte[200 * 1024];
        byte[] bytes15 = new byte[200 * 1024];
        byte[] bytes16 = new byte[200 * 1024];
        byte[] bytes17 = new byte[200 * 1024];

        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();

        bytes1 = null;
        bytes2 = null;
        bytes3 = null;
        bytes4 = null;
        bytes5 = null;
        bytes6 = null;
        bytes7 = null;
        bytes8 = null;
        bytes9 = null;
        bytes10 = null;

        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
        oneM();
    }

    public static void oneM() {
        for (int i = 0; i < 1024; i++) {
            byte[] bytes = new byte[1024];
        }
    }
}
