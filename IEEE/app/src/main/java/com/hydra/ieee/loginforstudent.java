package com.hydra.ieee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class loginforstudent extends AppCompatActivity {
    EditText emailofstudent,passwordofstudent;
    TextView loginforstud,donone,dontwo;
    FirebaseAuth firebaseAuth;
    ProgressBar progone;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginforstudent);
        getSupportActionBar().hide();
        firebaseAuth=FirebaseAuth.getInstance();
        emailofstudent=(EditText)findViewById(R.id.studentemailaddress);
        passwordofstudent=(EditText)findViewById(R.id.studentpassword);
        loginforstud=(TextView)findViewById(R.id.loginforstudent);
        donone=(TextView)findViewById(R.id.donthave);
        dontwo=(TextView)findViewById(R.id.donthavea);
        sharedPreferences=this.getSharedPreferences("com.hydra.ieee", Context.MODE_PRIVATE);
        progone=(ProgressBar)findViewById(R.id.progressBarforstudentlogin);

        donone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studreg=new Intent(loginforstudent.this,studentregister.class);
            startActivity(studreg);
            finish();
            }
        });
        dontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studreg=new Intent(loginforstudent.this,studentregister.class);
                startActivity(studreg);
                finish();
            }
        });
    loginforstud.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String semail=emailofstudent.getText().toString();
            String spass=passwordofstudent.getText().toString();
            if(TextUtils.isEmpty(semail)){
                emailofstudent.setError("Invalid email");
            return;
            }
            if(spass.length()<6){
                passwordofstudent.setError("at least 6 characters");
            return;
            }
            progone.setVisibility(View.VISIBLE);
          firebaseAuth.signInWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      progone.setVisibility(View.GONE);
                      Toast.makeText(loginforstudent.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                      new AlertDialog.Builder(loginforstudent.this)
                              .setIcon(R.drawable.notificatio)
                              .setTitle(" Login Status")
                              .setMessage("Do you want to save Password")
                              .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      sharedPreferences.edit().putString("studentlogin","save").apply();
                                      Intent intent=new Intent(loginforstudent.this,StudentWindow.class);
                                      startActivity(intent);
                                      finish();
                                  }
                              })
                              .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      Intent intent=new Intent(loginforstudent.this,StudentWindow.class);
                                      startActivity(intent);
                                      finish();
                                  }
                              }).show();


                  }

              }
          })  .addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                  progone.setVisibility(View.GONE);
                  Toast.makeText(loginforstudent.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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