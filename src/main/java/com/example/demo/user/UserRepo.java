package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This class to use SQL query by extending JpaRepository class
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    //  SELECT * FORM user WHERE email = ?
    Optional<User> findUserByEmail(String email);

    //  SELECT * FORM user WHERE phone_number = ?
    Optional<User> findUserByPhoneNumber(long phoneNumber);

}
