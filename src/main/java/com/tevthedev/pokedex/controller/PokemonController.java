package com.tevthedev.pokedex.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.proxy.PokemonProxy;
import helpers.HelperMethods;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    private final PokemonProxy pokemonProxy;

    public PokemonController(PokemonProxy pokemonProxy) {
        this.pokemonProxy = pokemonProxy;
    }

    @GetMapping("/pokemon/{id}")
    @ResponseBody
    public Pokemon getPokemon(@PathVariable int id) {
        JsonNode pokemonJsonNode = pokemonProxy.getPokemon(id);
        Pokemon newPokemon = HelperMethods.JsonToPokemonConverter(pokemonJsonNode);
        return newPokemon;
    }
}
