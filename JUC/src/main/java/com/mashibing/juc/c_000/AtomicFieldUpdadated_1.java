package com.mashibing.juc.c_000;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicFieldUpdadated_1 {

    public static void main(String[] args) {
        Person person = new Person(20,"cc");
        AtomicIntegerFieldUpdater<Person> updater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");

        updater.compareAndSet(person,20,21);
        System.out.println(person.age);
    }
}

class Person{
    volatile  int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
