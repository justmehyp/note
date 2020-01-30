package com.example.springboot.annotationattribute.a;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Transactional
@Service("transactionalService")
public @interface TransactionService {
    String name() default "";

    @AliasFor(annotation = Service.class)
    String value() default "txManager";
}
