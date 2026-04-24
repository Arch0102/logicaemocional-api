package com.logicaemocional.api2.Repository;

import com.logicaemocional.api2.entity.Role;
import com.logicaemocional.api2.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}