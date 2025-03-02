package com.roni2024.ronisfirstappplication;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnSave;
    private TextView tvSavedData;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // אתחול של ה-Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // אתחול של השדות
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSave = findViewById(R.id.btnSave);
        tvSavedData = findViewById(R.id.tvSavedData);

        // אתחול של SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // הצגת נתונים ששמורים ב-SharedPreferences
        displaySavedData();

        // שמירת הנתונים ב-SharedPreferences כאשר לוחצים על כפתור שמירה
        btnSave.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(login.this, "נא למלא את כל השדות", Toast.LENGTH_SHORT).show();
            } else {
                // שמירה ב-SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username); // שמירת שם המשתמש
                editor.putString("password", password); // שמירת הסיסמה
                editor.apply(); // שמירה

                // הצגת הנתונים שמורים בעמוד
                displaySavedData();

                // הצגת הודעה למשתמש על שמירת הנתונים
                Toast.makeText(login.this, "הנתונים נשמרו", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void displaySavedData() {
        // קבלת נתונים מ-SharedPreferences
        String savedUsername = sharedPreferences.getString("username", "לא הוזן שם משתמש");
        String savedPassword = sharedPreferences.getString("password", "לא הוזנה סיסמה");

        // הצגת הנתונים בתוך TextView
        tvSavedData.setText("שם משתמש: " + savedUsername + "\nסיסמה: " + savedPassword);
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
