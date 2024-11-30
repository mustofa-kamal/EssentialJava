package com.essential.misc.one;

import java.util.HashMap;
import java.util.Map;

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

public class ClassExample {
    public static void main(String[] args) {
        Map<Class<?>, String> soundMap = new HashMap<>();
        soundMap.put(Dog.class, "Bark");
        soundMap.put(Cat.class, "Meow");

        Animal myAnimal = new Dog();
        String sound = soundMap.get(myAnimal.getClass()); // At runtime, it determines to use Dog class though Animal myAnimal
        System.out.println(sound); // Outputs: Bark
        //getClass Retrieves the exact runtime class of an object.

    }
}
