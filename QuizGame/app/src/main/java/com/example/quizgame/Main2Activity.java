package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView name;
     String tem;
    Button but;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=(TextView)findViewById(R.id.planetext);

        but=(Button)findViewById(R.id.letsgo);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             gjn();


            }
        });


    }

    private void gjn() {
        Intent intent=new Intent(this,Main3Activity.class);

        tem=name.getText().toString();

        intent.putExtra("name1",tem);
        startActivity(intent);
    }



}
