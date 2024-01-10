package com.alperen.security.basicauth.dto;

import com.alperen.security.basicauth.model.Role;
import lombok.Builder;

import java.util.Set;
@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities

) {

}
