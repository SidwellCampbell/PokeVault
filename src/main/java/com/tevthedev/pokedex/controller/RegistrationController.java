package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.RegistrationForm;
import com.tevthedev.pokedex.repository.UserRepository;
import com.tevthedev.pokedex.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegistrationForm() {
        return "register.html";
    }

    @PostMapping
    public String processRegistrationForm(@ModelAttribute RegistrationForm regForm) {
        userService.saveUser(regForm.process(passwordEncoder));
        return "redirect:/pokemon/all";
    }
}


