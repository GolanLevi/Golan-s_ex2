package com.example.golans_ex2.Models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class LeaderBoard {

    private String name = " ";
    private ArrayList<Player> playersList;

    public LeaderBoard() {
        this.playersList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public  LeaderBoard setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public LeaderBoard setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
        return this;
    }

    public LeaderBoard addPlayer(Player player) {
        this.playersList.add(player);
        return this;
    }

    @Override
    public String toString() {
        return "LeaderBoard{" +
                "name='" + name + '\'' +
                ", playersList=" + playersList +
                '}';
    }
}
