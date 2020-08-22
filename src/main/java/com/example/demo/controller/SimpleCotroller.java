package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleCotroller {

    @GetMapping("/")
    public String get() {
        return "<h1>Hello SHAHFAHED</h1>";
    }

    @GetMapping("admin")
    public String getAdmin() {
        return "<h1>This is an Admin</h1>";
    }

    @GetMapping("user")
    public String getUser() {
        return "<h1>This is an User</h1>";
    }
}
