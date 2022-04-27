package com.example.skiSlope.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountType {
    None(0),
    Child(1),
    Student(2),
    Senior(3),
    Disabled(4);

    private final int value;

}
