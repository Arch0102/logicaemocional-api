package com.logicaemocional.api2.Repository;

import com.logicaemocional.api2.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserEmail(String email);
}
