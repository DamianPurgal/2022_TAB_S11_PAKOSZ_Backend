package com.example.skiSlope.model.request;


import com.example.skiSlope.exception.ResourceNotFoundException;
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

    private String name;

    @NumberFormat
    private Double maxHeight;

    @NumberFormat
    private Double skiRunLength;

    private String description;


    private void throwIfNull(){
        if(name == null)
            throw new ResourceNotFoundException("Name cannot be empty!");
        if(maxHeight == null)
            throw new ResourceNotFoundException("MaxHeight cannot be empty!");
        if(skiRunLength == null)
            throw new ResourceNotFoundException("skiRunLength cannot be empty!");
    }

    public SkiLift skiLiftRequest(){
        throwIfNull();
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