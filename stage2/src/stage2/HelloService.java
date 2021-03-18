package stage2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloService implements BundleActivator {
    @Override
    public void start(BundleContext context) {
        Hello impl = new HelloImpl();
        context.registerService(Hello.class.getName(), impl, null);
        impl.sayHello();
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("Service: Stopped");
    }
}
