package com.essential.dop;


import java.util.*;
import java.util.stream.Collectors;

/*
 -- Records are immutable by default, enforcing a core principle of DOP
 -- Using records eliminates the need for explicit fields, constructors, toString, equals, and hashCode methods
 -- Order objects are immutable by default.
 -- The original data is never altered. Instead, transformations result in new, immutable objects

 */

public class DopMain {

    // Immutable Inventory using Record
    public record Inventory(String productName, int stock) {
        public Inventory createWithReducedStock(int quantity) {
            if (quantity > stock) {
                throw new IllegalArgumentException("Insufficient stock for product: " + productName);
            }
            return new Inventory(productName, stock - quantity); // Returns a new immutable instance
        }

        @Override
        public String toString() {
            return "Inventory{productName='" + productName + "', stock=" + stock + '}';
        }
    }

    // Immutable Order using Record
    public record Order(String customerName, String productName, int quantity) {
        @Override
        public String toString() {
            return "Order{customerName='" + customerName + "', productName='" + productName + "', quantity=" + quantity + '}';
        }
    }

    // Service class to handle orders and inventory
    public static class OrderService {
        private final Map<String, Inventory> inventoryMap = new HashMap<>();
        private final List<Order> orders = new ArrayList<>();

        public OrderService() {
            // Initialize inventory with sample data
            inventoryMap.put("Laptop", new Inventory("Laptop", 10));
            inventoryMap.put("Phone", new Inventory("Phone", 20));
            inventoryMap.put("Mouse", new Inventory("Mouse", 50));
            inventoryMap.put("Keyboard", new Inventory("Keyboard", 15));
            inventoryMap.put("Monitor", new Inventory("Monitor", 8));
        }

        public Order placeOrder(String customerName, String productName, int quantity) {
            Inventory currentInventory = inventoryMap.get(productName);
            if (currentInventory == null) {
                throw new IllegalArgumentException("Product not available in inventory: " + productName);
            }

            // Use the renamed method to create a new inventory instance
            Inventory updatedInventory = currentInventory.createWithReducedStock(quantity);
            inventoryMap.put(productName, updatedInventory); // Replace old inventory with the updated one

            // Create and store the new order
            Order order = new Order(customerName, productName, quantity);
            orders.add(order);

            return order;
        }

        public List<Order> getAllOrders() {
            return List.copyOf(orders); // Return an immutable copy of orders
        }

        public List<Inventory> getAllInventory() {
            return List.copyOf(inventoryMap.values()); // Return an immutable copy of inventory values
        }
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        // Place orders
        try {
            System.out.println("Placing order 1...");
            Order order1 = orderService.placeOrder("Alice", "Laptop", 2);
            System.out.println("Order placed: " + order1);

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
}