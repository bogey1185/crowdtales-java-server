package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        return userRepository.findAll();
    }

    // GET SPECIFIC USER BY ID //

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) throws Exception {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();

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
            return userService.saveUser(user);

        } else throw new Exception("Passwords do not match");
    }

    // UPDATE USER //

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Integer id) throws Exception {

        Optional<User> originalUser = userRepository.findById(id);
        if (originalUser.isPresent()) {
            User userToUpdate = originalUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            return userRepository.save(userToUpdate);

        } else throw new Exception("User not found");
    }

    // DELETE USER //

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    // LOGIN USER //

    @PostMapping("/login")
    public User login(@RequestBody User loginUser, HttpSession session) throws IOException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userService.findUserByUsername(loginUser.getUsername());
        if (user == null) {
            throw new IOException("Invalid credentials");
        }
        boolean passwordIsValid = bCryptPasswordEncoder.matches(loginUser.getPassword(), user.getPassword());
        if (passwordIsValid) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", user.getId());
            return user;
        } else throw new IOException("Invalid credentials");
    }




}
