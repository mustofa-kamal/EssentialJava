package com.essential.switchcode;

import java.util.List;
import java.util.function.Function;

sealed interface Payment permits CreditCardPayment, PayPalPayment, CryptoPayment {
}

record CreditCardPayment(String cardHolderName, double amount) implements Payment {
}

record PayPalPayment(String email, double amount) implements Payment {
}

record CryptoPayment(String walletAddress, double amount) implements Payment {
}

record PaymentReport(String type, double amount, String status) {
}

public class PaymentProcessor {

    // Unified strategy using switch with pattern matching
    private static final Function<Payment, PaymentReport> paymentProcessor = payment ->
            switch (payment) {
                case CreditCardPayment cc ->
                        new PaymentReport("Credit Card", cc.amount(), "Processed for " + cc.cardHolderName());
                case PayPalPayment pp -> new PaymentReport("PayPal", pp.amount(), "Processed for " + pp.email());
                case CryptoPayment cp ->
                        new PaymentReport("Crypto", cp.amount(), "Processed for wallet: " + cp.walletAddress());
            };

    public static void main(String[] args) {
        // List of payments to process
        List<Payment> payments = List.of(
                new CreditCardPayment("John Doe", 150.00),
                new PayPalPayment("john.doe@example.com", 75.50),
                new CryptoPayment("1A2B3C4D5E", 0.0025),
                new CreditCardPayment("Jane Smith", 200.00) // Another credit card payment
        );

        // Process payments and generate reports
        List<PaymentReport> reports = payments.stream()
                .map(paymentProcessor) // Process each payment
                .toList(); // Collect reports

        // Display reports
        System.out.println("\n--- Payment Reports ---");
        reports.forEach(report -> System.out.printf(
                "Type: %-15s | Amount: %-8.2f | Status: %s%n",
                report.type(), report.amount(), report.status()
        ));
    }
}
