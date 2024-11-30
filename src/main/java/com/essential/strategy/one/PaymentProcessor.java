package com.essential.strategy.one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

sealed interface Payment permits CreditCardPayment, PayPalPayment, CryptoPayment, CryptoPayment1 {
}

record CreditCardPayment(String cardHolderName, double amount) implements Payment {
}

record PayPalPayment(String email, double amount) implements Payment {
}

record CryptoPayment(String walletAddress, double amount) implements Payment {
}

record CryptoPayment1(String walletAddress, double amount) implements Payment {
}



record PaymentReport(String type, double amount, String status) {
    boolean isSuccessful() {
        return !status.startsWith("Failed");
    }
}


public class PaymentProcessor {


    // Strategy map to process each payment type
    private static final Map<Class<? extends Payment>, Function<Payment, PaymentReport>> strategyMap = new HashMap<>();

    static {

        Function<Payment, PaymentReport> CreditCardPaymentReportFn = payment -> {
            CreditCardPayment cc = (CreditCardPayment) payment;
            return cc.amount() > 0
                    ? new PaymentReport("Credit Card", cc.amount(), "Processed for " + cc.cardHolderName())
                    : new PaymentReport("Credit Card", cc.amount(), "Failed: Invalid Amount");
        };

        Function<Payment, PaymentReport> PayPalPaymentReportFn = payment -> {
            PayPalPayment pp = (PayPalPayment) payment;
            return pp.email().contains("@") && pp.amount() > 0
                    ? new PaymentReport("PayPal", pp.amount(), "Processed for " + pp.email())
                    : new PaymentReport("PayPal", pp.amount(), "Failed: Invalid Email or Amount");
        };

        Function<Payment, PaymentReport> CryptoPaymentReportFn = payment -> {
            CryptoPayment cp = (CryptoPayment) payment;
            return !cp.walletAddress().isEmpty() && cp.amount() > 0
                    ? new PaymentReport("Crypto", cp.amount(), "Processed for wallet: " + cp.walletAddress())
                    : new PaymentReport("Crypto", cp.amount(), "Failed: Invalid Wallet or Amount");
        };

        Function<Payment, PaymentReport> CryptoPaymentReportFn1 = payment -> {
            CryptoPayment1 cp = (CryptoPayment1) payment;
            return !cp.walletAddress().isEmpty() && cp.amount() > 0
                    ? new PaymentReport("Crypto1", cp.amount(), "Processed for wallet: " + cp.walletAddress())
                    : new PaymentReport("Crypto1", cp.amount(), "Failed: Invalid Wallet or Amount");
        };

        strategyMap.put(CreditCardPayment.class, CreditCardPaymentReportFn);
        strategyMap.put(PayPalPayment.class, PayPalPaymentReportFn);
        strategyMap.put(CryptoPayment.class, CryptoPaymentReportFn);
        strategyMap.put(CryptoPayment1.class, CryptoPaymentReportFn1);
    }

    public static void main(String[] args) {


        // List of payments
        List<Payment> payments = List.of(
                new CreditCardPayment("John Doe", 150.00),
                new PayPalPayment("john.doe@example.com", 75.50),
                new CryptoPayment("1A2B3C4D5E", 0.0025),
                new CreditCardPayment("Jane Smith", -30.00), // Invalid payment
                new PayPalPayment("invalid-email", 100.00),   // Invalid email
                new CryptoPayment1("1A2B3C4D5E", 0.0025)
        );

        // Process payments and generate reports
        List<PaymentReport> reports = payments.stream()
                .map(payment -> processPayment(payment)) // Process each payment
                .toList(); // Collect reports

        // Display the reports and calculate the total processed amount
        System.out.println("\n--- Payment Report ---");
        double totalProcessed = reports.stream()
                .peek(report -> System.out.printf(
                        "Type: %-10s | Amount: %-8.2f | Status: %s%n",
                        report.type(), report.amount(), report.status()))
                .filter(PaymentReport::isSuccessful)
                .mapToDouble(PaymentReport::amount)
                .sum();

        System.out.printf("\nTotal Processed Amount: %.2f\n", totalProcessed);

    }

    private static PaymentReport processPayment(Payment payment) {
        // Delegate to the appropriate strategy based on the payment type


        Function<Payment, PaymentReport> strategy = strategyMap.get(payment.getClass());
        if (strategy == null) {
            strategy = p -> new PaymentReport("Unknown", 0, "Failed: Unsupported Payment Type");
        }
        return strategy.apply(payment);

    }
}
