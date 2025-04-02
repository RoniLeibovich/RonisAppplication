package com.roni2024.ronisfirstappplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MmusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize MediaPlayer and load the music once when the service is created
        mediaPlayer = MediaPlayer.create(this, R.raw.gorila_315977); // Replace with your music file
        mediaPlayer.setLooping(true); // Music will loop
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();

            if (action != null) {
                switch (action) {
                    case "START_MUSIC":
                        startMusic();
                        break;
                    case "STOP_MUSIC":
                        stopMusic();
                        break;
                    default:
                        // Handle additional actions if necessary
                        break;
                }
            }
        }
        // Return START_STICKY to keep the service alive in case it is killed
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Not bound to any component
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer when the service is destroyed
        releaseMediaPlayer();
    }

    private void startMusic() {
        // If the music is not playing, start it
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void stopMusic() {
        // If the music is playing, stop it (don't release the MediaPlayer)
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause(); // Pause instead of stopping to allow resumption
            // No release here, so the MediaPlayer is ready for restarting
        }
    }

    private void releaseMediaPlayer() {
        // Release the MediaPlayer only when the service is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

