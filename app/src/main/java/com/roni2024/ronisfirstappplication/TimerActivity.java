package com.roni2024.ronisfirstappplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TimerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        ImageView timerImage = findViewById(R.id.timer_image); // משתנה לתמונה

        final int[] images = {
                R.drawable.pink5,
                R.drawable.pink4,
                R.drawable.pink3,
                R.drawable.pink2,
                R.drawable.pink1
        };

        // הפעלת טיימר שמציג תמונות אחת אחרי השנייה
        new CountDownTimer(5000, 1000) {
            int index = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                if (index < images.length) {
                    timerImage.setImageResource(images[index]);
                    index++;
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(TimerActivity.this, SharedPreference.class);
                startActivity(intent);
                finish(); // סוגר את הפעילות הנוכחית כדי שלא תחזור אחורה
            }
        }.start();
    }
}