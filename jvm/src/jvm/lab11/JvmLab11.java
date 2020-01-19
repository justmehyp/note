package jvm.lab11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// -XX:+TraceClassUnloading
public class JvmLab11 {

    /**
     * JVM 自带的类加载器所加载的类是不能卸载的，因为JVM会一直引用这些类加载器，类加载器又引用着它所加载的类
     *
     * 类的实例也有对Class对象的引用
     *
     * 当类加载器不再被引用时，并且没有类的实例被引用时，Class 对象可以被卸载
     */
    public static void main(String[] args) throws Exception {

        MyClassLoader loader = new MyClassLoader(null); // parent: bootstrap classloader
        Class<?> aClass = loader.loadClass("jvm.lab1.JvmLab1");

        System.out.println(loader);
        System.out.println(aClass);

        loader = null;
        aClass = null;

        // [Unloading class jvm.lab1.JvmLab1 0x00000007c0061828]
//        System.gc(); // 也可以在 jvisualvm 中执行 gc

        Thread.sleep(100000);
    }
}

class MyClassLoader extends ClassLoader {

    protected MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("finding class:" + name);

        byte[] data = readClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] readClassData(String name) throws ClassNotFoundException {
        try {
            return Files.readAllBytes(Paths.get("/Users/hyp/code/note/jvm/target/classes/tmp", name.replaceAll("\\.", "/") + ".class"));
        } catch (IOException e) {
            throw new ClassNotFoundException("Read Class Data Exception", e);
        }
    }
}
