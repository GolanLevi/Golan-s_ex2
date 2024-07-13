package com.example.golans_ex2.Utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golans_ex2.Interfaces.Callback_LeaderboardClicked;
import com.example.golans_ex2.Models.Player;
import com.example.golans_ex2.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private final ArrayList<Player> players;
    private Callback_LeaderboardClicked callbackLeaderboardClicked;

    public PlayerAdapter(ArrayList<Player> players, Callback_LeaderboardClicked callbackListItemClicked) {
        this.players = players;
        this.callbackLeaderboardClicked = callbackLeaderboardClicked;
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_player_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = getItem(position);

        holder.high_score_name.setText(player.getName());
        holder.high_final_score.setText(String.valueOf(player.getFinalScore()));
        holder.high_score_name.setOnClickListener(v -> {
            if (callbackLeaderboardClicked != null) {
                callbackLeaderboardClicked.leaderboardClicked(player.getLat(), player.getLon());
            }
        });
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }

    public Player getItem(int position) {
        return players.get(position);
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView high_score_name;
        private final MaterialTextView high_final_score;


        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            high_score_name = itemView.findViewById(R.id.high_score_name);
            high_final_score = itemView.findViewById(R.id.high_final_score);
        }
    }
}
