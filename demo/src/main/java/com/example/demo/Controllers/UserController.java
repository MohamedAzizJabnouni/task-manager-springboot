package com.example.demo.Controllers;

import com.example.demo.Models.Role;
import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin")
    public void createAdmin(String name, String email, String password, Set<Task> tasks){
        userService.createAdminUser(name, email, password, tasks);
    }

    @PostMapping("/regularUser")
    public void createUser(String name, String email, String password, Set<Task> tasks){
        userService.createRegularUser(name, email, password, tasks);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(Long id){
        userService.DeleteUser(id);
    }


}
