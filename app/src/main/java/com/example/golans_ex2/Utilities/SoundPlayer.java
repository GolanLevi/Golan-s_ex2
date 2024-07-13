package com.example.golans_ex2.Utilities;

import android.content.Context;
import android.media.MediaPlayer;
import com.example.golans_ex2.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SoundPlayer {

    private Context context;
    private Executor executor;
    private MediaPlayer mediaPlayer;

    public SoundPlayer(Context context) {
        this.context = context;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void playSound(int resID) {
        if (mediaPlayer == null) {
            executor.execute(() -> {
                mediaPlayer = mediaPlayer.create(context, resID);
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.start();
            });
        }
    }

    public void playSoundUnLoop(int resID) {
        if (mediaPlayer == null) {
            executor.execute(() -> {
                mediaPlayer = mediaPlayer.create(context, resID);
                mediaPlayer.isLooping();
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.start();
            });
        }
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            executor.execute(() -> {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;

            });
        }
    }
}
