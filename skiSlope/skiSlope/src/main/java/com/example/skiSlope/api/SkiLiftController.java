package com.example.skiSlope.api;

import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.request.SkiLiftRequest;
import com.example.skiSlope.model.request.SkiLiftUpdateRequest;
import com.example.skiSlope.model.response.SkiLiftResponse;
import com.example.skiSlope.service.implementations.SkiLiftService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/skiLift")
@RestController
public class SkiLiftController {

    private SkiLiftService skiLiftService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public void addSkiLift(@Valid @NonNull @RequestBody SkiLiftRequest skiLiftRequest) {
        SkiLift skiLift = skiLiftRequest.skiLiftRequest();
        skiLiftService.addSkiLift(skiLift);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<SkiLiftResponse> getAllSkiLifts() {
        List<SkiLift> skiLiftList = skiLiftService.getAllSkiLifts();
        return skiLiftList.stream().map(
                skiListRes -> SkiLiftResponse
                        .builder()
                        .id(skiListRes.getId())
                        .name(skiListRes.getName())
                        .maxHeight(skiListRes.getMaxHeight())
                        .skiRunLength(skiListRes.getSkiRunLength())
                        .description(skiListRes.getDescription())
                        .active(skiListRes.getActive())
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public SkiLiftResponse getSkiLiftById(@PathVariable("id") Long id) {
        SkiLift skiLift = skiLiftService.getSkyLiftById(id);
        return SkiLiftResponse.builder()
                .id(skiLift.getId())
                .name(skiLift.getName())
                .maxHeight(skiLift.getMaxHeight())
                .skiRunLength(skiLift.getSkiRunLength())
                .description(skiLift.getDescription())
                .active(skiLift.getActive())
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteSkiLiftByCode(@PathVariable("id") Long id) {
        skiLiftService.deleteSkiLift(id);
    }

    @PutMapping("/{id}/edit")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void updateSkiLiftByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody SkiLiftUpdateRequest skiLiftUpdateRequest) {
        skiLiftService.updateSkiLiftsData(skiLiftUpdateRequest, id);
    }

    @PutMapping("/{id}/turnOnOff")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void setSkiLiftByCodeActive(@PathVariable("id") Long id, @Valid @NonNull @RequestBody SkiLiftUpdateRequest skiLiftUpdateRequest) {
        skiLiftService.setSkiLiftsActive(skiLiftUpdateRequest, id);
    }
}