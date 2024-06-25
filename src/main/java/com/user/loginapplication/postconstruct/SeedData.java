package com.user.loginapplication.postconstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.user.loginapplication.model.User;
import com.user.loginapplication.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class SeedData {
        
    @Autowired
	private UserService userService;

    @PostConstruct
    public void init(){
        // Initialize User
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User("Ted","ted.lee", encoder.encode("Manager123"), "ROLE_Manager");
		User user2 = new User("David", "david.tan", encoder.encode("User123"), "ROLE_User");
		userService.persist(user);
		userService.persist(user2);
    }
}
