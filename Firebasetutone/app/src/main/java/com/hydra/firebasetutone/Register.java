package com.hydra.firebasetutone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText Remail,Rpassword;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Remail=(EditText)findViewById(R.id.regEmailAddress);
        Rpassword=(EditText)findViewById(R.id.regpassword);
        register=(TextView)findViewById(R.id.Register);
        firebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newuserreg();
            }
        });
    }

    private void newuserreg() {
        String emailad=Remail.getText().toString();
        String pass=Rpassword.getText().toString();
        if(TextUtils.isEmpty(emailad)){
            Remail.setError("Invalid email");
            return;
        }
        if(pass.length()<6){
           Rpassword.setError("at least 6 characters");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(emailad,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser fr =firebaseAuth.getCurrentUser();
                    fr.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Register.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(Register.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Register.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}