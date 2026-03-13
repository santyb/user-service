package com.sdd.userservice.application.service;

import com.sdd.userservice.application.port.UserRepositoryPort;
import com.sdd.userservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private UserService userService;

    private User inputUser;

    @BeforeEach
    void setUp() {
        inputUser = new User();
        inputUser.setName("John Doe");
        inputUser.setEmail("john@example.com");
    }

    @Test
    void createUser_shouldAssignIdAndCreatedAtThenDelegate() {
        User savedUser = User.builder()
                .id(UUID.randomUUID())
                .name(inputUser.getName())
                .email(inputUser.getEmail())
                .createdAt(Instant.now())
                .build();

        when(userRepositoryPort.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(inputUser);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("John Doe");
        verify(userRepositoryPort, times(1)).save(any(User.class));
    }

    @Test
    void createUser_shouldSetIdAndCreatedAtOnInputBeforeSaving() {
        when(userRepositoryPort.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.createUser(inputUser);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();
    }

    @Test
    void getUserById_shouldReturnUserWhenFound() {
        UUID id = UUID.randomUUID();
        User found = User.builder().id(id).name("John").email("john@example.com").build();

        when(userRepositoryPort.findById(id)).thenReturn(Optional.of(found));

        Optional<User> result = userService.getUserById(id);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(id);
        verify(userRepositoryPort).findById(id);
    }

    @Test
    void getUserById_shouldReturnEmptyWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepositoryPort.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(id);

        assertThat(result).isEmpty();
        verify(userRepositoryPort).findById(id);
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() {
        List<User> users = List.of(
                User.builder().id(UUID.randomUUID()).name("User 1").email("u1@example.com").build(),
                User.builder().id(UUID.randomUUID()).name("User 2").email("u2@example.com").build()
        );

        when(userRepositoryPort.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("User 1");
        verify(userRepositoryPort).findAll();
    }

    @Test
    void getAllUsers_shouldReturnEmptyListWhenNoUsers() {
        when(userRepositoryPort.findAll()).thenReturn(List.of());

        List<User> result = userService.getAllUsers();

        assertThat(result).isEmpty();
    }
}
