package com.logicaemocional.api2.Service.Impl;

import com.logicaemocional.api2.Dto.Request.LoginRequest;
import com.logicaemocional.api2.Dto.Request.RegisterRequest;
import com.logicaemocional.api2.Dto.Response.AuthResponse;
import com.logicaemocional.api2.Entity.Professional;
import com.logicaemocional.api2.Entity.Role;
import com.logicaemocional.api2.Entity.Student;
import com.logicaemocional.api2.Entity.User;
import com.logicaemocional.api2.Enums.RoleName;
import com.logicaemocional.api2.Repository.ProfessionalRepository;
import com.logicaemocional.api2.Repository.RoleRepository;
import com.logicaemocional.api2.Repository.StudentRepository;
import com.logicaemocional.api2.Repository.UserRepository;
import com.logicaemocional.api2.Security.CustomUserDetails;
import com.logicaemocional.api2.Security.JwtService;
import com.logicaemocional.api2.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final ProfessionalRepository professionalRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Ya existe un usuario registrado con ese correo");
        }

        RoleName roleName = RoleName.valueOf(request.getRole().toUpperCase());

        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name(roleName)
                                .build()
                ));

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .roles(Set.of(role))
                .build();

        User savedUser = userRepository.save(user);

        if (roleName == RoleName.ROLE_STUDENT) {
            Student student = Student.builder()
                    .studentCode(request.getStudentCode())
                    .career(request.getCareer())
                    .semester(request.getSemester())
                    .user(savedUser)
                    .build();

            studentRepository.save(student);
        }

        if (roleName == RoleName.ROLE_PROFESSIONAL) {
            Professional professional = Professional.builder()
                    .professionalCode(request.getProfessionalCode())
                    .specialty(request.getSpecialty())
                    .user(savedUser)
                    .build();

            professionalRepository.save(professional);
        }

        CustomUserDetails userDetails = new CustomUserDetails(savedUser);
        String token = jwtService.generateToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .email(savedUser.getEmail())
                .role(roleName.name())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Role role = user.getRoles()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El usuario no tiene rol asignado"));

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String token = jwtService.generateToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .email(user.getEmail())
                .role(role.getName().name())
                .build();
    }
}