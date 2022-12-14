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

public class studentregister extends AppCompatActivity {
EditText studregname,studregemail,studregpassword,studregnumber;
TextView regnewstudent;
FirebaseFirestore firebaseFirestore;
FirebaseAuth firebaseAuth;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregister);
        getSupportActionBar().hide();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressBarforstudentregister);
        regnewstudent=(TextView)findViewById(R.id.registerforstudent);
        studregemail=(EditText)findViewById(R.id.editTextTextEmailAddress);
        studregname=(EditText)findViewById(R.id.editTextTextPersonname);
        studregnumber=(EditText)findViewById(R.id.studentphonenumber);
        studregpassword=(EditText)findViewById(R.id.editTextTextPassword);
        regnewstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newstudentregister();
            }
        });
    }

    private void newstudentregister() {


        String sname=studregname.getText().toString();
                String semail=studregemail.getText().toString();
                        String snumber=studregnumber.getText().toString();
                                String spassword=studregpassword.getText().toString();
  if(TextUtils.isEmpty(sname)){
      studregname.setError("Invalid name");
      return;
  }
        if(TextUtils.isEmpty(semail)){
            studregemail.setError("Invalid email address");
            return;
        }
        if(TextUtils.isEmpty(snumber)){
            studregnumber.setError("Invalid number");
            return;
        }
        if(snumber.length()<10){
            studregnumber.setError("Invalid number");
            return;
        }

        if(spassword.length()<6){
            studregpassword.setError("at least 6 characters");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
    firebaseAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               String userid=firebaseAuth.getCurrentUser().getUid();
               DocumentReference documentReference=firebaseFirestore.collection("IEEE Students").document(userid);
               Map<String,Object> map=new HashMap<>();
               map.put("Student name",sname);
               map.put("Mobile number",snumber);
               map.put("Email id",semail);
               documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       progressBar.setVisibility(View.GONE);
                       Intent regstuddone=new Intent(studentregister.this,loginforstudent.class);
                       startActivity(regstuddone);
                       finish();
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       progressBar.setVisibility(View.GONE);
                   }
               });
           }
           else {
               progressBar.setVisibility(View.GONE);
               Toast.makeText(studentregister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
           }

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(studentregister.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });










    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(this,loginforstudent.class);
        startActivity(intent);
        finish();
    }
}