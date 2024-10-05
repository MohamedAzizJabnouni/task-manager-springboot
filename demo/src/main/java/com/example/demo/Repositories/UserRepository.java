package com.example.demo.Repositories;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // retrieve all users with a specific role
    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.roles")
    List<User> findAllByRole(Role role);

    Optional<User> findByEmail(String email);
}
