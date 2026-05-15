package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncodeTest {

    @Test
    public void passwordEncodeTest() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "Password1!";

        // Encode password
        String encodedPassword = encoder.encode(rawPassword);

        assertNotNull(encodedPassword);

        // Match password
        assertTrue(encoder.matches(rawPassword, encodedPassword));

        // Wrong password
        assertFalse(encoder.matches("WrongPassword", encodedPassword));
    }
}