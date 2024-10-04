package com.example.paymentservice.Controllers;

import com.example.paymentservice.Services.PaymentService;
import com.example.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/")
    public String cratePaymentLink(@RequestBody CreatePaymentLinkRequestDto createPaymentLinkRequestDto)  {
        String payment = null;
        try {
            payment = paymentService.createPaymentLink(createPaymentLinkRequestDto.getOrderId());
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }

        return payment;
    }
}