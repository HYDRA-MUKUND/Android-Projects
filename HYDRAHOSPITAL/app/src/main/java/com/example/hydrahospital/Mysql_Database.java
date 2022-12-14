package com.example.hydrahospital;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mysql_Database extends AsyncTask<String,Void,String> {
   private AlertDialog alertDialog;
    private Context context;
    Mysql_Database(Context temp){
        context=temp;
    }
    @Override
    protected String doInBackground(String... str) {
        String checks = str[0];
        if (checks.equals("mydata")) {
            String login_url = "http:/192.168.43.6/practice2.php";
            try {
                String ps_name=str[1];
                String ps_age=str[2];
                String ps_dob=str[3];
                String ps_mobile=str[4];
                String ps_gender=str[5];
                String ps_type=str[6];
                String ps_condition=str[7];

                SimpleDateFormat formatt=new SimpleDateFormat("dd/MM/yyyy");
                Date datee=new Date();


                String timedate=formatt.format(datee);


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_datal = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(ps_name, "UTF-8") + "&" + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(ps_age, "UTF-8") + "&" + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(ps_mobile, "UTF-8")+ "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(ps_type, "UTF-8") + "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(ps_gender, "UTF-8")+ "&" + URLEncoder.encode("birthdate", "UTF-8") + "=" + URLEncoder.encode(ps_dob, "UTF-8")+ "&" + URLEncoder.encode("condition", "UTF-8") + "=" + URLEncoder.encode(ps_condition, "UTF-8")+ "&" + URLEncoder.encode("timedate", "UTF-8") + "=" + URLEncoder.encode(timedate, "UTF-8") ;
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
        alertDialog.setIcon(R.drawable.alert);
        alertDialog.setTitle("Appointment Status.!!");
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


