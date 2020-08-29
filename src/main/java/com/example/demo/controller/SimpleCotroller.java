package com.example.demo.controller;

import com.example.demo.dao.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class SimpleCotroller {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER')")
    public String get() {

        System.out.println(userRepository.findByUsername("shahfahed").getUsername());
        return "<h1>Hello SHAHFAHED</h1>";
    }

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('ACCESS_ADMIN')")
    public String getAdmin() {
        return "<h1>This is an Admin</h1>";
    }

    @GetMapping("user")
    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_MANAGER"})
    public String getUser() {
        return "<h1>This is an User</h1>";
    }

    @GetMapping("manager")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    public String getManager() {
        return "<h1>This is an Manager</h1>";
    }
}
