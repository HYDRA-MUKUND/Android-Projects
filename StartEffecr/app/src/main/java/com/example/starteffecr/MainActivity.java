package com.example.starteffecr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button.animate().translationYBy(-100).rotationXBy(180).setDuration(1000);
         okk();
    }

    private void okk() {
        button.animate().translationYBy(100).rotationXBy(180).setDuration(1000);
    }

}
