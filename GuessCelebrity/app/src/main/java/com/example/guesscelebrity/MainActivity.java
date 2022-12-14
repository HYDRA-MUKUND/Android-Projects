package com.example.guesscelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    int i=0;
    ImageView imageView;

    Button textView1, textView2, textView3, textView4;

    public class cele extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... stri) {
            try {
                 String st1="";
                URL url=new URL(stri[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();
                while (data!=-1)
                {

                 char str2=(char)data;
                 st1=st1+str2;
                 data=inputStreamReader.read();
                }
                return st1;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.celepicture);
        textView1 = (Button) findViewById(R.id.button1);
        textView2 = (Button) findViewById(R.id.button2);
        textView3 = (Button) findViewById(R.id.button3);
        textView4 = (Button) findViewById(R.id.button4);



       cele obj=new cele();
      try {
          String okk=obj.execute("https://www.forbesindia.com/lists/2019-celebrity-100/1819/all").get();
         Log.i("info",okk);

      }catch (Exception e){
          e.printStackTrace();
      }
    }


}
