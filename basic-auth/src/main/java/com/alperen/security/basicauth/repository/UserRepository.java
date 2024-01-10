package com.alperen.security.basicauth.repository;

import com.alperen.security.basicauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String userName);
}
