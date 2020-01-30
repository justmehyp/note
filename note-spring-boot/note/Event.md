# Spring Event Framework

## 如何监听 Spring 事件

### 实现接口 ApplicationListener
定义一个监听器 MyApplicationListener，实现接口 ApplicationListener，监听事件 ApplicationEvent：
```java
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        LogUtil.log(String.format("[%s] catch event type [%s], source [%s] of type [%s]",
                this.getClass().getSimpleName(), event.getClass().getSimpleName(), source, source.getClass().getSimpleName()));
    }
}
```

在写一个启动类，跑起来看看我们这个监听器能监听到什么事件：
```java
public class CustomEventListenerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        
        ac.addApplicationListener(new MyApplicationListener());
      
        ac.refresh();

        ac.stop();
    }
}
```

启动后，控制台输出如下：
```text
[17:30:03.682154] [MyApplicationListener] catch event type [ContextRefreshedEvent], source [org.springframework.context.annotation.AnnotationConfigApplicationContext@b7dd107, started on Sat Sep 28 17:30:03 CST 2019] of type [AnnotationConfigApplicationContext]
[17:30:03.694682] [MyApplicationListener] catch event type [ContextClosedEvent], source [org.springframework.context.annotation.AnnotationConfigApplicationContext@b7dd107, started on Sat Sep 28 17:30:03 CST 2019] of type [AnnotationConfigApplicationContext]
```

可以看到，我们的监听器 监听 到了 2 个事件，一个是 ContextRefreshedEvent，另一个是 ContextClosedEvent。
这 2 个事件，是由 ApplicationContext 生命周期 触发的。
事实上，由于我们监听的是ApplicationEvent，任何 Spring Event 都必须实现这个接口，因此 MyApplicationListener 可以监听任何 Spring 事件。
后面会加以验证。

### @EventListener
在 上一节 定义的启动类 CustomEventListenerApplication 中，新增一个方法:
```text
@EventListener
public void annotationApplicationListener(ApplicationEvent event) {
    Object source = event.getSource();
    LogUtil.log(String.format("[%s] catch event type [%s], source [%s] of type [%s]",
            "annotationApplicationListener", event.getClass().getSimpleName(), source, source.getClass().getSimpleName()));
}
```
然后在 main 方法中， `ac.refresh();` 语句之前插入一行 `ac.register(CustomEventListenerApplication.class);`，再次启动：
```text
[17:38:02.961878] [annotationApplicationListener] catch event type [ContextRefreshedEvent], source [org.springframework.context.annotation.AnnotationConfigApplicationContext@b7dd107, started on Sat Sep 28 17:38:02 CST 2019] of type [AnnotationConfigApplicationContext]
[17:38:02.962534] [MyApplicationListener] catch event type [ContextRefreshedEvent], source [org.springframework.context.annotation.AnnotationConfigApplicationContext@b7dd107, started on Sat Sep 28 17:38:02 CST 2019] of type [AnnotationConfigApplicationContext]
[17:38:02.976441] [annotationApplicationListener] catch event type [ContextClosedEvent], source [org.springframework.context.annotation.AnnotationConfigApplicationContext@b7dd107, started on Sat Sep 28 17:38:02 CST 2019] of type [AnnotationConfigApplicationContext]
[17:38:02.976705] [MyApplicationListener] catch event type [ContextClosedEvent], source [org.springframework.context.annotation.AnnotationConfigApplicationContext@b7dd107, started on Sat Sep 28 17:38:02 CST 2019] of type [AnnotationConfigApplicationContext]
```
可以看到 @EventListener 标注的 annotationApplicationListener 方法成功监听到事件。

## 有哪些内建的事件

## 监听自定义事件

## 事件是如何到达监听器的

## 替换默认的事件广播器

## ListenerCacheKey 和 ListenerRetriever

## Spring Boot 事件
Spring Boot 的事件父类是 SpringApplicationEvent，继承于 Spring 的 ApplicationEvent。
事件监听器来源于 spring.factories 加载的 ApplicationListener，以及 SpringApplication#addListeners 方法手动添加的监听器。 
Spring Boot 本身的生命周期事件由 SpringApplication 产生，在 SpringApplication.run(String...) 这个方法中。监听器的接口是 
SpringApplicationRunListener，事件回调函数包括：
- starting
- environmentPrepared
- contextPrepared
- contextLoaded
- started
- running
- failed

SpringApplicationRunListener 默认只有一个实现 EventPublishingRunListener，通过 spring.factories 加载，
这个类的角色类似于 ApplicationEventMulticaster，但其实是 ApplicationEventMulticaster 的封装类。
EventPublishingRunListener接收到事件后，交给 ApplicationEventMulticaster 广播事件。
在 contextLoaded 事件监听回调方法中，还会把当前 SpringApplication 的 ApplicationListener们 添加到 ConfigurableApplicationContext。
这样 Spring Boot 的事件监听器也可以同时监听到 Spring 事件。事实上，Spring Boot 只支持监听 框架已定义的SpringApplicationEvent 子类事件，
不支持自定义事件的发布和监听。自定义事件的发布和监听都将复用 Spring 框架的事件框架，并且需要在 contextLoaded 方法之后，
即 ApplicationPreparedEvent 事件发布之后。

如果想监听所有的 Spring Boot 生命周期事件，可以直接实现 SpringApplicationRunListener，并通过 spring.factories 工厂机制应用监听器。
也可以单独监听某一个事件，如 starting 方法，对应的事件是 ApplicationStartingEvent。其他回调方法与事件对应关系如下：
- starting              ApplicationStartingEvent
- environmentPrepared   ApplicationEnvironmentPreparedEvent
- contextPrepared       ApplicationContextInitializedEvent
- contextLoaded         ApplicationPreparedEvent
- started               ApplicationStartedEvent
- running               ApplicationReadyEvent
- failed                ApplicationFailedEvent
