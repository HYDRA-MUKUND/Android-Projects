package com.example.m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
SeekBar seekBar;
Button button;
TextView textView;
public  void onclick(View view){
    seekBar.setEnabled(false);
    button.setText("Stop");

    new CountDownTimer(seekBar.getProgress()*1000,1000) {
        @Override
        public void onTick(long l) {
            score((int)l/1000);
        }

        @Override
        public void onFinish() {
            MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.vehicle039);
            mediaPlayer.start();
        }
    }.start();
}
public  void score(int sec){
    int min=sec/60;
    int secs=sec-(min*60);
    textView.setText(Integer.toString(min)+":"+Integer.toString(secs));
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("value",Integer.toString(i));
                score(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
