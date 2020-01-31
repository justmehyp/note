package jvm.memory.lab4;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class JvmMemoryLab4 {
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            Enhancer e = new Enhancer();
            e.setUseCache(false);
            e.setSuperclass(JvmMemoryLab4.class);
            e.setCallback((MethodInterceptor) (obj, method, arg, proxy) -> {
                return null;
            });
            e.create();

            System.out.println(i++);
        }
    }
}
