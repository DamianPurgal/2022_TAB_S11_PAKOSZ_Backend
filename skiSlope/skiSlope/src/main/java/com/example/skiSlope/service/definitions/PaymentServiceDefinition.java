package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Payment;


import java.util.List;


public interface PaymentServiceDefinition {

    Payment addPayment(Payment payment);

    Payment getPaymentById(Long id);

    List<Payment> getAllPaymentsByUserId(Long userId);

    List<Payment> getAllPayments();

    void setPaymentAsPaidOffById( Long id);

    void updatePaymentData(Payment payment, Long id);

    void deletePayment(Long id);

}
