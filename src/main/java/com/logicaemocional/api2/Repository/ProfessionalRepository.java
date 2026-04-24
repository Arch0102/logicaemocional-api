package com.logicaemocional.api2.Repository;

import com.logicaemocional.api2.Entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    Optional<Professional> findByUserEmail(String email);
}
