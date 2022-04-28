package com.example.skiSlope.model.request;


import com.example.skiSlope.model.SkiLift;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;


import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class SkiLiftRequest {

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NumberFormat
    private Double maxHeight;

    @NonNull
    @NumberFormat
    private Double skiRunLength;

    private String description;


    public SkiLift skiLiftRequest(){
        return SkiLift.builder()
                .id(null)
                .name(name)
                .maxHeight(maxHeight)
                .skiRunLength(skiRunLength)
                .description(description)
                .active(true)
                .build();

    }

}
