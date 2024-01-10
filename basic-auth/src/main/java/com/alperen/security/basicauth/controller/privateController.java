package com.alperen.security.basicauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class privateController {
    @GetMapping
    public String helloWorldPrivate() {
        return "Hello world from private";
    }

    @GetMapping("/user")
    public String helloWorldUserPrivate() {
        return "Hello World from User Private";
    }

    @GetMapping("/admin")
    public String helloWorldAdminPrivate() {
        return "Hello World from Admin Private";
    }


}
