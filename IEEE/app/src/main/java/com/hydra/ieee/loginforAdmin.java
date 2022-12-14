package com.hydra.ieee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginforAdmin extends AppCompatActivity {
    EditText emailofadmin,passwordofadmin;
    TextView loginforadmins,addonstwo,addons;
    FirebaseAuth AfirebaseAuth;
    ProgressBar progforadlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfor_admin);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        AfirebaseAuth=FirebaseAuth.getInstance();
        emailofadmin=(EditText)findViewById(R.id.adminemailaddress);
        passwordofadmin=(EditText)findViewById(R.id.adminpassword);
        loginforadmins=(TextView)findViewById(R.id.loginforadmin);
        addons=(TextView)findViewById(R.id.Adonthave);
        addonstwo=(TextView)findViewById(R.id.AAdonthave);
        progforadlogin=(ProgressBar)findViewById(R.id.progressBarforAdminlogin);
      if(AfirebaseAuth.getCurrentUser()!=null){
          Intent alrereg=new Intent(loginforAdmin.this,Admininfo.class);
          startActivity(alrereg);
          finish();
      }

        addons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adreg=new Intent(loginforAdmin.this,Adminregister.class);
                startActivity(adreg);
                finish();
            }
        });
        addonstwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adreg=new Intent(loginforAdmin.this,Adminregister.class);
                startActivity(adreg);
                finish();
            }
        });
        loginforadmins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Aemail=emailofadmin.getText().toString();
                String Apass=passwordofadmin.getText().toString();
                if(TextUtils.isEmpty(Aemail)){
                    emailofadmin.setError("Invalid email");
                    return;
                }
                if(Apass.length()<6){
                    passwordofadmin.setError("at least 6 characters");
                    return;
                }
                progforadlogin.setVisibility(View.VISIBLE);
                AfirebaseAuth.signInWithEmailAndPassword(Aemail,Apass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progforadlogin.setVisibility(View.GONE);
                            Toast.makeText(loginforAdmin.this, "Login Successfull", Toast.LENGTH_SHORT).show();


                                            Intent intent=new Intent(loginforAdmin.this,Admininfo.class);
                                            startActivity(intent);
                                       finish();


                        }

                    }
                })  .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progforadlogin.setVisibility(View.GONE);
                        Toast.makeText(loginforAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

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