package com.essential.functional.introduction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
  f(x) = 2x;
  x=2
  f(x)= 2*2
  =4
 */




@FunctionalInterface
interface Myinterface<T> {
    int getLength(T t);
}

public class AnonymousMain {

    static void someFn(Myinterface<String> myinterface, String s){
        myinterface.getLength(s);

    }


    public static void main(String[] args) {

        Myinterface<String> myinterface = new Myinterface<String>() {
            @Override
            public int getLength(String s) {
                return s.length();
            }
        };

        myinterface.getLength("this is test");

        Myinterface<String> myinterfaceLamda = (s)-> s.length();

        myinterfaceLamda.getLength("This is test");

        someFn(myinterfaceLamda, "this is a testing");







    }
}