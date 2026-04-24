package com.logicaemocional.api2.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private String token;
    private String tokenType;
    private String email;
    private String role;
}