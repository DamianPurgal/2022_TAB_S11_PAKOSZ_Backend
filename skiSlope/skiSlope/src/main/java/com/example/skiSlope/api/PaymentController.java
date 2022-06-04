package com.example.skiSlope.api;

import com.example.skiSlope.model.*;
import com.example.skiSlope.model.request.PaymentCreateRequest;
import com.example.skiSlope.model.request.TicketRequest;
import com.example.skiSlope.model.response.TicketResponse;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.PaymentService;
import com.example.skiSlope.service.implementations.TicketService;
import com.example.skiSlope.service.implementations.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/payment")
@RestController
public class PaymentController {

    private PaymentService paymentService;
    private TicketService ticketService;
    private VoucherService voucherService;
    private UserService userService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void addPayment(@Valid @NonNull @RequestBody PaymentCreateRequest paymentCreateRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Payment payment = paymentCreateRequest.paymentRequest();
        payment.setUser(loggedUser);
        paymentService.addPayment(payment);
        for (int i = 0; i < payment.getCardSet().size(); i++) {
            if (payment.getCardSet().get(i).getCardType().equals("Ticket"))
                ticketService.addTicket((Ticket) payment.getCardSet().get(i));
            else
                voucherService.addVoucher((Voucher) payment.getCardSet().get(i));
        }
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
//        return ticketList.stream().map(
//                ticketRes->TicketResponse
//                        .builder()
//                        .id(ticketRes.getId())
//                        .code(ticketRes.getCode())
//                        .active(ticketRes.getActive())
//                        .entryAmount(ticketRes.getNumberOfEntries())
//                        .ownerName(ticketRes.getOwnerName())
//                        .skiLiftName(ticketRes.getSkiLift().getName())
//                        .build()
//        ).collect(Collectors.toList());
//    }
    }
}
