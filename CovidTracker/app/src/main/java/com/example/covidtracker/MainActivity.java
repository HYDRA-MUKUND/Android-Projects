package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        download obj=new download();
        obj.execute("")

    }
    public class download extends AsyncTask<String ,Void, String>{

        @Override
        protected String doInBackground(String... str) {
            String okk="";
            try {
                URL url=new URL(str[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();
                while (data!=-1)
                {
                    char temp=(char)data;
                    okk+=temp;
                    data=inputStreamReader.read();
                }
                return  okk;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

}
