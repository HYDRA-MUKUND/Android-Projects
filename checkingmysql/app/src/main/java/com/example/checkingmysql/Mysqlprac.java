package com.example.checkingmysql;

import android.content.Context;
import android.os.AsyncTask;


import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Mysqlprac extends AsyncTask<String,Void,String> {
    Context context;
    Mysqlprac(Context temp){
        context=temp;
    }
    @Override
    protected String doInBackground(String... str) {
        String urls=str[0];
        try {
            URL url=new URL(urls);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result = null;
            String linereader=bufferedReader.readLine();
            while (linereader!=null){
                result+=linereader;
                linereader=bufferedReader.readLine();
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
