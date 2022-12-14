package com.example.h_admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


public class UpPatRecord extends AsyncTask<String,Void,String> {
    private AlertDialog alertDialog;
    private Context context;
    UpPatRecord(Context temp){
        context=temp;
    }
    @Override
    protected String doInBackground(String... str) {
        String checks = str[0];
        if (checks.equals("mydata")) {
            String login_url = "http:/192.168.43.6/updatepatdata.php";
            try {
                String ps_name=str[1];
                String ps_number=str[2];
                String ps_birthdate=str[3];
                String ps_apdate=str[4];
                String ps_weight=str[5];
                String mobileid=str[6];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_datal = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(ps_name, "UTF-8") + "&" + URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(ps_weight, "UTF-8") + "&" + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(ps_number, "UTF-8")+ "&" + URLEncoder.encode("apdate", "UTF-8") + "=" + URLEncoder.encode(ps_apdate, "UTF-8") + "&" + URLEncoder.encode("birthdate", "UTF-8") + "=" + URLEncoder.encode(ps_birthdate, "UTF-8")+ "&" + URLEncoder.encode("mobileid", "UTF-8") + "=" + URLEncoder.encode(mobileid, "UTF-8");
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
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setIcon(R.drawable.updated);
        alertDialog.setTitle("Updation Status..?");
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
