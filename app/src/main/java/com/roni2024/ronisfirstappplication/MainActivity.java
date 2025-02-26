package com.roni2024.ronisfirstappplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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

        Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();

        initViews();

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

    // This method creates the context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.iv) { // Check if the long-clicked view is the ImageView
            getMenuInflater().inflate(R.menu.menu_main, menu); // This inflates the menu resource
        }
    }

    // This method handles the context menu item selection
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_item) {
            // Start NewActivity when 'new_item' is selected
            Intent intent = new Intent(this, TimerActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
