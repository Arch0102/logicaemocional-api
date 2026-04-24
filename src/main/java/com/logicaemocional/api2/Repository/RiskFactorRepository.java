package com.logicaemocional.api.repository;

import com.logicaemocional.api.entity.RiskFactor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskFactorRepository extends JpaRepository<RiskFactor, Long> {

    Optional<RiskFactor> findByName(String name);
}