package com.example.cabahugdiceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Random random = new Random();
    ImageView dice1,dice2;
    private Bitmap[] diceImages; // Array to store preloaded dice images


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice1 = findViewById(R.id.dice_image1);
        dice2 = findViewById(R.id.dice_image2);
        Button roll = findViewById(R.id.roll);
        roll.setOnClickListener(this);
        preloadDiceImages();
    }

    private void preloadDiceImages() {
        diceImages = new Bitmap[6];
        for (int i = 1; i <= 6; i++) {
            String drawableName = String.format("dice_%d", i);
            int imageDrawable = getResources().getIdentifier(drawableName, "drawable", getPackageName());
            diceImages[i - 1] = BitmapFactory.decodeResource(getResources(), imageDrawable);
        }
    }

    private Bitmap setImage() {
        int i = random.nextInt(6);
        Bitmap image = diceImages[i].copy(Bitmap.Config.ARGB_8888, true);

        int targetColor = Color.parseColor("#FFFFFF"); // Color you want to change
        int replacementColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixelColor = image.getPixel(x, y);

                if (pixelColor == targetColor) {
                    image.setPixel(x, y, replacementColor);
                }
            }
        }

        return image;
    }

    @Override
    public void onClick(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        dice1.startAnimation(animation);
        dice1.setImageBitmap(setImage());
        dice2.startAnimation(animation);
        dice2.setImageBitmap(setImage());
    }





}