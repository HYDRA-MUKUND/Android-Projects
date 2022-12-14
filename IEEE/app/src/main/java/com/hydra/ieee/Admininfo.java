package com.hydra.ieee;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Admininfo extends AppCompatActivity {
TextView ongoingevent,addevent,logoutforad;
FirebaseAuth adminauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admininfo);
        adminauth=FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#F4450D"));

        ongoingevent = (TextView) findViewById(R.id.currentevent);

        addevent = (TextView) findViewById(R.id.addevent);
      logoutforad=(TextView)findViewById(R.id.logoutadmin);
      ongoingevent.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Admininfo.this, StudentWindow.class);
              startActivity(intent);
              finish();
          }
      });
      logoutforad.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              FirebaseAuth.getInstance().signOut();
              Intent intent = new Intent(Admininfo.this, loginforAdmin.class);
              startActivity(intent);
              finish();

          }
      });

        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admininfo.this, Adminwindow.class);
                startActivity(intent);
                finish();
            }
        });


    }


    }