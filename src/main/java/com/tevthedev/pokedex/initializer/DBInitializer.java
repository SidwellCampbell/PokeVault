package com.tevthedev.pokedex.initializer;

import com.tevthedev.pokedex.repository.PokemonJPARepository;
import com.tevthedev.pokedex.service.DBInitializerService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DBInitializer {

    private final DBInitializerService dbInitializerService;
    private final PokemonJPARepository pokemonJPARepository;
    private final Logger logger = Logger.getLogger(DBInitializer.class.getName());

    public DBInitializer(DBInitializerService dbInitializerService, PokemonJPARepository pokemonJPARepository) {
        this.dbInitializerService = dbInitializerService;
        this.pokemonJPARepository = pokemonJPARepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initializeDB() {
        if (pokemonJPARepository.count() != 1025) {
            dbInitializerService.initializeDB();
            logger.info("DB initialized for first time");
        } else {
            logger.info("DB already initialized");
        }
    }
}
