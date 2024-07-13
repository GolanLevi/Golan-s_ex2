package com.example.golans_ex2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.golans_ex2.Models.LeaderBoard;
import com.example.golans_ex2.Models.Player;
import com.example.golans_ex2.Utilities.LeaderboardManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class EndGameActivity extends AppCompatActivity {

    public MaterialTextView end_game_TEXT;
    public static final String KEY_STATUS = "KEY_STATUS";
    public static final String KEY_SCORE = "KEY_SCORE";
    private static final int LOCATION_CODE = 100;
    private MaterialTextView end_LBL_status;
    private MaterialButton end_BTN_PlayAgain;
    private MaterialButton end_BTN_SaveYourScore;
    private MaterialTextView end_LBL_finalScore;
    private EditText end_EditText_Name;
    private LocationManager locationManager;
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        findView();
        initView();
        setupEditTextListener();
    }

    private void findView() {
        end_LBL_status = findViewById(R.id.end_LBL_status);
        end_BTN_PlayAgain = findViewById(R.id.end_BTN_PlayAgain);
        end_LBL_finalScore = findViewById(R.id.end_LBL_finalScore);
        end_EditText_Name = findViewById(R.id.end_EditText_Name);
        end_BTN_SaveYourScore = findViewById(R.id.end_BTN_SaveYourScore);
        end_BTN_SaveYourScore.setEnabled(false);
    }

    private void initView() {
        Intent prevIntent = getIntent();
        String status = prevIntent.getStringExtra(KEY_STATUS);
        int scoreValue = prevIntent.getIntExtra(KEY_SCORE, 0);
        end_LBL_status.setText(status);
        end_LBL_finalScore.setText(String.valueOf(scoreValue));

        end_BTN_PlayAgain.setOnClickListener(view -> {
            Intent intent = new Intent(EndGameActivity.this, StartGameActivity.class);
            startActivity(intent);
            finish();
        });
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        end_BTN_SaveYourScore.setOnClickListener(view -> {
            getLocation(scoreValue);
        });
    }

    private void getLocation(int scoreValue) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location!=null){
            lat = location.getLatitude();
            lon = location.getLongitude();
        }
        savePlayerData(scoreValue);
        Log.d("Lat",String.valueOf(lat));
        Log.d("Lon",String.valueOf(lon));
    }

    private void savePlayerData(int scoreValue) {
        LeaderBoard leaderBoard = LeaderboardManager.useLeaderboard();
        Log.d("leaderBoard",leaderBoard.toString());
        leaderBoard.addPlayer(new Player()
                .setName(end_EditText_Name.getText().toString())
                .setFinalScore(scoreValue)
                .setLat(lat)
                .setLon(lon)
        );
        LeaderboardManager.saveLeaderBoard(leaderBoard);
        highScores();
    }

    private void highScores() {
        Intent intent = new Intent(this, BestScoreActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupEditTextListener() {
        end_EditText_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                end_BTN_SaveYourScore.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation(getIntent().getIntExtra(KEY_SCORE, 0));
            }
        }
    }
}