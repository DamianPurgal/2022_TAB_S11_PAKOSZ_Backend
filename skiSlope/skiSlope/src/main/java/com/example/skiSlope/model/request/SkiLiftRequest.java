package com.example.skiSlope.model.request;


import com.example.skiSlope.model.SkiLift;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.format.annotation.NumberFormat;


import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class SkiLiftRequest {

    @NotBlank
    private String name;

    @NumberFormat
    private Double maxHeight;

    private String description;

    @BooleanFlag
    private Boolean active;

    public SkiLift skiLiftRequest(){
        return SkiLift.builder()
                .id(null)
                .name(name)
                .maxHeight(maxHeight)
                .description(description)
                .active(true)
                .build();

    }
    public SkiLift skiListUpdateInfoRequest(SkiLift skiLift){
        if(name==null)
            skiLift.getName();
        if(description==null)
            skiLift.getDescription();
        if(maxHeight==null)
            skiLift.getMaxHeight();
        return SkiLift.builder()
                .id(skiLift.getId())
                .name(name)
                .maxHeight(maxHeight)
                .description(description)
                .active(true)
                .build();
    }
    public SkiLift skiListSetActiveInfoRequest(SkiLift skiLift){
        return SkiLift.builder()
                .id(skiLift.getId())
                .name(skiLift.getName())
                .maxHeight(skiLift.getMaxHeight())
                .description(skiLift.getDescription())
                .active(active)
                .build();
    }
}
