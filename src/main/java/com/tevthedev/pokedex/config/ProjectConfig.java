package com.tevthedev.pokedex.config;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.service.PokemonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableFeignClients(basePackages = "com.tevthedev.pokedex.proxy")
public class ProjectConfig {


//    @Bean
//    public Map<String, String> typeIconMap(PokemonService pokemonService) {
//        List<Pokemon> pokemon = pokemonService.getAll();
//
//        List<String> types = Stream.concat(pokemon.stream().map(Pokemon::getPrimaryType),
//                pokemon.stream().map(Pokemon::getSecondaryType)).distinct().toList();
//
//        Map<String, String> typeIconMap =
//                types.stream().collect(Collectors.toMap(StringUtils::capitalize, type -> "/" + type + ".png"));
//
//        return typeIconMap;
//
//    }
}

