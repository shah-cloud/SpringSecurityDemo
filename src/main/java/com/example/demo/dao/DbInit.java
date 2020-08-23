package com.example.demo.dao;

import com.example.demo.dao.jpa.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        // Crete users
        User user = new User("shahfahed", passwordEncoder.encode("1234"), "USER", "");
        User admin = new User("admin", passwordEncoder.encode("1234"), "ADMIN", "ACCESS_USER,ACCESS_ADMIN");
        User manager = new User("manager", passwordEncoder.encode("manager123"), "MANAGER", "ACCESS_USER");

        List<User> users = Arrays.asList(user, admin, manager);

        userRepository.deleteAll();
        userRepository.saveAll(users);
    }
}
