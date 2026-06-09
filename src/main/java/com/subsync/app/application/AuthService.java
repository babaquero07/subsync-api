package com.subsync.app.application;

import com.subsync.app.application.dto.AuthResponse;
import com.subsync.app.application.dto.LoginCommand;
import com.subsync.app.application.dto.RegisterCommand;
import com.subsync.app.domain.model.User;
import com.subsync.app.domain.port.in.AuthUseCase;
import com.subsync.app.domain.port.out.TokenProvider;
import com.subsync.app.domain.port.out.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * SERVICIO DE APLICACIÓN — implementa el puerto de entrada AuthUseCase.
 * Orquesta la lógica de negocio usando los puertos de salida.
 * No sabe nada de HTTP, JPA ni JWT — solo trabaja con abstracciones del dominio.
 */
@Service
public class AuthService implements AuthUseCase {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, TokenProvider tokenProvider,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(RegisterCommand command) {
       if(userRepository.existsByEmail(command.email())) {
           throw new IllegalArgumentException(
                   "Email is already registered" + command.email()
           );
       }

       String hashedPassword = passwordEncoder.encode(command.password());

       User newUser = User.register(
                command.email(),
                hashedPassword,
                command.firstName(),
                command.lastName()
       );

       User savedUser = userRepository.save(newUser);
       String token = tokenProvider.generateToken(savedUser);

       return new AuthResponse(token, savedUser.getEmail(), savedUser.getFullName());
    }

    @Override
    public AuthResponse login(LoginCommand command) {
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if(!user.isActive()) {
            throw new IllegalStateException("User is not active");
        }

        String token = tokenProvider.generateToken(user);

        return new AuthResponse(token, user.getEmail(), user.getFullName());
    }
}
