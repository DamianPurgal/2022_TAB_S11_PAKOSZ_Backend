package com.example.skiSlope.model.enums;

import com.example.skiSlope.exception.NoAvailableEntryOptionException;

public enum TimePeriod {
    twoHours("2h"),
    fourHours("4h"),
    day("1 day"),
    threeDays("3 days"),
    week("week"),
    twoWeeks("2 weeks"),
    month("month"),
    threeMonths("3 months"),
    year("year");

    private String name;
    private static String[] names = new String[]{"2h", "4h", "1 day", "3 days", "week", "2 weeks", "month", "3 months", "year"};

    TimePeriod(String name) {
        this.name = name;
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
    public String getName() {
        return name;
    }

    public static String[] allTimePeriodsOptions(){
        return names;
    }
}
