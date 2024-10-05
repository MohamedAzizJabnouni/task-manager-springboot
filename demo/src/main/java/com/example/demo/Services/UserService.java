package com.example.demo.Services;

import com.example.demo.Models.Role;
import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createAdminUser(String name, String email, String password, Set<Task> tasks){
        User adminUser = new User(name, email, password, Set.of(Role.ROLE_ADMIN), tasks);
        userRepository.save(adminUser);
    }

    public void createRegularUser(String name, String email, String password, Set<Task> tasks){
        User regularUser = new User(name, email, password, Set.of(Role.ROLE_USER), tasks);
        userRepository.save(regularUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getAllAdmins(){
        return userRepository.findAllByRole(Role.ROLE_ADMIN);
    }

    public void DeleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
