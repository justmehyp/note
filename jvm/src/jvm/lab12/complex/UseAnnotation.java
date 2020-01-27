package jvm.lab12.complex;

import jvm.lab12.simple.GenderAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @see java.lang.annotation.Annotation
 * @see sun.misc.ProxyGenerator
 * @see sun.reflect.annotation.AnnotationParser
 * @see sun.reflect.annotation.AnnotationInvocationHandler
 * @see sun.reflect.annotation.AnnotationInvocationHandler#memberValues
 */

//@GenderAnnotation
@GenderAnnotation(abc = "hello")
public class UseAnnotation {

    @GenderAnnotation
    public static void main(String[] args) throws Exception {

        // 注解只在首次获取时才动态生成
        Annotation annotation = UseAnnotation.class.getAnnotations()[0];

        // 每使用一个注解，都会生成一个动态代理类；多次使用同一个注解，使用的是同一个动态代理类；因为他们实现的是同一个接口，ProxyGenerator返回同一个代理类
        // class com.sun.proxy.$Proxy1
        System.out.println(annotation.getClass()); // 第一次使用
        System.out.println(UseAnnotation.class.getDeclaredMethod("main", String[].class).getAnnotations()[0].getClass());  // 第二次使用
        System.out.println("--------------");

        System.out.println(Arrays.toString(Class.forName("com.sun.proxy.$Proxy0").getInterfaces())); // 原来是 @Retention
        System.out.println("--------------");

        System.out.println(annotation.getClass().getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("--------------");

        System.out.println(annotation.annotationType()); // interface jvm.lab12.simple.GenderAnnotation
        System.out.println(Arrays.toString(annotation.getClass().getInterfaces())); // [interface jvm.lab12.simple.GenderAnnotation]
        System.out.println("--------------");

        Field h = annotation.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        System.out.println(h.get(annotation)); // sun.reflect.annotation.AnnotationInvocationHandler@49476842
        System.out.println("--------------");

        GenderAnnotation genderAnnotation = ((GenderAnnotation) annotation);
        genderAnnotation.abc();
    }
}

// public final class $Proxy1 extends Proxy implements GenderAnnotation {
//    private static Method m1;
//    private static Method m2;
//    private static Method m3;
//    private static Method m0;
//
//    public $Proxy1(InvocationHandler var1) throws  {
//        super(var1);
//    }
//
//    public final boolean equals(Object var1) throws  {
//        try {
//            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
//        } catch (RuntimeException | Error var3) {
//            throw var3;
//        } catch (Throwable var4) {
//            throw new UndeclaredThrowableException(var4);
//        }
//    }
//
//    public final String toString() throws  {
//        try {
//            return (String)super.h.invoke(this, m2, (Object[])null);
//        } catch (RuntimeException | Error var2) {
//            throw var2;
//        } catch (Throwable var3) {
//            throw new UndeclaredThrowableException(var3);
//        }
//    }
//
//    public final Class annotationType() throws  {
//        try {
//            return (Class)super.h.invoke(this, m3, (Object[])null);
//        } catch (RuntimeException | Error var2) {
//            throw var2;
//        } catch (Throwable var3) {
//            throw new UndeclaredThrowableException(var3);
//        }
//    }
//
//    public final int hashCode() throws  {
//        try {
//            return (Integer)super.h.invoke(this, m0, (Object[])null);
//        } catch (RuntimeException | Error var2) {
//            throw var2;
//        } catch (Throwable var3) {
//            throw new UndeclaredThrowableException(var3);
//        }
//    }
//
//    static {                                                              // Annotation 接口中定义的方法，都实现了
//        try {
//            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
//            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
//            m2 = Class.forName("java.lang.Object").getMethod("toString");
//            m3 = Class.forName("jvm.lab12.simple.GenderAnnotation").getMethod("annotationType");
//        } catch (NoSuchMethodException var2) {
//            throw new NoSuchMethodError(var2.getMessage());
//        } catch (ClassNotFoundException var3) {
//            throw new NoClassDefFoundError(var3.getMessage());
//        }
//    }
//}
