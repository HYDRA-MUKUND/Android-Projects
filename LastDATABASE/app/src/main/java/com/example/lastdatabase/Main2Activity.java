package com.example.lastdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
EditText username,password,mobileno;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Database obj=new Database(Main2Activity.this);
        username=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.pass);
        mobileno=(EditText)findViewById(R.id.mobile);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               boolean inscheck= obj.insertValues(mobileno.getText().toString(),username.getText().toString(),password.getText().toString());
            if(inscheck==true){
                Toast.makeText(Main2Activity.this, "Succesfully Inserted", Toast.LENGTH_SHORT).show();
                new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        Intent intent =new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }.start();

            }
            else {
                Toast.makeText(Main2Activity.this, "Failed to insert", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}
