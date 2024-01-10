package com.alperen.security.basicauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/public")
public class publicController {
    @GetMapping
    public String helloWorldPublic(Principal principal){
        return "Hello world from public";
    }
}
