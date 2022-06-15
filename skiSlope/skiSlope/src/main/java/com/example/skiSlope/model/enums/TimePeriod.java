package com.example.skiSlope.model.enums;

import com.example.skiSlope.exception.NoAvailableEntryOptionException;
import lombok.Getter;

import java.util.Date;

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
    private final String name;

    @Getter
    private final double value;

    private static final String[] names = new String[]{"2h", "4h", "1 day", "3 days", "week", "2 weeks", "month", "3 months", "year"};

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
    public static Date nameToTimeInMiliseconds(String name) {
        switch (name) {
            case "2h":
                return new Date(1000 * 60 * 60 * 2);
            case "4h":
                return new Date(1000 * 60 * 60 * 4);
            case "1 day":
                return new Date(1000 * 60 * 60 * 24);
            case "3 days":
                return new Date(1000 * 60 * 60 * 24 * 3);
            case "week":
                return new Date(1000 * 60 * 60 * 24 * 7);
            case "2 weeks":
                return new Date(1000 * 60 * 60 * 24 * 7 * 2);
            case "month":
                return new Date(1000L * 60 * 60 * 24 * 31);
            case "3 months":
                return new Date(1000L * 60 * 60 * 24 * 31 * 3);
            case "year":
                return new Date(1000L * 60 * 60 * 24 * 31 * 12);
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
