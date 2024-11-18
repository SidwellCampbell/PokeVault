package com.tevthedev.pokedex.service;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException() {
        super("Pokemon not found");
    }
}
