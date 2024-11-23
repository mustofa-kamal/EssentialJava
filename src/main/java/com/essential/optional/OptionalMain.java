package com.essential.optional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/*

--Optional is designed to represent the presence or absence of a single value, not for use inside collections.
--A List already represents a collection of elements that may include null values.
--Adding Optional on top of this creates redundancy and complexity.

 */


class User {
    private int id;
    private String name;
    private Address address;

    public User(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}

class Address {
    private String streetName;

    public Address(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetName() {
        return streetName;
    }
}






public class OptionalMain {

    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();

        Optional<String> optionalNullable = Optional.ofNullable(null);
        Optional<String> first = List.of("aa","bb").stream().findFirst();
        Optional<String> result = methodReturningOptional();
        Optional<String> optionalValue = Optional.of("value");
        Optional<Integer> length = optionalValue.map(String::length);

        // Avoids using optional in fields
        // Avoid using Optional as method parameter.
        // Avoid using Optional in collection

        // Get method not recommanded










       // 8 use case

        Optional<User> userOptional = findUserById(1);
        userOptional.ifPresentOrElse(
                user -> System.out.println("User found: " + user.getName()),
                () -> System.out.println("User not found")
        );


        // Use Case 2: Wrapping Null-Safe Chains
        //Eliminates repetitive null checks.
        User user = new User(1, "John Doe", new Address("123 Main St"));

        //The use of Optional ensures that I don’t encounter a NullPointerException
        // while navigating through nested objects like User → Address → streetName.

        String streetName = Optional.ofNullable(user)
                .flatMap(u -> Optional.ofNullable(u.getAddress()))// If Address is null, directly execute  orElse
                .map(a->a.getStreetName())
                .orElse("Unknown Street");

        System.out.println("Street Name: " + streetName);

        // Use Case 3: Transforming Data with map
        Optional<String> userName = Optional.of("John Doe");
        String upperName = userName.map(String::toUpperCase).orElse("Default Name");// same idea optional is empty, directly execute orEles
        System.out.println("Upper Case Name: " + upperName);

        // Use Case 4: Chaining Optional Logic with flatMap
        //Optional.ofNullable(u.getAddress()) => returns optional so I need flatmap instead of map
        Optional<String> optionalStreetName = Optional.of(user)
                .flatMap(u -> Optional.ofNullable(u.getAddress()))
                .map(Address::getStreetName);
        System.out.println("FlatMapped Street Name: " + optionalStreetName.orElse("No Address"));

        // Use Case 5: Providing Default Values with orElse and orElseGet
        Optional<String> emptyOptional = Optional.empty();
        String defaultName = emptyOptional.orElse("Default Name");
        String computedDefaultName = emptyOptional.orElseGet(() -> "Computed Default Name");
        System.out.println("Default Name: " + defaultName);
        System.out.println("Computed Default Name: " + computedDefaultName);

        // Use Case 6: Replacing null in Configuration Values
        String configValue = getConfig("APP_ENV").orElse("Development");
        System.out.println("Config Value: " + configValue);

        // Use Case 7: Stream Integration
        Optional<String> firstNameStartingWithJ = List.of("Alice", "Bob", "John")
                .stream()
                .filter(name -> name.startsWith("J"))
                .findFirst();
        System.out.println("First Name Starting With J: " + firstNameStartingWithJ.orElse("No Match"));

        // Use Case 8: Avoiding Exceptions
        Optional<User> userById = findUserById(2);

        userById.ifPresentOrElse(
                user2 -> System.out.println("User found: " + user2.getName()),
                () -> System.out.println("User with ID 2 not found")
        );
    }

    // Use Case 1 Helper
    public static Optional<User> findUserById(int id) {
        if (id == 1) {
            return Optional.of(new User(1, "John Doe", new Address("123 Main St")));
        }
        return Optional.empty();
    }




    // Use Case 2 Helper
    public static Optional<String> getConfig(String key) {
        // Simulating environment variable fetching
        if ("APP_ENV".equals(key)) {
            return Optional.of("Production");
        }
        return Optional.empty();
    }


    public static Optional methodReturningOptional(){
        return Optional.empty();
    }
}



















