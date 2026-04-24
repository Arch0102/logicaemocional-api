package com.logicaemocional.api.dto.response;

import com.logicaemocional.api.enums.RiskLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AiAnalysisResponse {

    private RiskLevel riskLevel;
    private List<String> detectedFactors;
    private String recommendation;
}