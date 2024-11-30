package com.essential.strategy.two;

public class CryptoPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentReport process(Payment payment) {
        if (payment instanceof CryptoPayment cp) {
            return !cp.walletAddress().isEmpty() && cp.amount() > 0
                    ? new PaymentReport("Crypto", cp.amount(), "Processed for wallet: " + cp.walletAddress())
                    : new PaymentReport("Crypto", cp.amount(), "Failed: Invalid Wallet or Amount");
        }
        throw new IllegalArgumentException("Invalid payment type");
    }
}