package com.hydra.ieee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Types extends AppCompatActivity {
TextView gotostudent,gotoadmin;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#E9F4450D"));
        gotoadmin=(TextView)findViewById(R.id.typefacultylogin);
        gotostudent=(TextView)findViewById(R.id.typestudentlogin);
        sharedPreferences=this.getSharedPreferences("com.hydra.ieee", Context.MODE_PRIVATE);

        gotostudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String studentlogincheck=sharedPreferences.getString("studentlogin","notsave");
                if(studentlogincheck.equals("notsave")){
                    Intent intent=new Intent(Types.this,loginforstudent.class);
                    startActivity(intent);
                    finish();
                }
                else  {
                    Intent intentd=new Intent(Types.this,StudentWindow.class);
                    startActivity(intentd);
                    finish();
                }
            }
        });
        gotoadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkingadmin=sharedPreferences.getString("ok","not");
                if(checkingadmin.equals("not")){
                Intent intent=new Intent(Types.this,adminchecker.class);
                startActivity(intent);
                finish();
            }
                else  {
                    Intent intentd=new Intent(Types.this,loginforAdmin.class);
                    startActivity(intentd);
                    finish();
                }
            }
        });


    }
}