package com.example.wang.huntergod;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Choose2Activity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose2);
        iv= (ImageView) findViewById(R.id.image);

        AnimationDrawable ad = (AnimationDrawable) getResources().getDrawable(
                R.drawable.shaking_cat1);
        iv.setBackgroundDrawable(ad);
        ad.start();


    }
}
