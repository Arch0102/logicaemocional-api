package com.logicaemocional.api.service;

import com.logicaemocional.api.dto.request.EmotionalEntryRequest;
import com.logicaemocional.api.dto.response.EmotionalEntryResponse;

import java.util.List;

public interface EmotionalEntryService {

    EmotionalEntryResponse createEntry(String email, EmotionalEntryRequest request);

    List<EmotionalEntryResponse> getMyEntries(String email);

    List<EmotionalEntryResponse> getEntriesByRiskLevel(String riskLevel);
}