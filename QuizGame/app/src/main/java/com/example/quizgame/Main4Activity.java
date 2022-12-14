package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main4Activity extends AppCompatActivity {
TextView setname;
Button but0,but2,but3,but1;
    int Tot,max=0,min=0;
    String playerName;
    TextView quetext, timetext, countertext;





    public void click (View view){
        max++;
        if ((view.getTag().toString().equals(Integer.toString(Tot)))) {
            min++;


        } else {


        }
        countertext.setText(Integer.toString(min) + "/" + Integer.toString(max));
        sett();
    }
    public void sett()
    {

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Random random = new Random();
        int a = 1+ random.nextInt(1000);
        int b = 1+ random.nextInt(1000);
        quetext.setText(Integer.toString(a) + "+" + Integer.toString(b) + "= ?");
        Tot = a + b;

        int total = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (total == i) {
                arrayList.add(a + b);
            } else {
                int wrong = random.nextInt(2200);
                while (wrong == a + b) {
                    wrong = random.nextInt(2200);

                }
                arrayList.add(wrong);
            }
        }
        but0.setTag(Integer.toString(arrayList.get(0)));
        but1.setTag(Integer.toString(arrayList.get(1)));
        but2.setTag(Integer.toString(arrayList.get(2)));
        but3.setTag(Integer.toString(arrayList.get(3)));
        but0.setText(Integer.toString(arrayList.get(0)));
        but1.setText(Integer.toString(arrayList.get(1)));
        but2.setText(Integer.toString(arrayList.get(2)));
        but3.setText(Integer.toString(arrayList.get(3)));



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setname=(TextView)findViewById(R.id.playername);
       Intent tt=getIntent();
       playerName=tt.getStringExtra("name2");
       setname.setText(playerName);

        but0=(Button)findViewById(R.id.button0);
        but1=(Button)findViewById(R.id.button1);
        but2=(Button)findViewById(R.id.button2);
        but3=(Button)findViewById(R.id.button3);
        timetext = (TextView) findViewById(R.id.timertext);
        quetext=(TextView)findViewById(R.id.quetext);
        countertext = (TextView) findViewById(R.id.countertext);
        sett();
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                timetext.setText(Long.toString(l / 1000));


            }

            @Override
            public void onFinish() {
                act5();




            }

        }.start();



    }

    private void act5() {
        timetext.setText("0");
        Intent act4=new Intent(Main4Activity.this,Main5Activity.class);
        act4.putExtra("name3",playerName);
        act4.putExtra("maxvalue",max);
        act4.putExtra("minvalue",min);
        startActivity(act4);
    }

}
