package com.essential.strategy.two;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

sealed interface Payment permits CreditCardPayment, PayPalPayment, CryptoPayment {}

record CreditCardPayment(String cardHolderName, double amount) implements Payment {}

record PayPalPayment(String email, double amount) implements Payment {}

record CryptoPayment(String walletAddress, double amount) implements Payment {}

record PaymentReport(String type, double amount, String status) {}

public class PaymentProcessor {

    // Strategy map
    private static final Map<Class<? extends Payment>, PaymentStrategy> strategyMap = new HashMap<>();

    static {
        // Initialize the strategy map with encapsulated  strategy classes
        strategyMap.put(CreditCardPayment.class, new CreditCardPaymentStrategy());
        strategyMap.put(PayPalPayment.class, new PayPalPaymentStrategy());
        strategyMap.put(CryptoPayment.class, new CryptoPaymentStrategy());
    }

    public static void main(String[] args) {
        // List of payments to process
        List<Payment> payments = List.of(
                new CreditCardPayment("John Doe", 150.00),
                new PayPalPayment("john.doe@example.com", 75.50),
                new CryptoPayment("1A2B3C4D5E", 0.0025),
                new CreditCardPayment("Jane Smith", -30.00) // Invalid payment
        );

        // Process payments and generate reports
        List<PaymentReport> reports = payments.stream()
                .map(payment -> processPayment(payment)) // Process each payment
                .toList(); // Collect reports

        // Display reports
        System.out.println("\n--- Payment Reports ---");
        reports.forEach(report -> System.out.printf(
                "Type: %-15s | Amount: %-8.2f | Status: %s%n",
                report.type(), report.amount(), report.status()
        ));
    }

    private static PaymentReport processPayment(Payment payment) {
        // Retrieve the strategy for the given payment type
        PaymentStrategy strategy = strategyMap.get(payment.getClass());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + payment.getClass());
        }
        return strategy.process(payment);
    }
}
