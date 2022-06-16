package com.example.skiSlope.paypal;

import com.example.skiSlope.exception.AlreadyPaidOffPayment;
import com.example.skiSlope.service.implementations.PaymentService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
@RequestMapping("/api")
public class PayPalController {

    @Autowired
    PayPalService service;

    @Autowired
    PaymentService paymentService;

    public static final String SUCCESSFUL_OPERATION_URL = "https://projekt-pp-tab-2022.herokuapp.com/api/payment/update/";
    public static final String FAILED_OPERATION_URL = "https://projekt-pp-tab-2022.herokuapp.com/api/payment/delete/";

    @GetMapping("/pay/{id}")
    public String makePayment(@PathVariable("id") Long id) {
        com.example.skiSlope.model.Payment payment1 = paymentService.getPaymentById(id);
        if(payment1.getPaidOff())
            throw new AlreadyPaidOffPayment();
        String description = "Zakup biletu Srebrne Stoki " +payment1.getUser().getFirstName()+" "+payment1.getUser().getLastName()+" Numer zamowienia: "+id;
        try {
            Amount amount = new Amount();
            amount.setTotal(String.format("%.3f", payment1.getTotalPrice()));
            Payment payment = service.createPayment(payment1.getTotalPrice(), "PLN", "POST",
                    "sale", description, FAILED_OPERATION_URL+id,
                    SUCCESSFUL_OPERATION_URL+id);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

            System.out.println(payment.getId() + " get state " + payment.getState());

        } catch (PayPalRESTException e) {

            e.printStackTrace();
            e.getDetails();
        }
        System.out.println(BigDecimal.valueOf(payment1.getTotalPrice()).setScale(3, RoundingMode.UP).doubleValue());
        return "redirect:/";
    }
}