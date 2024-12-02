package com.tevthedev.pokedex.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "PokemonDB")
public class Pokemon {

    @Id
    private long id;

    //    @Column(nullable = false)
    private String name;

    //    @Column(nullable = false)
    private String primaryType;


    private String secondaryType;

    //    @Column(nullable = false)
    private int HP;

    //    @Column(nullable = false)
    private int attack;

    //    @Column(nullable = false)
    private int defense;

    //    @Column(nullable = false)
    private String sprite;

    @ManyToMany(mappedBy = "ListOfFavoritePokemon")
    @JsonIgnore
    @Transient
    private List<User> favoritedBy;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pokemon pokemon)) return false;
        return getId() == pokemon.getId() && Objects.equals(getName(), pokemon.getName());
    }

}