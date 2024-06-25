package com.user.loginapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.loginapplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    @Query("SELECT u FROM User u WHERE u.username = :userUsername")
    Optional<User> findByUsername(@Param("userUsername") String username);
}
