package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.models.User;
import com.tevthedev.pokedex.service.PokemonService;
import com.tevthedev.pokedex.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PokemonService pokemonService;
    Logger logger = Logger.getLogger(UserController.class.getName());

    public UserController(UserService userService, PokemonService pokemonService, @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.userService = userService;
        this.pokemonService = pokemonService;
    }

    @GetMapping("/favorites")
    public String getFavorites(@AuthenticationPrincipal User user, Model model) {
        List<Pokemon> faves = user.getListOfFavoritePokemon();
        model.addAttribute("userFaves", faves);
        return "userFavorites";
    }

    @PostMapping("/{pokemonId}")
    public String savePokemonToListOfFaves(@AuthenticationPrincipal User user, @PathVariable Long pokemonId) {
        Pokemon poke = pokemonService.findById(pokemonId);
        userService.addToFavesAndSave(user.getUsername(), poke);
//        userService.saveUser(user);
        return "redirect:/user/favorites";

    }

//    TODO
//    ADD IN DELETE METHOD;


}
