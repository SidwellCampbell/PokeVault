package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.tevthedev.pokedex.models.Pokemon;

public class HelperMethods {

    public static Pokemon JsonToPokemonConverter(JsonNode jsonNode) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(jsonNode.get("name").asText());
        for (JsonNode typeJsonNode: jsonNode.get("types")) {
            pokemon.setTypes(typeJsonNode.get("type").get("name").asText());
        }
        pokemon.setHP(jsonNode.get("stats").get(0).get("base_stat").asInt());
        pokemon.setAttack(jsonNode.get("stats").get(1).get("base_stat").asInt());
        pokemon.setDefense(jsonNode.get("stats").get(2).get("base_stat").asInt());


        pokemon.setSprite(jsonNode
                .get("sprites")
                .get("other")
                .get("official-artwork")
                .get("front_default").asText());
        return pokemon;
    }
}
