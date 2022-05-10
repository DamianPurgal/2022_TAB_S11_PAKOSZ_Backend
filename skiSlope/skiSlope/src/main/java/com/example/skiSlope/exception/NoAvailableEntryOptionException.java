package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.skiSlope.model.enums.EntriesEnum.allEntryEnumValues;
import static com.example.skiSlope.model.enums.TimePeriod.allTimePeriodsOptions;

public class NoAvailableEntryOptionException extends  BusinessException{
    public NoAvailableEntryOptionException() {
        super(HttpStatus.BAD_REQUEST.value(), "This entry option is not available! The following entry amounts that are available: "
                + Arrays.stream(allEntryEnumValues()).mapToObj(s->Integer.toString(s)).collect(Collectors.joining(", ")));
    }
}