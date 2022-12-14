package com.example.flashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
         Handler handler=new Handler();
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
              Intent intent=new Intent(MainActivity.this,Main2Activity.class);
              startActivity(intent);
              finish();
             }
         },2000);

    }


    }
