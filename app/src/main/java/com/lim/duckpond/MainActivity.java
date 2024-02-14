package com.lim.duckpond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnStart, btnInstructions, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnOnClick();
    }

    // Initialize Views (Buttons)
    private void initViews() {
        btnStart = findViewById(R.id.btn_start);
        btnInstructions = findViewById(R.id.btn_instructions);
        btnExit = findViewById(R.id.btn_exit);
    }

    // Set click listeners for buttons
    private void btnOnClick() {
        // Start button click listener
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open GameActivity when the Start button is clicked
                ActivityHelper.openNewActivity(MainActivity.this, GameActivity.class);
            }
        });

        // Instructions button click listener
        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open InstructionsActivity when the Instructions button is clicked
                ActivityHelper.openNewActivity(MainActivity.this, InstructionsActivity.class);
            }
        });

        // Exit button click listener
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the MainActivity (close the app) when the Exit button is clicked
                finish();
            }
        });
    }
}