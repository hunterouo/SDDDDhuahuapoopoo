package com.example.wang.huntergod;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class AddCatActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UPLOAD_URL = "http://163.13.201.93/catdata/cat.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;

    private EditText catname;
    private EditText catsex;
    private Spinner catadopt;
    private ImageView imageView;

    private Bitmap bitmap;
    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);

        buttonChoose = (Button) findViewById(R.id.buttonLoadPicture);
        buttonUpload = (Button) findViewById(R.id.button20);
        catname=(EditText)findViewById(R.id.editText8);
        catsex=(EditText)findViewById(R.id.editText9);
        catadopt = (Spinner)findViewById(R.id.spinner4);

        imageView = (ImageView) findViewById(R.id.imgView);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.adapt, android.R.layout.simple_spinner_item);
        //設定選單
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //設定adapter
        catadopt.setAdapter(adapter);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



    private void uploadImage(String[] pa){
        final String name=pa[0];
        final String sex=pa[1];
        final String adopt=pa[2];
        class UploadImage extends AsyncTask<Bitmap,Void,String>{



            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddCatActivity.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }


            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];



                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put("catname",name);
                data.put("catgender",sex);
                data.put("catadopt",adopt);
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

   /* private class DownloadImage extends AsyncTask<Void,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... params) {

            return null;
        }
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
        }

    }
*/
    @Override
    public void onClick(View v) {
        String[] p={catname.getText().toString(),catsex.getText().toString(),catadopt.getSelectedItem().toString()};
        if (v == buttonChoose) {
            showFileChooser();
        }
        if(v == buttonUpload){
            uploadImage(p);
            Intent resultData = new Intent();
            resultData.putExtra("catname", catname.getText().toString());
            resultData.putExtra("catgender", catsex.getText().toString());
            resultData.putExtra("catadopt", catadopt.getSelectedItem().toString());

            setResult(1001, resultData);
            AddCatActivity.this.finish();

        }
    }
}
