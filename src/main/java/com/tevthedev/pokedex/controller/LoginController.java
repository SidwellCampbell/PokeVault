package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.LoginForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/login")
@Controller()
public class LoginController {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public LoginController(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping
    public String getLoginForm(@RequestParam(required = false, value = "error") Boolean errorPresent, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/user/favorites";
        } else if (errorPresent == null) {
            return "login"; // Render the login.html view
        } else {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("Username or password is incorrect");
            model.addAttribute("errorPresent", true);
            model.addAttribute("errorMessages", errorMessages);
            return "login"; // Render the login.html view
        }

    }

}
