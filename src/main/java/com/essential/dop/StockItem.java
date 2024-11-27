package com.essential.dop;

public record StockItem(String productName, int stock) {

    public StockItem createWithReducedStock(int quantity) {
        if (quantity > stock) {
            throw new IllegalArgumentException("Insufficient stock for product: " + productName);
        }
        return new StockItem(productName, stock - quantity); // Returns a new immutable instance
    }

    @Override
    public String toString() {
        return "Inventory{productName='" + productName + "', stock=" + stock + '}';
    }
}
