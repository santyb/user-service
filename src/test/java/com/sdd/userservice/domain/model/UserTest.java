package com.sdd.userservice.domain.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void shouldCreateUserWithBuilder() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();

        User user = User.builder()
                .id(id)
                .name("John Doe")
                .email("john@example.com")
                .createdAt(now)
                .build();

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john@example.com");
        assertThat(user.getCreatedAt()).isEqualTo(now);
    }

    @Test
    void shouldCreateUserWithNoArgsConstructorAndSetters() {
        User user = new User();
        user.setName("Jane Smith");
        user.setEmail("jane@example.com");

        assertThat(user.getName()).isEqualTo("Jane Smith");
        assertThat(user.getEmail()).isEqualTo("jane@example.com");
        assertThat(user.getId()).isNull();
        assertThat(user.getCreatedAt()).isNull();
    }

    @Test
    void shouldBeEqualWhenSameFields() {
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();

        User user1 = User.builder().id(id).name("Alice").email("alice@example.com").createdAt(now).build();
        User user2 = User.builder().id(id).name("Alice").email("alice@example.com").createdAt(now).build();

        assertThat(user1).isEqualTo(user2);
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentId() {
        User user1 = User.builder().id(UUID.randomUUID()).name("Alice").email("alice@example.com").build();
        User user2 = User.builder().id(UUID.randomUUID()).name("Alice").email("alice@example.com").build();

        assertThat(user1).isNotEqualTo(user2);
    }
}
