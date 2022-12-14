package com.example.h_admin;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DeleteAmbData extends AsyncTask<String,Void,String> {
    private Context context;
    DeleteAmbData(Context temp){
        context=temp;
    }


    @Override
    protected String doInBackground(String... strings) {
        String checks = strings[0];
        if (checks.equals("mydataa")) {
            String login_url = "http:/192.168.43.6/deleteambdata.php";
            try {
                String ps_datee=strings[1];




                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_datal = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(ps_datee, "UTF-8") ;
                bufferedWriter.write(post_datal);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
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
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
