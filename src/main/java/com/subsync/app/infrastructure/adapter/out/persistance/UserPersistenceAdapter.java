package com.subsync.app.infrastructure.adapter.out.persistance;

import com.subsync.app.domain.model.User;
import com.subsync.app.domain.port.out.UserRepository;
import com.subsync.app.infrastructure.adapter.out.persistance.mapper.UserPersistanceMapper;
import com.subsync.app.infrastructure.adapter.out.persistance.repository.SpringUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * ADAPTADOR SECUNDARIO (Driven Adapter) de persistencia.
 * Implementa el puerto de salida UserRepository del dominio.
 * Traduce entre domain model ↔ JPA entity usando el mapper.
 */
@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {
    private final SpringUserRepository userRepository;
    private final UserPersistanceMapper mapper;

    @Override
    public User save(User user) {
        var entity = mapper.toJpaEntity(user);
        var savedEntity = userRepository.save(entity);

        return mapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(mapper::toDomainModel);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
