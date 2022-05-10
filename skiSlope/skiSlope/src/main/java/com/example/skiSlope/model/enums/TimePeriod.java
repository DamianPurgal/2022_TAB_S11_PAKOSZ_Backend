package com.example.skiSlope.model.enums;

import com.example.skiSlope.exception.NoAvailableEntryOptionException;
import lombok.Getter;

public enum TimePeriod {
    twoHours("2h", 1.0),
    fourHours("4h", 1.5),
    day("1 day", 4.0),
    threeDays("3 days", 8.0),
    week("week", 20.0),
    twoWeeks("2 weeks", 13.5),
    month("month", 40.0),
    threeMonths("3 months", 60.0),
    year("year", 120.0);

    @Getter
    private String name;

    @Getter
    private double value;

    private static String[] names = new String[]{"2h", "4h", "1 day", "3 days", "week", "2 weeks", "month", "3 months", "year"};

    TimePeriod(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public static TimePeriod transformStringToName(String name) {
        switch (name) {
            case "2h":
                return twoHours;
            case "4h":
                return fourHours;
            case "1 day":
                return day;
            case "3 days":
                return threeDays;
            case "week":
                return week;
            case "2 weeks":
                return twoWeeks;
            case "month":
                return month;
            case "3 months":
                return threeMonths;
            case "year":
                return year;
            default:
                throw new NoAvailableEntryOptionException();
        }
    }
//    public String getName() {
//        return name;
//    }
//
//    public double getValue() {return value;}

    public static String[] allTimePeriodsOptions(){
        return names;
    }
}
