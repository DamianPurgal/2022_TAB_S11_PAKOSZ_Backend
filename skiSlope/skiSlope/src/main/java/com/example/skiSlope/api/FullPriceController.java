package com.example.skiSlope.api;

import com.example.skiSlope.exception.NewStartDateBeforeStartDateException;
import com.example.skiSlope.model.FullPrice;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import com.example.skiSlope.model.enums.TimePeriod;
import com.example.skiSlope.model.request.TicketOptionFullPriceRequest;
import com.example.skiSlope.model.request.VoucherOptionFullPriceRequest;
import com.example.skiSlope.service.implementations.TicketOptionService;
import com.example.skiSlope.service.implementations.VoucherOptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/prices/set")
@RestController
public class FullPriceController {

    TicketOptionService ticketOptionService;

    VoucherOptionService voucherOptionService;

    @PostMapping("/ticket")
    public void addNewTicketOption(@Valid @NonNull @RequestBody FullPrice fullPrice) throws ParseException {

        for(TicketOption t: ticketOptionService.getAllTicketOptions()){
            if(fullPrice.getStartDate().compareTo(t.getStartDate()) <= 0){
                throw new NewStartDateBeforeStartDateException();
            }
        }
        ticketOptionService.updateLatestTicketOptionData(fullPrice.getStartDate());
        List<TicketOption> ticketOptionList = new ArrayList<>();
        for(EntriesEnum entriesEnum : EntriesEnum.values() ){
            for(DiscountType discountType : DiscountType.values()){
                TicketOption ticketOption = new TicketOptionFullPriceRequest().createFullPricePriceRequest(discountType, entriesEnum, fullPrice);
                ticketOptionList.add(ticketOption);
//                ticketOptionService.addTicketOption(ticketOption);
            }
            ticketOptionService.addTicketOptions(ticketOptionList);
        }
    }

    @PostMapping("/voucher")
    public void addNewVoucherOption(@Valid @NonNull @RequestBody FullPrice fullPrice) throws ParseException {
        for(VoucherOption v: voucherOptionService.getAllVoucherOptions()){
            if(fullPrice.getStartDate().compareTo(v.getStartDate()) <= 0){
                throw new NewStartDateBeforeStartDateException();
            }
        }
        voucherOptionService.updateLatestVoucherOptionData(fullPrice.getStartDate());
        List<VoucherOption> voucherOptionList = new ArrayList<>();
        for(TimePeriod timePeriod : TimePeriod.values() ){
            for(DiscountType discountType : DiscountType.values()){
                VoucherOption voucherOption = new VoucherOptionFullPriceRequest().createFullPriceVoucherRequest(discountType, timePeriod, fullPrice);
                voucherOptionList.add(voucherOption);
            }
            voucherOptionService.addVoucherOptions(voucherOptionList);
        }
    }
}
