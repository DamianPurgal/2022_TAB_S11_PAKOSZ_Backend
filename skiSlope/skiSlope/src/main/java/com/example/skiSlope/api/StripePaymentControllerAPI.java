package com.example.skiSlope.api;

import com.example.skiSlope.model.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Balance;
import com.stripe.net.RequestOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
public class StripePaymentControllerAPI {

    @Value("${stripe.apikey}")
    String stripeKey;

    @RequestMapping("/create")
    public User index(@RequestBody User user){
        return user;
    }

    @RequestMapping("/balance")
    public Object balance() throws StripeException {
        Stripe.apiKey = stripeKey;
        RequestOptions requestOptions = RequestOptions.builder().setStripeAccount("acct_1Kye2JHIYoDF4dT1").build();

        Balance balance = Balance.retrieve(requestOptions);
//        Balance balance = new Balance();
        return balance.getAvailable();
    }

    @RequestMapping("/charge")
    public Object charge() throws StripeException {
        Stripe.apiKey = stripeKey;
        RequestOptions requestOptions = RequestOptions.builder().setStripeAccount("acct_1Kye2JHIYoDF4dT1").build();

        Balance balance = Balance.retrieve(requestOptions);
//        Balance balance = new Balance();
        return balance.getAvailable();
    }

}
