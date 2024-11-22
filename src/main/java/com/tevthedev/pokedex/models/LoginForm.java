package com.tevthedev.pokedex.models;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class LoginForm {

    private String username;
    private String password;

}
