package com.example.wang.huntergod;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.VideoView;

import java.util.Random;

public class Game_video_shower extends Activity implements SurfaceHolder.Callback{
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

        VideoView videoView = (VideoView) findViewById(R.id.videoView005);

        Random rank = new Random();
        int num = rank.nextInt(2) + 1;

        videoView.setVideoPath("storage/emulated/0/Movies/cat1/shower/shower" + num + ".mp4");
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                /*Intent intent = new Intent();
                intent.setClass(Game_video_play.this, MainActivity.class);
                startActivity(intent);*/
                mp5.stop();
                Game_video_shower.this.finish();
            }
        });

     /*   button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView videoView = (VideoView) findViewById(R.id.videoView);
                String uriPath = "android.resource://example.prgguru.com.myapplication/"+R.raw.ykzzldx;
                Uri uri = Uri.parse(uriPath);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();

            }
        });*/


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        return;
        //super.onBackPressed();
    }
}
