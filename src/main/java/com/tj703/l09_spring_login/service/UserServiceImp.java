package com.tj703.l09_spring_login.service;

import com.tj703.l09_spring_login.entity.User;
import com.tj703.l09_spring_login.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Override
    public Optional<User> login(String id, String pw) {
        return userRepository.findByIdAndPw(id, pw);
    }

    // 혜성, 1234
    // 혜성, $2a$10$IFo5D1AGpaUOvF2behpJcOdR1Ik9phVuJNCFDzFztLwWuD4fkXC1O
    @Override
    public Optional<User> loginHash(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());
        if (userOpt.isPresent()) {
            User loginUser = userOpt.get();
            if(BCrypt.checkpw(user.getPw(), loginUser.getPw())) {
                return userOpt;
            }
        }
        return null;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> detail(String id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void register(User user) {
        User existUser = entityManager.find(User.class, user.getId());
        if(existUser != null) {
            throw new IllegalArgumentException("이미 존재합니다");
        }
        entityManager.persist(user);
    }
}
