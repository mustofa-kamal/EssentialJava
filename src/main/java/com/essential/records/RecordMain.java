package com.essential.records;

import java.util.ArrayList;
import java.util.List;

record Product(String name, double price, int quantity, List items) {
    public Product {
        items = List.copyOf(items);
    }
}

public class RecordMain {
    public static void main(String[] args) {

        List<String> ss = new ArrayList<>();
        ss.add("asd");
        ss.add("asd");
        Product product = new Product("pa", 12.3, 2, ss);

        ss.add("111");



       int x =1;

    }
}
