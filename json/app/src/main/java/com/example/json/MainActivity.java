package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public  class  jj extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String temp = "";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();
                while (data!=-1)
                {
                    char tempo=(char)data;
                    temp+=tempo;
                    data=inputStreamReader.read();
                }
                return temp;
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


           try {
               JSONObject jsonObject=new JSONObject(s);
             String  hh = jsonObject.getString("weather");
               JSONArray jsonArray=new JSONArray(hh);
               for(int i=0;i<jsonArray.length();i++)
               {
                   JSONObject jjj=jsonArray.getJSONObject(i);
                   Log.i("info",jjj.getString("main"));
                   Log.i("info",jjj.getString("description"));
               }
           }catch (Exception e)
           {
               e.printStackTrace();
           }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jj obj=new jj();
        obj.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");
    }
}
