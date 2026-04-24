package com.logicaemocional.api.service;

import com.logicaemocional.api.dto.response.AiAnalysisResponse;

public interface AiAnalysisService {

    AiAnalysisResponse analyzeText(String content);
}