package com.logicaemocional.api.repository;

import com.logicaemocional.api.entity.EmotionalEntry;
import com.logicaemocional.api.entity.Student;
import com.logicaemocional.api.enums.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionalEntryRepository extends JpaRepository<EmotionalEntry, Long> {

    List<EmotionalEntry> findByStudent(Student student);

    List<EmotionalEntry> findByRiskLevel(RiskLevel riskLevel);
}