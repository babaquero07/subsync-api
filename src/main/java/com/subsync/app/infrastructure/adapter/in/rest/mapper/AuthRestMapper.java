package com.subsync.app.infrastructure.adapter.in.rest.mapper;

import com.subsync.app.application.dto.LoginCommand;
import com.subsync.app.application.dto.RegisterCommand;
import com.subsync.app.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.subsync.app.infrastructure.adapter.in.rest.dto.RegisterRequest;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper: convierte los DTOs REST en Commands del dominio.
 * Mantiene la separación entre capas.
 */
@Mapper(componentModel = "spring")
public interface AuthRestMapper {
    RegisterCommand toRegisterCommand(RegisterRequest request);
    LoginCommand toLoginCommand(LoginRequest request);
}
