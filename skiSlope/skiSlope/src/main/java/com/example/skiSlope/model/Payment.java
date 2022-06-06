package com.example.skiSlope.model;


import com.example.skiSlope.model.enums.CardType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "paid_off")
    private Boolean paidOff;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payment")
    private List<Card> cardSet = new ArrayList<>();

    /**
     * Adds new card to payment
     * @param card - new card that supposed to be added to payment
     */
    public void addCardToPayment(Card card){
        this.cardSet.add(card);
    }
    public List<Ticket> getTickets(){
        List<Ticket> tickets = new ArrayList<>();
        if(cardSet==null)
            return null;
        for(Card c: cardSet){
            if(c.getCardType().toString().equals(CardType.Ticket.toString()))
                tickets.add(((Ticket) c));
        }
        return tickets;
    }
    public List<Voucher> getVouchers(){
        List<Voucher> vouchers = new ArrayList<>();
        if(cardSet==null)
            return null;
        for(Card c: cardSet){
            if(c.getCardType().equals(CardType.Voucher))
                vouchers.add(((Voucher) c));
        }
        return vouchers;
    }
    public Double getTotalPrice(){
        if(cardSet==null)
            return null;
        totalCost = 0.0;
        for(Card c: cardSet){
            totalCost = totalCost + c.getPrice().getPrice();
        }
        return totalCost;
    }

}
