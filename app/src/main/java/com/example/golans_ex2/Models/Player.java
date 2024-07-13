package com.example.golans_ex2.Models;

public class Player {

    private String name = " ";
    private int FinalScore;
    private double lat;
    private double lon;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public int getFinalScore() {
        return FinalScore;
    }

    public Player setFinalScore(int finalScore) {
        FinalScore = finalScore;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Player setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Player setLon(double lon) {
        this.lon = lon;
        return this;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", FinalScore=" + FinalScore +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

