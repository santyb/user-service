package com.sdd.userservice.application.service;

import com.sdd.userservice.application.port.UserRepositoryPort;
import com.sdd.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        user.setCreatedAt(Instant.now());
        log.info("Creating user with name='{}' email='{}'", user.getName(), user.getEmail());
        User saved = userRepositoryPort.save(user);
        log.debug("User created with id='{}'", saved.getId());
        return saved;
    }

    public Optional<User> getUserById(UUID id) {
        log.info("Fetching user with id='{}'", id);
        return userRepositoryPort.findById(id);
    }

    public List<User> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepositoryPort.findAll();
        log.debug("Found {} users", users.size());
        return users;
    }
}
