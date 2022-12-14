package com.example.flash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


 private  Button on,off;
 Camera camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       on=findViewById(R.id.button1);
       off=findViewById(R.id.button2);
       camera=Camera.open();
       final Camera.Parameters parameters=camera.getParameters();
       on.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
               camera.setParameters(parameters);
               camera.startPreview();
           }
       });
       off.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
               camera.setParameters(parameters);
               camera.stopPreview();
           }
       });



    }
}
