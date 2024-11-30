package com.essential.misc.two;

abstract class Animal {
    public abstract String getSound();
}

class Dog extends Animal {
    @Override
    public String getSound() {
        return "Bark";
    }
}

class Cat extends Animal {
    @Override
    public String getSound() {
        return "Meow";
    }
}

public class PolymorphismExample {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Or new Cat()
        System.out.println(myAnimal.getSound()); // Outputs: Bark
    }
}
