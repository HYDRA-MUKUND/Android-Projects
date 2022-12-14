package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

public class mysqldb extends AsyncTask<String,Void,String> {
    Context context;
    mysqldb(Context temp){
        context=temp;
    }
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
