package com.subsync.app.domain.port.out;

import com.subsync.app.domain.model.User;

import java.util.Optional;

/**
 * PUERTO DE SALIDA (Driven Port).
 * Define cómo el dominio necesita interactuar con la persistencia.
 * El dominio depende de esta interfaz; la implementación vive en infraestructura.
 */
public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
