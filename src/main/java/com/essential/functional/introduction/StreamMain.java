package com.essential.functional.introduction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*

Immutable objects are inherently thread-safe. In concurrent use - make class immutability -
avoid synchronization issues.


An immutable class is a class whose instances cannot be modified after creation.

it means - its state (i.e., the values of its fields) remains constant
 throughout its lifetime.



 */


public class StreamMain {

    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R get(T t, U u, V v);
    }



    public static void main(String[] args) {


        // Usage:
        List<String> hobbies = new ArrayList<>();




        Person person = new Person("Alice", 30, Arrays.asList("Reading", "Cycling"));

        // Modifying the original list does not affect the person's hobbies
        hobbies.add("Swimming");
        System.out.println(person.getHobbies()); // Outputs: [Reading, Cycling]

        Supplier<Person> personSupplier = ()->new Person("Nip", 11, Arrays.asList("Chess", "Swimming"));

        Person person1 = personSupplier.get();

        TriFunction<String, Integer, List<String>,Person > triFunction = Person::new;

        Person p1 = triFunction.get("Nip1", 12, Arrays.asList("Chess2", "Swimming3"));

        int z =1;

















    }
}
