package com.example.springboot.annotationattribute.a.a;

import com.example.springboot.annotationattribute.a.bean.TransactionServiceBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;


@Configuration
@ComponentScan(basePackageClasses = TransactionServiceBean.class)
@EnableTransactionManagement
public class AA {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(AA.class);
        ac.getBeansOfType(TransactionServiceBean.class).forEach(
                (beanName, bean) -> {
                    System.err.println("beanName: " + beanName + ", bean: " + bean);
                    bean.save();
                }
        );
        ac.close();
        //20:49:29.120 [main] INFO org.springframework.beans.factory.support.DefaultListableBeanFactory - Overriding bean definition for bean 'txManager' with a different definition: replacing [Generic bean: class [com.example.springboot.annotationattribute.a.bean.TransactionServiceBean]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in file [/Users/hyp/code/note/note-spring-boot/code/annotation-attribute/target/classes/com/example/springboot/annotationattribute/a/bean/TransactionServiceBean.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=AA; factoryMethodName=txManager; initMethodName=null; destroyMethodName=(inferred); defined in com.example.springboot.annotationattribute.a.a.AA]

    }

    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("txManager commit");
            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("txManager rollback");
            }
        };
    }

    @Bean("txManager2")
    public PlatformTransactionManager txManager2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("txManager2 commit");
            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("txManager2 rollback");
            }
        };
    }
}





