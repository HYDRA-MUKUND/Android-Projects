package com.example.audioprac;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
MediaPlayer media;
AudioManager audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audio=(AudioManager)getSystemService(AUDIO_SERVICE);

        media=MediaPlayer.create(this,R.raw.pract);
        media.start();
        int max=audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int cuu=audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar obj=(SeekBar)findViewById(R.id.volumseek);
        obj.setMax(max);
        obj.setProgress(cuu);
        obj.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar gg=(SeekBar)findViewById(R.id.typepass);
        gg.setMax(media.getDuration());
        gg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Value",Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gg.setProgress(media.getCurrentPosition());
            }
        },0 , 100 );




    }
}
