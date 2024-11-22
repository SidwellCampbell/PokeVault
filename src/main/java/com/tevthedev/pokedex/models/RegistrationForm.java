package com.tevthedev.pokedex.models;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String confirm;
    private String email;

    public User process(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), email);
    }

    public boolean passwordsMatch() {
        return password.equals(confirm);
    }

}
