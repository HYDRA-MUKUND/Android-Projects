package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button, button0, button1, button2, button3;
    int Tot,count=0,min=0,flag=0;
    TextView quetext, timetext, countertext,resulttext,gameover,finalresult;


    public void go(final View view) {
        view.setVisibility(view.INVISIBLE);


        button0.setVisibility(view.VISIBLE);
        button1.setVisibility(view.VISIBLE);
        button2.setVisibility(view.VISIBLE);
        button3.setVisibility(view.VISIBLE);
        quetext.setVisibility(view.VISIBLE);
        timetext.setVisibility(View.VISIBLE);
        countertext.setVisibility(View.VISIBLE);
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                timetext.setText(Long.toString(l / 1000));


            }

            @Override
            public void onFinish() {
                timetext.setText("0");
                resulttext.setVisibility(View.INVISIBLE);
                button0.setVisibility(view.INVISIBLE);
                button1.setVisibility(view.INVISIBLE);
                button2.setVisibility(view.INVISIBLE);
                button3.setVisibility(view.INVISIBLE);
                quetext.setVisibility(view.INVISIBLE);
                timetext.setVisibility(View.INVISIBLE);
                countertext.setVisibility(View.INVISIBLE);
                gameover.setVisibility(View.VISIBLE);
                finalresult.setText("Score:"+Integer.toString(min)+"/"+Integer.toString(count));
                finalresult.setVisibility(View.VISIBLE);

            }

        }.start();

    }
        public void click (  View view){
               count++;
            if ((view.getTag().toString().equals(Integer.toString(Tot)))) {
                min++;
                resulttext.setText("Correct");

                resulttext.setVisibility(View.VISIBLE);


            } else {
                resulttext.setText("Wrong");
                resulttext.setVisibility(View.VISIBLE);

            }
            countertext.setText(Integer.toString(min) + "/" + Integer.toString(count));
            refre();
        }
        public void refre() {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
                Random random = new Random();
                int a = random.nextInt(21);
                int b = random.nextInt(21);
                quetext.setText(Integer.toString(a) + "+" + Integer.toString(b) + "= ?");
                Tot = a + b;

                int total = random.nextInt(4);
                for (int i = 0; i < 4; i++) {
                    if (total == i) {
                        arrayList.add(a + b);
                    } else {
                        int wrong = random.nextInt(41);
                        while (wrong == a + b) {
                            wrong = random.nextInt(41);

                        }
                        arrayList.add(wrong);
                    }
                }
                button0.setTag(Integer.toString(arrayList.get(0)));
                button1.setTag(Integer.toString(arrayList.get(1)));
                button2.setTag(Integer.toString(arrayList.get(2)));
                button3.setTag(Integer.toString(arrayList.get(3)));
                button0.setText(Integer.toString(arrayList.get(0)));
                button1.setText(Integer.toString(arrayList.get(1)));
                button2.setText(Integer.toString(arrayList.get(2)));
                button3.setText(Integer.toString(arrayList.get(3)));


            }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.gobutton);
        quetext = (TextView) findViewById(R.id.quetext);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        timetext = (TextView) findViewById(R.id.timertext);
        resulttext=(TextView)findViewById(R.id.resulttext);
        countertext = (TextView) findViewById(R.id.countertext);
        finalresult=(TextView)findViewById(R.id.finalresult);
        gameover=(TextView)findViewById(R.id.gameover);

refre();
    }
}

