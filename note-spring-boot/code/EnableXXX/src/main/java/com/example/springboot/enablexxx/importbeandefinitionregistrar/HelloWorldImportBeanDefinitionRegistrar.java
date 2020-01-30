package com.example.springboot.enablexxx.importbeandefinitionregistrar;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import com.example.springboot.enablexxx.importbeandefinitionregistrar.EnableHelloOrWorld.HelloOrWorld;


import java.util.Map;

public class HelloWorldImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableHelloOrWorld.class.getName());
        HelloOrWorld helloOrWorld = (HelloOrWorld) annotationAttributes.get("value");
        if (HelloOrWorld.HELLO.equals(helloOrWorld)) {
            register(registry, Hello.class);
        } else {
            register(registry, World.class);
        }
    }

    private void register(BeanDefinitionRegistry registry, Class registeringClass) {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(registeringClass).getBeanDefinition();
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
    }
}
