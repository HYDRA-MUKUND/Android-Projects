package com.example.shiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {
MediaPlayer mediaPlayer;
public  void story(View view){

    Intent intent=new Intent(this,Main2Activity.class);
    startActivity(intent);
}
public void wallpaper(View view){

    Intent intent=new Intent(this,Main4Activity.class);
    startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
}
