package com.essential.dop;


/*
 -- Records are immutable by default, enforcing a core principle of DOP
 -- Using records eliminates the need for explicit fields, constructors, toString, equals, and hashCode methods
 -- Order objects are immutable by default.
 -- The original data is never altered. Instead, transformations result in new, immutable objects

 */


public class DataOrientedOrderSystem {

    public static void main(String[] args) {
        OrderService orderService = new OrderService(); // Create OrderService instance
        orderService.initInventory(); // Initialize inventory with sample data

        // Place orders
        try {
            System.out.println("Placing order 1...");
            Order order1 = orderService.placeOrder("Alice", "Laptop", 2);
            System.out.println("Order placed: " + order1);
            printInventoryForProduct(orderService, "Laptop");


            System.out.println("Placing order 2...");
            Order order2 = orderService.placeOrder("Bob", "Phone", 1);
            System.out.println("Order placed: " + order2);

            System.out.println("Placing order 3...");
            Order order3 = orderService.placeOrder("Alice", "Mouse", 5);
            System.out.println("Order placed: " + order3);

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
        Inventory inventory = orderService.getInventoryForProduct(productName);
        System.out.println("Updated Inventory for " + productName + ": " + inventory);
    }
}