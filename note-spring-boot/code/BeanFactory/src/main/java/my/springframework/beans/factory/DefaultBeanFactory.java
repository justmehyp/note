package my.springframework.beans.factory;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory {

    private Map<Class<?>, String> singletonTypes = new HashMap<>();

    private Map<String, Object> singletons = new HashMap<>();
    private Map<String, Class<?>> prototypes = new HashMap<>();

    public void registerSingleton(String beanName, Class<?> beanType) {
        singletonTypes.put(beanType, beanName);
        singletons.put(beanName, beanType);
    }

    public void registerProtoType(Class<?> beanType) {
        String beanName = buildBeanName(beanType);
        prototypes.put(beanName, beanType);
    }

    private String buildBeanName(Class<?> beanType) {
        return beanType.getSimpleName();
    }

    private Object newInstance(Class<?> beanClass) {
        try {
            Constructor<?> constructor = beanClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Could not create instance for type '" + beanClass + "'", e);
        }
    }

    @Override
    public Object getBean(String beanName) {
        Object bean = singletons.get(beanName);
        if (bean == null) {
            Class<?> beanClass = prototypes.get(beanName);
            if(beanClass != null) {
                bean = newInstance(beanClass);
            }
        }
        if (bean == null) {
            throw new NoSuchBeanDefinitionException(beanName);
        }
        return bean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> beanType) {
        String beanName = singletonTypes.get(beanType);
        if (beanName != null) {
            return (T) singletons.get(beanName);
        } else if (prototypes.containsValue(beanType)) {
            Class<?> beanClass = prototypes.get(buildBeanName(beanType));
            return (T) newInstance(beanClass);
        } else {
            throw new NoSuchBeanDefinitionException(beanType);
        }
    }

    @Override
    public boolean containsBean(String beanName) {
        return singletons.containsKey(beanName) || prototypes.containsKey(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        if (singletons.containsKey(beanName)) {
            return true;
        } else if (prototypes.containsKey(beanName)) {
            return false;
        } else {
            throw new NoSuchBeanDefinitionException(beanName);
        }
    }

    @Override
    public boolean isPrototype(String beanName) {
        if (prototypes.containsKey(beanName)) {
            return true;
        } else if (singletons.containsKey(beanName)) {
            return false;
        } else {
            throw new NoSuchBeanDefinitionException(beanName);
        }
    }
}
