package com.sdd.userservice.infrastructure.config;

import com.sdd.userservice.application.service.UserService;
import com.sdd.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        log.info("Inserting sample users...");

        User john = new User();
        john.setName("John Doe");
        john.setEmail("john.doe@example.com");

        User jane = new User();
        jane.setName("Jane Smith");
        jane.setEmail("jane.smith@example.com");

        User bob = new User();
        bob.setName("Bob Johnson");
        bob.setEmail("bob.johnson@example.com");

        userService.createUser(john);
        userService.createUser(jane);
        userService.createUser(bob);

        log.info("Sample data initialized: 3 users inserted.");
    }
}
