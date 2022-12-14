package com.example.promt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    b=(TextView)findViewById(R.id.textView);
    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            okk();
        }
    });
    }

    private void okk() {
        promt pp=new promt();
        pp.show(getSupportFragmentManager(),"msg");
    }
}
