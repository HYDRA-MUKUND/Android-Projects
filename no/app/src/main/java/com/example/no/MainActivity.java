package com.example.no;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


   private EditText editText;
    private EditText editText2;
    public void reset(View view){
    editText.setText("00");
    editText2.setText("00");
    }
    public  void start(View view){
        int x1=Integer.parseInt(editText.getText().toString());

       final int x2=Integer.parseInt(editText2.getText().toString());

        long tt=((x1*60)+x2)*1000;
        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l)
                editText2.setText(Integer.toString());

            }

            @Override
            public void onFinish() {
                Log.i("ff","END");

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
    }
}
