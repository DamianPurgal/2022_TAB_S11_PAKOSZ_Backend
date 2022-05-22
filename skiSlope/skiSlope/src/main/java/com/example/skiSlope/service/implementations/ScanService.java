package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.CardInactiveException;
import com.example.skiSlope.exception.CardNotFoundException;
import com.example.skiSlope.exception.CardUnpaidException;
import com.example.skiSlope.exception.UnsupportedSkiliftScannerException;
import com.example.skiSlope.model.*;
import com.example.skiSlope.model.enums.TimePeriod;
import com.example.skiSlope.model.request.TicketUpdateRequest;
import com.example.skiSlope.repository.ScanRepository;
import com.example.skiSlope.repository.ScannerQRRepository;
import com.example.skiSlope.service.definitions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ScanService implements ScanServiceDefinition {

    ScanRepository scanRepository;

    ScannerQRServiceDefinition scannerQRService;

    SkiLiftServiceDefinition skiLiftService;

    VoucherServiceDefinition voucherService;

    TicketServiceDefinition ticketService;

    VoucherOptionService voucherOptionService;


    @Override
    public Scan addScan(Long scannerId, UUID code) {
        Optional<Ticket> ticket = ticketService.getTicketByCode(code);
        ScannerQR scannerQR;
        Card card;

        if(ticket.isPresent()){
            scannerQR = scannerQRService.getById(scannerId);
            card = scanTicket(ticket.get(), scannerQR);
        }else{
            Optional<Voucher> voucher = voucherService.getVoucherByCode(code);
            if(voucher.isPresent()){
                scannerQR = scannerQRService.getById(scannerId);
                card = scanVoucher(voucher.get());
            }else{
                throw new CardNotFoundException();
            }
        }
        return scanRepository.save(new Scan(
                null,
                new Date(System.currentTimeMillis()),
                card,
                scannerQR.getSkiLift(),
                scannerQR
        ));
    }

    private Card scanTicket(Ticket ticket, ScannerQR scannerQR){
        if(ticket.getNumberOfEntries() < 1){
            throw new CardInactiveException();
        }
        if(!ticket.getActive()){
            throw new CardUnpaidException();
        }

        if(!ticket.getSkiLift().getId().equals(scannerQR.getSkiLift().getId())){
            throw new UnsupportedSkiliftScannerException();
        }

        ticket.setNumberOfEntries(ticket.getNumberOfEntries() - 1);
        return ticketService.updateTicket(ticket);
    }

    private Card scanVoucher(Voucher voucher){
        if(voucher.getStartDate() == null || voucher.getExpireDate() == null){
            voucher.setStartDate(new Date(System.currentTimeMillis()));
            VoucherOption voucherOption = voucherOptionService.getVoucherOptionById(voucher.getPrice().getId());
            voucher.setExpireDate(
                    new Date(voucher.getStartDate().getTime() +  TimePeriod.nameToTimeInMiliseconds(voucherOption.getTimePeriod().getName()).getTime())
            );
            return voucherService.updateVoucher(voucher);
        }else{
            if(new Date(System.currentTimeMillis()).after(voucher.getExpireDate())){
                throw new CardInactiveException();
            }
            if(!voucher.getActive()){
                throw new CardUnpaidException();
            }
            return voucher;
        }
    }
}
