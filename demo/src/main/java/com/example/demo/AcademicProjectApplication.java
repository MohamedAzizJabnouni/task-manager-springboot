package com.example.demo;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AcademicProjectApplication implements CommandLineRunner, RepositoryRestConfigurer{

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AcademicProjectApplication.class, args);
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		config.exposeIdsFor(Task.class,User.class);
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Check and create users only if they don't already exist
		createUserIfNotExists("Azizz", "aziz12@gmail.com", "azerty", Set.of(Role.ROLE_USER));
		createUserIfNotExists("Aziz", "aziz11@gmail.com", "azerty", Set.of(Role.ROLE_ADMIN));
		createUserIfNotExists("Ahmed", "Ahmed11@gmail.com", "azerty", Set.of(Role.ROLE_USER));

		// tasks
		Task task1 = new Task("Write Unit Tests", "Write unit tests for the UserService class using JUnit and Mockito.");
		Task task2 = new Task("Write Integration Tests", "Write unit tests for the UserService class using JUnit and Mockito.");
		taskRepository.save(task1);
		taskRepository.save(task2);

		// associate tasks with users
		User user1 = userRepository.findByEmail("aziz11@gmail.com").orElseThrow(() -> new RuntimeException("User not found"));
		User user2 = userRepository.findByEmail("aziz12@gmail.com").orElseThrow(() -> new RuntimeException("User not found"));
		User user3 = userRepository.findByEmail("Ahmed11@gmail.com").orElseThrow(() -> new RuntimeException("User not found"));

		Set<Task> tasks1 = new HashSet<>(Set.of(task1));
		Set<Task> tasks2 = new HashSet<>();
		tasks2.add(task1);
		tasks2.add(task2);
		user1.setTasks(tasks1);
		user2.setTasks(tasks2);
		user3.setTasks(tasks2);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}

	// Method to create user if it does not exist
	private void createUserIfNotExists(String name, String email, String password, Set<Role> roles) {
		if (!userRepository.findByEmail(email).isPresent()) {
			User newUser = new User(name, email, password, new HashSet<>(), new HashSet<>());
			newUser.setRoles(roles);
			userRepository.save(newUser);
		} else {
			System.out.println("User with email " + email + " already exists.");
		}
	}
}

