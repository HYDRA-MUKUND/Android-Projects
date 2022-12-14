package com.example.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ProgressBar progressBar2;
int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar2.setMax(100);
        progressBar2.setProgress(a);
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
              a+=10;
              progressBar2.setProgress(a);
            }

            @Override
            public void onFinish() {
                progressBar2.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

}
