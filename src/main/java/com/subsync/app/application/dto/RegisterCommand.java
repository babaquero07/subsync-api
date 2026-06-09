package com.subsync.app.application.dto;

public record RegisterCommand(
        String email,
        String password,
        String firstName,
        String lastName
) {
}
