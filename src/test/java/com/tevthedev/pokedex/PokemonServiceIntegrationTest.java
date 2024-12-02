package com.tevthedev.pokedex;


import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.service.PokemonService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class PokemonServiceIntegrationTest {

    @Autowired
    PokemonService pokemonService;

    private static Pokemon pikachu = new Pokemon();
    private static Pokemon charmander = new Pokemon();


    @Test
    void testSavePokemon() {
        pokemonService.savePokemon(pikachu);
        assertEquals(pokemonService.findById(2L), pikachu);
    }

    @Test
    void testfindById() {
        pokemonService.savePokemon(pikachu);
        pokemonService.savePokemon(charmander);
        assertEquals(pokemonService.findById(3L), charmander);
    }

    @Test
    void testfindByIdDoesntExist() {
        pokemonService.savePokemon(pikachu);
        pokemonService.savePokemon(charmander);
        assertNull(pokemonService.findById(100L));
    }

    @Test
    void testFindByName() {
        pokemonService.savePokemon(pikachu);
        assertEquals(pikachu, pokemonService.findByName("pikachu"));
    }

    @Test
    void testFindByNameIgnoreCase() {
        pokemonService.savePokemon(pikachu);
        assertEquals(pikachu, pokemonService.findByName("PiKaChU"));
    }

    @Test
    void testGetPokemonByPage() {
        for (int i = 0; i < 55; i++) {
            Pokemon poke = new Pokemon();
            poke.setId(i);
            poke.setName("Poke" + i);
            pokemonService.savePokemon(poke);
        }
        var pokePage1 = pokemonService.getAllPokemonByPage(0);
        var pokePage2 = pokemonService.getAllPokemonByPage(1);
        assertEquals(50, pokePage1.size());
        assertEquals(5, pokePage2.size());
    }

    @Test
    void testGetAll() {
        pokemonService.savePokemon(pikachu);
        pokemonService.savePokemon(charmander);
        assertEquals(2, pokemonService.getAll().size());
    }


    @BeforeAll
    static void setUp() {
        pikachu.setId(2L);
        pikachu.setName("Pikachu");
        pikachu.setPrimaryType("Electric");
        pikachu.setSecondaryType("None");
        pikachu.setHP(35);
        pikachu.setAttack(55);
        pikachu.setDefense(40);
        pikachu.setSprite("https://example.com/sprite.png");

        charmander.setId(3L);
        charmander.setName("Charmander");
        charmander.setPrimaryType("Fire");
        charmander.setSecondaryType("None");
        charmander.setHP(99);
        charmander.setAttack(99);
        charmander.setDefense(99);
        charmander.setSprite("https://example.com/sprite.png");

    }
}
