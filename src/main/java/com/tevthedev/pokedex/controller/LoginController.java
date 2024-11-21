package com.tevthedev.pokedex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login")
@Controller()
public class LoginController {

//    @GetMapping
//    public String login() {
//        return "login.html";
//    }


    @GetMapping
    public String loginProcess(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "fatal message here");
        }
        return "login.html"; // Render the login.html view
    }
}
