package com.subsync.app.infrastructure.adapter.out.persistance.mapper;

import com.subsync.app.domain.model.Role;
import com.subsync.app.domain.model.User;
import com.subsync.app.infrastructure.adapter.out.persistance.entity.RoleJpa;
import com.subsync.app.infrastructure.adapter.out.persistance.entity.UserJpaEntity;
import org.mapstruct.Mapper;

/**
 * Mapper entre modelo de dominio y entidad JPA.
 */
@Mapper(componentModel = "spring")
public interface UserPersistanceMapper {
    UserJpaEntity toJpaEntity(User user);
    User toDomainModel(UserJpaEntity entity);

    default RoleJpa toRoleJpa(Role role) {
        return RoleJpa.valueOf(role.name());
    }

    default Role toRole(RoleJpa roleJpa) {
        return Role.valueOf(roleJpa.name());
    }
}
