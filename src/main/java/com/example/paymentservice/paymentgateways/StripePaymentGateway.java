package com.example.paymentservice.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    @Override
    public String createPaymentLink(Long orderId) {
        return "";
    }
}
