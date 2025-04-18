package com.tj703.l09_spring_login.controller;

import com.tj703.l09_spring_login.entity.User;
import com.tj703.l09_spring_login.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @Controller // html 을 렌더링하는 컨트롤러 : ssr SPA
@RestController // data 를 전달만하는 컨트롤러 : fsr MPA
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/user/list.do")
    public List<User> userList() {
        return userService.list();
    }

}
