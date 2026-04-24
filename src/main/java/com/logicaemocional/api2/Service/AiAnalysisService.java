package com.logicaemocional.api.Service;

import com.logicaemocional.api.dto.response.AiAnalysisResponse;

public interface AiAnalysisService {

    AiAnalysisResponse analyzeText(String content);
}