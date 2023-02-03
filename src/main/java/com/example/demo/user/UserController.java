package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is for control of API routing
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // GET all users in DB
    @GetMapping
    public List<User> users() {
        return service.getUsers();
    }

    // POST new user to DB
    @PostMapping
    public void addNewUser(@RequestBody User user) {
        service.addUser(user);
    }

    // DELETE user from DB
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        service.deleteUser(userId);
    }

    // UPDATE user from DB
    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") int userId,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password) {
        service.updateUser(userId, email, password);
    }
}
