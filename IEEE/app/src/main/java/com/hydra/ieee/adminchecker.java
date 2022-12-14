package com.hydra.ieee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminchecker extends AppCompatActivity {
TextView submit;
private EditText uniquekey;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminchecker);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.parseColor("#FF3700B3"));
        submit=(TextView)findViewById(R.id.Submit);
        uniquekey=(EditText)findViewById(R.id.uniuekey);
        sharedPreferences=this.getSharedPreferences("com.hydra.ieee", Context.MODE_PRIVATE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=uniquekey.getText().toString();
                if(TextUtils.isEmpty(key)){
                    uniquekey.setError("Invalid key");
                return;
                }
                else if(key.equals("Adminaccess@123")){
                    new  AlertDialog.Builder(adminchecker.this)
                            .setIcon(R.drawable.notificatio)
                            .setTitle(" Admin Status")
                            .setMessage("Do you want to save Key ?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    sharedPreferences.edit().putString("ok","done").apply();
                                    Intent intent=new Intent(adminchecker.this,loginforAdmin.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent=new Intent(adminchecker.this,loginforAdmin.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();

                }
                else {
                    Toast.makeText(adminchecker.this, "Wrong Key", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Types.class);
        startActivity(intent);
        finish();
    }
}