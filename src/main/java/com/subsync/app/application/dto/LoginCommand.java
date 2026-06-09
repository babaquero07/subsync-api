package com.subsync.app.application.dto;

public record LoginCommand(
        String email,
        String password
) {
}
