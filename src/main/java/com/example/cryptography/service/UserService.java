
package com.example.cryptography.service;


import com.example.cryptography.model.User;
import com.example.cryptography.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, String email, String string, Role role);
    User findByUsername(String username);
}

