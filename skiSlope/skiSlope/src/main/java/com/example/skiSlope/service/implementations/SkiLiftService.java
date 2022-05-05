package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.SkiLiftNotFoundException;
import com.example.skiSlope.exception.UserNotFoundException;
import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.request.SkiLiftUpdateRequest;
import com.example.skiSlope.repository.SkiLiftRepository;
import com.example.skiSlope.service.definitions.SkiLiftServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class SkiLiftService implements SkiLiftServiceDefinition {

    private SkiLiftRepository skiLiftRepository;

    @Override
    public SkiLift addSkiLift(SkiLift skiLift) {
        return skiLiftRepository.save(skiLift);
    }

    @Override
    public SkiLift getSkyLiftById(Long id) {

        return skiLiftRepository.findById(id).orElseThrow(SkiLiftNotFoundException::new);

    }

    @Override
    public List<SkiLift> getAllSkiLifts() {
        return skiLiftRepository.findAll();
    }

    @Override
    public void updateSkiLiftsData(SkiLiftUpdateRequest skiLiftUpdateRequest, Long id) {
        SkiLift skiLift = skiLiftRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        skiLift = skiLiftUpdateRequest.skiListUpdateInfoRequest(skiLift);
        skiLiftRepository.save(skiLift);
    }

    @Override
    public void setSkiLiftsActive(SkiLiftUpdateRequest skiLiftUpdateRequest, Long id) {
        SkiLift skiLift = skiLiftRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        skiLift = skiLiftUpdateRequest.skiListSetActiveInfoRequest(skiLift);
        skiLiftRepository.save(skiLift);
    }

    @Override
    public void deleteSkiLift(Long id) {
        skiLiftRepository.deleteById(id);
    }
}