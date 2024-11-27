package com.essential.dop;

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
