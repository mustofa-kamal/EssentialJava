package com.essential.dop;

public record Order(String customerName, String productName, int quantity) {
    @Override
    public String toString() {
        return "Order{customerName='" + customerName + "', productName='" + productName + "', quantity=" + quantity + '}';
    }
}
