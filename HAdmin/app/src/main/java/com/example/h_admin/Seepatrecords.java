package com.example.h_admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Seepatrecords extends AppCompatActivity {
TextView viewnoofpat,viewlocations,viewdateandtime;
ImageView gotoambrecords,deleteambrecords;
String dateidd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seepatrecords);
        getWindow().setStatusBarColor(Color.parseColor("#2B65EC"));
       viewnoofpat=(TextView)findViewById(R.id.viewnoofpat);
       viewdateandtime=(TextView)findViewById(R.id.viewdateandtime);
       viewlocations=(TextView)findViewById(R.id.viewlocation);
       gotoambrecords=(ImageView)findViewById(R.id.gotoambrecords);
       deleteambrecords=(ImageView)findViewById(R.id.deleteambdata);

        Intent patrecord=getIntent();
        viewlocations.setText(patrecord.getStringExtra("locationP"));
        dateidd=patrecord.getStringExtra("datesP");
        viewdateandtime.setText(dateidd);

        viewnoofpat.setText(patrecord.getStringExtra("patientsP"));
        gotoambrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoambrecords.setEnabled(false);
                gotomabrecmethod();
            }
        });
        deleteambrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteambrecordsmethod();
            }
        });
    }

    private void deleteambrecordsmethod() {
        new AlertDialog.Builder(Seepatrecords.this)
                .setIcon(R.drawable.complain)
                .setTitle("  Are you sure to Delete..?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteAmbData mysql_databas = new DeleteAmbData(Seepatrecords.this);
                        mysql_databas.execute("mydataa",dateidd);
                        Toast.makeText(Seepatrecords.this,"Record Successfully Deleted", Toast.LENGTH_SHORT).show();
                        Intent intb=new Intent(Seepatrecords.this, ambulacerecord.class);
                        startActivity(intb);
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

    private void gotomabrecmethod() {
        Intent intb=new Intent(Seepatrecords.this, ambulacerecord.class);
        startActivity(intb);
        finish();

    }



    @Override
    public void onBackPressed() {
        Intent intb=new Intent(Seepatrecords.this, ambulacerecord.class);
        startActivity(intb);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gotoambrecords.setEnabled(true);
    }
}