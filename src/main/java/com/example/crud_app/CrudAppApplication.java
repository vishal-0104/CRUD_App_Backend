package com.example.crud_app;

import com.example.crud_app.model.User;
import com.example.crud_app.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedAdminUser(UserService userService) {
        return args -> {
            // Check if admin user already exists
            if (userService.findAll().stream().noneMatch(user -> user.getEmail().equals("admin@example.com"))) {
                User admin = new User();
                admin.setName("Admin User");
                admin.setEmail("admin@example.com");
                admin.setPassword("password123"); // Will be encoded by UserService
                admin.setRole("admin");
                userService.save(admin);
                System.out.println("Admin user created successfully");

                // Seed a test user as well
                User testUser = new User();
                testUser.setName("Test User");
                testUser.setEmail("test@example.com");
                testUser.setPassword("password123");
                testUser.setRole("user");
                userService.save(testUser);
                System.out.println("Test user created successfully");
            } else {
                System.out.println("Admin user already exists, skipping seeding");
            }
        };
    }
}