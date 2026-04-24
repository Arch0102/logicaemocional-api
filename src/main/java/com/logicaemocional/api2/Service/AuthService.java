package com.logicaemocional.api2.Service;

import com.logicaemocional.api2.Dto.Request.LoginRequest;
import com.logicaemocional.api2.Dto.Request.RegisterRequest;
import com.logicaemocional.api2.Dto.Response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}