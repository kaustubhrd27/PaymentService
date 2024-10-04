package com.example.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    String createPaymentLink(Long orderId) throws RazorpayException;
}
