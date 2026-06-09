package com.subsync.app.infrastructure.adapter.out.persistance.repository;

import com.subsync.app.infrastructure.adapter.out.persistance.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio Spring Data JPA — solo conoce la entidad JPA.
 */
public interface SpringUserRepository extends JpaRepository<UserJpaEntity, String> {
    Optional<UserJpaEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
