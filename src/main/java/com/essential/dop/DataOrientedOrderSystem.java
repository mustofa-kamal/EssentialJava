package com.essential.dop;


/*
--Data-Oriented Programming (DOP) in Java has gained popularity as a paradigm
-- that emphasizeson immutable data structures, transformations, and stateless functions
 -- Records are immutable by default, enforcing a core principle of DOP
 -- Using records eliminates the need for explicit fields, constructors, toString, equals, and hashCode methods

 */


public class DataOrientedOrderSystem {

    public static void main(String[] args) {
        OrderService orderService = new OrderService(); // Create OrderService instance
        orderService.initInventory(); // Initialize inventory with sample data

        // Place orders
        try {

            System.out.println("\nInitialize Inventory:");
            orderService.getAllInventory().forEach(System.out::println);


            System.out.println("Placing order 1...");
            Order order1 = orderService.placeOrder("Alice", "Laptop", 1);
            System.out.println("Order placed: " + order1);
            printInventoryForProduct(orderService, "Laptop");


            System.out.println("Placing order 2...");
            Order order2 = orderService.placeOrder("Bob", "Phone", 1);
            System.out.println("Order placed: " + order2);
            printInventoryForProduct(orderService, "Phone");

            System.out.println("Placing order 3...");
            Order order3 = orderService.placeOrder("Alice", "Mouse", 5);
            System.out.println("Order placed: " + order3);
            printInventoryForProduct(orderService, "Mouse");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display all orders
        System.out.println("\nAll Orders:");
        orderService.getAllOrders().forEach(System.out::println);

        // Display current inventory
        System.out.println("\nCurrent Inventory:");
        orderService.getAllInventory().forEach(System.out::println);
    }

    private static void printInventoryForProduct(OrderService orderService, String productName) {
        StockItem stockItem = orderService.getInventoryForProduct(productName);
        System.out.println("Updated Inventory for " + productName + ": " + stockItem);
    }
}