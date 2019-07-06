package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // GET ALL USERS //

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    // GET SPECIFIC USER BY ID //

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) throws Exception {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            return user;

        } else throw new Exception("User not found");
    }

    // REGISTER USER //

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        String passwordOne = user.getPassword();
        String passwordTwo = user.getPassword2();

        if (passwordOne.equals(passwordTwo)) {
            user.setEmail(user.getEmail().toLowerCase());
            user.setPassword2AsNull();
            User createdUser = userService.saveUser(user);
            return createdUser;

        } else throw new Exception("Passwords do not match");
    }

}
