package com.example.skiSlope.model.enums;

import com.example.skiSlope.exception.NoAvailableEntryOptionException;
import com.example.skiSlope.exception.PriceNotFoundException;

public enum EntriesEnum {
    one(1),
    five(5),
    ten(10),
    fifteen(15),
    twenty(20),
    fifty(50);
    private final int value;
    private static int[] values = new int[]{1, 5, 10, 15, 20, 50};

    EntriesEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static EntriesEnum transformIntToValue(int entriesAmount) throws NoAvailableEntryOptionException {
        switch (entriesAmount) {
            case 1:
                return one;
            case 5:
                return five;
            case 10:
                return ten;
            case 15:
                return fifteen;
            case 20:
                return twenty;
            case 50:
                return fifty;
            default:
                throw new NoAvailableEntryOptionException();
        }
    }
    public static int[] allEntryEnumValues(){
        return values;
    }

}