package com.example.restmyfirstproject.repos;

import com.example.restmyfirstproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
