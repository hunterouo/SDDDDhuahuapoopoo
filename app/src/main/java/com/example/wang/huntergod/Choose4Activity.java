package com.example.wang.huntergod;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

public class Choose4Activity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose4);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iv= (ImageView) findViewById(R.id.image);

        AnimationDrawable ad = (AnimationDrawable) getResources().getDrawable(
                R.drawable.shaking_cat1);
        iv.setBackgroundDrawable(ad);
        ad.start();
    }
}
