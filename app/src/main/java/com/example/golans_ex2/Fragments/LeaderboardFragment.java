package com.example.golans_ex2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golans_ex2.Interfaces.Callback_LeaderboardClicked;
import com.example.golans_ex2.Models.LeaderBoard;
import com.example.golans_ex2.Models.Player;
import com.example.golans_ex2.R;
import com.example.golans_ex2.Utilities.LeaderboardManager;
import com.example.golans_ex2.Utilities.PlayerAdapter;

public class LeaderboardFragment extends Fragment {

    private RecyclerView main_LB_players;
    private LeaderBoard leaderBoard;
    private Callback_LeaderboardClicked callbackLeaderboardClicked;

    public LeaderboardFragment() {
    }

    public void setCallbackLeaderboardClicked(Callback_LeaderboardClicked callbackLeaderboardClicked) {
        this.callbackLeaderboardClicked = callbackLeaderboardClicked;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        findViews(v);
        initViews();
        return v;
    }

    private void initViews() {
        leaderBoard = LeaderboardManager.useLeaderboard();
        PlayerAdapter playerAdapter = new PlayerAdapter(leaderBoard.getPlayersList(), callbackLeaderboardClicked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_LB_players.setLayoutManager(linearLayoutManager);
        main_LB_players.setAdapter(playerAdapter);
    }

    private void onLeaderboardItemClicked(Player player) {
        if (callbackLeaderboardClicked != null) {
            callbackLeaderboardClicked.leaderboardClicked(player.getLat(), player.getLon());
        }
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    private void findViews(View v) {
        main_LB_players = v.findViewById(R.id.main_LB_players);
    }
}
