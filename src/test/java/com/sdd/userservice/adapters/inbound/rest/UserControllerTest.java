package com.sdd.userservice.adapters.inbound.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdd.userservice.application.service.UserService;
import com.sdd.userservice.domain.model.User;
import com.sdd.userservice.infrastructure.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(WebConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User user;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = User.builder()
                .id(userId)
                .name("John Doe")
                .email("john@example.com")
                .createdAt(Instant.now())
                .build();
    }

    @Test
    void createUser_shouldReturn201WithCreatedUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user);

        User request = new User();
        request.setName("John Doe");
        request.setEmail("john@example.com");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void getUserById_shouldReturn200WhenUserExists() throws Exception {
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void getUserById_shouldReturn404WhenUserNotFound() throws Exception {
        UUID unknownId = UUID.randomUUID();
        when(userService.getUserById(unknownId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/{id}", unknownId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_shouldReturn200WithUserList() throws Exception {
        User second = User.builder()
                .id(UUID.randomUUID())
                .name("Jane Smith")
                .email("jane@example.com")
                .createdAt(Instant.now())
                .build();

        when(userService.getAllUsers()).thenReturn(List.of(user, second));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    void getAllUsers_shouldReturn200WithEmptyList() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
