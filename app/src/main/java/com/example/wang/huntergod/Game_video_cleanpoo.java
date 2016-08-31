package com.example.wang.huntergod;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.WindowManager;

public class Game_video_cleanpoo extends Activity {
    private MediaPlayer mp2;
    private int point;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_video_cleanpoo);

        mp2 = MediaPlayer.create(this,R.raw.videomusic);
        mp2.setLooping(true);
        mp2.start();
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        return;
        //super.onBackPressed();
    }
}
