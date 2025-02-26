package com.roni2024.ronisfirstappplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;

public class GameActivity3 extends AppCompatActivity {

    private EditText editText3;
    private Button btnRandom, btnHome, btnGuess;
    private RadioGroup radioGroup;
    private int randomNumber;int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3); // Ensure this matches your layout file

        counter=0;
        // Initialize UI components
        editText3 = findViewById(R.id.editText3); // Guess input
        btnRandom = findViewById(R.id.btn2); // Button to generate random number
        btnHome = findViewById(R.id.btnHome); // Button to navigate home
        btnGuess = findViewById(R.id.btn); // Guess button
        radioGroup = findViewById(R.id.radioGroup); // RadioGroup for range selection

        // Button to generate a random number
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = 0, max = 0;

                // Get the selected radio button and set the range accordingly
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.radioButton1) {
                    min = 0;
                    max = 100;
                } else if (selectedId == R.id.radioButton2) {
                    min = 101;
                    max = 200;
                } else if (selectedId == R.id.radioButton3) {
                    min = 201;
                    max = 300;
                }

                // If no radio button is selected, show a toast
                if (selectedId == -1) {
                    Toast.makeText(GameActivity3.this, "Please select a range", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Generate a random number within the selected range
                randomNumber = generateRandomNumber(min, max);

                // Notify user
                Toast.makeText(GameActivity3.this, "Random number generated! Try to guess.", Toast.LENGTH_SHORT).show();
            }
        });

        // Button to check the guess
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                String guessText = editText3.getText().toString().trim();

                if (!guessText.isEmpty()) {
                    try {
                        int userGuess = Integer.parseInt(guessText);

                        if (userGuess == randomNumber) {
                            Toast.makeText(GameActivity3.this, "True! You guessed the correct number.", Toast.LENGTH_SHORT).show();
                            Intent result = new Intent();
                            result.putExtra("num_guesses",counter);
                            setResult(RESULT_OK,result);
                            finish();
                        } else if (userGuess < randomNumber) {
                            Toast.makeText(GameActivity3.this, "Your guess is too low! Try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GameActivity3.this, "Your guess is too high! Try again.", Toast.LENGTH_SHORT).show();

                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(GameActivity3.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GameActivity3.this, "Please enter a guess", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button to return to the home page
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to generate a random number within a given range
    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
