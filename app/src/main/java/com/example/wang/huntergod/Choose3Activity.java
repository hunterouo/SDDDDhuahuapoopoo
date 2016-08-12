package com.example.wang.huntergod;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Choose3Activity extends AppCompatActivity {
    ImageView iv;
    private AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose3);
        iv= (ImageView) findViewById(R.id.image_choose3);


        iv.setImageResource(R.drawable.shaking_cat2);
        ad= (AnimationDrawable) iv.getDrawable();
        ad.start();


        int duration = 0;

        for(int i=0;i<ad.getNumberOfFrames();i++){

            duration += ad.getDuration(i);

        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            public void run() {

                Intent intent = new Intent(Choose3Activity.this,MainActivity.class);
                startActivity(intent);

            }

        }, duration);







    }
}

