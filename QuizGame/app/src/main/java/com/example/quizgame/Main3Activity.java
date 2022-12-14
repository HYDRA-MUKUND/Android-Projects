package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView textView,ready;
    int cou=6;
     String namess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView=(TextView)findViewById(R.id.counter);
        ready=(TextView)findViewById(R.id.readytext);





        new CountDownTimer(6300, 1000) {
            @Override
            public void onTick(long l) {

                textView.setText(String.valueOf(cou));
                cou--;
            }

            @Override
            public void onFinish() {

              ghhh();

            }
        }.start();

    }

    private void ghhh() {
        Intent ij=getIntent();
        Intent inten=new Intent(this,Main4Activity.class);
        namess=ij.getStringExtra("name1");
        inten.putExtra("name2",namess);
        startActivity(inten);
    }


}
