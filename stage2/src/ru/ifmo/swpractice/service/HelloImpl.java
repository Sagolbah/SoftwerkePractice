package ru.ifmo.swpractice.service;

public class HelloImpl implements Hello {

    @Override
    public void sayHello() {
        System.out.println("Hello OSGi world!");
    }
}
