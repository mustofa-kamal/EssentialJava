package com.essential.dop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final Map<String, StockItem> inventoryMap = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();

    public OrderService() {
        // Constructor does not initialize sample data
    }

    public void initInventory() {
        // Initialize inventory with sample data
        inventoryMap.put("Laptop", new StockItem("Laptop", 10));
        inventoryMap.put("Phone", new StockItem("Phone", 20));
        inventoryMap.put("Mouse", new StockItem("Mouse", 50));
        inventoryMap.put("Keyboard", new StockItem("Keyboard", 15));
        inventoryMap.put("Monitor", new StockItem("Monitor", 8));
    }

    public Order placeOrder(String customerName, String productName, int quantity) {
        StockItem currentStockItem = inventoryMap.get(productName);
        if (currentStockItem == null) {
            throw new IllegalArgumentException("Product not available in inventory: " + productName);
        }

        StockItem newStockItem = currentStockItem.createWithReducedStock(quantity);
        inventoryMap.put(productName, newStockItem); // Replace old inventory with the new one

        Order order = new Order(customerName, productName, quantity);
        orders.add(order);

        return order;
    }

    public List<Order> getAllOrders() {
        return List.copyOf(orders); // Return an immutable copy of orders
    }
    public List<StockItem> getAllInventory() {
        return List.copyOf(inventoryMap.values()); // Return an immutable copy of inventory values
    }
    public StockItem getInventoryForProduct(String productName) {
        return inventoryMap.get(productName);
    }

}
