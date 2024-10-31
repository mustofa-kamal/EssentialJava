package com.essential.enums;

/*
valueOf(String name): Returns the enum constant of the specified name.
name(): Returns the name of the enum constant as a String.
ordinal(): Returns the ordinal position of the constant in the enum declaration.
compareTo(): Compares the ordinal values of two enum constants.
toString(): By default, returns the name of the constant (can be overridden).
getDeclaringClass(): Returns the Class object corresponding to the enum type.
*/

/*
- Java calls the enum constructor automatically during class load time

*/

/*
public static final Day Monday = new Day("Monday", 0);
public static final Day Tuesday = new Day("Tuesday", 1);
 */

/*
- Defining enum creates new type of static final class
-- in this example, I created a new type called Day and MONDAY,TUESDAY are sinleton instance of type Day, so that
we can assign as Day d = D.MONDAY [ D.MONDAY works becasue enum Day is like static class]

-- Optionally, we can pass argument, like "Start of the week". In that cases, we also need to declare
private final String description;

-- name and ordinal are set by the compiler form Enum class, becasue Day extends from Enum, when we declare enum Day

-- values() generated by compiler and added automatically so that we can use them in out code.

-- Enums are inherently serializable in Java



Enum constants are implicitly public static final.

TrafficSignal is a type

public class Colors {
    public static final String RED = "Red";
    public static final String GREEN = "Green";
    public static final String BLUE = "Blue";
}

String red = Colors.RED

Similarly

TrafficSignal ts = TrafficSignal.RED


 */


import java.util.concurrent.ThreadLocalRandom;

enum TrafficSignal {

    RED("We must stop") {
        @Override
        public String action() {
            return "Stop";
        }
    },
    GREEN("We must move") {
        @Override
        public String action() {
            return "Go";
        }
    },
    YELLOW("We must get ready to stop") {
        @Override
        public String action() {
            return "Caution";
        }
    };


    // Field to store the description
    private final String description;

    // Constructor for the enum
    private TrafficSignal(String description) {
        this.description = description;
    }


    // custom method
    // this point to current enum instance
    boolean isStop(){
        return this == RED;
    }

    // custom method
    public static TrafficSignal getRandom() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }


    // Abstract method that each constant must implement


    //values()

    abstract String action();

}



public class EnumMain {


    public static void main( String[] args )
    {

        TrafficSignal ts = TrafficSignal.YELLOW;

        for (TrafficSignal signal : TrafficSignal.values()) {
            System.out.println(signal + ": " + signal.action());
        }

        for(TrafficSignal di: TrafficSignal.values()){
            System.out.println(di.name());
        }



        TrafficSignal red = TrafficSignal.RED;

        System.out.println(red.isStop()); // Output: true

        TrafficSignal green = TrafficSignal.GREEN;

        System.out.println(green.isStop()); // Output: false

        TrafficSignal a =  TrafficSignal.getRandom();
        switch (a){
            case RED:
                System.out.println("red");
                break;
            case GREEN:
                System.out.println("GREEN");
                break;
            case YELLOW:
                System.out.println("YELLOw");

        }
    }
}
