package com.example.h_admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Updatedata extends AppCompatActivity {
    ImageView gotopdata,deletepatrecord;
    EditText ps_name,ps_mobile,ps_birthdate,ps_apdate,ps_weight;
    TextView updatedataofpat;
   private  String mobileid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);
        getWindow().setStatusBarColor(Color.parseColor("#2B65EC"));
        gotopdata=(ImageView)findViewById(R.id.gotopersonrec);
        deletepatrecord=(ImageView)findViewById(R.id.deletepatdata);
        ps_weight=(EditText)findViewById(R.id.ps_weight);
        ps_name=(EditText)findViewById(R.id.ps_name);
        ps_mobile=(EditText)findViewById(R.id.ps_number);
        ps_birthdate=(EditText) findViewById(R.id.ps_birthdate);
        ps_apdate=(EditText) findViewById(R.id.ps_apdate);
        updatedataofpat=(TextView)findViewById(R.id.updatepatrecord);
        Intent patrecord=getIntent();
       ps_name.setText(patrecord.getStringExtra("nameP"));
        mobileid=patrecord.getStringExtra("numberP");
        ps_mobile.setText(mobileid);
        ps_apdate.setText(patrecord.getStringExtra("apdateP"));
        ps_birthdate.setText(patrecord.getStringExtra("birthdateP"));
        ps_weight.setText(patrecord.getStringExtra("weightP"));

        gotopdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotopdata.setEnabled(false);
                gototypesssd();
            }
        });
        updatedataofpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepatrecmethod();
            }
        });
        deletepatrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delelterec();
            }
        });
    }

    private void delelterec() {
        new AlertDialog.Builder(Updatedata.this)
                .setIcon(R.drawable.complain)
                .setTitle("  Are you sure to Delete..?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Deletedata mysql_database = new Deletedata(Updatedata.this);
                        mysql_database.execute("mydata",mobileid );
                        Toast.makeText(Updatedata.this,"Record Successfully Deleted", Toast.LENGTH_SHORT).show();
                        Intent intb=new Intent(Updatedata.this, patientsrecord.class);
                        startActivity(intb);
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

    private void updatepatrecmethod() {

        UpPatRecord objjj = new UpPatRecord(this);
      objjj.execute("mydata",ps_name.getText().toString(), ps_mobile.getText().toString(), ps_birthdate.getText().toString(), ps_apdate.getText().toString(), ps_weight.getText().toString(),mobileid);

    }

    public  void gototypesssd(){
        Intent intb=new Intent(Updatedata.this, patientsrecord.class);
        startActivity(intb);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intb=new Intent(Updatedata.this, patientsrecord.class);
        startActivity(intb);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gotopdata.setEnabled(true);
    }
}