package com.alperen.security.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")

public class PublicController {
    @GetMapping
    public String privateHelloWorld() {
        return "Hello world from public endpoint";
    }
}
