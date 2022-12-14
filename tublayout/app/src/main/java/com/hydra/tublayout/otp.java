package com.hydra.tublayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
Button resendotp,verifyotp;
FirebaseAuth firebaseAuth;

EditText otpone,otptwo,otpthree,otpfour,otpfive,otpsix;
PhoneAuthProvider.ForceResendingToken token;
PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallb;
String sysotp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        sendotppppp();
        resendotp = (Button) findViewById(R.id.resendotp);
        verifyotp = (Button) findViewById(R.id.verifyotp);
        otpfive = (EditText) findViewById(R.id.numfive);
        otpsix = (EditText) findViewById(R.id.numsix);
        otpfour = (EditText) findViewById(R.id.numfour);
        otpthree = (EditText) findViewById(R.id.numthree);
        otptwo = (EditText) findViewById(R.id.numtwo);
        otpone = (EditText) findViewById(R.id.numone);


        firebaseAuth = FirebaseAuth.getInstance();
        mcallb = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                verifycomplete(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(otp.this, "Failed", Toast.LENGTH_SHORT).show();
                resendotp.setVisibility(View.VISIBLE);
            }
            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                resendotp.setVisibility(View.VISIBLE);
            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                sysotp = s;
                token = forceResendingToken;
            }



        };

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userotp = otpone.getText().toString() + otptwo.getText().toString() + otpthree.getText().toString() + otpfour.getText().toString() + otpfive.getText().toString() + otpsix.getText().toString();
               PhoneAuthCredential credential=PhoneAuthProvider.getCredential(sysotp,userotp);
            }
        });
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotppppp();
            }
        });



    }

    private void verifycomplete(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(otp.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(otp.this, "Failed  "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void sendotppppp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+919075443323",                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                otp.this,        // Activity (for callback binding)
                mcallb);
    }
    private void resendotppppp() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+919075443323",                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                otp.this,        // Activity (for callback binding)
                mcallb,token);                      // OnVerificationStateChangedCallbacks
    }

    }


