package com.example.downloadwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public  class web extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urll) {
            String str = "";
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            try {


                url = new URL(urll[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = (InputStream) httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = (int) inputStreamReader.read();
                while (data != -1) {
                    char temp = (char) data;
                    str += temp;
                    data = (int) inputStreamReader.read();
                }
                return str;
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

        web gg=new web();
        String rr= null;
        try {


            rr = gg.execute("https://www.zappycode.com").get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i("output",rr);
    }
}
