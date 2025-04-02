package com.roni2024.ronisfirstappplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, bL, bCountTimer;
    TextView tv1;
    ConstraintLayout layout;

    ImageView iv;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();

        initViews();

        // הוספת כפתור הגדרות ליד ה-Menu



        // Register for Context Menu (make sure to add to the ImageView or another view)
        registerForContextMenu(iv); // This registers the ImageView to show the context menu when long-clicked
    }

    private void initViews() {
        tv1=findViewById(R.id.tv1);
        // Finding buttons and setting onClickListeners
        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("Thank you for your click");
                Log.d("Roni", "b1");
            }
        });

        b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("So Fun");
                Log.d("Roni", "b2");
            }
        });

        bL = findViewById(R.id.btnLinear);
        bL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Transition to NoamActivity2
                Intent intent = new Intent(MainActivity.this, NoamActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        // Finding ImageView and SeekBar, setting alpha change listener
        iv = findViewById(R.id.iv);
        sb = findViewById(R.id.sb);

        // Default transparency value
        sb.setProgress(100);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float alpha = (float) i / 100;
                iv.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        bCountTimer = findViewById(R.id.bCountTimer);
        bCountTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // שינוי צבע ה-title של כל הפריטים בתפריט
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString s = new SpannableString(item.getTitle());
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, s.length(), 0);
            item.setTitle(s);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                // יצירת Intent לאקטיביטי של קטגוריה 1
                Intent intent1 = new Intent(this, GameActivity3.class);
                startActivity(intent1);
                return true;
            case R.id.item2:
                // יצירת Intent לאקטיביטי של קטגוריה 2
                Intent intent2 = new Intent(this, TakanonActivity3.class);
                startActivity(intent2);
                return true;
            case R.id.item3:
                // יצירת Intent לאקטיביטי של קטגוריה 3
                Intent intent3 = new Intent(this, login.class);
                startActivity(intent3);
            case R.id.settings:
                // יצירת Intent לאקטיביטי של קטגוריה 3
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.main:
                Intent intent4 = new Intent(this, MainActivity.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // יצירת תפריט Context Menu לתמונה
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu); // קישור לתפריט ה-XML
    }

    // טיפול בלחיצה על הפריט בתפריט ההקשר
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
