package com.subsync.app.domain.port.out;

import com.subsync.app.domain.model.User;

/**
 * PUERTO DE SALIDA para generación y validación de tokens.
 * El dominio define el contrato; infraestructura lo implementa con JWT.
 */
public interface TokenProvider {
    String generateToken(User user);
    String extractEmail(String token);
    Boolean isTokenValid(String token, String email);
}
