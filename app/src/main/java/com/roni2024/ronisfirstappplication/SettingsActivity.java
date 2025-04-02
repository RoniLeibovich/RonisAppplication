package com.roni2024.ronisfirstappplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch switchMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        switchMusic = findViewById(R.id.swMusic);
        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent serviceIntent = new Intent(SettingsActivity.this, MmusicService.class);
                    serviceIntent.setAction("START_MUSIC"); // שליחת פקודת עצירה
                    startService(serviceIntent); // הפעלת השירות עם הפקודה לעוצר אותו
                } else {
                    Intent serviceIntent = new Intent(SettingsActivity.this, MmusicService.class);
                    serviceIntent.setAction("STOP_MUSIC"); // שליחת פקודת עצירה
                    startService(serviceIntent); // הפעלת השירות עם הפקודה לעוצר אותו
                }
            }
        });
    }
}

