package com.example.wang.huntergod;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class firstActivity extends AppCompatActivity {
    //按鈕聲
    private int point;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first);

        setTitle("Catdeo");
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        point = soundPool.load(this, R.raw.water_drop, 1);


        ImageButton imagebutton2= (ImageButton) findViewById(R.id.imageButton2);
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                Intent intent = new Intent();
                intent.setClass(firstActivity.this,loginActivity.class );
                startActivity(intent);
            }
        });
        ImageButton imagebutton1= (ImageButton) findViewById(R.id.imageButton1);
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                Intent intent = new Intent();
                intent.setClass(firstActivity.this,loginmidActivity.class );
                startActivity(intent);
            }
        });
    }
}
