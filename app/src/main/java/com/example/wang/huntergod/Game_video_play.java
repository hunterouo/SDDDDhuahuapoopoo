package com.example.wang.huntergod;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.VideoView;

import java.util.Random;

public class Game_video_play extends Activity implements SurfaceHolder.Callback {
    private MediaPlayer mp4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_video_play);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        mp4 = MediaPlayer.create(this,R.raw.videomusic);
        mp4.setLooping(true);
        mp4.start();


        VideoView videoView = (VideoView) findViewById(R.id.videoView004);

        Random rank = new Random();
        int num = rank.nextInt(12) + 1;

        videoView.setVideoPath("storage/emulated/0/Movies/cat1/play/play" + num + ".mp4");
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
                mp4.stop();
                Game_video_play.this.finish();
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
