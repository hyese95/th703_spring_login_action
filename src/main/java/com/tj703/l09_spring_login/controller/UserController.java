package com.tj703.l09_spring_login.controller;

import com.tj703.l09_spring_login.entity.User;
import com.tj703.l09_spring_login.jwt.JwtUtil;
import com.tj703.l09_spring_login.security.CustomUserDetailsService;
import com.tj703.l09_spring_login.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 로그인폼
    // -> /jwt/login.do 요청 {id: 혜성, pw: 1234}
    // -> 로그인이 되었다면 jwt 토큰 생성 후 응답
    // -> 로그인 양식에서 jwt 토큰을 받아서 로컬에 저장
    @PostMapping("/jwt/login.do")
    public ResponseEntity<Map<String, String>> loginAction(
            @RequestBody User user
            ) {
        System.out.println("loginAction 중");
        boolean isLogin = userService.loginHash(user);
        if (isLogin) {
            String jwt=jwtUtil.generateToken(user.getId());
            Map<String, String> map = Collections.singletonMap("jwt", jwt);
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.status(403).build();
        // 403 인증이 안됨 (유저가 없거나 비밀번호가 틀림)
    }
    // session -> session 만료
    // security + session (/logout) -> SecurityContextHolder.getContext().setAuthentication(null)
    // cookie + jwt -> cookie 만료

    // LocalStorage + jwt -> 브라우저 로컬저장소에 jwt 를 삭제
    // @GetMapping("/logout.do")
    // public String logoutAction(
            // @CookieValue(name = "jwt", required = false) Cookie jwtCookie,
            // HttpServletResponse response
    // ) {
        // if (jwtCookie != null) {
            // jwtCookie.setMaxAge(0);
            // jwtCookie.setPath("/");
            // response.addCookie(jwtCookie);
        // }
        // return "redirect:/";
    // }
}
