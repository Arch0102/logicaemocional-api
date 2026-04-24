package com.logicaemocional.api2.Service.Impl;

import com.logicaemocional.api2.Dto.Request.EmotionalEntryRequest;
import com.logicaemocional.api2.Dto.Response.AiAnalysisResponse;
import com.logicaemocional.api2.Dto.Response.EmotionalEntryResponse;
import com.logicaemocional.api2.Entity.EmotionalEntry;
import com.logicaemocional.api2.Entity.RiskFactor;
import com.logicaemocional.api2.Entity.Student;
import com.logicaemocional.api2.Enums.RiskLevel;
import com.logicaemocional.api2.Repository.EmotionalEntryRepository;
import com.logicaemocional.api2.Repository.RiskFactorRepository;
import com.logicaemocional.api2.Repository.StudentRepository;
import com.logicaemocional.api2.Service.AiAnalysisService;
import com.logicaemocional.api2.Service.EmotionalEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmotionalEntryServiceImpl implements EmotionalEntryService {

    private final EmotionalEntryRepository emotionalEntryRepository;
    private final StudentRepository studentRepository;
    private final RiskFactorRepository riskFactorRepository;
    private final AiAnalysisService aiAnalysisService;

    @Override
    public EmotionalEntryResponse createEntry(String email, EmotionalEntryRequest request) {
        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        AiAnalysisResponse analysis = aiAnalysisService.analyzeText(request.getContent());

        Set<RiskFactor> riskFactors = analysis.getDetectedFactors()
                .stream()
                .map(name -> riskFactorRepository.findByName(name)
                        .orElseGet(() -> riskFactorRepository.save(
                                RiskFactor.builder()
                                        .name(name)
                                        .description("Factor detectado automáticamente por IA")
                                        .build()
                        )))
                .collect(Collectors.toSet());

        EmotionalEntry entry = EmotionalEntry.builder()
                .content(request.getContent())
                .riskLevel(analysis.getRiskLevel())
                .aiRecommendation(analysis.getRecommendation())
                .createdAt(LocalDateTime.now())
                .student(student)
                .riskFactors(riskFactors)
                .build();

        EmotionalEntry savedEntry = emotionalEntryRepository.save(entry);

        return mapToResponse(savedEntry);
    }

    @Override
    public List<EmotionalEntryResponse> getMyEntries(String email) {
        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return emotionalEntryRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<EmotionalEntryResponse> getEntriesByRiskLevel(String riskLevel) {
        RiskLevel level = RiskLevel.valueOf(riskLevel.toUpperCase());

        return emotionalEntryRepository.findByRiskLevel(level)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EmotionalEntryResponse mapToResponse(EmotionalEntry entry) {
        Set<String> factors = entry.getRiskFactors()
                .stream()
                .map(RiskFactor::getName)
                .collect(Collectors.toSet());

        return EmotionalEntryResponse.builder()
                .id(entry.getId())
                .content(entry.getContent())
                .riskLevel(entry.getRiskLevel())
                .aiRecommendation(entry.getAiRecommendation())
                .createdAt(entry.getCreatedAt())
                .riskFactors(factors)
                .build();
    }
}