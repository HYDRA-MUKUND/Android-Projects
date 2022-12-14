package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;

    public class weather extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... str) {
            String store = "";
            try {
                URL url = new URL(str[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char temp = (char) data;
                    store += temp;
                    data = inputStreamReader.read();

                }
                return store;
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null)
            {
                Toast.makeText(MainActivity.this, "Wrong Input", Toast.LENGTH_SHORT).show();
            }
            else
            {
            try {
                JSONObject jsonObject = new JSONObject(s);
                String str1 = jsonObject.getString("main");
                  JSONObject okk=new JSONObject(str1);
                String outputt = okk.getString("temp");








                if (!outputt.equals("")) {
                    double x=Double.parseDouble(outputt);


                    textView.setText(String.valueOf(x));
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }

        }}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.editText);


    }

    public void onclick(View view) {
        try {
            String sgsg = URLEncoder.encode(editText.getText().toString(), "UTF-8");
            weather task = new weather();
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + sgsg + "&appid=439d4b804bc8187953eb36d2a8c26a02");
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Input", Toast.LENGTH_SHORT).show();
        }

    }
}
