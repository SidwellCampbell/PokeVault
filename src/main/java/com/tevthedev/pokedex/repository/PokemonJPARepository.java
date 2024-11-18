package com.tevthedev.pokedex.repository;

import com.tevthedev.pokedex.models.Pokemon;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PokemonJPARepository extends JpaRepository<Pokemon, Long> {


}
