package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.VoucherNotFoundException;
import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.request.SkiLiftRequest;
import com.example.skiSlope.model.request.SkiLiftUpdateRequest;
import com.example.skiSlope.repository.SkiLiftRepository;
import com.example.skiSlope.service.definitions.SkiLiftServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<SkiLift> getSkyLiftById(Long id) {
        return skiLiftRepository.findById(id);
    }

    @Override
    public List<SkiLift> getAllSkiLifts() {
        return skiLiftRepository.findAll();
    }

    @Override
    public void updateSkiLiftsData(SkiLiftUpdateRequest skiLiftUpdateRequest, Long id) {
        SkiLift skiLift = skiLiftRepository.findById(id)
                .orElseThrow(VoucherNotFoundException::new);
        skiLift = skiLiftUpdateRequest.skiListUpdateInfoRequest(skiLift);
        skiLiftRepository.save(skiLift);
    }

    @Override
    public void setSkiLiftsActive(SkiLiftUpdateRequest skiLiftUpdateRequest, Long id) {
        SkiLift skiLift = skiLiftRepository.findById(id)
                .orElseThrow(VoucherNotFoundException::new);
        skiLift = skiLiftUpdateRequest.skiListSetActiveInfoRequest(skiLift);
        skiLiftRepository.save(skiLift);
    }

    @Override
    public void deleteSkiLift(Long id) {
        skiLiftRepository.deleteById(id);
    }
}
