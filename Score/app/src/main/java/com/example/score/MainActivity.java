package com.example.score;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Download download=new Download();
        try {


        Log.i("data",download.execute("https://www.google.com/search?q=ipl+score&oq=ipl&aqs=chrome.0.69i59j69i57j69i59j46j0j69i61j69i60j69i61.1425j1j7&sourceid=chrome&ie=UTF-8#sie=m;/g/11k7xtxhgk;5;/m/03b_lm1;cms;fp;1;;").get());
    }catch (Exception e){
            e.printStackTrace();
        }
    }
    public class Download extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String info="";
            try {


            URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();
                while (data!=-1)
                {
                    char temp=(char)data;
                    info+=temp;
                    data=inputStreamReader.read();
                }
                return info;
        }catch (Exception e){
                e.printStackTrace();
                return "Error";
            }
        }
    }
}
