package com.example.wang.huntergod;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.VideoView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class Game_video_feed extends Activity {
    TabHost th;
    VideoView video;

    private GoogleApiClient client;
    ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_video_feed);
        Random rank = new Random();
        int num = rank.nextInt(4) + 1;
        video = (VideoView) findViewById(R.id.videoView003);
        String path1 = "http://163.13.201.93/video/eat/eat"+num+".mp4";


        pDialog = new ProgressDialog(Game_video_feed.this);
        // Set progressbar title
        pDialog.setTitle("Waiting");
        // Set progressbar message
        pDialog.setMessage("Meow~");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();
        // Create tabs using XML

        try {
            MediaController mc = new MediaController(this);
            mc.setAnchorView(video);
            mc.setMediaPlayer(video);
            Uri uri = Uri.parse(path1);
            //video.setMediaController(mc);
            video.setVideoURI(uri);
        }catch (Exception e){
            Log.e("Error",e.getMessage());
            e.printStackTrace();
        }
        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                video.start();
            }
        });


        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent();
                intent.setClass(Game_video_feed.this, MainActivity.class);
                startActivity(intent);
                Game_video_feed.this.finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        return;
        //super.onBackPressed();
    }


}