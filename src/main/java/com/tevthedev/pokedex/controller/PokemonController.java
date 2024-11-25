package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.proxy.PokemonProxy;
import com.tevthedev.pokedex.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/")
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonProxy pokemonProxy, PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping
    public String getAllPokemon(@RequestParam(required = false) Integer page, Model model) {
        List<Pokemon> allPokemon = pokemonService.getAllPokemonByPage(Objects.requireNonNullElse(page, 0));
        model.addAttribute("allPokemon", allPokemon);
        return "index";
    }


    @GetMapping("/pokemon/{id}")
    @ResponseBody
    public Pokemon getPokemon(@PathVariable Long id) {
        return pokemonService.findById(id);
    }


}


