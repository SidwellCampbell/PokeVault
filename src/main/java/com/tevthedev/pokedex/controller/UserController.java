package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.models.User;
import com.tevthedev.pokedex.service.PokemonService;
import com.tevthedev.pokedex.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PokemonService pokemonService;
    private final UserDetailsService userDetailsService;
    Logger logger = Logger.getLogger(UserController.class.getName());

    public UserController(UserService userService, PokemonService pokemonService, @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.userService = userService;
        this.pokemonService = pokemonService;
        this.userDetailsService = userDetailsService;
    }

//    @GetMapping("/all")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//
//    }

    @PostMapping("/{pokemonId}")
    public void savePokemonToListOfFaves(@AuthenticationPrincipal User user, @PathVariable Long pokemonId) {
        List<Pokemon> currentUserFaves = user.getListOfFavoritePokemon();
        Pokemon poke = pokemonService.findById(pokemonId);
        currentUserFaves.add(poke);

    }
}
