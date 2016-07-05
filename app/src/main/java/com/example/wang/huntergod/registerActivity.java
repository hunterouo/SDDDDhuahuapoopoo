package com.example.wang.huntergod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(registerActivity.this,loginActivity.class );
                startActivity(intent);
            }
        });

        Spinner Spinner1 = (Spinner)findViewById(R.id.spinner1);

        //設定功能表項目陣列，使用createFromResource()

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.items,

                android.R.layout.simple_spinner_item);

        //設定選單

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //設定adapter

        Spinner1.setAdapter(adapter);


    }
}
