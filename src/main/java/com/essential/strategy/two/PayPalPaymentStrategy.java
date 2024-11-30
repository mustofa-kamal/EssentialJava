package com.essential.strategy.two;

public class PayPalPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentReport process(Payment payment) {
        if (payment instanceof PayPalPayment pp) {
            return pp.email().contains("@") && pp.amount() > 0
                    ? new PaymentReport("PayPal", pp.amount(), "Processed for " + pp.email())
                    : new PaymentReport("PayPal", pp.amount(), "Failed: Invalid Email or Amount");
        }
        throw new IllegalArgumentException("Invalid payment type");
    }
}