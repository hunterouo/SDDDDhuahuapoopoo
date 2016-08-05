package com.example.wang.huntergod;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Choose4Activity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose4);
        iv= (ImageView) findViewById(R.id.image);

        iv.setBackgroundResource(R.drawable.shaking_cat3);
        AnimationDrawable ad= (AnimationDrawable) iv.getBackground();
        ad.start();

        int duration = 0;

        for(int i=0;i<ad.getNumberOfFrames();i++){

            duration += ad.getDuration(i);

        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            public void run() {

                Intent intent = new Intent(Choose4Activity.this,MainActivity.class);
                startActivity(intent);

            }

        }, duration);
    }
}

