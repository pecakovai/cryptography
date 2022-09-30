package com.example.cryptography.service;


import com.example.cryptography.model.User;

import java.util.Optional;

public interface AuthService {

    User login(String username, String password);

    Optional<User> findByUsername(String username);

    void delete(String username);

    User update(String username, String usernameOfUser, String nameOfUser, String surnameOfUser, String emailOfUser);

}