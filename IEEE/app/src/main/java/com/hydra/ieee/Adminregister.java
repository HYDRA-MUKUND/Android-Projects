package com.hydra.ieee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Adminregister extends AppCompatActivity {
    EditText adminregname,adminregemail,adminregpassword,adminregnumber;
    TextView regnewadmin;
    FirebaseFirestore AfirebaseFirestore;
    FirebaseAuth AfirebaseAuth;
    ProgressBar AprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregister);
        getSupportActionBar().hide();
        AfirebaseAuth=FirebaseAuth.getInstance();
        AfirebaseFirestore=FirebaseFirestore.getInstance();
        AprogressBar=(ProgressBar)findViewById(R.id.progressBarforadminregister);
        regnewadmin=(TextView)findViewById(R.id.registerforadmin);
        adminregemail=(EditText)findViewById(R.id.editTextTextAdminEmailAddress);
        adminregname=(EditText)findViewById(R.id.editTextTextAdminname);
        adminregnumber=(EditText)findViewById(R.id.adminphonenumber);
        adminregpassword=(EditText)findViewById(R.id.editTextTextAdminPassword);
        regnewadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newadminregister();
            }
        });
    }

    private void newadminregister() {


        String Aname=adminregname.getText().toString();
        String Aemail=adminregemail.getText().toString();
        String Anumber=adminregnumber.getText().toString();
        String Apassword=adminregpassword.getText().toString();
        if(TextUtils.isEmpty(Aname)){
            adminregname.setError("Invalid name");
            return;
        }
        if(TextUtils.isEmpty(Aemail)){
            adminregemail.setError("Invalid email address");
            return;
        }
        if(TextUtils.isEmpty(Anumber)){
            adminregnumber.setError("Invalid number");
            return;
        }
        if(Anumber.length()<10){
            adminregnumber.setError("Invalid number");
            return;
        }

        if(Apassword.length()<6){
            adminregpassword.setError("at least 6 characters");
            return;
        }
        AprogressBar.setVisibility(View.VISIBLE);
        AfirebaseAuth.createUserWithEmailAndPassword(Aemail,Apassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String Auserid=AfirebaseAuth.getCurrentUser().getUid();
                    DocumentReference AdocumentReference=AfirebaseFirestore.collection("COMMITTEE Members").document(Auserid);
                    Map<String,Object> map=new HashMap<>();
                    map.put("Faculty name",Aname);
                    map.put("Mobile number",Anumber);
                    map.put("Email id",Aemail);
                    AdocumentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            AprogressBar.setVisibility(View.GONE);
                            Intent regadmindone=new Intent(Adminregister.this,loginforAdmin.class);
                            startActivity(regadmindone);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            AprogressBar.setVisibility(View.GONE);
                        }
                    });
                }
                else {
                    AprogressBar.setVisibility(View.GONE);
                    Toast.makeText(Adminregister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                AprogressBar.setVisibility(View.GONE);
                Toast.makeText(Adminregister.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });










    }

    @Override
    public void onBackPressed() {

        Intent Aintent=new Intent(this,loginforAdmin.class);
        startActivity(Aintent);
        finish();
    }
}