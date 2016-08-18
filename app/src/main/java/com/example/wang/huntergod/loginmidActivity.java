package com.example.wang.huntergod;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class loginmidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmid);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("中途登入");

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Login Result", "start login");
                EditText username = (EditText)findViewById(R.id.editTextA);
                EditText password = (EditText)findViewById(R.id.editTextB);
                String[] param = {username.getText().toString(), password.getText().toString()};
                new LoginMidTask().execute(param);



            }
        });
    }
    private class LoginMidTask extends AsyncTask<String, Void, String>{



        @Override
        protected String doInBackground(String... params) {
            String responseString = "";
            HttpURLConnection connection = null;

            try {
                // 初始化 URL
                String username = params[0];
                String password = params[1];
                String data = "username=" + URLEncoder.encode(username, "UTF-8") + "&password="+ URLEncoder.encode(password, "UTF-8");
                String urlString = "http://163.13.201.93/home/login2.php?" + data;
                URL url = new URL(urlString);
                // 取得連線物件
                Log.v("Login Result", "open connection");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                // 設定 request timeout
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(10000);
                // 模擬 Chrome 的 user agent, 因為手機的網頁內容較不完整
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
                // 設定開啟自動轉址
                connection.setInstanceFollowRedirects(true);

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
            Log.v("Login Result", result);
            super.onPostExecute(result);
            if(result.equals(""))
                Toast.makeText(loginmidActivity.this, "請輸入正確的帳號密碼", Toast.LENGTH_LONG).show();
            else{

                Toast.makeText(loginmidActivity.this, "welcome", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(loginmidActivity.this, midinforActivity.class);
                loginmidActivity.this.startActivity(intent);
                ((Activity) loginmidActivity.this).finish();
            }



        }

    }
}
