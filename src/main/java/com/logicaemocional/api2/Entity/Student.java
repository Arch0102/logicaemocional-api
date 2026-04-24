package com.logicaemocional.api2.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentCode;

    private String career;

    private String semester;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}