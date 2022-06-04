package com.example.skiSlope.security.utility;

import com.example.skiSlope.model.StripeCustomerData;
import com.example.skiSlope.model.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeUtil {

    @Value("${stripe.apikey}")
    String stripeKey;

    public StripeCustomerData getCustomer(String id) throws StripeException {
        Stripe.apiKey = stripeKey;

        Customer customer = Customer.retrieve(id);
        StripeCustomerData user = setCustomerData(customer);
        return user;
    }

    public StripeCustomerData setCustomerData(Customer customer) {
        StripeCustomerData customerData = new StripeCustomerData();
        customerData.setCustomerId(customer.getId());
        customerData.setName(customer.getName());
        customerData.setEmail(customer.getEmail());

        return customerData;
    }
}
