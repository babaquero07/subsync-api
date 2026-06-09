package com.subsync.app.domain.port.in;

import com.subsync.app.application.dto.AuthResponse;
import com.subsync.app.application.dto.LoginCommand;
import com.subsync.app.application.dto.RegisterCommand;

/**
 * PUERTO DE ENTRADA (Driving Port).
 * Define lo que la aplicación puede hacer desde el exterior.
 * El REST controller llamará a esta interfaz.
 */
public interface AuthUseCase {
    AuthResponse register(RegisterCommand command);
    AuthResponse login(LoginCommand command);
}
