package com.tevthedev.pokedex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.proxy.PokemonProxy;
import com.tevthedev.pokedex.repository.PokemonJPARepository;
import org.springframework.stereotype.Service;

import static com.tevthedev.pokedex.service.PokemonService.JsonToPokemonConverter;

@Service
public class DBInitializerService {

    private final PokemonService pokemonService;
    private final PokemonProxy pokemonProxy;

    public DBInitializerService(PokemonService pokemonService, PokemonProxy pokemonProxy) {
        this.pokemonService = pokemonService;
        this.pokemonProxy = pokemonProxy;
    }

    public void initializeDB() {
        int MAX_POKEMON = 1025;
        for (int i = 1; i <= MAX_POKEMON; i++) {
        JsonNode pokemonJsonNode = pokemonProxy.getPokemon(i);
        Pokemon newPokemon = JsonToPokemonConverter(pokemonJsonNode);
        pokemonService.savePokemon(newPokemon);
    }
    }



}
