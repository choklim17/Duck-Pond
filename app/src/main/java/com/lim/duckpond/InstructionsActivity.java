package com.lim.duckpond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity {
    private TextView txtObjective, txtSelectingDucks, txtRevealPrizes, txtPrizeTypes, txtEndOfRound;
    private Button btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        // Initialize views and set text for each instruction section
        initViews();
        setTexts();

        // Set click listener for the main menu button
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open MainActivity when "Main Menu" button is clicked
                ActivityHelper.openNewActivity(InstructionsActivity.this, MainActivity.class);
            }
        });
    }

    // Handle back button press to navigate to the main menu
    public void onBackPressed() {
        ActivityHelper.openNewActivity(InstructionsActivity.this, MainActivity.class);
    }

    // Initialize views by finding their respective IDs
    private void initViews() {
        txtObjective = findViewById(R.id.txt_objectiveString);
        txtSelectingDucks = findViewById(R.id.txt_selectingDucks);
        txtRevealPrizes = findViewById(R.id.txt_revealingPrizes);
        txtPrizeTypes = findViewById(R.id.txt_types);
        txtEndOfRound = findViewById(R.id.txt_endOfRound);

        btnMainMenu = findViewById(R.id.btn_menu);
    }

    // Set text for each instruction section
    private void setTexts() {
        String objective = "Your goal is to select three ducks out of the seven displayed on the screen and uncover any potential prizes associated with them.";
        txtObjective.setText(objective);

        String selectingDucks = "Tap on any of the seven ducks to make your selection. You can choose up to three ducks.";
        txtSelectingDucks.setText(selectingDucks);

        String revealPrizes = "After you have selected three ducks, click on each duck to reveal its prize. The prizes include a grand prize, normal prizes, or no prize.";
        txtRevealPrizes.setText(revealPrizes);

        String prizeTypes = "Grand Prize: The most coveted prize. You win if you uncover the duck containing the grand prize.\n\n" +
                            "Normal Prize: There are three normal prizes available. Uncover the ducks holding these prizes to win.\n\n" +
                            "No Prize: Not every duck holds a prize. If you uncover a duck with no prize, keep trying!";
        txtPrizeTypes.setText(prizeTypes);

        String endOfRound = "After you have revealed the prizes associated with your selected ducks, the round ends. You can choose to play again or exit the game.";
        txtEndOfRound.setText(endOfRound);
    }
}