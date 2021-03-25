package ru.ifmo.swpractice.stage3.helloclient;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import ru.ifmo.swpractice.stage3.helloservice.Hello;

@Component
public class HelloClient {
    @Reference
    private Hello hello;

    @Activate
    public void activate() {
        System.out.println("Client started, calling sayHello()");
        hello.sayHello();
    }

    @Deactivate
    public void deactivate() {
        System.out.println("Client closed");
    }

}