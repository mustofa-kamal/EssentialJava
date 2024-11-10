package com.essential.functional.introduction;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
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


    public static int getLength(String str){
        return str.length();
    }

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

        Myinterface<String> myinterfaceLamda1 =AnonymousMain::getLength;

        myinterfaceLamda1.getLength("ssfsff");

        Myinterface<String> myinterfaceLamda2 =String::length;

        myinterfaceLamda2.getLength("ssfsff");

        Consumer<String> consumer = (s)->System.out.println(s);

        consumer.accept("please print this");

        Supplier<Double> supplier = Math::random;

        supplier.get();

        Function<String, String> trim=String::trim;

       // trim.apply("thsi s ");

        Function<String, String > toLowerCase = String::toLowerCase;

        Function<String, String >  result = trim.andThen(toLowerCase);

        String sr = result.apply("  THIS IS A TEST  ");

        Predicate<Integer> predicate = (x)-> x>10;

        predicate.test(11);

        BinaryOperator<Integer> add = (x, y)->x+y;

        add.apply(4,4);

        BinaryOperator<Integer> com = BinaryOperator.minBy(Integer::compare);

        int res = com.apply(4,3);

        int u = 0;






    }
}