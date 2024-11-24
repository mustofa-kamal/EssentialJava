package com.essential.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VarMain {
    /*
    -- java var introduced in version 10
    -- var is only allowed for local variables. Not for instance or class-level variables
    -- Developers can focus more on logic than writing boilerplate type information.
     */
    public static void main(String[] args) {


        Map<String, Integer> map0 = new HashMap<>();// not Good

        //Obvious Types: Use var when the type is immediately clear from the context.
        var map = new HashMap<String, Integer>();//Good


        //For generic or nested types, var reduces verbosity.
        var numbers = new ArrayList<Integer>(); // Good



        var map1 = new HashMap<String, Integer>() {{
            put("key1", 1);
            put("key2", 2);
            put("key3", 3);
        }};


        for (Map.Entry<String, Integer> entry : map1.entrySet()) { // not good
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }

        //Local Variables in Loops. Readable then the previous one
        for (var entry : map1.entrySet()) { // good
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }


        //For Anonymous Objects: Use var when creating anonymous classes or lambda
        var runnable = new Runnable() {
            public void run() {
                System.out.println("runnable");
            }
        };
        runnable.run();

        //Ambiguous Types: Avoid var when the type is not obvious from the initializer.
        String data = getData(); // What type is `data`? It's unclear without looking at `getData`'s return type.

        //Primitive Types: Avoid using var for primitives where clarity is better with explicit types.
        int age = 20; // It's better to use `int age = 20;` for clarity.










    }

    private static String  getData() {
        return "some text";
    }
}
