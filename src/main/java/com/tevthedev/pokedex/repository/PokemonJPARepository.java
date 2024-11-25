package com.tevthedev.pokedex.repository;

import com.tevthedev.pokedex.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonJPARepository extends JpaRepository<Pokemon, Long> {


    Pokemon findPokemonByNameIgnoreCase(String name);

}
