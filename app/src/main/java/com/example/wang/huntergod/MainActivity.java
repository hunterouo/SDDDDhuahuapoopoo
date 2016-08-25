package com.example.wang.huntergod;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;//背景音樂
    ImageButton questionBt;
    ImageView animation_iv, score_test, poo_1,poo_2,poo_3,poo_4;
    TextView score_happy;
    VideoView video007;
    RelativeLayout relay;
    ImageButton eat_btn,bath_btn,play_btn,brush_btn,poopoo_btn;
    static int[] status=new int[5];
    static StatusDB myDB;
    private int point;//按鈕聲
    private SoundPool soundPool;
    int score=0;
    private RelativeLayout mLayout;
    /*Handler handlerback;
    Runnable runnableback;*/
    Date mDate = new Date();
    int mhour;
    //private SumTime sumTime;
    //private MyHandler myHandler=new MyHandler();
    static int test_statusimg[]={R.mipmap.game_score,R.mipmap.game_score1, R.mipmap.game_score2, R.mipmap.game_score3, R.mipmap.game_score4, R.mipmap.game_score5, R.mipmap.game_score6, R.mipmap.game_score7, R.mipmap.game_score8, R.mipmap.game_score9, R.mipmap.game_score10};
    //static int shelter_statusimg[]={R.mipmap.game_total,R.mipmap.game_total1000, R.mipmap.game_total2000, R.mipmap.game_total3000};

    private Handler handler = new Handler();
    private Runnable updateTimer = new Runnable() {
        public void run() {
            changeStatus();
            SQLiteDatabase db = myDB.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("EAT", status[0]);
            values.put("BATH", status[1]);
            values.put("PLAY", status[2]);
            values.put("HAPPY", status[3]);
            db.update("DBtable", values, null, null);


            myDB.close();
            handler.postDelayed(this, 600000);

        }
    };
    Handler handler_poo = new Handler();
    Runnable runnable_poo = new Runnable(){

        @Override

        public void run() {
            if (poo_1.getVisibility()==View.INVISIBLE) {
                poo_1.setVisibility(View.VISIBLE);
            }

            else if (poo_4.getVisibility()==View.INVISIBLE){
                poo_4.setVisibility(View.VISIBLE);

            }
            else if (poo_3.getVisibility()==View.INVISIBLE){
                poo_3.setVisibility(View.VISIBLE);
            }
            else if (poo_2.getVisibility()==View.INVISIBLE){
                poo_2.setVisibility(View.VISIBLE);

            }
            handler_poo.postDelayed(runnable_poo,30000);

            // 是延時時長

        }

    };



    //handler_poo.postDelayed(runnable_poo, 30000);// 打開定時器，執行操作

    //handler_poo.removeCallbacks(runnable_poo);// 關閉定時器處理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setTitle("Catdeo");


        handler.removeCallbacks(updateTimer);
        handler.postDelayed(updateTimer, 600000);
        handler_poo.removeCallbacks(runnable_poo);
        handler_poo.postDelayed(runnable_poo,30000);
        //音樂
        mp = MediaPlayer.create(this,R.raw.background);
        mp.setLooping(true);
        mp.start();
        //貓咪動畫
        startAnimation();


        myDB = new StatusDB(this, "MyDB", null, 1);

        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);

        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        final Cursor myCursor=myDB.selectDB();
        if (isFirstRun){
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            if(!myCursor.moveToFirst()) {
                myDB.initials();
            }
           // sumTime=new SumTime();  設置5天結束遊戲 跑不動

           // sumTime.start();

        }
        else {
            if(myCursor.moveToFirst()) {
                status[0] = myCursor.getInt(1);
                status[1] = myCursor.getInt(2);
                status[2] = myCursor.getInt(3);
                status[3] = myCursor.getInt(4);
                status[4] = myCursor.getInt(5);
            }
        }

        score_happy= (TextView) findViewById(R.id.score_textView);
        score=status[3];
        score_happy.setText(String.valueOf(status[3]));

        mLayout= (RelativeLayout) findViewById(R.id.RelativeLayout);
        /*handlerback = new Handler();
        runnableback = new Runnable() {
            @Override
            public void run() {
                Resources res = getResources();
                Drawable drawable;
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                if(hour <= 6){
                    drawable = res.getDrawable(R.drawable.back_night);
                    mLayout.setBackgroundDrawable(drawable);
                }
                else if(hour > 6 && hour <= 15){
                    drawable = res.getDrawable(R.drawable.back_morning);
                    mLayout.setBackgroundDrawable(drawable);
                }
                else if(hour >=16 && hour <=18)
                {
                    drawable = res.getDrawable(R.drawable.back_evening);
                    mLayout.setBackgroundDrawable(drawable);
                }
                else if (hour > 18)
                {
                    drawable = res.getDrawable(R.drawable.back_night);
                    mLayout.setBackgroundDrawable(drawable);
                }
                handlerback.postDelayed(runnableback,3000000);

            }
        };
        handlerback.postDelayed(runnableback,3000000);*/
        Resources res = getResources();
        Drawable drawable;
       /* Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);*/
        mhour=mDate.getHours();


        //�H�ɶ��ܧ�I����
        switch(mhour) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                drawable = res.getDrawable(R.drawable.back_night);
                mLayout.setBackgroundDrawable(drawable);
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                drawable = res.getDrawable(R.drawable.back_morning);
                mLayout.setBackgroundDrawable(drawable);
                break;
            case 16:
            case 17:
            case 18:
            case 19:
                drawable = res.getDrawable(R.drawable.back_evening);
                mLayout.setBackgroundDrawable(drawable);
                break;
            case 20:
            case 21:
            case 22:
            case 23:
            case 0:
                drawable = res.getDrawable(R.drawable.back_night);
                mLayout.setBackgroundDrawable(drawable);
                break;
            default:
                break;
        }


        poo_1= (ImageView) findViewById(R.id.poo1);
        poo_2= (ImageView) findViewById(R.id.poo2);
        poo_3= (ImageView) findViewById(R.id.poo3);
        poo_4= (ImageView) findViewById(R.id.poo4);


        brush_btn= (ImageButton) findViewById(R.id.brush_img_Button);
        poopoo_btn= (ImageButton) findViewById(R.id.poopoo_img_Button);
        eat_btn= (ImageButton) findViewById(R.id.eat_img_Button);
        play_btn= (ImageButton) findViewById(R.id.play_img_Button);
        bath_btn= (ImageButton) findViewById(R.id.bath_img_Button);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        point = soundPool.load(this, R.raw.water_drop, 1);
        video007= (VideoView) findViewById(R.id.videoView007);
        relay= (RelativeLayout) findViewById(R.id.relay_video);

        eat_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                if (status[0] >= 10) {
                    //出現dialog
                    final Dialog eat_dialog = new Dialog(MainActivity.this, R.style.selectorDialog);
                    eat_dialog.setContentView(R.layout.eat_dialog);
                    Button OK = (Button) eat_dialog.findViewById(R.id.eat_OK_bt);
                    eat_dialog.show();

                    OK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            eat_dialog.dismiss();
                        }
                    });

                    status[0]=10;
                } else {
                    relay.setVisibility(View.VISIBLE);

                    Random rank = new Random();
                    int num = rank.nextInt(4) + 1;
                    video007.setVideoPath("storage/emulated/0/Movies/eat/eat" + num + ".mp4");
                    video007.setVideoPath("storage/emulated/0/Movies/eat/eat" + num + ".mp4");
                    video007.requestFocus();
                    video007.start();
                    video007.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setVolume(0, 0);
                        }
                    });

                    video007.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            video007.stopPlayback(); //video007.release();
                            relay.setVisibility(View.INVISIBLE);
                        }
                    });


                   /* Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_feed.class);
                    startActivity(intent);*/



                    status[0]++;
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));
                }


            }
        });
        bath_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                if (status[1] >= 10) {
                    //出現dialog
                    final Dialog bath_dialog = new Dialog(MainActivity.this, R.style.selectorDialog);
                    bath_dialog.setContentView(R.layout.bath_dialog);
                    Button OK = (Button) bath_dialog.findViewById(R.id.bath_OK_bt);
                    bath_dialog.show();

                    OK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            bath_dialog.dismiss();
                        }
                    });

                    status[1]=10;
                } else {
                   /* Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_shower.class);
                    startActivity(intent);*/
                    status[1]++;
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));
                }


            }
        });
        play_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                if (status[2] >= 10) {
                    //出現dialog
                    final Dialog play_dialog = new Dialog(MainActivity.this, R.style.selectorDialog);
                    play_dialog.setContentView(R.layout.play_dialog);
                    Button OK = (Button) play_dialog.findViewById(R.id.play_OK_bt);
                    play_dialog.show();

                    OK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            play_dialog.dismiss();
                        }
                    });

                    status[2]=10;
                } else {
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_play.class);
                    startActivity(intent);
                    status[2]++;
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));
                }

            }
        });


        poopoo_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                if (poo_1.getVisibility()==View.INVISIBLE&&poo_2.getVisibility()==View.INVISIBLE&&poo_3.getVisibility()==View.INVISIBLE&&poo_4.getVisibility()==View.INVISIBLE) {
                    //出現dialog
                    final Dialog poo_dialog = new Dialog(MainActivity.this, R.style.selectorDialog);
                    poo_dialog.setContentView(R.layout.poo_dialog);
                    Button OK = (Button) poo_dialog.findViewById(R.id.poo_OK_bt);
                    poo_dialog.show();

                    OK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            poo_dialog.dismiss();
                        }
                    });


                } else if (poo_3.getVisibility()==View.VISIBLE){
                    /*Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_cleanpoo.class);
                    startActivity(intent);*/
                    poo_3.setVisibility(View.INVISIBLE);
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));

                }else if (poo_2.getVisibility()==View.VISIBLE){
                   /* Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_cleanpoo.class);
                    startActivity(intent);*/
                    poo_2.setVisibility(View.INVISIBLE);
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));

                }else if(poo_4.getVisibility()==View.VISIBLE){
                    /*Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_cleanpoo.class);
                    startActivity(intent);*/
                    poo_4.setVisibility(View.INVISIBLE);
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));

                }else if(poo_1.getVisibility()==View.VISIBLE){
                    /*Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_cleanpoo.class);
                    startActivity(intent);*/
                    poo_1.setVisibility(View.INVISIBLE);
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));

                }

            }
        });
        brush_btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                if (status[1] >= 10) {
                    //出現dialog
                    final Dialog brush_dialog = new Dialog(MainActivity.this, R.style.selectorDialog);
                    brush_dialog.setContentView(R.layout.brush_dialog);
                    Button OK = (Button) brush_dialog.findViewById(R.id.brush_OK_bt);
                    brush_dialog.show();

                    OK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            brush_dialog.dismiss();
                        }
                    });

                    status[1]=10;
                } else {
                    /*Intent intent=new Intent();
                    intent.setClass(MainActivity.this ,Game_video_brush.class);
                    startActivity(intent);*/
                    status[1]++;
                    status[3]++;
                    score_happy.setText(String.valueOf(status[3]));
                }

            }
        });


        questionBt= (ImageButton) findViewById(R.id.direction_imageButton);

        questionBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog direction_dialog = new Dialog(MainActivity.this, R.style.DialogTheme);
                direction_dialog.setContentView(R.layout.direction_dialog);
                Button q_OK = (Button) direction_dialog.findViewById(R.id.direction_OKbutton);
                direction_dialog.show();

                q_OK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        direction_dialog.dismiss();
                    }
                });

            }
        });




    }
    /*class MyHandler extends Handler{  設置5天結束遊戲  跑不動


        @Override

        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch(msg.what) {

                case 0:

                    sumTime.interrupt();

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this , End_Victory.class);
                    startActivity(intent);

                    break;
            }
        }
    }
    class SumTime extends Thread {

        @Override
        public void  run(){
            super.run();
            try{
                //sleep(432000000);
                sleep(60000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            Message message=Message.obtain();

            message.what=0;

            myHandler.sendMessage(message);

        }
    }*/

    @Override

    public boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);

        menu.add(0,0,0,"選單");

        menu.add(0,2,0,"影片收藏");

        menu.add(0,1,1,"離開");

        return true;

    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item){

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){

            case 0:

                Intent intent = new Intent();

                intent.setClass(MainActivity.this,GameActivity.class);

                startActivity(intent);

                break;

            case 1:

                finish();

            case 2:

                Intent intent_video = new Intent();

                intent_video.setClass(MainActivity.this,Game_video_favorite_Activity.class);

                startActivity(intent_video);
        }

        return true;

    }


    private void startAnimation(){
        switch(status[4]){
            case 0:

            case 1:

                animation_iv= (ImageView) findViewById(R.id.cat_animation_view);
                AnimationDrawable ad1 = (AnimationDrawable) getResources().getDrawable(
                        R.drawable.cat_video);
                animation_iv.setBackgroundDrawable(ad1);
                ad1.start();
                break;
            case 2:

                animation_iv= (ImageView) findViewById(R.id.cat_animation_view);
                AnimationDrawable ad2 = (AnimationDrawable) getResources().getDrawable(
                        R.drawable.cat_video2);
                animation_iv.setBackgroundDrawable(ad2);
                ad2.start();
                break;
            case 3:

                animation_iv= (ImageView) findViewById(R.id.cat_animation_view);
                AnimationDrawable ad3 = (AnimationDrawable) getResources().getDrawable(
                        R.drawable.cat_video3);
                animation_iv.setBackgroundDrawable(ad3);
                ad3.start();
                break;



        }


    }

    public void changeStatus(){
        if(status[0]>0) {
            status[0]--;
        }
        if(status[1]>0){
            status[1]--;
        }
        if(status[2]>0){
            status[2]--;
        }
        if(poo_1.getVisibility()==View.VISIBLE){
            status[3]--;
            score_happy.setText(String.valueOf(status[3]));
        }
        if(poo_2.getVisibility()==View.VISIBLE){
            status[3]--;
            score_happy.setText(String.valueOf(status[3]));
        }
        if(poo_3.getVisibility()==View.VISIBLE){
            status[3]--;
            score_happy.setText(String.valueOf(status[3]));
        }
        if(poo_4.getVisibility()==View.VISIBLE){
            status[3]--;
            score_happy.setText(String.valueOf(status[3]));
        }

        if(status[0]<=0) {
            status[3]--;
            score_happy.setText(String.valueOf(status[3]));

            if(status[1]<=0) {
                status[3]--;
                score_happy.setText(String.valueOf(status[3]));

                if(status[2]<=0) {
                    status[3]--;
                    score_happy.setText(String.valueOf(status[3]));
                }
            }
        }
        if(status[0]==0){
            status[0]=0;
        }
        if(status[1]==0){
            status[1]=0;
        }
        if(status[2]==0){
            status[2]=0;
        }
        if(status[3]==0){
            status[3]=0;
            score_happy.setText(String.valueOf(status[3]));
        }
        if(status[4]==0){
            status[4]=0;
        }

        //貓咪逃走
        if(status[0]<=0 || status[1]<=0 || status[2]<=0 || status[3]<=0){
            status[4]=status[3]+status[3];

            Intent end = new Intent();
            end.setClass(MainActivity.this, End.class);
            startActivity(end);
            MainActivity.this.finish();

        }
        myDB.upStatus(status);

    }
    protected void onResume(){
        super.onResume();

        mp.start();
        mp.setLooping(true);
    }

    protected void onPause(){
        super.onPause();
        mp.pause();
    }
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
        mp=null;
    }

}
