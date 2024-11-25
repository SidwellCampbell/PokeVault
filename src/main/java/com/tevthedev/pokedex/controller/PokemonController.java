package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.helpers.TypeIconMappingService;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String getAllPokemon(@RequestParam(required = false, name = "page") Integer pageNumber, Model model) {
        List<Pokemon> pokemonToDisplay;
        pokemonToDisplay = pokemonService.getAllPokemonByPage(Objects.requireNonNullElse(pageNumber, 0));
        model.addAttribute("allPokemon", pokemonToDisplay);
        model.addAttribute("typeIcons", typeIconMappingService.getIcons());
        model.addAttribute("totalPages", pokemonService.getTotalPages());
        return "index";
    }


    @GetMapping("/pokemon/{id}")
    @ResponseBody
    public Pokemon getPokemon(@PathVariable Long id) {
        return pokemonService.findById(id);
    }


}


