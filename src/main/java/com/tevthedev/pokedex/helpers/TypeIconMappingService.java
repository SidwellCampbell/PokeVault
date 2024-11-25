package com.tevthedev.pokedex.helpers;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.service.PokemonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TypeIconMappingService {
    private final PokemonService pokemonService;
    Logger logger = Logger.getLogger(TypeIconMappingService.class.getName());


    public TypeIconMappingService(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    public Map<String, String> getIcons() {
        logger.info("initialize Icons method called");
        List<Pokemon> pokemon = pokemonService.getAll();
        logger.info("Number of Pokemon retrieved: " + pokemon.size());

        List<String> types = Stream.concat(pokemon.stream().map(Pokemon::getPrimaryType),
                pokemon.stream().map(Pokemon::getSecondaryType)).distinct().filter(Objects::nonNull).toList();

        for (String type : types) {
            logger.info(type);
        }

        return types.stream().collect(Collectors.toMap(String::toLowerCase, type -> "/" + type + ".png"));

    }

}


