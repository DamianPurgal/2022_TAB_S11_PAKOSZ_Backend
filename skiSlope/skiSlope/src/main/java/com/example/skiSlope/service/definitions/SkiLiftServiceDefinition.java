package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.request.SkiLiftUpdateRequest;

import java.util.List;

public interface SkiLiftServiceDefinition {

    SkiLift addSkiLift(SkiLift skiLift);

    SkiLift getSkyLiftById(Long id);

    List<SkiLift> getAllSkiLifts();

    void updateSkiLiftsData(SkiLiftUpdateRequest skiLiftUpdateRequest, Long id);

    void setSkiLiftsActive(SkiLiftUpdateRequest skiLiftUpdateRequest, Long id);

    void deleteSkiLift(Long id);
}
