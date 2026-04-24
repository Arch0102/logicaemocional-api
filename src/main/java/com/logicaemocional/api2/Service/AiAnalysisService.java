package com.logicaemocional.api2.Service;

import com.logicaemocional.api2.Dto.Response.AiAnalysisResponse;

public interface AiAnalysisService {

    AiAnalysisResponse analyzeText(String content);
}