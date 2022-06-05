package com.example.skiSlope.api;

import com.example.skiSlope.model.*;
import com.example.skiSlope.model.request.PaymentCreateRequest;
import com.example.skiSlope.model.request.TicketCreatePaymentRequest;
import com.example.skiSlope.model.request.VoucherCreatePaymentRequest;
import com.example.skiSlope.model.response.*;
import com.example.skiSlope.service.implementations.*;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/payment")
@RestController
public class PaymentController {

    private PaymentService paymentService;
    private TicketService ticketService;
    private VoucherService voucherService;
    private SkiLiftService skiLiftService;
    private TicketOptionService ticketOptionService;
    private VoucherOptionService voucherOptionService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public PaymentResponse addPayment(@Valid @NonNull @RequestBody PaymentCreateRequest paymentCreateRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Payment payment = paymentCreateRequest.paymentRequest();
        payment.setUser(loggedUser);
        paymentService.addPayment(payment);
        List<Ticket> tickets = new ArrayList<>();
        List<Voucher> vouchers = new ArrayList<>();
        if (paymentCreateRequest.getTickets() != null) {
            for (TicketCreatePaymentRequest t : paymentCreateRequest.getTickets()) {
                Ticket ticket = t.ticketRequest();
                ticket.setPayment(payment);
                ticket.setSkiLift(skiLiftService.getSkiLiftByName(t.getSkiLiftName()));
                ticket.setUser(loggedUser);
                ticket.setPrice(ticketOptionService.getTicketOptionByCurrentDateAndDiscountTypeAndEntries(t.getDiscountType(), t.getNumberOfEntries()));
                ticketService.addTicket(ticket);
                tickets.add(ticket);
            }
        }
        if (paymentCreateRequest.getVouchers() != null) {
            for (VoucherCreatePaymentRequest v : paymentCreateRequest.getVouchers()) {
                Voucher voucher = v.voucherRequest();
                voucher.setUser(loggedUser);
                voucher.setPayment(payment);
                voucher.setPrice(voucherOptionService.getCurrentVoucherOptionByDiscountTypeAndTimePeriod(v.getDiscountType(), v.getTimePeriod()));
                voucherService.addVoucher(voucher);
                vouchers.add(voucher);
//                payment.addCardToPayment(voucher);
            }
            List<Card> cards = new ArrayList<>();
            cards.addAll(vouchers);
            cards.addAll(tickets);
            payment.setCardSet(cards);
        }
        if (payment.getCardSet() != null)
            System.out.println(payment.getCardSet().size());
        System.out.println(payment.getTickets());
        paymentService.updatePaymentData(payment, payment.getId());
        return PaymentResponse.builder()
                .id(payment.getId())
                .firstName(payment.getUser().getFirstName())
                .lastName(payment.getUser().getLastName())
                .email(payment.getUser().getEmail())
                .totalPrice(BigDecimal.valueOf(payment.getTotalPrice()).setScale(2, RoundingMode.UP))
                .paidOff(payment.getPaidOff())
                .items(CardItemsResponse.builder()
                        .tickets(payment.getTickets().stream().map(
                                ticketRes -> TicketPaymentResponse.builder()
                                        .ownerName(ticketRes.getOwnerName())
                                        .ticketPrice(BigDecimal.valueOf(ticketRes.getPrice().getPrice()).setScale(2, RoundingMode.UP))
                                        .discountType(ticketRes.getPrice().getDiscountType().toString())
                                        .entryAmount(ticketRes.getNumberOfEntries())
                                        .skiLiftName(ticketRes.getSkiLift().getName())
                                        .build()
                        ).collect(Collectors.toList()))
                        .vouchers(payment.getVouchers().stream().map(
                                voucher -> VoucherPaymentResponse.builder()
                                        .ownerName(voucher.getOwnerName())
                                        .voucherPrice(BigDecimal.valueOf(voucher.getPrice().getPrice()).setScale(2, RoundingMode.UP))
                                        .discountType(voucher.getPrice().getDiscountType().toString())
                                        .periodTime(((VoucherOption) voucher.getPrice()).getTimePeriod().getName())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build())
                .build();
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return payments.stream().map(
                payment -> PaymentResponse.builder()
                        .id(payment.getId())
                        .firstName(payment.getUser().getFirstName())
                        .lastName(payment.getUser().getLastName())
                        .email(payment.getUser().getEmail())
                        .totalPrice(BigDecimal.valueOf(payment.getTotalPrice()).setScale(2, RoundingMode.UP))
                        .paidOff(payment.getPaidOff())
                        .items(CardItemsResponse.builder()
                                .tickets(payment.getTickets().stream().map(
                                        ticketRes -> TicketPaymentResponse.builder()
                                                .ownerName(ticketRes.getOwnerName())
                                                .ticketPrice(BigDecimal.valueOf(ticketRes.getPrice().getPrice()).setScale(2, RoundingMode.UP))
                                                .discountType(ticketRes.getPrice().getDiscountType().toString())
                                                .entryAmount(ticketRes.getNumberOfEntries())
                                                .skiLiftName(ticketRes.getSkiLift().getName())
                                                .build()
                                ).collect(Collectors.toList()))
                                .vouchers(payment.getVouchers().stream().map(
                                        voucher -> VoucherPaymentResponse.builder()
                                                .ownerName(voucher.getOwnerName())
                                                .voucherPrice(BigDecimal.valueOf(voucher.getPrice().getPrice()).setScale(2, RoundingMode.UP))
                                                .discountType(voucher.getPrice().getDiscountType().toString())
                                                .periodTime(((VoucherOption) voucher.getPrice()).getTimePeriod().getName())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build())
                        .build()).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public PaymentBasicResponse getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        System.out.println(payment.getTickets().get(0));
        return PaymentBasicResponse.builder()
                .id(payment.getId())
                .firstName(payment.getUser().getFirstName())
                .lastName(payment.getUser().getLastName())
                .email(payment.getUser().getEmail())
                .totalPrice(BigDecimal.valueOf(payment.getTotalPrice()).setScale(2, RoundingMode.UP))
                .paidOff(payment.getPaidOff())
//                .items(CardItemsResponse.builder()
//                        .tickets(payment.getTickets().stream().map(
//                                ticketRes -> TicketPaymentResponse.builder()
//                                        .ownerName(ticketRes.getOwnerName())
//                                        .ticketPrice(BigDecimal.valueOf(ticketRes.getPrice().getPrice()).setScale(2, RoundingMode.UP))
//                                        .discountType(ticketRes.getPrice().getDiscountType().toString())
//                                        .entryAmount(ticketRes.getNumberOfEntries())
//                                        .skiLiftName(ticketRes.getSkiLift().getName())
//                                        .build()
//                        ).collect(Collectors.toList()))
//                        .vouchers(payment.getVouchers().stream().map(
//                                voucher -> VoucherPaymentResponse.builder()
//                                        .ownerName(voucher.getOwnerName())
//                                        .voucherPrice(BigDecimal.valueOf(voucher.getPrice().getPrice()).setScale(2, RoundingMode.UP))
//                                        .discountType(voucher.getPrice().getDiscountType().toString())
//                                        .periodTime(((VoucherOption) voucher.getPrice()).getTimePeriod().getName())
//                                        .build()
//                        ).collect(Collectors.toList()))
//                        .build())
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public String deletePaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        ticketService.deleteAllTicketsByPayment(payment);
        voucherService.deleteAllVouchersByPayment(payment);
        paymentService.deletePayment(id);
        return "payment successfully deleted";
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteAllPayments() {
        for (Payment p : paymentService.getAllPayments()) {
            ticketService.deleteAllTicketsByPayment(p);
            voucherService.deleteAllVouchersByPayment(p);
            paymentService.deletePayment(p.getId());
        }

    }

}
