package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/users")
    public String getUsers() { return "HELLO WORLD"; }

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
