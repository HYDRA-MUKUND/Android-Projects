package com.example.moving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.name);
        textView2 = (TextView) findViewById(R.id.phone);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
        });
    }


    public void go(){
        Intent intent=new Intent(this, Main2Activity.class);
        String str=textView1.getText().toString();
        long re=Long.parseLong(textView2.getText().toString());
        intent.putExtra("msg1",str);
        intent.putExtra("msg2",re);
        startActivity(intent);

    }
}
