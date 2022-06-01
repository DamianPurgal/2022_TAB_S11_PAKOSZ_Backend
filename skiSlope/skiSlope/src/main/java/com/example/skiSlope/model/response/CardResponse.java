package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    Long id;
    UUID code;
    String type;
    String ownerName;
    Boolean active;
}
