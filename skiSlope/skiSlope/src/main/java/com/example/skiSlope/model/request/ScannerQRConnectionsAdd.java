package com.example.skiSlope.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScannerQRConnectionsAdd {

    @NonNull
    @NumberFormat
    Long scannerId;

    @NonNull
    @JsonDeserialize(as = ArrayList.class)
    private List<Long> skiLifts;
}
