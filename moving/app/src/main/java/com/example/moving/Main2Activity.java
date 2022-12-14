package com.example.moving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView2=(TextView)findViewById(R.id.num);
        textView1=(TextView)findViewById(R.id.strr);
        Intent intent=getIntent();
        String str=intent.getStringExtra("msg1");
        textView1.setText(str);
        long ff=intent.getLongExtra("msg2",0);

        textView2.setText(String.valueOf(ff));
    }
}
