package com.subsync.app.application.dto;

public record AuthResponse(
        String token,
        String email,
        String fullName
) {
}
