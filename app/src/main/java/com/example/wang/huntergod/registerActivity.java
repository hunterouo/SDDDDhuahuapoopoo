package com.example.wang.huntergod;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.v("Login Result", "start login");
                EditText username = (EditText)findViewById(R.id.editText3);
                EditText password = (EditText)findViewById(R.id.editText4);
                RadioButton gender=(RadioButton)findViewById(R.id.btnMan);
                int sex;
                if(gender.isChecked()){
                    sex=0;
                }
                else{
                    sex=1;
                }

                Spinner spr=(Spinner)findViewById(R.id.spinner1);

                String[] param = {username.getText().toString(), password.getText().toString(),Integer.toString(sex),spr.getSelectedItem().toString()};

                new RegisterTask().execute(param);

            }
        });



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

    private class RegisterTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String responseString = "";
            HttpURLConnection connection = null;

            try {
                // 初始化 URL
                String username = params[0];
                String password = params[1];
                String gender=params[2];
                String y=params[3];

                URL mURL = new URL("http://163.13.201.93/android_login_api/register1.php");
                // 调用URL的openConnection()方法,获取HttpURLConnection对象
                Log.v("Login Result", "open connection");

                connection = (HttpURLConnection) mURL.openConnection();
                connection.setRequestMethod("POST");
                // 設定 request timeout
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(10000);
                // 模擬 Chrome 的 user agent, 因為手機的網頁內容較不完整
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
                // 設定開啟自動轉址
                connection.setInstanceFollowRedirects(true);
                connection.setDoOutput(true);// 设置此方法,允许向服务器输出内容
                String data = "username=" + username + "&password=" + password+"&gender="+gender+"&year="+y;

                OutputStream out = connection.getOutputStream();// 获得一个输出流,向服务器写数据
                out.write(data.getBytes());
                out.flush();
                out.close();

                // 若要求回傳 200 OK 表示成功取得網頁內容
                if( connection.getResponseCode() == HttpURLConnection.HTTP_OK ){
                    Log.v("Login Result", "receive http code");
                    // 讀取網頁內容
                    InputStream     inputStream     = connection.getInputStream();
                    BufferedReader  bufferedReader  = new BufferedReader( new InputStreamReader(inputStream) );

                    String tempStr;
                    StringBuffer stringBuffer = new StringBuffer();

                    while( ( tempStr = bufferedReader.readLine() ) != null ) {
                        stringBuffer.append( tempStr );
                    }

                    bufferedReader.close();
                    inputStream.close();

                    // 取得網頁內容類型
                    String  mime = connection.getContentType();
                    boolean isMediaStream = false;

                    // 判斷是否為串流檔案
                    if( mime.indexOf("audio") == 0 ||  mime.indexOf("video") == 0 ){
                        isMediaStream = true;
                    }

                    // 網頁內容字串
                    responseString = stringBuffer.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                // 中斷連線
                if( connection != null ) {
                    connection.disconnect();
                }
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v("Register Result", result);
            Toast.makeText(registerActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(registerActivity.this, loginActivity.class);
            registerActivity.this.startActivity(intent);
            ((Activity) registerActivity.this).finish();
        }

    }

}