//package com.example.skiSlope.model.request;
//
//import com.example.skiSlope.exception.NoAvailableEntryOptionException;
//import com.example.skiSlope.exception.PriceGreaterThanFullPriceException;
//import com.example.skiSlope.model.*;
//import com.example.skiSlope.model.enums.DiscountType;
//import com.example.skiSlope.model.enums.EntriesEnum;
//import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NonNull;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.NumberFormat;
//
//
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static com.example.skiSlope.model.enums.EntriesEnum.transformIntToValue;
//
//@AllArgsConstructor
//@Getter
//public class TicketOptionRequest {
//
//    @NonNull
//    @NumberFormat
//    protected double price;
//
//    @DateTimeFormat
//    protected Date startDate;
//
//    @DateTimeFormat
//    protected Date expireDate;
//
//    @Enumerated(EnumType.STRING)
//    protected DiscountType discountType;
//
//    @NumberFormat
//    protected Double fullPrice;
//
//    @NonNull
//    private Integer entries;
//
////    @NonNull
////    @Enumerated(EnumType.STRING)
////    private EntriesEnum entriesEnum;
//
//    private void setNullValues() throws ParseException {
//        if(discountType==null) {
//            discountType = DiscountType.None;}
//
//        if(fullPrice==null) {
//            fullPrice = price/(discountType.getValue());}
//
//        if(startDate==null) {
//            Long startDateLong = System.currentTimeMillis();
//            SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
//            startDate = new Date(startDateLong);}
//
//        if(expireDate==null){
//            String expireDateString = "9999-12-31T22:59:59.000-0000";
//            expireDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse(expireDateString);}
//    }
//    private boolean checkExpireDateCorrectness(){
//        if(expireDate.compareTo(startDate) < 0){
//            throw new ExpireDateEarlierThanStartDateException();
//        }
//        return true;
//    }
//    private boolean checkPriceCorrectness() {
//        if(fullPrice!=null && price > fullPrice){
//           throw new PriceGreaterThanFullPriceException();
//        }
//        return true;
//    }
//    private EntriesEnum intToEnum() throws NoAvailableEntryOptionException {
//        EntriesEnum entriesEnum = transformIntToValue(entries);
//        return entriesEnum;
//    }
//
//    public TicketOption ticketOptionRequest() throws ParseException, ExpireDateEarlierThanStartDateException, PriceGreaterThanFullPriceException, NoAvailableEntryOptionException {
//        setNullValues();
//        checkExpireDateCorrectness();
//        checkPriceCorrectness();
//        return TicketOption.builder()
//                .id(null)
//                .price(price)
//                .startDate(startDate)
//                .expireDate(expireDate)
//                .discountType(discountType)
//                .fullPrice(fullPrice)
//                .entriesEnum(intToEnum())
//                .build();
//    }
//}