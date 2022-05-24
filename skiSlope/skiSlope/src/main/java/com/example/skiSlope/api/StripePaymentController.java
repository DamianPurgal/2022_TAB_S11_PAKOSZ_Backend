package com.example.skiSlope.api;

//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StripePaymentController {

    @Value("${stripe.apikey}")
    String stripeKey;

    @RequestMapping("/")
    public String index(){
        return "hello "+stripeKey;
    }
}
