package com.tj703.l09_spring_login.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;
    @Test
    void generateToken() {
        String token = jwtUtil.generateToken("user1");
        System.out.println(token);
        // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTc0NDY5MDMzOH0.PbCarJqsYmtfoMsywjqkFlg5LMvIN4Daimfl1xzLmsqbh8Mqfao6kdEm58kDLO8TmM8qgivXXGE8826QKugoOA
    }

    @Test
    void validateToken() {
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTc0NDY5MDMzOH0.PbCarJqsYmtfoMsywjqkFlg5LMvIN4Daimfl1xzLmsqbh8Mqfao6kdEm58kDLO8TmM8qgivXXGE8826QKugoOA";
        boolean check=jwtUtil.validateToken(token);
        assertTrue(check);
    }

    @Test
    void getUsername() {
        String token=jwtUtil.generateToken("user1");
        String username= jwtUtil.getUsername(token);
        System.out.println(username);
        assertEquals(username,"user1");
    }
}