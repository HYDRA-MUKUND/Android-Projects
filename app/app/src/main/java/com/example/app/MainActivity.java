package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.text);
          TextPaint textPaint=textView.getPaint();
          float width=textPaint.measureText(textView.getText().toString());
          Shader shader=new LinearGradient(0,0,width,textView.getTextSize(),new int[]{

                  Color.parseColor("#64B678"),
                  Color.parseColor("#478AEA"),
          },null,Shader.TileMode.CLAMP);
          textView.getPaint().setShader(shader);
}}

