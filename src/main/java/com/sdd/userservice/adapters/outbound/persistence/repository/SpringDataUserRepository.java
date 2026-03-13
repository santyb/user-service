package com.sdd.userservice.adapters.outbound.persistence.repository;

import com.sdd.userservice.adapters.outbound.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, UUID> {
}
