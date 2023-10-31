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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice1 = findViewById(R.id.dice_image1);
        dice2 = findViewById(R.id.dice_image2);
        Button roll = findViewById(R.id.roll);
        roll.setOnClickListener(this);
    }

    public Bitmap setImage(){
        int i = random.nextInt(6)+1;
        String drawable_name = String.format("dice_%d",i);
        int imagedrawable = getResources().getIdentifier(drawable_name,"drawable",getPackageName());

        Bitmap image = BitmapFactory.decodeResource(getResources(), imagedrawable);

        Bitmap mutableImage = image.copy(Bitmap.Config.ARGB_8888, true);

        int targetColor = Color.parseColor("#FFFFFF"); // Color you want to change

        int red = random.nextInt(256); // 0-255
        int green = random.nextInt(256); // 0-255
        int blue = random.nextInt(256); // 0-255


        int replacementColor = Color.argb(255, red, green, blue); // Color to replace with

        for (int x = 0; x < mutableImage.getWidth(); x++) {
            for (int y = 0; y < mutableImage.getHeight(); y++) {
                int pixelColor = mutableImage.getPixel(x, y);

                if (pixelColor == targetColor) {
                    mutableImage.setPixel(x, y, replacementColor);
                }
            }
        }

        return mutableImage;
    }

    @Override
    public void onClick(View view) {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        dice1.startAnimation(animation);
        dice1.setImageBitmap(setImage());
        dice2.startAnimation(animation);
        dice2.setImageBitmap(setImage());








    }




}