package jvm.memory.lab1;

import java.util.ArrayList;
import java.util.List;

public class JvmMemoryLab1 {

    public static void main(String[] args) {
        List list = new ArrayList();
        while (true) {
            list.add(new Object());
            System.gc();
        }
    }
}
