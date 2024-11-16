package com.tevthedev.pokedex.models;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private List<String> types = new ArrayList<>();
    private int HP;
    private int attack;
    private int defense;
    private String sprite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(String type) {
        this.types.add(type);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
