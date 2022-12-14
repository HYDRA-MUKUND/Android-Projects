package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
private  MediaPlayer objaa;
AudioManager obja;

    public void play(View view){
        objaa= MediaPlayer.create(this,R.raw.ok);
        objaa.start();
    }
    public void stop(View view){
        if(objaa.isPlaying())
        {
            objaa.pause();
        }
        else{
            objaa.start();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obja=(AudioManager)getSystemService(AUDIO_SERVICE);
        int max=obja.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        SeekBar obj=(SeekBar)findViewById(R.id.seekBar);
        obj.setMax(max);
        obj.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Value",Integer.toString(i));
                obja.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
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
