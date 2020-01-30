package com.example.springboot.enablexxx.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes("com.example.springboot.enablexxx.importselector.EnableHelloOrWorld");
        return new String[]{((Class) annotationAttributes.get("value")).getName()};
    }
}
