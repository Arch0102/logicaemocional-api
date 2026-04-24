package com.logicaemocional.api2.service;

import com.logicaemocional.api2.Dto.Request.EmotionalEntryRequest;
import com.logicaemocional.api2.Dto.Response.EmotionalEntryResponse;

import java.util.List;

public interface EmotionalEntryService {

    EmotionalEntryResponse createEntry(String email, EmotionalEntryRequest request);

    List<EmotionalEntryResponse> getMyEntries(String email);

    List<EmotionalEntryResponse> getEntriesByRiskLevel(String riskLevel);
}
