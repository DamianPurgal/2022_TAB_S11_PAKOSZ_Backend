package com.example.skiSlope.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountType {
    None(0),
    Child(0.51),
    Student(0.51),
    Senior(0.75),
    Disabled(0.8);

    private final double value;

}