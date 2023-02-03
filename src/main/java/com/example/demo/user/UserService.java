package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This class is for writing Business Logic
@Service
public class UserService {

    private final UserRepo repository;

    @Autowired
    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    // Method that return a list of data form DB
    public List<User> getUsers() {
        return repository.findAll();
    }

    // Method that check if email or phone is duplicated in DB or not and add new user if not duplicated
    public void addUser(User user) {

        Optional<User> userEmail = repository.findUserByEmail(user.getEmail());
        Optional<User> userByPhoneNumber = repository.findUserByPhoneNumber(user.getPhoneNumber());

        if (userEmail.isPresent() || userByPhoneNumber.isPresent()) {
            throw new IllegalStateException("email or phone number is already inserted into DB");
        }

        repository.save(user);

    }

    // Method that check if id is exists in DB or not and delete user if exists
    public void deleteUser(int userId) {

        if (!repository.existsById(userId)) {
            throw new IllegalStateException("user with id " + userId + " not exists");
        }
        repository.deleteById(userId);
    }

    /*
     * Method that check if id is exists in DB or not and update user email or password if exists
     * And also check if email or password that user inserted is null or not
     * And if check newEmail or newPassword is same the oldEmail or oldPassword
     * And check length of them != 0
     *
     * */
    @Transactional
    public void updateUser(int userId, String userEmail, String userPassword) {

        User user = repository.findById(userId).orElseThrow(() ->
                new IllegalStateException("user with id " + userId + " not exists")
        );

        if (userEmail != null && userEmail.length() > 0 && !userPassword.equals(user.getPassword())) {
            user.setEmail(userEmail);
        }

        if (userPassword != null && userPassword.length() > 0 && !userPassword.equals(user.getPassword())) {
            user.setPassword(userPassword);
        }
    }
}

