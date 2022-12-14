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

public class ambmysql extends AsyncTask<String,Void,String> {

    AlertDialog alertDialogamb;
    Context contextamb;
    ambmysql(Context temp){
        contextamb=temp;
    }
    @Override
    protected String doInBackground(String... strl) {
    String checks = strl[0];
        if (checks.equals("myambulance")) {
        String login_urll = "http:/192.168.43.6/practice3.php";
        try {
           String psl_address=strl[1];
           String psl_lon=strl[3];
           String psl_ptcount=strl[4];
           String psl_lat=strl[2];
            SimpleDateFormat formats=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date=new Date();


          String timedetails=formats.format(date);


            URL urll = new URL(login_urll);
            HttpURLConnection httpURLConnectionl = (HttpURLConnection) urll.openConnection();
            httpURLConnectionl.setDoInput(true);
            httpURLConnectionl.setDoOutput(true);
            httpURLConnectionl.setRequestMethod("POST");
            OutputStream outputStreaml = httpURLConnectionl.getOutputStream();
            BufferedWriter bufferedWriterl = new BufferedWriter(new OutputStreamWriter(outputStreaml, "UTF-8"));
            String post_datal = URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(psl_address, "UTF-8") + "&" + URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(psl_lat, "UTF-8") + "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(psl_lon, "UTF-8")+ "&" + URLEncoder.encode("noofpat", "UTF-8") + "=" + URLEncoder.encode(psl_ptcount, "UTF-8") + "&" + URLEncoder.encode("timedetails", "UTF-8") + "=" + URLEncoder.encode(timedetails, "UTF-8") ;
            bufferedWriterl.write(post_datal);
            bufferedWriterl.flush();
            bufferedWriterl.close();
            outputStreaml.close();
            InputStream inputStreaml=httpURLConnectionl.getInputStream();
            BufferedReader bufferedReaderl=new BufferedReader(new InputStreamReader(inputStreaml, "iso-8859-1"));
            String resultl="";
            String linereaderl=bufferedReaderl.readLine();
            while (linereaderl!=null){
                resultl+=linereaderl;
                linereaderl=bufferedReaderl.readLine();
            }
            bufferedReaderl.close();
            inputStreaml.close();
            httpURLConnectionl.disconnect();
            return resultl;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        return null;
}

    @Override
    protected void onPreExecute() {
        alertDialogamb=new AlertDialog.Builder(contextamb).create();
        alertDialogamb.setIcon(R.drawable.alert);
        alertDialogamb.setTitle("Ambulance Status.!!");
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialogamb.setMessage(s);
        alertDialogamb.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialogamb.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
