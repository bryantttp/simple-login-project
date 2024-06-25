package com.user.loginapplication.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.loginapplication.model.User;
import com.user.loginapplication.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    private static Logger logger = LogManager.getLogger(UserService.class);
    
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    // Create
    public void persist(User user) {
		Optional<User> returnedUser = userRepo.findByUsername(user.getUsername());
		if (returnedUser.isEmpty()) {
			userRepo.save(user);
			logger.info("User successfully registered");
		} else {
			logger.warn("User already exists");
		}
	}

    // Read
    public User findUserById(long customerId) {
		Optional<User> returnedUser = userRepo.findById(customerId);
		if (returnedUser.isEmpty()) {
			logger.warn("Could not find User in Database");
			return null;
		} else {
			logger.info("Returning user's details");
			return returnedUser.get();
		}
	}

    // Update
    public void update(User user) {
		Optional<User> returnedUser = userRepo.findByUsername(user.getUsername());
		if (returnedUser.isEmpty()) {
			logger.warn("User does not exist in database");
		} else {
			userRepo.save(user);
			logger.info("User successfully updated");
		}
	}

    // Delete
    public void deleteById(long userId) {
		Optional<User> returnedUser = userRepo.findById(userId);
		if (returnedUser.isEmpty()) {
			logger.warn("User does not exist in database");
		} else {
			userRepo.deleteById(userId);
			logger.info("User deleted from Database");
		}
	}
}   
