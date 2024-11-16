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



















    }
}
