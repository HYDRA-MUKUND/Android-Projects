package com.example.buttton_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;



public class Main3Activity extends AppCompatActivity {
InputStream inputStream;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
readData();
    }
    private void readData() {
        inputStream=getResources().openRawResource(R.raw.file);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String lineread;
        String [] datareader;
        try {
            while ((lineread=bufferedReader.readLine())!=null){
                datareader=lineread.split(",");
                Log.i("data",datareader[0]+datareader[1]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
