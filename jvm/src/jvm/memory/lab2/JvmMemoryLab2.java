package jvm.memory.lab2;

public class JvmMemoryLab2 {
    private int depth;

    public void test() throws InterruptedException {
        depth++;
        Thread.sleep(1000);
        test();
    }

    public static void main(String[] args) {
        JvmMemoryLab2 jvmMemoryLab2 = new JvmMemoryLab2();

        try {
            jvmMemoryLab2.test();
        } catch (Throwable e) {
            System.out.println(jvmMemoryLab2.depth);
            e.printStackTrace();
        }
    }
}
