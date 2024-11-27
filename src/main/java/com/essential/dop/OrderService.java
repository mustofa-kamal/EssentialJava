package com.essential.dop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public class OrderService {
    private final Map<String, Inventory> inventoryMap = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();

    public OrderService() {
        // Constructor does not initialize sample data
    }

    public void initInventory() {
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

        Inventory updatedInventory = currentInventory.createWithReducedStock(quantity);
        inventoryMap.put(productName, updatedInventory); // Replace old inventory with the updated one

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
