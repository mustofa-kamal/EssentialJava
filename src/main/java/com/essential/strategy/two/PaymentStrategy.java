package com.essential.strategy.two;

interface PaymentStrategy {
    PaymentReport process(Payment payment);
}
