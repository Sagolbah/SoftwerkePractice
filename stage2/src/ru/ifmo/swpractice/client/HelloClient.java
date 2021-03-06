package ru.ifmo.swpractice.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import ru.ifmo.swpractice.service.Hello;

public class HelloClient implements BundleActivator {

    @Override
    public void start(BundleContext context) throws InvalidSyntaxException {
        // I really don't like it. Ungenerified types, strange casts.
        // However, it's in the book.
        // TODO: Ask about it on Meeting 2.
        ServiceReference ref = context.getServiceReference(Hello.class.getName());
        ((Hello) context.getService(ref)).sayHello();
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("Client: Stopped");
    }
}
