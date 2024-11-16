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
                new Person("Alice", 30, "Female", Arrays.asList("Reading", "Hiking")),
                new Person("Bob", 25, "Male", Arrays.asList("Cycling", "Gaming")),
                new Person("Charlie", 35,"Female", Arrays.asList("Cooking", "Traveling")),
                new Person("Diana", 28, "Male", Arrays.asList("Running", "Swimming")),
                new Person("Eve", 40, "Female" ,Arrays.asList("Gardening", "Reading")),
                new Person("Frank", 22, "Male", Arrays.asList("Gaming", "Music")),
                new Person("Grace", 27, "Female", Arrays.asList("Hiking", "Photography"))
        );

        //basicStreamOperations(persons);
        //intermediateStreamOperations(persons);
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

                .filter(person -> person.getAge() >= 30)

                .map(Person::getName)

                .collect(Collectors.toList());

        System.out.println("\nNames of all persons:");
        names.forEach(System.out::println);


       // The operations are applied sequentially as per the code
        // Stream API manages the iteration and calls the provided lambda expressions
        //each line create





    }



        public static void intermediateStreamOperations(List<Person> persons) {


          Comparator<Person> comparator= (o1,o2)-> o1.getAge()-o2.getAge();


            List<Person> sortedByAge = persons.stream()
                    .sorted(Comparator.comparingInt(Person::getAge))
                    .collect(Collectors.toList());

            System.out.println("\nPersons sorted by age:");
            sortedByAge.forEach(person -> System.out.println(person.getName() + " - " + person.getAge()));







            // Getting the top 3 youngest persons
            List<Person> top3Youngest = persons.stream()
                    .sorted(Comparator.comparingInt(Person::getAge))
                    .limit(3)
                    .collect(Collectors.toList());

            System.out.println("\nTop 3 youngest persons:");
            top3Youngest.forEach(person -> System.out.println(person.getName() + " - " + person.getAge()));


            // Finding unique hobbies
            List<String> uniqueHobbies = persons.stream()
                    .flatMap(person -> person.getHobbies().stream())
                    .distinct()
                    .collect(Collectors.toList());

            System.out.println("\nUnique hobbies:");
            uniqueHobbies.forEach(System.out::println);



        }




    public static void advancedStreamOperations(List<Person> persons) {
        /*
        .collect(
                Collectors.groupingBy(
                        // Classifier function
                        // Downstream collector
                )
        )
    */

        persons.stream().collect(Collectors.groupingBy(person->person.getGender(),
                Collectors.mapping(Person::getName, Collectors.toList())));


       /* System.out.println("\nPersons grouped by gender group:");
        re.forEach((x, y) -> {
            System.out.println(x + ":");
            y.forEach(k -> System.out.println(" - " +k ));
        });*/



       Map<String, List<Person>> groupByAge = persons.stream()
                .collect(Collectors.groupingBy(person -> {
                    int age = person.getAge();
                    if (age >= 20 && age < 30) return "20s";
                    else if (age >= 30 && age < 40) return "30s";
                    else if (age >= 40 && age < 50) return "40s";
                    else return "Other";
                }));

        System.out.println("\nPersons grouped by age group:");
        groupByAge.forEach((ageGroup, groupPersons) -> {
            System.out.println(ageGroup + ":");
            groupPersons.forEach(person -> System.out.println(" - " + person.getName()));
        });

         Map<String, Map<String, List<Person>>> groupByAgeByGender = persons.stream()
                .collect(Collectors.groupingBy(person -> {
                    int age = person.getAge();
                    if (age >= 20 && age < 30) return "20s";
                    else if (age >= 30 && age < 40) return "30s";
                    else if (age >= 40 && age < 50) return "40s";
                    else return "Other";
                }, Collectors.groupingBy(x->x.getGender())));

        groupByAgeByGender.forEach((ageGroup, genderMap) -> {
            System.out.println("Age Group: " + ageGroup);
            genderMap.forEach((gender, people) -> {
                System.out.println("  Gender: " + gender);
                for (Person person : people) {
                    System.out.println("    - Name: " + person.getName() + ", Age: " + person.getAge());
                }
            });
        });




        Map<String, Long> countByAgeGroup = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                person -> { int age = person.getAge();
                                    if (age >= 20 && age < 30) return "20s";
                                    else if (age >= 30 && age < 40) return "30s";
                                    else if (age >= 40 && age < 50) return "40s";
                                    else return "Other"; },
                                Collectors.counting()
                        )
                );

                System.out.println("\nPersons grouped by age group and count:");
                countByAgeGroup.forEach((x, y) -> {
                    System.out.println(x + ":"+ y);

                });







        Map<String, List<String>> groupByAgeName = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                person -> { int age = person.getAge();
                                    if (age >= 20 && age < 30) return "20s";
                                    else if (age >= 30 && age < 40) return "30s";
                                    else if (age >= 40 && age < 50) return "40s";
                                    else return "Other"; },
                                Collectors.mapping(Person::getName, Collectors.toList())
                        )
                );



        System.out.println("\nPersons grouped by age group and count:");
        groupByAgeName.forEach((x, y) -> {
            System.out.println(x + ":"+ y);

        });








    }



    }

