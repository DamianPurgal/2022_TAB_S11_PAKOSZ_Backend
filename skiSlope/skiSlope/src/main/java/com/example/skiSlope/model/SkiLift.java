package com.example.skiSlope.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SkiLift {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ski_lift_sequence")
    @SequenceGenerator(name = "ski_lift_sequence", sequenceName = "ski_lift_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Double maxHeight;

    private String description;

    private Boolean active;

}
