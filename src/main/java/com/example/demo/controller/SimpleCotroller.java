package com.example.demo.controller;

import com.example.demo.dao.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleCotroller {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String get() {

        System.out.println(userRepository.findByUsername("shahfahed").getUsername());
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
