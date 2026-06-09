package com.subsync.app.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * ENTIDAD JPA — vive exclusivamente en infraestructura.
 * Es el modelo de persistencia; no es el modelo de dominio.
 */
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {
    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @ElementCollection(fetch = FetchType.EAGER) // Indica a JPA que esta colección no es una entidad independiente, sino una colección de valores simples.
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")) // Le dice a JPA dónde guardar la colección.
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<RoleJpa> roles;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String createdAt;
}
