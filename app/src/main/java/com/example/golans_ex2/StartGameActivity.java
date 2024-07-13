package com.example.golans_ex2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class StartGameActivity extends AppCompatActivity {

    public MaterialTextView start_game_TEXT;
    private MaterialButton start_BTN_LeaderBoard;
    private MaterialButton start_BTN_SensorMode;
    private MaterialButton start_BTN_ButtonMode;

      public static final String KEY_STATUS = "KEY_STATUS";
      public static final String KEY_SCORE = "KEY_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findView();
        initView();
    }


    private void findView() {
        start_game_TEXT = findViewById(R.id.start_game_TEXT);
        start_BTN_LeaderBoard = findViewById(R.id.start_BTN_LeaderBoard);
        start_BTN_SensorMode = findViewById(R.id.start_BTN_SensorMode);
        start_BTN_ButtonMode = findViewById(R.id.start_BTN_ButtonMode);
    }

    private void initView() {
        start_BTN_SensorMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGameActivity.this, SensorsMainActivity.class);
                startActivity(intent);
            }
        });

        start_BTN_ButtonMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        start_BTN_LeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGameActivity.this, BestScoreActivity.class);
                startActivity(intent);
            }
        });
    }
}