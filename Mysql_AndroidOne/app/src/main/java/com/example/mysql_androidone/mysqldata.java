package com.example.mysql_androidone;

import android.app.AlertDialog;
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


public class mysqldata extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context context;
    mysqldata(Context sample){
        context=sample;
    }
    @Override
    protected String doInBackground(String... str) {

        String check=str[0];
        if(check.equals("save")) {
            String login_url = "http:/192.168.43.6/practice2.php";
            try {
                 String ps_name=str[1];
                 String ps_age=str[2];
                 String ps_number=str[3];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(ps_name,"UTF-8")+"&"+URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(ps_age,"UTF-8")+"&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(ps_number,"UTF-8");
          bufferedWriter.write(post_data);
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
            } catch (Exception e){
                e.printStackTrace();

            }

        }

return null;
    }

    @Override
    protected void onPostExecute(String s) {
       alertDialog.setMessage(s);
       alertDialog.show();
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Request Status");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
