package com.essential.strategy.two;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentReport process(Payment payment) {
        if (payment instanceof CreditCardPayment cc) {
            return cc.amount() > 0
                    ? new PaymentReport("Credit Card", cc.amount(), "Processed for " + cc.cardHolderName())
                    : new PaymentReport("Credit Card", cc.amount(), "Failed: Invalid Amount");
        }
        throw new IllegalArgumentException("Invalid payment type");
    }
}