package com.example.cryptography.web;


import com.example.cryptography.model.enumerations.Role;
import com.example.cryptography.model.exceptions.*;
import com.example.cryptography.service.AuthService;
import com.example.cryptography.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String repeatedPassword,
                                     @RequestParam String name,
                                     @RequestParam String surname,
                                     @RequestParam String email,
                                     @RequestParam String genre,

                                     Model model) {
        try {
            System.out.printf("%s %s %s %s %s %s %s",username,password,repeatedPassword,name,surname,email,genre);
            this.userService.register(username, password, repeatedPassword, name, surname,email, genre,Role.ROLE_USER);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | EmailAlreadyExistsException |
                 InvalidEmailException | UsernameAlreadyExistsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
