package com.example.h_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class logincheck extends AppCompatActivity {
EditText password;
Button loginbutton;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincheck);
        password=(EditText)findViewById(R.id.getpass);
       loginbutton=(Button)findViewById(R.id.loginbutton);
       sharedPreferences=this.getSharedPreferences("com.example.h_admin", Context.MODE_PRIVATE);

       getWindow().setStatusBarColor(Color.parseColor("#2B65EC"));
       loginbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               loginbutton.setEnabled(false);
               passwordchecking();
           }
       });

    }

    private void passwordchecking() {
        String pass=password.getText().toString();
        if(pass.equals("")){
            password.setHintTextColor(Color.RED);
            password.setHint(" invalid input");
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            loginbutton.setEnabled(true);
        }
        else {
            if(pass.equals("Hydra0123care")){
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.notificatio)
                        .setTitle(" Login Status")
                        .setMessage("Do you want to save password ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPreferences.edit().putString("code","done").apply();
                                Toast.makeText(logincheck.this, "Password Saved", Toast.LENGTH_SHORT).show();
                                Intent login=new Intent(logincheck.this,Types.class);
                                startActivity(login);
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(logincheck.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent login=new Intent(logincheck.this,Types.class);
                                startActivity(login);
                                finish();
                            }
                        })
                        .show();




            }
            else {
                password.setText("");
                loginbutton.setEnabled(true);
                password.setHintTextColor(Color.RED);
                password.setHint(" wrong password");
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                loginbutton.setEnabled(true);
            }
        }
    }

}
