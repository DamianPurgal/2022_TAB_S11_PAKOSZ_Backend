package com.example.skiSlope.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="skilifts")
public class SkiLift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_height")
    private Double maxHeight;

    @Column(name = "ski_run_length")
    private Double skiRunLength;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

}