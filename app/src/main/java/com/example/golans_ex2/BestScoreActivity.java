package com.example.golans_ex2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.golans_ex2.Fragments.LeaderboardFragment;
import com.example.golans_ex2.Fragments.MapFragment;
import com.google.android.material.button.MaterialButton;

public class BestScoreActivity extends AppCompatActivity {

    private FrameLayout main_FRAME_list;
    private FrameLayout main_FRAME_map;
    private MaterialButton back_to_main_btn;

    private LeaderboardFragment leaderboardFragment;
    private MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestscore);
        findViews();
        initViews();

    }

    private void findViews() {
        main_FRAME_list = findViewById(R.id.main_FRAME_list);
        main_FRAME_map = findViewById(R.id.main_FRAME_map);
        back_to_main_btn = findViewById(R.id.back_to_main_btn);
    }

    private void initViews() {
        leaderboardFragment = new LeaderboardFragment();
        leaderboardFragment.setCallbackLeaderboardClicked((lat, lon) -> {
            if (mapFragment!=null){
                mapFragment.moveMapToLocation(lat, lon);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_list, leaderboardFragment).commit();
        mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mapFragment).commit();
        back_to_main_btn.setOnClickListener(v -> backToStartGame());
    }

    private void backToStartGame() {
        Intent intent = new Intent(this, StartGameActivity.class);
        startActivity(intent);
        finish();
    }
}
