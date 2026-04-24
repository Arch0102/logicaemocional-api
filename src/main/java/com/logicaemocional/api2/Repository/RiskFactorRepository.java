package com.logicaemocional.api2.Repository;

import com.logicaemocional.api2.Entity.RiskFactor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskFactorRepository extends JpaRepository<RiskFactor, Long> {

    Optional<RiskFactor> findByName(String name);
}