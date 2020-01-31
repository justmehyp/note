package jvm.memory.lab3;

public class JvmMemoryLab3 {

    public static void main(String[] args) {
        new Thread(() -> A.a(), "Thread-A").start();
        new Thread(() -> B.b(), "Thread-B").start();
    }
}

class A {
    public static synchronized void a() {
        try {
            Thread.sleep(1000);
            B.b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class B {
    public static synchronized void b() {
        try {
            Thread.sleep(1000);
            A.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
