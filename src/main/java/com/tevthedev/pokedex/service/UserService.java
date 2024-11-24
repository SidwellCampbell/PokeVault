package com.tevthedev.pokedex.service;

import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.models.User;
import com.tevthedev.pokedex.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private UserRepository userRepository;
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(User user) {
        logger.info("user being saved to database from service method");
        userRepository.save(user);
    }

    public void addPokemonToUserFavorites(Pokemon pokemon, User user) {
        user.getListOfFavoritePokemon().add(pokemon);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }


    //    @Transactional
    public void addToFavesAndSave(String username, Pokemon poke) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        user.getListOfFavoritePokemon().add(poke);
        userRepository.save(user);
    }

    public void deletePokemon(String username, Pokemon pokemon) {
        var user = userRepository.findByUsernameIgnoreCase(username);
        logger.info("user found in user service delete method. user: " + user.getUsername());
        logger.info(" size of pokemon list before delete: " + user.getListOfFavoritePokemon().size());
        user.getListOfFavoritePokemon().removeIf(poke -> poke.getId() == pokemon.getId());
        logger.info(" size of pokemon list after delete: " + user.getListOfFavoritePokemon().size());
        userRepository.save(user);

    }
}
