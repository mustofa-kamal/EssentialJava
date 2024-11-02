package com.essential.generics;

import java.util.ArrayList;
import java.util.List;




public class SuperMain {

    static class Animal {
        @Override
        public String toString() {
            return "Animal";
        }
    }

    // Subclass of Animal
    static class Dog extends Animal {
        @Override
        public String toString() {
            return "Dog";
        }
    }

    // Subclass of Dog
    static class Puppy extends Dog {
        @Override
        public String toString() {
            return "Puppy";
        }
    }

    //object-> Animal -> Dog -> Puppy






    public static void main(String[] args) {



        // Example 1
        //strict list
        List<Dog> a = new ArrayList<>();
        a.add(new Dog());
        a.add(new Puppy());

        Object retrievedObj1 = a.get(0);
        if (retrievedObj1 instanceof Dog) {
            Dog f = (Dog) retrievedObj1;

        }

        //flexible list

        List<? super Dog> aDogs = a;
        aDogs.add(new Dog());
        aDogs.add(new Puppy());

        List<Animal> b = new ArrayList<>();
        List<? super Dog> bDogs = b;
        bDogs.add(new Dog());
        bDogs.add(new Puppy());

        List<Object> c = new ArrayList<>();
        List<? super Dog> cDogs = c;
        cDogs.add(new Dog());
        cDogs.add(new Puppy());

        Object retrievedObj2 = c.get(0);
        if (retrievedObj2 instanceof Dog) {
            Dog g = (Dog) retrievedObj2;
        }















    }

    }












