package com.logicaemocional.api2.Repository;

import com.logicaemocional.api2.Entity.EmotionalEntry;
import com.logicaemocional.api2.Entity.Student;
import com.logicaemocional.api2.Enums.RiskLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionalEntryRepository extends JpaRepository<EmotionalEntry, Long> {

    List<EmotionalEntry> findByStudent(Student student);

    List<EmotionalEntry> findByRiskLevel(RiskLevel riskLevel);
}