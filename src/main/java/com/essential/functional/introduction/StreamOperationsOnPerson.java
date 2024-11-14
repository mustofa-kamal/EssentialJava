package com.essential.functional.introduction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*

 - A Stream is a sequence of elements
 - allow sequential and parallel operations.
  - work with lambda expression
  - filtering, mapping, and reducing, without iteration - declarative
 - evaluated lazily
 - Streams produce result without changing the original data.
 */

public class StreamOperationsOnPerson {

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Alice", 30, Arrays.asList("Reading", "Hiking")),
                new Person("Bob", 25, Arrays.asList("Cycling", "Gaming")),
                new Person("Charlie", 35, Arrays.asList("Cooking", "Traveling")),
                new Person("Diana", 28, Arrays.asList("Running", "Swimming")),
                new Person("Eve", 40, Arrays.asList("Gardening", "Reading")),
                new Person("Frank", 22, Arrays.asList("Gaming", "Music")),
                new Person("Grace", 27, Arrays.asList("Hiking", "Photography"))
        );

        basicStreamOperations(persons);
        intermediateStreamOperations(persons);
        advancedStreamOperations(persons);
    }

    public static void basicStreamOperations(List<Person> persons) {

        // Filtering persons aged 30 or older

        Predicate<Person> predicate = x->x.getAge()>=30;

        List<Person> filteredPersons = persons.stream()
                .filter(person -> person.getAge() >= 30)
                .collect(Collectors.toList());


        System.out.println("Persons aged 30 or older:");
        filteredPersons.forEach(person -> System.out.println(person.getName()));

        // Mapping persons to their names
        List<String> names = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("\nNames of all persons:");
        names.forEach(System.out::println);







    }

    public static void intermediateStreamOperations(List<Person> persons) {

    }

    public static void advancedStreamOperations(List<Person> persons) {

    }
}

