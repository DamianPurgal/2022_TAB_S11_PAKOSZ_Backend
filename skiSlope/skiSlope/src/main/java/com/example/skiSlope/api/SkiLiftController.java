package com.example.skiSlope.api;

import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.SkiLiftRequest;
import com.example.skiSlope.model.request.SkiLiftUpdateRequest;
import com.example.skiSlope.model.request.VoucherRequest;
import com.example.skiSlope.service.implementations.SkiLiftService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/skiLift")
@RestController
public class SkiLiftController {

    private SkiLiftService skiLiftService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void addSkiLift(@Valid @NonNull @RequestBody SkiLiftRequest skiLiftRequest) {
        SkiLift skiLift = skiLiftRequest.skiLiftRequest();
        skiLiftService.addSkiLift(skiLift);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<SkiLift> getAllSkiLifts() {
        return skiLiftService.getAllSkiLifts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public SkiLift getSkiLiftById(@PathVariable("id") Long id) {
        return skiLiftService.getSkyLiftById(id)
                .orElse(null);
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
