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

    // Method to add a specific item (Dog or Puppy) to a list of Dog or superclass of Dog
    public static void addWithWildCard(List<? super Dog> list, Dog item) {
        list.add(item);
    }

    // Method to retrieve and print the first element from a list of Animal or its subclass
    public static void retrieveWithWildCard(List<? extends Animal> list) {
        System.out.println(list.get(0).speak());
    }
    // Object -> Animal -> Dog -> Puppy

    // Method to retrieve an element from a list with a specific type parameter
    public static <T extends Animal> T retrieveTypeParameter(List<T> list) {
        return list.get(0);
    }

    // Method with type parameter to add an item to a list
    public static <T extends Dog> void addWithTypeParameter (List<T> list, T item) {
        list.add(item);
    }

    public static void main(String[] args) {
        // Create a list of animals
        List<Animal> animals = new ArrayList<>();


        // Add specific items (Dog or Puppy) to the list of animals using addWithSuper
        addWithWildCard(animals, new Dog());
        addWithWildCard(animals, new Puppy());

        // Casting Example
        System.out.println("Using Casting:");
        Dog d = (Dog)animals.get(0);  // Casting required. But Casting may cause ClassCastException at runtime
        // if the actual type isn’t what I expect
        System.out.println(d.speak());  // Output: Dog






        System.out.println("\nUsing Wildcard with `retrieveWithWildCard()`:");
        retrieveWithWildCard(animals);  // No casting required, works with any list of Animal or subclass

        // Using Generics with Type Parameter
        System.out.println("\nUsing Generics with Type Parameter:");
        Animal a = retrieveTypeParameter(animals);  // No casting required
        System.out.println(a.speak());  // Output: Dog

        // Create a list of dogs
        List<Dog> dogs = new ArrayList<>();
        addWithTypeParameter(dogs, new Dog());
        addWithTypeParameter(dogs, new Puppy());

        System.out.println("\nItems in dogs list:");
        for (Dog dog : dogs) {
            System.out.println(dog.speak());  // Output: Dog, Puppy
        }


        // Object -> Animal -> Dog -> Puppy

        List<? extends Dog> x = new ArrayList<>();

       // x.add(new Dog());

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Dog());
        animalList.add(new Puppy());

        List<? super Dog> y = new ArrayList<>();

        y.add(new Dog());

        y.add(new Puppy());





    }
}



/*

Summary:

? extends T:  (I can read from it).

? super T:  (I can write to it).


List<Dog>: I know exact type, so I can add type Dog or any subtype of Dog.

List<? extends Dog>: The exact type is not known. ? => any type which could be Dog, Puppy in this case.
  adding were allowed by the compiler.

List<? super Dog>: You know that the list can hold at least Dog or any superclass of Dog. Therefore, adding Dog
 is allowed by the compiler


conclusion:
adding elements to a list needs exact type to prevents runtime type errors.












 */
