package com.example.wang.huntergod;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class editmidinforActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmidinfor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Log.v("Login Result", "start login");
                EditText homename = (EditText)findViewById(R.id.editText);
                EditText email = (EditText)findViewById(R.id.editText5);
                EditText address = (EditText)findViewById(R.id.editText6);
                EditText html = (EditText)findViewById(R.id.editText7);

                String[] param = {homename.getText().toString(), email.getText().toString(),address.getText().toString(),html.getText().toString()};

                new editmidinTask().execute(param);

                Intent resultData = new Intent();
                resultData.putExtra("homename", homename.getText().toString());
                resultData.putExtra("email", email.getText().toString());
                resultData.putExtra("address", address.getText().toString());
                resultData.putExtra("html", html.getText().toString());
                setResult(1001, resultData);
                editmidinforActivity.this.finish();
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.finishmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    private class editmidinTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String responseString = "";
            HttpURLConnection connection = null;

            try {
                // 初始化 URL
                String homename = params[0];
                String email = params[1];
                String address=params[2];
                String html=params[3];

                URL mURL = new URL("http://163.13.201.93/homedata/midin.php");
                // 调用URL的openConnection()方法,获取HttpURLConnection对象
                Log.v("editmidin Result", "open connection");

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
                String data = "homename=" + homename + "&email=" + email+"&address="+address+"&html="+html;

                OutputStream out = connection.getOutputStream();// 获得一个输出流,向服务器写数据
                out.write(data.getBytes());
                out.flush();
                out.close();

                // 若要求回傳 200 OK 表示成功取得網頁內容
                if( connection.getResponseCode() == HttpURLConnection.HTTP_OK ){
                    Log.v("editmidin Result", "receive http code");
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
            Log.v("editmidin Result", result);
        }

    }
}
