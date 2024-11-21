package com.tevthedev.pokedex.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.proxy.PokemonProxy;
import com.tevthedev.pokedex.service.PokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.tevthedev.pokedex.service.PokemonService.JsonToPokemonConverter;


@RestController
@RequestMapping("/")
public class PokemonController {

    private final PokemonProxy pokemonProxy;
    private final PokemonService pokemonService;

    public PokemonController(PokemonProxy pokemonProxy, PokemonService pokemonService) {
        this.pokemonProxy = pokemonProxy;
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemon(@RequestParam(required = false) Integer page) {
        return pokemonService.getAllPokemonByPage(Objects.requireNonNullElse(page, 0));
    }


    @GetMapping("/pokemon/{id}")
    @ResponseBody
    public Pokemon getPokemon(@PathVariable Long id) {
        return pokemonService.findById(id);
    }


}


