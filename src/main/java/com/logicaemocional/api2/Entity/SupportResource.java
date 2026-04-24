package com.logicaemocional.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "support_resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String type;

    private String url;
}