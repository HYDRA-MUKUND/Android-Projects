package com.example.project6video;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       VideoView videoView=findViewById(R.id.videoView);

       MediaController hydra=new MediaController(this);
       hydra.setAnchorView(videoView);
       videoView.setMediaController(hydra);
        videoView.setVideoURI(Uri.parse("android.resourse://"+getPackageName()+"/"+R.raw.demovideo));
       videoView.start();

    }
}
