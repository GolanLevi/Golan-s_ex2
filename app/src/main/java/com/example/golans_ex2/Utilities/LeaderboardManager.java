
package com.example.golans_ex2.Utilities;

import com.example.golans_ex2.Models.LeaderBoard;
import com.example.golans_ex2.Models.Player;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardManager {

    public static LeaderBoard useLeaderboard() {
        Gson gson = new Gson();
        String leaderBoardAsJson = SharePreferencesManager.getInstance().getString("leaderboard", " ");
        LeaderBoard leaderBoard = gson.fromJson(leaderBoardAsJson, LeaderBoard.class);
        if (leaderBoard == null) {
            leaderBoard = new LeaderBoard();
        } else {
            sortPlayersInLeaderboard(leaderBoard);
        }
        return leaderBoard;
    }

    public static void sortPlayersInLeaderboard(LeaderBoard leaderBoard) {
        ArrayList<Player> players = leaderBoard.getPlayersList();
        Collections.sort(players, (player1, player2) -> Integer.compare(player2.getFinalScore(), player1.getFinalScore()));
        if (players.size() > 10) {
            players = new ArrayList<>(players.subList(0, 10));
        }
        leaderBoard.setPlayersList(players);
    }

    public static void saveLeaderBoard(LeaderBoard leaderBoard) {
        Gson gson = new Gson();
        sortPlayersInLeaderboard(leaderBoard);
        String leaderBoardAsJson = gson.toJson(leaderBoard);

        SharePreferencesManager.getInstance().putString("leaderboard", leaderBoardAsJson);
    }
}

