package com.example.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// special class giving to spring to scan at start time will check for beans and will store this in the spring application context
// This annotation indicates that the class contains one or more bean methods and can be processed by the
// Spring container to generate bean definitions and service requests for those beans at runtime.
public class RazorpayConfig {

    @Value("${razorpay.key.id}")
    // This annotation is used to inject values from the application's properties(application.properties) into java fields
    private String razorpayKeyID;
    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;
    @Bean
//  This annotation tells Spring that this method will return an object that should be
//  registered as a bean in the Spring application context.
//  This means the RazorpayClient will be available for dependency injection elsewhere in our application.
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayKeyID,razorpayKeySecret);
    }

}