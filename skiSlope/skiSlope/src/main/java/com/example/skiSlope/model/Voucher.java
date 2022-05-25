package com.example.skiSlope.model;

import com.example.skiSlope.model.enums.CardType;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vouchers")
@PrimaryKeyJoinColumn(name="id")
public class Voucher extends Card {

    @Column(name = "begin_time")
    private Date startDate;

    @Column(name = "end_time")
    private Date expireDate;

    @Builder
    public Voucher(Long id, UUID code, User user, Long paymentId, Price price, String ownerName, CardType cardType, Boolean active, Date startDate, Date expireDate) {
        this.id = id;
        this.code = UUID.randomUUID();
        this.user = user;
        this.paymentId = paymentId;
        this.price = price;
        this.cardType = CardType.Voucher;
        this.active = true;
        this.ownerName = ownerName;
        if (ownerName == null){
            User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.ownerName = loggedUser.getFirstName()+" "+loggedUser.getLastName();
        }
        this.startDate = startDate;
        this.expireDate = expireDate;
    }

    public boolean isVoucherExpired(){
        if(startDate == null || expireDate == null){
            return false;
        }
        return new Date(System.currentTimeMillis()).after(this.getExpireDate());
    }
}
