package com.example.skiSlope.paypal;

import com.example.skiSlope.api.PaymentController;
import com.example.skiSlope.model.response.PaymentResponse;
import com.example.skiSlope.service.implementations.PaymentService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@AllArgsConstructor
public class PayPalController {

    @Autowired
    PayPalService service;

    PaymentService paymentService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    public static final String SUCCESSFUL_OPERATION_URL = "http://localhost:8080/api/payment/update/";
    public static final String FAILED_OPERATION_URL = "http://localhost:8080/api/payment/delete/";


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
            e.getDetails();
        }
        return "redirect:/";
    }
    @PostMapping("/redPay")
    public String makePayment() {
//        Long id = Long.valueOf(70);
//        System.out.println(paymentService.getAllPayments().get(0).getId());
//        com.example.skiSlope.model.Payment payment1 = paymentService.getAllPayments().get(0);
        String description = "Zakup biletu Srebrne Stoki Sarah Stein" +"\nNumer zamowienia: 22245 ";
        try {
            Payment payment = service.createPayment(102.45, "PLN", "POST",
                    "sale", description, FAILED_OPERATION_URL+70,
                    SUCCESSFUL_OPERATION_URL+70);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
            e.getDetails();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage() + " " + e.getDetails());
        }
        return "redirect:/";

    }
}