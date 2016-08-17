package com.example.wang.huntergod;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityVideoShower extends Activity {

    private static final int SELECT_AUDIO = 2;
    private Uri selectedVideo;
    private Button choose;
    private Button complete;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__choose);
        choose=(Button)findViewById(R.id.button16);
        complete=(Button)findViewById(R.id.button_video);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGalleryAudio();

            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int permissionCheck = ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            (Activity)v.getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                } else {
                    UploadVideo(v.getContext().getContentResolver());

                }
            }
        });



    }


    public void UploadVideo(ContentResolver resolver) {

        UploadShower i=new UploadShower();
        try {
            InputStream stream = resolver.openInputStream(selectedVideo);
            Cursor cursor = resolver.query(selectedVideo, null, null, null, null);
            cursor.moveToFirst();
            String fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            i.upload(fileName, stream);
        } catch(Exception ex){
            Log.v("Context", ex.toString());
        };
        return;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 100:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    UploadVideo(this.getContentResolver());
                }
                break;

            default:
                break;
        }
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

    public void openGalleryAudio(){
        Intent intent = new Intent( Intent.ACTION_PICK );

        // 過濾檔案格式
        intent.setType( "video/*" );

        // 建立 "檔案選擇器" 的 Intent  (第二個參數: 選擇器的標題)
        Intent destIntent = Intent.createChooser( intent, "選擇檔案" );

        // 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
        startActivityForResult( destIntent, 0 );

 /*       Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Audio "), SELECT_AUDIO);*/

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {



            Uri selectedImageUri = data.getData();
            //        selectedPath = getPath(selectedImageUri);
            if(selectedImageUri!=null){
                VideoView iv = (VideoView)this.findViewById(R.id.videoView01);
                iv.setVideoURI( selectedImageUri );
                setTitle( selectedImageUri.toString() );
                selectedVideo = selectedImageUri;
            }
            else{
                setTitle("無效的檔案路徑 !!");
            }

        }
        else{
            setTitle("取消選擇檔案 !!");
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



}
class UploadShower {

    InputStream stream = null;

    public void upload(String fileName, InputStream stream) throws IOException {



        this.stream = stream;
        String[] params = {fileName};
        new VideoUploadTask().execute(params);
    }

    private class VideoUploadTask extends AsyncTask<String, Void, String> {




        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            DataOutputStream outputStream = null;
            DataInputStream inputStream = null;

            String urlServer = "http://163.13.201.93/video/shower_upload.php";
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            String fileName = params[0];

            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1024 * 1024;
            String serverResponseMessage = "";

            try {


                URL url = new URL(urlServer);
                connection = (HttpURLConnection) url.openConnection();

                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type",
                        "multipart/form-data;boundary=" + boundary);

                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream
                        .writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
                                + fileName + "\"" + lineEnd);
                outputStream.writeBytes(lineEnd);

                bytesAvailable = stream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                bytesRead = stream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    outputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = stream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = stream.read(buffer, 0, bufferSize);
                }

                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + twoHyphens
                        + lineEnd);

                int serverResponseCode = connection.getResponseCode();
                BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line).append('\n');
                }
                serverResponseMessage = total.toString();

                stream.close();
                outputStream.flush();
                outputStream.close();
            } catch (Exception ex) {
                Log.v("Context", ex.toString());
                serverResponseMessage = ex.toString();
            }
            return serverResponseMessage;
        }
        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC",progress[0]);

        }
        @Override
        protected void onPreExecute() {

            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(String result) {
            Log.v("Video Upload", result);

        }
    }
}

