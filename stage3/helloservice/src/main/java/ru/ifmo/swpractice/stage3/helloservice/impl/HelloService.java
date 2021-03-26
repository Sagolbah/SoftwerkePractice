package ru.ifmo.swpractice.stage3.helloservice.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import ru.ifmo.swpractice.stage3.helloservice.Hello;

// Using OSGi annotations, since Apache are deprecated after 6.0.0

@Component(service = Hello.class)
public class HelloService implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello OSGi world!");
    }

    @Activate
    public void activate() {
        System.out.println("Service started, calling sayHello()");
        sayHello();
    }

    @Deactivate
    public void deactivate() {
        System.out.println("Service closed");
    }

}