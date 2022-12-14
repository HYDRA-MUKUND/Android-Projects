package com.example.downloadimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void down(View view){



        ImageDownloader imageDownloader=new ImageDownloader();
        Bitmap bitmap;
        try {


            bitmap=imageDownloader.execute("https://upload.wikimedia.org/wikipedia/en/0/02/Homer_Simpson_2006.png").get();
           imageView.setImageBitmap(bitmap);
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
        imageView=(ImageView) findViewById(R.id.imageView);


    }
    public static  class ImageDownloader extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... str) {
            try {
                URL url =new URL(str[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();

                Bitmap bitmapa= BitmapFactory.decodeStream(inputStream);
                return bitmapa;

            } catch (Exception e) {

                e.printStackTrace();
                return null;

            }
        }
    }
}
