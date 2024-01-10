package com.alperen.security.dev.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {
    @GetMapping
    public String privateHelloWorld() {
        return "Hello World from private endpoint";
    }
//    @PreAuthorize("hasRole('USER')") //@PreAuthorize ile hasRole kullanarak User rolüne sahip kişiler girebilir
    @GetMapping("/user")
    public String privateUserHelloWorld() {
        return "Hello World from user private endpoint";
    }
//    @PreAuthorize("hasRole('ADMIN')")//@PreAuthorize ile hasRole kullanarak Admin rolüne sahip kişiler girebilir
    @GetMapping("/admin")
    public String privateAdminHelloWorld() {
        return "Hello World from admin private endpoint";
    }
}
