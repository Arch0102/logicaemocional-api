package com.logicaemocional.api2.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "professionals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String professionalCode;

    private String specialty;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
