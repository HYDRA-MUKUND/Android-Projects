package com.example.h_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=this.getSharedPreferences("com.example.h_admin", Context.MODE_PRIVATE);
        final String checker=sharedPreferences.getString("code","none");

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checker.equals("done")){
                Intent intent=new Intent(MainActivity.this,Types.class);
                startActivity(intent);}
                else {
                    Intent intent=new Intent(MainActivity.this,logincheck.class);
                    startActivity(intent);
                }
                finish();
            }
        },1500);
    }
}
