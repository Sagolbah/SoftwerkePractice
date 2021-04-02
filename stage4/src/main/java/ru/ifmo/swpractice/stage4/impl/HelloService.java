package ru.ifmo.swpractice.stage4.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import ru.ifmo.swpractice.stage4.Hello;

@Component(property = {
        "osgi.command.scope=practice",
        "osgi.command.function=hello"
})
public class HelloService implements Hello {
    @Override
    public void hello(String name) {
        System.out.println("Hello, " + name);
    }

    public HelloService() {
        System.out.println("Hello Command service started");
    }

    @Deactivate
    public void deactivate() {
        System.out.println("Service closed");
    }

}