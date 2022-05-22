package com.example.skiSlope.model;

import com.example.skiSlope.model.utils.LongListConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Scanners")
@Table(name="Scanners")
public class ScannerQR {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="id")
    private Long id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scannerQR" , orphanRemoval = true)
    List<ScannerQRSkiLiftConnection> skiLifts = new ArrayList<>();
}
