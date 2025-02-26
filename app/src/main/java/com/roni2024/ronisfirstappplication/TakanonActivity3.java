package com.roni2024.ronisfirstappplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TakanonActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takanon);

        // מציאת כפתור "מאשר"
        Button agreeButton = findViewById(R.id.agree_button);

        // הגדרת פעולה על כפתור "מאשר"
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // חזרה למסך הקודם
                //TODO onBackPressed();
                finish();
            }
        });
    }
}
