package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.RegistrationForm;
import com.tevthedev.pokedex.repository.UserRepository;
import com.tevthedev.pokedex.service.RegistrationFormService;
import com.tevthedev.pokedex.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final Logger logger = Logger.getLogger(RegistrationController.class.getName());
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationFormService registrationFormService;


    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder, RegistrationFormService registrationFormService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.registrationFormService = registrationFormService;
    }


    @GetMapping
    public String getRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String processRegistrationForm(@ModelAttribute RegistrationForm regForm, Model model) {

        List<String> errorMessages = registrationFormService.validate(regForm);
        if (errorMessages.isEmpty()) {
            userService.saveUser(regForm.process(passwordEncoder));
            logger.info("new user saved in controller");
            return "redirect:/";
        }
        //if errors, redirect to registration page again, add in error messages
        model.addAttribute("errorMessages", errorMessages);
        model.addAttribute("formInvalid", true);
        return "register";
    }
}


