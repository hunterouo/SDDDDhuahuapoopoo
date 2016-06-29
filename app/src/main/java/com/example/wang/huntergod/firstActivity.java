package com.example.wang.huntergod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class firstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        ImageButton imagebutton2= (ImageButton) findViewById(R.id.imageButton2);
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(firstActivity.this,loginActivity.class );
                startActivity(intent);
            }
        });
        ImageButton imagebutton1= (ImageButton) findViewById(R.id.imageButton1);
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(firstActivity.this,loginmidActivity.class );
                startActivity(intent);
            }
        });
    }
}
