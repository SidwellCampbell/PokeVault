package com.tevthedev.pokedex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.repository.PokemonJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonJPARepository pokemonJPARepository;

    public PokemonService(PokemonJPARepository pokemonJPARepository) {
        this.pokemonJPARepository = pokemonJPARepository;
    }

    public void savePokemon(Pokemon pokemon) {
        // Save the pokemon to the database
        pokemonJPARepository.save(pokemon);

    }


    public Pokemon findById(Long id) {
        return pokemonJPARepository.findById(id).orElseThrow(PokemonNotFoundException::new);
    }

    public List<Pokemon> getAllPokemonByPage(int page) {
        Pageable pageable = PageRequest.of(page, 50);
        return pokemonJPARepository.findAll(pageable).getContent();
    }

    public static Pokemon JsonToPokemonConverter(JsonNode jsonNode) {
        Pokemon pokemon = new Pokemon();

        pokemon.setId(jsonNode.get("id").asLong());
        pokemon.setName(jsonNode.get("name").asText());

        /* If the Pokemon only has one type, set it as the primary type.
        Otherwise, set the first type as the primary type and the second type as the secondary type. */
        if (jsonNode.get("types").size() == 1) {
            pokemon.setPrimaryType(jsonNode.get("types").get(0).get("type").get("name").asText());
        } else {
            pokemon.setPrimaryType(jsonNode.get("types").get(0).get("type").get("name").asText());
            pokemon.setSecondaryType(jsonNode.get("types").get(1).get("type").get("name").asText());
        }

        pokemon.setHP(jsonNode.get("stats").get(0).get("base_stat").asInt());

        pokemon.setAttack(jsonNode.get("stats").get(1).get("base_stat").asInt());

        pokemon.setDefense(jsonNode.get("stats").get(2).get("base_stat").asInt());

        pokemon.setSprite(jsonNode
                .get("sprites")
                .get("other")
                .get("official-artwork")
                .get("front_default").asText());
        return pokemon;
    }


}
