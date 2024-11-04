package com.essential.generics;

import java.util.ArrayList;
import java.util.List;

public class ExtendMain {

    static class Animal {
        public String speak() {
            return "Animal";
        }
    }

    // Subclass of Animal
    static class Dog extends Animal {
        @Override
        public String speak() {
            return "Dog";
        }
    }

    // Subclass of Dog
    static class Puppy extends Dog {
        @Override
        public String speak() {
            return "Puppy";
        }


    }

    // Object -> Animal -> Dog -> Puppy

    /* Use ? extends T:  (I can read from it).
       Use ? super T:  (I can write to it).
    */

    // Method to add a Dog or subclass of Dog to a list of Dog or superclass of Dog
    public static void add(List<? super Dog> list) {
        list.add(new Dog());
        list.add(new Puppy());

    }

    // Method to retrieve and print the first element from a list of Animal or its subclass
    public static void retrieveWithWildCard(List<? extends Animal> list) {
        System.out.println(list.get(0).speak());
    }

    // Method to retrieve an element from a list with a specific type parameter
    public static <T extends Animal> T retrieveWithParam(List<T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        // Create a list of animals
        List<Animal> animals = new ArrayList<>();

        // Add a Dog to the list of animals
        add(animals);

        // Casting Example
        System.out.println("Using Casting:");
        Dog d = (Dog) animals.get(0);  // Casting required. But Casting may cause ClassCastException at runtime
                                        // if the actual type isn’t what I expect
        System.out.println(d.speak());  // Output: Dog



        // Using Wildcard with `retrieveWithWildCard()`
        System.out.println("\nUsing Wildcard with `retrieveWithWildCard()`:");
        retrieveWithWildCard(animals);  // No casting required, works with any list of Animal or subclass


        // Using Generics with Type Parameter
        System.out.println("\nUsing Generics with Type Parameter:");
        Animal a = retrieveWithParam(animals);  // No casting required
        System.out.println(a.speak());  // Output: Dog


    }
}
