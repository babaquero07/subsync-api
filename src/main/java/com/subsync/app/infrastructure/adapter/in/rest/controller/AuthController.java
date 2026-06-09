package com.subsync.app.infrastructure.adapter.in.rest.controller;

import com.subsync.app.application.dto.AuthResponse;
import com.subsync.app.domain.port.in.AuthUseCase;
import com.subsync.app.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.subsync.app.infrastructure.adapter.in.rest.dto.RegisterRequest;
import com.subsync.app.infrastructure.adapter.in.rest.mapper.AuthRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ADAPTADOR PRIMARIO (Driving Adapter).
 * Recibe peticiones HTTP y las transforma en llamadas al puerto de entrada.
 * No contiene lógica de negocio — solo traduce y delega.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;
    private final AuthRestMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authUseCase.register(mapper.toRegisterCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authUseCase.login(mapper.toLoginCommand(request));

        return ResponseEntity.ok(response);
    }
}
