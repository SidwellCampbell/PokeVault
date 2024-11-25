package com.tevthedev.pokedex.helpers;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.service.PokemonService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TypeIconMappingService {
    private final PokemonService pokemonService;


    public TypeIconMappingService(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    public Map<String, String> getIcons() {
        List<Pokemon> pokemon = pokemonService.getAll();

        List<String> types = Stream.concat(
                pokemon.stream().map(Pokemon::getPrimaryType),
                pokemon.stream().map(Pokemon::getSecondaryType)
        ).distinct().filter(Objects::nonNull).toList();

        return types.stream().collect(Collectors.toMap(String::toLowerCase, type -> "/" + type + ".png"));

    }

}


