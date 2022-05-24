package com.example.skiSlope.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Scans")
public class Scan {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="id")
    private Long id;

    @Column(name="time")
    protected Date scanDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    protected Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ski_lift_id", nullable = false)
    protected SkiLift skiLift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scanner_id", nullable = false)
    protected ScannerQR scannerQR;

}
