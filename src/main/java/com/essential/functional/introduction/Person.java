package com.essential.functional.introduction;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


//Final Class
//Private Final Fields:
//No Setter Methods:
//Proper Initialization - constructor
//Defensive Copies - arrays or other mutable classes, make defensive copy

public final class Person {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // Make a defensive copy to prevent external modifications
        this.hobbies = List.copyOf(hobbies);
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getHobbies() { return hobbies; }







}


