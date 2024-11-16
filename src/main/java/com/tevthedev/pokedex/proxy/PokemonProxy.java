package com.tevthedev.pokedex.proxy;

import com.fasterxml.jackson.databind.JsonNode;
import com.tevthedev.pokedex.models.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="pokemonAPI", url = "https://pokeapi.co/api/v2/pokemon")
public interface PokemonProxy {

    @GetMapping("/{id}")
    JsonNode getPokemon(@PathVariable int id);
}
