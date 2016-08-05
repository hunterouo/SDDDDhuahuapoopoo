package com.example.wang.huntergod;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class End extends Activity {

    Button okBtn;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //背景音樂
        mp = MediaPlayer.create(this,R.raw.bearbig);
        mp.setLooping(false);
        mp.start();

        okBtn= (Button) findViewById(R.id.OKbutton);
        okBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提供優惠券
            }
        });
    }


}

