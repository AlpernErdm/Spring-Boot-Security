package com.alperen.security.basicauth;

import com.alperen.security.basicauth.dto.CreateUserRequest;
import com.alperen.security.basicauth.model.Role;
import com.alperen.security.basicauth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class BasicAuthApplication implements CommandLineRunner {
private final UserService userService;

    public BasicAuthApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		createDummyData();

	}

	private void createDummyData(){

		CreateUserRequest request=CreateUserRequest.builder()
				.username("alperen")
				.name("Alpiş")
				.password("pass")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.createUser(request);

		CreateUserRequest request1=CreateUserRequest.builder()
				.username("alp")
				.name("vhhh")
				.password("pass")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.createUser(request1);

		CreateUserRequest request3=CreateUserRequest.builder()
				.username("aden")
				.name("Adoş")
				.password("pass")
				.authorities(Set.of(Role.ROLE_ALP))
				.build();
		userService.createUser(request3);
	}
}
