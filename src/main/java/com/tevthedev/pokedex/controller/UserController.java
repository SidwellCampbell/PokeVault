package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.models.User;
import com.tevthedev.pokedex.service.PokemonService;
import com.tevthedev.pokedex.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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

    //    @Transactional
    @GetMapping("/favorites")
    public String getFavorites(@AuthenticationPrincipal User user, Model model) {
        user = userService.findByUsername(user.getUsername());
        model.addAttribute("favoritePokemon", user.getListOfFavoritePokemon());
        return "userFavorites";
    }

    //    @Transactional
    @PostMapping("/favorites")
    public String savePokemonToListOfFaves(@AuthenticationPrincipal User user,
                                           @RequestParam(required = false, name = "_method", defaultValue = "POST") String method,
                                           @RequestParam("pokemonId") Long pokemonId, Model model) {


        var pokemon = pokemonService.findById(pokemonId);

        if (method.equals("DELETE")) {
            userService.deletePokemon(user.getUsername(), pokemon);
        } else {
            userService.addToFavesAndSave(user.getUsername(), pokemon);
        }

        return "redirect:/user/favorites";

    }

}
