package my.springframework.beans.factory;


import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

public class BeanFactoryTest {

    public static DefaultBeanFactory newBeanFactory() {
        return new DefaultBeanFactory();
    }

    public static class TestGetBeanByBeanName {

        @Test
        public void getSingleton() {
            BeanFactory beanFactory = prepareDataForGetSingleton();
            Object singleton1 = beanFactory.getBean("singleton");
            Object singleton2 = beanFactory.getBean("singleton");
            if (singleton1 != singleton2) {
                throw new RuntimeException("Except singleton bean, but prototype beans returned.");
            }
        }

        private BeanFactory prepareDataForGetSingleton() {
            DefaultBeanFactory beanFactory = newBeanFactory();
            beanFactory.registerSingleton("singleton", Object.class);
            return beanFactory;
        }

        @Test
        public void getBeanThrowsNoSuchBeanDefinitionException() {
            BeanFactory beanFactory = new DefaultBeanFactory();
            try {
                beanFactory.getBean("xxx");
            } catch (NoSuchBeanDefinitionException e) {
                return;
            }
            throw new RuntimeException("Should throw NoSuchBeanDefinitionException, But not.");
        }
    }

    public static class TestGetBeanByBeanType {

        @Test
        public void getBeanSuccess() {
            BeanFactory beanFactory = createBeanFactoryForGetBeanSuccess();
            Object prototype1 = beanFactory.getBean("Object");
            Object prototype2 = beanFactory.getBean("Object");
            if (prototype1 == prototype2) {
                throw new RuntimeException("Except prototype bean, but singleton bean returned.");
            }
        }

        private BeanFactory createBeanFactoryForGetBeanSuccess() {
            DefaultBeanFactory beanFactory = newBeanFactory();
            beanFactory.registerProtoType(Object.class);
            return beanFactory;
        }

        @Test
        public void getBeanThrowsNoSuchBeanDefinitionException() {
            BeanFactory beanFactory = newBeanFactory();
            try {
                beanFactory.getBean("xxx");
            } catch (NoSuchBeanDefinitionException e) {
                return;
            }
            throw new RuntimeException("Should throw NoSuchBeanDefinitionException, but not.");
        }

        @Test
        public void getBeanThrowsNoUniqueBeanDefinitionException() {
            // no need up to now
        }
    }

    public static class TestContainsBean {

        @Test
        public void containsBean() {
            BeanFactory beanFactory = buildBeanFactoryForContainsBean();

            if (!beanFactory.containsBean("singleton")) {
                throw new RuntimeException("Should contain singleton.");
            }

            if (!beanFactory.containsBean("Object")) {
                throw new RuntimeException("Should contain prototype.");
            }
        }

        private BeanFactory buildBeanFactoryForContainsBean() {
            DefaultBeanFactory beanFactory = newBeanFactory();
            beanFactory.registerSingleton("singleton", Object.class);
            beanFactory.registerProtoType(Object.class);
            return beanFactory;
        }

        @Test
        public void notContainsBean() {
            BeanFactory beanFactory = newBeanFactory();

            if (beanFactory.containsBean("singleton")) {
                throw new RuntimeException("Should contain singleton.");
            }

            if (beanFactory.containsBean("Object")) {
                throw new RuntimeException("Should contain prototype.");
            }
        }
    }

    public static class TestIsPrototype {

        @Test
        public void isPrototype() {
            BeanFactory beanFactory = buildBeanFactoryForIsPrototype();
            boolean isPrototype = beanFactory.isPrototype("Object");
            if (!isPrototype) {
                throw new RuntimeException("Should be prototype.");
            }
        }

        private BeanFactory buildBeanFactoryForIsPrototype() {
            DefaultBeanFactory beanFactory = newBeanFactory();
            beanFactory.registerProtoType(Object.class);
            return beanFactory;
        }

        @Test
        public void throwsNoSuchBeanDefinitionException() {
            BeanFactory beanFactory = newBeanFactory();
            try {
                beanFactory.isPrototype("xxx");
            } catch (NoSuchBeanDefinitionException e) {
                return;
            }
            throw new RuntimeException("Should throw NoSuchBeanDefinitionException.");
        }
    }

    public static class TestIsSingleton {

        @Test
        public void isSingleton() {
            BeanFactory beanFactory = buildBeanFactoryForIsSingleton();
            boolean isSingleton = beanFactory.isSingleton("singleton");
            if (!isSingleton) {
                throw new RuntimeException("Should be singleton.");
            }
        }

        private BeanFactory buildBeanFactoryForIsSingleton() {
            DefaultBeanFactory beanFactory = newBeanFactory();
            beanFactory.registerSingleton("singleton", Object.class);
            return beanFactory;
        }

        @Test
        public void throwsNoSuchBeanDefinitionException() {
            BeanFactory beanFactory = newBeanFactory();
            try {
                beanFactory.isSingleton("xxx");
            } catch (NoSuchBeanDefinitionException e) {
                return;
            }
            throw new RuntimeException("Should throw NoSuchBeanDefinitionException.");
        }
    }
}
