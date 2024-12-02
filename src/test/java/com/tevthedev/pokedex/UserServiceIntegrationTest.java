package com.tevthedev.pokedex;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.models.User;
import com.tevthedev.pokedex.repository.PokemonJPARepository;
import com.tevthedev.pokedex.service.PokemonService;
import com.tevthedev.pokedex.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceIntegrationTest {


    @Autowired
    private UserService userService;

    @Autowired
    private PokemonJPARepository pokemonJPARepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User("Testuser10", "password", "testuser1@email.com");
        userService.saveUser(user);
        User foundUser = userService.findByUsername("Testuser10");
        assertEquals(user, foundUser);
    }

    @Test
    void testSaveAndFindUserIgnoresCase() {
        User user = new User("TESTUSER10", "password", "feafrsgver");
        userService.saveUser(user);
        User foundUser = userService.findByUsername("TESTUSER10");
        assertEquals(user, foundUser);
    }

    @Test
    void findNonExistantUser() {
        User user = userService.findByUsername("nonexistantuser");
        assertNull(user);
    }


    @Test
    void testGetAllUsers() {
        for (int i = 2; i < 5; i++) {
            User user = new User();
            user.setUsername("Testuser" + i);
            user.setPassword("password");
            user.setEmail("testuser" + i + "@email.com");
            userService.saveUser(user);
        }
        assertEquals(3, userService.getAllUsers().size());
    }


    @Test
    void deletePokemonFromUserFavorites() {
        User user = new User("Testuser10", "password", "testuser1@email.com");
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setId(1L);
        pokemonJPARepository.save(pokemon1);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setId(2L);
        pokemonJPARepository.save(pokemon2);

        user.getListOfFavoritePokemon().add(pokemon1);
        user.getListOfFavoritePokemon().add(pokemon2);

        userService.saveUser(user);
        userService.deletePokemon("Testuser10", pokemon1);

        User updatedUser = userService.findByUsername("Testuser10");
        assertEquals(1, updatedUser.getListOfFavoritePokemon().size());
        assertEquals(pokemon2, updatedUser.getListOfFavoritePokemon().getFirst());
    }

    @Test
    void deleteNonExistingPokemonFromUserFavorites() {
        User user = new User("Testuser10", "password", "testuser1@email.com");
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setId(1L);
        pokemonJPARepository.save(pokemon1);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setId(2L);
        pokemonJPARepository.save(pokemon2);

        user.getListOfFavoritePokemon().add(pokemon1);

        userService.saveUser(user);

        userService.deletePokemon("Testuser10", pokemon2);

        User updatedUser = userService.findByUsername("Testuser10");
        assertEquals(1, updatedUser.getListOfFavoritePokemon().size());
        assertEquals(pokemon1, updatedUser.getListOfFavoritePokemon().getFirst());
    }
}
