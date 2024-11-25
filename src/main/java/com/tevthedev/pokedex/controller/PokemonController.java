package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.helpers.TypeIconMappingService;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.proxy.PokemonProxy;
import com.tevthedev.pokedex.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping("/")
public class PokemonController {
    private final PokemonService pokemonService;
    private final TypeIconMappingService typeIconMappingService;

    public PokemonController(PokemonService pokemonService, TypeIconMappingService typeIconMappingService) {
        this.pokemonService = pokemonService;
        this.typeIconMappingService = typeIconMappingService;
    }


    @GetMapping
    public String getAllPokemon(@RequestParam(required = false) Integer page, Model model) {
        List<Pokemon> pokemonToDisplay = new ArrayList<>();
        pokemonToDisplay = pokemonService.getAllPokemonByPage(Objects.requireNonNullElse(page, 0));
        model.addAttribute("allPokemon", pokemonToDisplay);
        typeIconMappingService.getIcons();
        model.addAttribute("typeIcons", typeIconMappingService.getIcons());
        return "index";
    }


    @GetMapping("/pokemon/{id}")
    @ResponseBody
    public Pokemon getPokemon(@PathVariable Long id) {
        return pokemonService.findById(id);
    }


}


