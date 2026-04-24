package com.logicaemocional.api2.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "risk_factors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}