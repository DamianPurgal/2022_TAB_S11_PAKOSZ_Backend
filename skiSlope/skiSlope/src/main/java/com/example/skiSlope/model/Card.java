package com.example.skiSlope.model;

import java.util.UUID;

enum DiscountType{
    None,
    Child,
    Student,
    Senior,
    Disabled
}
public interface Card {

    UUID code = null;

    DiscountType discountType = null;

    double cost = 0.0;
    
    default UUID getId(){return this.code;}
    
    default  DiscountType getDiscountType(){return this.discountType;}
    
    default double getCost(){return this.cost;}
   
    



}
