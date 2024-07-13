package com.example.golans_ex2.Utilities;

import com.example.golans_ex2.Models.LeaderBoard;
import com.example.golans_ex2.Models.Player;

public class DataGenerator {
    public static LeaderBoard genPlayerList() {

        LeaderBoard leaderBoard = new LeaderBoard();

        leaderBoard.setName("Best scores!")
                .addPlayer(
                        new Player()
                                .setName("Golan")
                                .setFinalScore(600)
                                .setLat(40.712776)
                                .setLon(-74.005974)
                ).addPlayer(
                        new Player()
                                .setName("Melinda")
                                .setFinalScore(100)
                                .setLat(37.774929)
                                .setLon(-122.419416)
                ).addPlayer(
                        new Player()
                                .setName("Almog")
                                .setFinalScore(2000)
                                .setLat(34.052235)
                                .setLon(-118.243683)
                ).addPlayer(
                        new Player()
                                .setName("Michal")
                                .setFinalScore(1000)
                                .setLat(40.712776)
                                .setLon(-74.005974)
                ).addPlayer(
                        new Player()
                                .setName("Dana")
                                .setFinalScore(800)
                                .setLat(32.15)
                                .setLon(34.883333)
                );
        return leaderBoard;
    }
}

