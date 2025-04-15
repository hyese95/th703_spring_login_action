package com.tj703.l09_spring_login.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
class SecurityConfigTest {

    @Test
    void passwordEncoder() {
        System.out.println(BCrypt.hashpw("1234", BCrypt.gensalt()));
        // $2a$10$IFo5D1AGpaUOvF2behpJcOdR1Ik9phVuJNCFDzFztLwWuD4fkXC1O
    }
}