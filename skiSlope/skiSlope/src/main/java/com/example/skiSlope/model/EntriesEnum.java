package com.example.skiSlope.model;

public enum EntriesEnum {
    one(1),
    five(5),
    ten(10),
    fifteen(15),
    twenty(20),
    fifty(50);
    private final int value;

    EntriesEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static EntriesEnum transformIntToValue(int entriesAmount){
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
                return null;
        }
    }
}
