package com.example.skiSlope.model.request;

import com.example.skiSlope.model.SkiLift;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@AllArgsConstructor
public class SkiLiftUpdateRequest {

    private String name;

    @NumberFormat
    protected Double maxHeight;

    @NumberFormat
    private Double skiRunLength;

    private String description;

    @BooleanFlag
    private Boolean active;

    void setIfNull(SkiLift skiLift){
        if(name==null)
            name = skiLift.getName();
        if(description==null)
            description = skiLift.getDescription();
        if(maxHeight==null)
            maxHeight = skiLift.getMaxHeight();
        if(skiRunLength==null)
            skiRunLength = skiLift.getSkiRunLength();
    }
    void setActiveIfNull(SkiLift skiLift){
        if(active == null)
            active = skiLift.getActive();
    }

    public SkiLift skiListUpdateInfoRequest(SkiLift skiLift){
        setIfNull(skiLift);
        return SkiLift.builder()
                .id(skiLift.getId())
                .name(name)
                .maxHeight(maxHeight)
                .skiRunLength(skiRunLength)
                .description(description)
                .active(true)
                .build();
    }
    public SkiLift skiListSetActiveInfoRequest(SkiLift skiLift){
        setActiveIfNull(skiLift);
        return SkiLift.builder()
                .id(skiLift.getId())
                .name(skiLift.getName())
                .maxHeight(skiLift.getMaxHeight())
                .skiRunLength(skiLift.getSkiRunLength())
                .description(skiLift.getDescription())
                .active(active)
                .build();
    }
}