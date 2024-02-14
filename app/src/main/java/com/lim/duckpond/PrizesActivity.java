package com.lim.duckpond;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PrizesActivity extends AppCompatActivity {
    private ImageView imgPrize1, imgPrize2, imgPrize3;
    private int duck1Value, duck2Value, duck3Value;
    private Duck duck1, duck2, duck3;
    private ImageView[] prizeImages;
    private Duck[] ducks;
    private ObjectAnimator animator;
    private Button btnPlayAgain, btnExitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prizes);

        // Initialize views and retrieve intent extras
        initViews();
        getIntents();

        // Initialize ducks and set up shake animations for prize images
        initDucks();
        for (ImageView prizeImage : prizeImages) {
            applyShakeAnimation(prizeImage);
        }

        // Set click listeners for prize images
        for (int i = 0; i < prizeImages.length; i++) {
            int duckValue = ducks[i].getDuckValue();
            int index = i;

            prizeImages[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cancel shake animation when the image is clicked
                    ObjectAnimator objectAnimator = (ObjectAnimator) prizeImages[index].getTag();
                    if (objectAnimator != null) {
                        objectAnimator.cancel();
                    }

                    // Set the appropriate prize image based on the duck value
                    if (duckValue == 0) {
                        prizeImages[index].setImageResource(R.drawable.trophy_image);
                    }
                    else if (duckValue == 1) {
                        prizeImages[index].setImageResource(R.drawable.medal_image);
                    }
                    else if (duckValue == 2) {
                        prizeImages[index].setImageResource(R.drawable.egg_image);
                    }

                }
            });
        }

        // Set click listeners for buttons
        buttonsListener();
    }

    // Initialize views
    private void initViews() {
        imgPrize1 = findViewById(R.id.img_prize1);
        imgPrize2 = findViewById(R.id.img_prize2);
        imgPrize3 = findViewById(R.id.img_prize3);

        btnPlayAgain = findViewById(R.id.btn_playAgain);
        btnExitGame = findViewById(R.id.btn_exitGame);
    }

    // Retrieve intent extras to get duck values
    private void getIntents() {
        Intent intent = getIntent();
        String value1 = intent.getStringExtra("duck1");
        String value2 = intent.getStringExtra("duck2");
        String value3 = intent.getStringExtra("duck3");

        duck1Value = (value1 != null && !value1.isEmpty()) ? Integer.parseInt(value1) : 2;
        duck2Value = (value2 != null && !value2.isEmpty()) ? Integer.parseInt(value2) : 2;
        duck3Value = (value3 != null && !value3.isEmpty()) ? Integer.parseInt(value3) : 2;
    }

    // Apply shake animation to a prize image
    private void applyShakeAnimation(ImageView duckImage) {
        animator = ObjectAnimator.ofFloat(duckImage, "translationX", -10f, 10f);
        animator.setDuration(100);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);

        animator.start();
        duckImage.setTag(animator);
    }

    // Initialize ducks and set prize images based on duck values
    private void initDucks() {
        duck1 = new Duck(R.drawable.duck_image, duck1Value);
        duck2 = new Duck(R.drawable.duck_image, duck2Value);
        duck3 = new Duck(R.drawable.duck_image, duck3Value);

        // Sets ImageView src
        imgPrize1.setImageResource(duck1.getDuckImage());
        imgPrize2.setImageResource(duck2.getDuckImage());
        imgPrize3.setImageResource(duck3.getDuckImage());

        prizeImages = new ImageView[] {imgPrize1, imgPrize2, imgPrize3};
        ducks = new Duck[] {duck1, duck2, duck3};
    }

    // Set click listeners for buttons
    private void buttonsListener() {
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open GameActivity when "Play Again" button is clicked
                ActivityHelper.openNewActivity(PrizesActivity.this, GameActivity.class);
            }
        });

        btnExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit the activity when "Exit Game" button is clicked
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cancel shake animation for each prize image to prevent memory leaks
        for (ImageView duckImage : prizeImages) {
            ObjectAnimator objectAnimator = (ObjectAnimator) duckImage.getTag();

            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
        }
    }
}