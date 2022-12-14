package com.hydra.ieee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class   MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

       Handler handler=new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {

               Intent intent=new Intent(MainActivity.this,Types.class);
               startActivity(intent);
               finish();
           }
       },1000);

    }
}