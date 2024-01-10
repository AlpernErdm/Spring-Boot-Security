package com.alperen.security.basicauth.service;

import com.alperen.security.basicauth.dto.CreateUserRequest;
import com.alperen.security.basicauth.model.User;
import com.alperen.security.basicauth.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    public User createUser(CreateUserRequest createUserRequest) {

        User newUser = User.builder()
                .name(createUserRequest.name())
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .authorities(createUserRequest.authorities())
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonExpired(true)
                .build();

        return userRepository.save(newUser);
    }
}
