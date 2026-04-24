package com.logicaemocional.api.service.impl;

import com.logicaemocional.api.dto.response.AiAnalysisResponse;
import com.logicaemocional.api.enums.RiskLevel;
import com.logicaemocional.api.service.AiAnalysisService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiAnalysisServiceImpl implements AiAnalysisService {

    @Override
    public AiAnalysisResponse analyzeText(String content) {
        String text = content.toLowerCase();

        RiskLevel riskLevel = RiskLevel.LOW;
        List<String> factors = new ArrayList<>();
        String recommendation = "Se recomienda continuar con hábitos saludables y revisar los recursos de apoyo disponibles.";

        if (text.contains("cansado") || text.contains("agotado") || text.contains("estrés") || text.contains("estres")) {
            riskLevel = RiskLevel.MEDIUM;
            factors.add("agotamiento emocional");
            recommendation = "Se recomienda realizar una pausa activa y considerar hablar con bienestar universitario.";
        }

        if (text.contains("solo") || text.contains("aislado") || text.contains("nadie")) {
            riskLevel = RiskLevel.HIGH;
            factors.add("aislamiento social");
            recommendation = "Se recomienda priorizar acompañamiento por parte de un profesional de bienestar.";
        }

        if (text.contains("no puedo más") || text.contains("no quiero seguir") || text.contains("me quiero rendir")) {
            riskLevel = RiskLevel.CRITICAL;
            factors.add("señal crítica emocional");
            recommendation = "Se recomienda atención prioritaria por parte de un profesional humano.";
        }

        if (factors.isEmpty()) {
            factors.add("sin factores críticos detectados");
        }

        return AiAnalysisResponse.builder()
                .riskLevel(riskLevel)
                .detectedFactors(factors)
                .recommendation(recommendation)
                .build();
    }
}