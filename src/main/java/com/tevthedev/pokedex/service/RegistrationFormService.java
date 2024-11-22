package com.tevthedev.pokedex.service;

import com.tevthedev.pokedex.controller.RegistrationController;
import com.tevthedev.pokedex.models.RegistrationForm;
import com.tevthedev.pokedex.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RegistrationFormService {

    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(RegistrationFormService.class.getName());


    public RegistrationFormService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> validate(RegistrationForm form) {

        // init variable to determine whether any errors occured + list to hold error messages
        List<String> errorMessages = new ArrayList<>();

        //check to see if username is currently taken
        UserDetails user = userRepository.findByUsernameIgnoreCase(form.getUsername());
        if (user != null) {
            errorMessages.add("Username is already taken");

            //check to see if passwords
        } else if (!form.passwordsMatch()) {
            errorMessages.add("Passwords do not match");


        }
        return errorMessages;
    }
}
