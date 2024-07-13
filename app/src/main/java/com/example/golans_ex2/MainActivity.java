package com.example.golans_ex2;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.golans_ex2.Utilities.SoundPlayer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Timer
    private Timer timer;
    private long startTime;
    private boolean timerOn = false;
    private static final long DELAY = 500L;

    // Car & buttons
    private MaterialButton main_BTN_left;
    private MaterialButton main_BTN_right;
    private AppCompatImageView[] main_IMG_racingCars;
    private AppCompatImageView[] main_IMG_hearts;
    private AppCompatImageView[][] main_IMG_cones;
    private AppCompatImageView[][] main_IMG_coins;
    private MaterialTextView main_LBL_score;
    private GameManager gameManager;
    private SoundPlayer backRoundSound;
    private SoundPlayer crashSound;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        gameManager = new GameManager(main_IMG_cones.length, main_IMG_cones[0].length, main_IMG_hearts.length);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        crashSound = new SoundPlayer(this);
        backRoundSound = new SoundPlayer(this);
        backRoundSound.playSound(R.raw.notfornothing);
    }

    @Override
    protected void onPause() {
        super.onPause();
        backRoundSound.stopSound();
    }

    private void initViews() {
        main_BTN_left.setOnClickListener(v -> gameManager.racingCarMovingLeft());
        main_BTN_right.setOnClickListener(v -> gameManager.racingCarMovingRight());
        main_LBL_score.setText(String.valueOf(gameManager.getFinalScore()));
        if (!timerOn) {
            startTime = System.currentTimeMillis();
            timerOn = true;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> conesUI());
                }
            }, 0L, DELAY);
        }
    }

    private void refreshUI() {
        main_LBL_score.setText(String.valueOf(gameManager.getFinalScore()));
        if (gameManager.isGameLost()) {
            long currentTime = System.currentTimeMillis();
            Log.d("GAME STATUS", "GAME OVER");
            changeActivity("GAME OVER \n🏎️",gameManager.getFinalScore());
        }

        for (int i = 0; i < gameManager.getNumOfRows(); i++) {
            for (int j = 0; j < gameManager.getNumOfCols(); j++) {
                if (i == 0) {
                    main_IMG_racingCars[j].setVisibility(gameManager.getMain_IMG_racingCars()[j] == 1 ? View.VISIBLE : View.INVISIBLE);
                }
                main_IMG_cones[i][j].setVisibility(gameManager.getMain_IMG_cones()[i][j] == 1 ? View.VISIBLE : View.INVISIBLE);
                main_IMG_coins[i][j].setVisibility(gameManager.getMain_IMG_coins()[i][j] == 1 ? View.VISIBLE : View.INVISIBLE);
            }
        }

        if (gameManager.getNumOfCrashes() != 0) {
            main_IMG_hearts[gameManager.getNumOfCrashes() - 1].setVisibility(View.INVISIBLE);
        }
    }

    private void changeActivity(String status, int score) {
        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtra(EndGameActivity.KEY_SCORE, score);
        intent.putExtra(EndGameActivity.KEY_STATUS, status);
        startActivity(intent);
        timer.cancel();
        finish();
    }

    private void findViews() {
        main_LBL_score = findViewById(R.id.main_LBL_score);
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);

        main_IMG_racingCars = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_racingCar1),
                findViewById(R.id.main_IMG_racingCar2),
                findViewById(R.id.main_IMG_racingCar3),
                findViewById(R.id.main_IMG_racingCar4),
                findViewById(R.id.main_IMG_racingCar5)
        };

        main_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };

        main_IMG_cones = new AppCompatImageView[][]{
                {
                        findViewById(R.id.main_IMG_cone_i1_j1),
                        findViewById(R.id.main_IMG_cone_i1_j2),
                        findViewById(R.id.main_IMG_cone_i1_j3),
                        findViewById(R.id.main_IMG_cone_i1_j4),
                        findViewById(R.id.main_IMG_cone_i1_j5)
                }, {
                findViewById(R.id.main_IMG_cone_i2_j1),
                findViewById(R.id.main_IMG_cone_i2_j2),
                findViewById(R.id.main_IMG_cone_i2_j3),
                findViewById(R.id.main_IMG_cone_i2_j4),
                findViewById(R.id.main_IMG_cone_i2_j5)
        },{
                findViewById(R.id.main_IMG_cone_i3_j1),
                findViewById(R.id.main_IMG_cone_i3_j2),
                findViewById(R.id.main_IMG_cone_i3_j3),
                findViewById(R.id.main_IMG_cone_i3_j4),
                findViewById(R.id.main_IMG_cone_i3_j5)
        }, {
                findViewById(R.id.main_IMG_cone_i4_j1),
                findViewById(R.id.main_IMG_cone_i4_j2),
                findViewById(R.id.main_IMG_cone_i4_j3),
                findViewById(R.id.main_IMG_cone_i4_j4),
                findViewById(R.id.main_IMG_cone_i4_j5)
        },{
                findViewById(R.id.main_IMG_cone_i5_j1),
                findViewById(R.id.main_IMG_cone_i5_j2),
                findViewById(R.id.main_IMG_cone_i5_j3),
                findViewById(R.id.main_IMG_cone_i5_j4),
                findViewById(R.id.main_IMG_cone_i5_j5)
        },{
                findViewById(R.id.main_IMG_cone_i6_j1),
                findViewById(R.id.main_IMG_cone_i6_j2),
                findViewById(R.id.main_IMG_cone_i6_j3),
                findViewById(R.id.main_IMG_cone_i6_j4),
                findViewById(R.id.main_IMG_cone_i6_j5)
        }, {
                findViewById(R.id.main_IMG_cone_i7_j1),
                findViewById(R.id.main_IMG_cone_i7_j2),
                findViewById(R.id.main_IMG_cone_i7_j3),
                findViewById(R.id.main_IMG_cone_i7_j4),
                findViewById(R.id.main_IMG_cone_i7_j5)
        },{
                findViewById(R.id.main_IMG_cone_i8_j1),
                findViewById(R.id.main_IMG_cone_i8_j2),
                findViewById(R.id.main_IMG_cone_i8_j3),
                findViewById(R.id.main_IMG_cone_i8_j4),
                findViewById(R.id.main_IMG_cone_i8_j5)
        },
        };

        main_IMG_coins = new AppCompatImageView[][]{
                {
                        findViewById(R.id.main_IMG_coin_i1_j1),
                        findViewById(R.id.main_IMG_coin_i1_j2),
                        findViewById(R.id.main_IMG_coin_i1_j3),
                        findViewById(R.id.main_IMG_coin_i1_j4),
                        findViewById(R.id.main_IMG_coin_i1_j5)
                }, {
                findViewById(R.id.main_IMG_coin_i2_j1),
                findViewById(R.id.main_IMG_coin_i2_j2),
                findViewById(R.id.main_IMG_coin_i2_j3),
                findViewById(R.id.main_IMG_coin_i2_j4),
                findViewById(R.id.main_IMG_coin_i2_j5)
        },{
                findViewById(R.id.main_IMG_coin_i3_j1),
                findViewById(R.id.main_IMG_coin_i3_j2),
                findViewById(R.id.main_IMG_coin_i3_j3),
                findViewById(R.id.main_IMG_coin_i3_j4),
                findViewById(R.id.main_IMG_coin_i3_j5)
        }, {
                findViewById(R.id.main_IMG_coin_i4_j1),
                findViewById(R.id.main_IMG_coin_i4_j2),
                findViewById(R.id.main_IMG_coin_i4_j3),
                findViewById(R.id.main_IMG_coin_i4_j4),
                findViewById(R.id.main_IMG_coin_i4_j5)
        },{
                findViewById(R.id.main_IMG_coin_i5_j1),
                findViewById(R.id.main_IMG_coin_i5_j2),
                findViewById(R.id.main_IMG_coin_i5_j3),
                findViewById(R.id.main_IMG_coin_i5_j4),
                findViewById(R.id.main_IMG_coin_i5_j5)
        },{
                findViewById(R.id.main_IMG_coin_i6_j1),
                findViewById(R.id.main_IMG_coin_i6_j2),
                findViewById(R.id.main_IMG_coin_i6_j3),
                findViewById(R.id.main_IMG_coin_i6_j4),
                findViewById(R.id.main_IMG_coin_i6_j5)
        }, {
                findViewById(R.id.main_IMG_coin_i7_j1),
                findViewById(R.id.main_IMG_coin_i7_j2),
                findViewById(R.id.main_IMG_coin_i7_j3),
                findViewById(R.id.main_IMG_coin_i7_j4),
                findViewById(R.id.main_IMG_coin_i7_j5)
        },{
                findViewById(R.id.main_IMG_coin_i8_j1),
                findViewById(R.id.main_IMG_coin_i8_j2),
                findViewById(R.id.main_IMG_coin_i8_j3),
                findViewById(R.id.main_IMG_coin_i8_j4),
                findViewById(R.id.main_IMG_coin_i8_j5)
        },
        };

    }

    private void conesUI() {
        long currentTime = System.currentTimeMillis();
        final MediaPlayer crushSound = MediaPlayer.create(this, R.raw.crushsound);
        gameManager.coinsAndConesMoving(currentTime - startTime);
        refreshUI();
        if (gameManager.isCrash()) {
            crushSound.start();
            toastAndVibrate("You lost " + gameManager.getNumOfCrashes() + " life!");
        }
    }

    private void toastAndVibrate(String text) {
        vibrate();
        toast(text);
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }
    }

}
