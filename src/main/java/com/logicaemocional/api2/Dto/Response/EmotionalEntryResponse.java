package com.logicaemocional.api.dto.Response;

import com.logicaemocional.api.enums.RiskLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class EmotionalEntryResponse {

    private Long id;
    private String content;
    private RiskLevel riskLevel;
    private String aiRecommendation;
    private LocalDateTime createdAt;
    private Set<String> riskFactors;
}