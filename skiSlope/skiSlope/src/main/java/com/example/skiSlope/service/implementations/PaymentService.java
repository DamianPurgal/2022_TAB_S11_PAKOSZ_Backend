package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.NoPaymentFoundException;
import com.example.skiSlope.model.Payment;
import com.example.skiSlope.repository.CardRepository;
import com.example.skiSlope.repository.PaymentRepository;
import com.example.skiSlope.service.definitions.PaymentServiceDefinition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PaymentService implements PaymentServiceDefinition {

    private PaymentRepository paymentRepository;
//    private CardRepository cardRepository;


    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(NoPaymentFoundException::new);
    }

    @Override
    public List<Payment> getAllPaymentsByUserId(Long userId) {
        return paymentRepository.findAllByUserId(userId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void setPaymentAsPaidOffById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(NoPaymentFoundException::new);
        payment.setPaidOff(true);
        paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentData(Payment payment, Long id) {
      payment.setId(id);
      paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.findById(id)
                .orElseThrow(NoPaymentFoundException::new);
        paymentRepository.deleteById(id);
    }

    @Override
    public void setPaymentToPaidOff(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(NoPaymentFoundException::new);
        payment.setPaymentDate(new Date(System.currentTimeMillis()));
        payment.setPaidOff(true);
        paymentRepository.save(payment);
    }
}
