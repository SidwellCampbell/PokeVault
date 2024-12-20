package com.tevthedev.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokedexApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

    // TODO FUNCTIONAL
    //     ADD IN DELETE METHOD IN USER CONTROLLER ✅
    //     COMPLETE USER FAVORITES PAGE W/ DELETE BUTTON ON EACH CARD ✅
    //     SEARCH FUNCTIONALITY ✅
    //     PAGINATION LINKS TO VIEW ALL PAGES IN DB ✅
    //     SORT FAVORITES RETURNED BY NAME. ✅
    //     ADD LOGIC TO AVOID DUPLICATES IN FAVES ✅
    //     CORRECT LOGOUT FUNCTIONALITY ✅
    //     ADD FAVORITES LINK TO NAV BAR ✅
    //     REDIRECT LOGGED IN USERS TO "FAVES" WHEN VISITING "LOGIN" ✅
    //     REMOVE SIGN UP BUTTON FOR LOGGED IN USERS + POSSIBLY REMOVE LOGIN BUTTON ✅
    //     ADD IN SEARCH VIEW ✅


    // TODO VISUAL
    //    ADD IN TITLE ICON ✅
    //    ADD ICON TO SAVE BUTTON ✅
    //    ADD IN POKEMON TYPES ✅
    //    FIX ISSUES WITH RESPONSIVE DESIGN(CARDS LOOK WEIRD ON MEDIUM DISPLAY*MAYBE FONT CHANGE*. //ALSO TOO WIDE ON SMALL DISPLAY*COL-12*)


    //TODO TEST
    //    ADD IN TESTS FOR USER SERVICE(INTEGRATED) ✅
    //    ADD IN TESTS FOR REGISTRATION FORM SERVICE(INT + UNIT)


}
