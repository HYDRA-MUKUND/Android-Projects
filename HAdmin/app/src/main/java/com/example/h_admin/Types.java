package com.example.h_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Types extends AppCompatActivity {
TextView ambulacerec,patientsrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);
        getWindow().setStatusBarColor(Color.parseColor("#2B65EC"));
        ambulacerec=(TextView)findViewById(R.id.ambulance);
        patientsrec=(TextView)findViewById(R.id.patients);
        ambulacerec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambulacerec.setEnabled(false);
                Intent intam=new Intent(Types.this,ambulacerecord.class);
                startActivity(intam);
            }
        });
        patientsrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientsrec.setEnabled(false);
                Intent intpat=new Intent(Types.this,patientsrecord.class);
                startActivity(intpat);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        patientsrec.setEnabled(true);
        ambulacerec.setEnabled(true);
    }
}
