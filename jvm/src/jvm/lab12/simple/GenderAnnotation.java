package jvm.lab12.simple;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 还是一个接口，没有实现任何方法
@Retention(RetentionPolicy.RUNTIME)
public @interface GenderAnnotation { // public interface jvm.lab12.simple.GenderAnnotation extends java.lang.annotation.Annotation

    /**
     * public abstract java.lang.String abc();
     *     AnnotationDefault:
     *       default_value: s#7
     *         "123"
     */
    String abc() default "123";
}
