package com.lim.duckpond;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private ImageView imgDuck1, imgDuck2, imgDuck3, imgDuck4, imgDuck5, imgDuck6, imgDuck7;
    private Duck duck1, duck2, duck3, duck4, duck5, duck6, duck7;
    private ImageView[] duckImages;
    private Duck[] ducks;
    private ArrayList<Integer> values;
    private ArrayList<String> chosenDuckValues;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize views, ducks, and values
        initViews();
        initDucks();
        initValues();
        assignRandomValuesToDucks();

        // Initialize chosenDuckValues list
        chosenDuckValues = new ArrayList<>();

        // Move ducks with animations
        for (int i = 0; i < duckImages.length; i++) {
            Random random = new Random();
            long delay = random.nextInt(1500);
            moveDuck(duckImages[i], delay, ducks[i]);
        }

        // Set click listeners for duck images
        for (int i = 0; i < duckImages.length; i++) {
            String duckValue = Integer.toString(ducks[i].getDuckValue());
            int index = i;

            duckImages[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Add clicked duck's value to chosenDuckValues and hide the image
                    chosenDuckValues.add(duckValue);
                    duckImages[index].setVisibility(View.INVISIBLE);

                    // Start the PrizesActivity when 3 ducks are chosen
                    if (chosenDuckValues.size() == 3) {
                        startPrizesActivity();
                    }
                }
            });
        }
    }

    // Initialize views (duck images)
    private void initViews() {
        imgDuck1 = findViewById(R.id.img_duck1);
        imgDuck2 = findViewById(R.id.img_duck2);
        imgDuck3 = findViewById(R.id.img_duck3);
        imgDuck4 = findViewById(R.id.img_duck4);
        imgDuck5 = findViewById(R.id.img_duck5);
        imgDuck6 = findViewById(R.id.img_duck6);
        imgDuck7 = findViewById(R.id.img_duck7);

    }

    // Initialize Duck instances and set an image
    private void initDucks() {
        duck1 = new Duck(R.drawable.duck_image);
        duck2 = new Duck(R.drawable.duck_image);
        duck3 = new Duck(R.drawable.duck_image);
        duck4 = new Duck(R.drawable.duck_image);
        duck5 = new Duck(R.drawable.duck_image);
        duck6 = new Duck(R.drawable.duck_image);
        duck7 = new Duck(R.drawable.duck_image);

        // Sets the ImageViews src
        imgDuck1.setImageResource(duck1.getDuckImage());
        imgDuck2.setImageResource(duck2.getDuckImage());
        imgDuck3.setImageResource(duck3.getDuckImage());
        imgDuck4.setImageResource(duck4.getDuckImage());
        imgDuck5.setImageResource(duck5.getDuckImage());
        imgDuck6.setImageResource(duck6.getDuckImage());
        imgDuck7.setImageResource(duck7.getDuckImage());

        duckImages = new ImageView[] {imgDuck1, imgDuck2, imgDuck3, imgDuck4, imgDuck5, imgDuck6, imgDuck7};
        ducks = new Duck[] {duck1, duck2, duck3, duck4, duck5, duck6, duck7};
    }

    // Initialize the possible values for ducks (Prizes)
    private void initValues() {
        values = new ArrayList<>();
        values.add(0);
        values.add(1);
        values.add(1);
        values.add(1);
        values.add(2);
        values.add(2);
        values.add(2);
    }

    // Assign random values to ducks from the available values
    private void assignRandomValuesToDucks() {
        Random random = new Random();

        for (Duck duck : ducks) {
            int randomValueIndex = random.nextInt(values.size());
            int randomValue = values.get(randomValueIndex);

            duck.setDuckValue(randomValue);
            values.remove(randomValueIndex);
        }
    }

    // Move a duck with animation
    private void moveDuck(ImageView duckImage, long delay, Duck duck) {
        duckImage.setTranslationX(-duckImage.getWidth());
        duckImage.setTranslationY(0);

        animator = ObjectAnimator.ofFloat(duckImage, "translationX", getScreenWidth());
        animator.setDuration(3000);
        animator.setStartDelay(delay);
        animator.setRepeatCount(ObjectAnimator.INFINITE);

        animator.start();
    }

    // Get the width of the screen
    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    // Start the PrizesActivity with chosen duck values
    private void startPrizesActivity() {
        Intent intent = new Intent(GameActivity.this, PrizesActivity.class);
        intent.putExtra("duck1", chosenDuckValues.get(0));
        intent.putExtra("duck2", chosenDuckValues.get(1));
        intent.putExtra("duck3", chosenDuckValues.get(2));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cancel the animator if it's running to prevent memory leaks
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
    }
}