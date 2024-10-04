package com.example.paymentservice.Services;

import com.example.paymentservice.paymentgateways.PaymentGateway;
import com.example.paymentservice.paymentgateways.RazorPaymentGateway;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    private Logger logger = LoggerFactory.getLogger(RazorPaymentGateway.class);
    PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }



    public String createPaymentLink(Long orderId) throws RazorpayException {
        // here we should call Razorpay/Stripe PG to generate the payment link
        // Validate the order ID
        if (orderId == null) {
            logger.error("Order ID cannot be null.");
            throw new IllegalArgumentException("Order ID must not be null.");
        }
        return paymentGateway.createPaymentLink(orderId);
    }
}
