package com.example.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
// This annotation marks the class as a Spring-managed component,
// allowing it to be automatically detected during classpath scanning and registered as a Spring bean.

@Primary
public class RazorPaymentGateway implements PaymentGateway {
    private Logger logger = LoggerFactory.getLogger(RazorPaymentGateway.class);
    private static final String CURRENCY = "INR";
    private static final int AMOUNT_IN_PAISA = 1000; // 10.00 in INR

    private RazorpayClient razorpay;

    public RazorPaymentGateway(RazorpayClient razorpay) {
        this.razorpay = razorpay;
    }

    @Override
    public String createPaymentLink(Long orderId) {
        // Create the payment link request
        JSONObject paymentLinkRequest = new JSONObject();
        //This creates a new JSON object that will hold the parameters for the payment link request.
        paymentLinkRequest.put("amount", AMOUNT_IN_PAISA); // 10.00 INR
        paymentLinkRequest.put("currency", CURRENCY);
        paymentLinkRequest.put("expire_by", System.currentTimeMillis() / 1000 + 3600); // 1 hour from now
        paymentLinkRequest.put("reference_id", orderId.toString());
        //This adds a reference ID, in our case the order ID, which will help us in identifying the transaction later
        // Customer details
        JSONObject customer = new JSONObject();
        customer.put("name", "Kaustubh Deshmukh");
        customer.put("contact", "+919768632222");
        customer.put("email", "kaustubhrd27@gmail.com");
        paymentLinkRequest.put("customer", customer);
        // Notification preferences
        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("reminder_enable", true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name", "Scaler");
        paymentLinkRequest.put("notes", notes);


        // Callback URL and method
        paymentLinkRequest.put("callback_url", "https://www.scaler.com/academy/instructor-dashboard/");
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink payment;
        try {
            payment = razorpay.paymentLink.create(paymentLinkRequest);
            logger.info("Payment link created successfully for order ID: {}", orderId);
        } catch (RazorpayException e) {
            logger.error("Failed to create payment link for order ID: {}", orderId, e);
            throw new RuntimeException("Unable to create payment link for order ID: " + orderId, e);
        }

        return payment.toString();
    }
}
