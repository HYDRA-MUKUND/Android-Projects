package com.example.counterproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity<textView1> extends AppCompatActivity {


     TextView textView1;

    private SeekBar seekBar;
   int min,temp1,temp2;
   int sec;

    MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.vehicle039);
    public  void clickme1(View view){

        textView1.setText("00:30");
        seekBar.setEnabled(true);
        seekBar.setProgress(30);


    }
    public void clickme2(View view){
        seekBar.setEnabled(false);
        temp1 = --sec;
        temp2 = min;
        new CountDownTimer(((min*60)+sec)*1000, 1000) {
            @Override
            public void onTick(long l) {

                if(temp1!=0) {
                    textView1.setText(Integer.toString(min) + ":" + Integer.toString(sec));
                    temp1--;
                }
                else {
                    if(temp2!=0)
                    {
                        temp2--;
                        temp1=59;
                        textView1.setText(Integer.toString(min) + ":" + Integer.toString(sec));
                    }
                }
            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Context context;
        setContentView(R.layout.activity_main);
        textView1=(TextView)findViewById(R.id.textView1);
         seekBar=(SeekBar)findViewById(R.id.seekBar);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                min=i/60;
                sec=i-(min*60);
               textView1.setText(Integer.toString(min)+":"+Integer.toString(sec));

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
