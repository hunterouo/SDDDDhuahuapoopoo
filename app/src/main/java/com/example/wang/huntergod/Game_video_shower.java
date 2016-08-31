package com.example.wang.huntergod;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.WindowManager;

public class Game_video_shower extends Activity {
    private MediaPlayer mp5;
    private int point;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_video_shower);

        mp5 = MediaPlayer.create(this,R.raw.videomusic);
        mp5.setLooping(true);
        mp5.start();
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        return;
        //super.onBackPressed();
    }
}
