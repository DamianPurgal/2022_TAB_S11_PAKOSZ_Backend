package com.example.skiSlope.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
//@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="cards")
public abstract class Card {

    @Id
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_sequence")
    @Column(name = "id", nullable = false)
    protected Long id;

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="code", nullable = false)
    protected UUID code;

    @Column(name="discountType")
    @Enumerated(EnumType.STRING)
    protected DiscountType discountType;

    @Column(name = "customerId", nullable = false)
    protected Long userId;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    protected CardType cardType;

    @Column(name="ownerName")
    protected String ownerName;

    @Column(name="paymentId")
    protected Long paymentId;

    @Column(name="priceId")
    protected Long priceId;

    @Column(name="active")
    protected Boolean active;

    //implement method to search for a user by theirs id
    void setOwnerNameWhenNull(){
        if(ownerName==null){

        }
    }
//    public static class CardBuilder{
//
//        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_sequence")
//        protected Long id;
//        protected final UUID code;
//        protected DiscountType discountType;
//        protected Long userId;
//        protected CardType cardType;
//        protected String ownerName;
//        protected Long paymentId;
//        protected Long priceId;
//        protected Boolean active;
//
//        public CardBuilder() {
//            this.id = id;
//            this.code = UUID.randomUUID();
//        }
//
////        public CardBuilder id(Long id) {
////            this.id = id;
////            return this;
////        }
////        public CardBuilder userId(Long userId) {
////            this.userId = userId;
////            return this;
////        }
////        public CardBuilder discountType(DiscountType discountType) {
////            this.discountType = discountType;
////            return this;
////        }
////        public CardBuilder cardType(CardType cardType) {
////            this.cardType = cardType;
////            return this;
////        }
////        public CardBuilder ownerName(String ownerName) {
////            this.ownerName = ownerName;
////            return this;
////        }
////        public CardBuilder paymentId(Long userId) {
////            this.userId = paymentId;
////            return this;
////        }
////        public CardBuilder priceId(Long priceId) {
////            this.priceId = priceId;
////            return this;
////        }
////        public CardBuilder active(Boolean active) {
////            this.active = active;
////            return this;
////        }
//
//        protected void validateCardObject(Card card){
//            card.setId(Objects.nonNull(card.getId()) ? card.getId() : id );
//            card.setCode(Objects.nonNull(card.getCode())? card.getCode():UUID.randomUUID());
//            card.setDiscountType(Objects.nonNull(card.getDiscountType())? card.getDiscountType():DiscountType.None);
//            //card.setUserId(Objects.nonNull(card.getUserId())? card.getUserId(): throws new RuntimeException("No user id added!"));
//
//        }
//
//    }

}
