package my.springframework.beans.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

/**
 * The root interface for accessing a Spring bean container.
 *
 * <p>This interface is implemented by objects that hold a number of bean definitions, each uniquely identified by a
 * Spring name.
 *
 * <p>Depending on bean definition, the factory will return either an independent instance of a contained object (the
 * prototype design pattern), or a single shared instance (a superior alternative to the singleton design pattern, in
 * which the instance is a singleton in the scope of the factory).
 */
public interface BeanFactory {

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     *
     * @param beanName the name of the bean to retrieve
     * @return an instance of the bean
     * @throws NoSuchBeanDefinitionException if there is no bean with the specified name
     */
    Object getBean(String beanName);

    /**
     * Return an instance, which may be shared or independent, of the specified object type.
     *
     * @param beanType the object type of the bean to retrieve
     * @return an instance of the bean matching the specified type
     * @throws NoSuchBeanDefinitionException if no bean of the given type was found
     * @throws NoUniqueBeanDefinitionException if more than one bean of the given type was found
     */
    <T> T getBean(Class<T> beanType);

    /**
     * Determine if this bean factory contains a bean definition with the given name?
     * @param beanName the name of the bean to query
     * @return true if a bean with the given name is present
     */
    boolean containsBean(String beanName);

    /**
     * Determine if the bean with given name is shared singleton. That is, will {@link #getBean(String)} always return
     * the same instance?
     * @param beanName the name of bean to query
     * @return true if the bean of give name is a singleton instance
     * @throws NoSuchBeanDefinitionException if there is no bean with given name
     */
    boolean isSingleton(String beanName);

    /**
     * Determine if the bean with given name is a prototype. That is, will {@link #getBean(String)} always return
     * independent instances?
     *
     * @param beanName the name of bean to query
     * @return true if the bean of give name is a independent instance
     * @throws NoSuchBeanDefinitionException if there is no bean with given name
     */
    boolean isPrototype(String beanName);
}
