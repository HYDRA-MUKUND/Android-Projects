package com.hydra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.concurrent.ScheduledExecutorService;


public class MainActivity extends AppCompatActivity {
Button button;
FirebaseAuth auth;
EditText email,pass,name;
FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
      button=(Button)findViewById(R.id.button2);
email=(EditText)findViewById(R.id.editTextTextEmailAddress);
pass=(EditText)findViewById(R.id.editTextTextPassword);
name=(EditText)findViewById(R.id.editTextTextPersonname);


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String emailadd=email.getText().toString();
        String passs=pass.getText().toString();
        final String fullname=name.getText().toString();
        if(TextUtils.isEmpty(emailadd)){
            email.setError("Invalid Email");
        return;
        }
        if(TextUtils.isEmpty(passs)){
            pass.setError("Invalid password");
            return;
        }
        if(passs.length()<6){
            pass.setError("At least 6 digits");
        return;
        }
        if(TextUtils.isEmpty(fullname)){
            name.setError("Invalid input");
            return;
        }
        final String userid=auth.getCurrentUser().getUid();
        auth.createUserWithEmailAndPassword(emailadd,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                   DocumentReference documentReference=firebaseFirestore.collection("IEEE Students").document(userid);
                   Map<String,Object> map=new HashMap<>();
                   map.put("Student name",fullname);
                   documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                       }
                   })
                           .addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           });
                   Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
});


    }
}
