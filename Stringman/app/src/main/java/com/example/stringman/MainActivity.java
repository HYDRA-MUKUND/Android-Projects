package com.example.stringman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    public void onclick(View view) {
        Bitmap strf;
        imageDown gh = new imageDown();
        try {

        strf=gh.execute("https://i.pinimg.com/originals/4c/a3/e6/4ca3e679afd3243941fb9424c9ac09c0.png").get();
           img.setImageBitmap(strf);
           img.buildDrawingCache();
           Bitmap serr=img.getDrawingCache();
            WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
            wallpaperManager.setBitmap(serr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          img=(ImageView)findViewById(R.id.imagedown);



    }
    public class imageDown extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... str) {
            try {
                URL url=new URL(str[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                Bitmap bit= BitmapFactory.decodeStream(inputStream);
                return  bit;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
