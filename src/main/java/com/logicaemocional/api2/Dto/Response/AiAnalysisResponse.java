package com.logicaemocional.api2.Dto.Response;

import com.logicaemocional.api2.Enums.RiskLevel;
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