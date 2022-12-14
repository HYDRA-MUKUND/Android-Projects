package com.hydra.mobileotpverfication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpverfication extends AppCompatActivity {
    Button generateotp, verifyotp, resendotp;
    private FirebaseAuth auth;
    String OTP;
    EditText nummm;
    private PhoneAuthProvider.ForceResendingToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_otpverfication);
        generateotp = (Button) findViewById(R.id.generateotp);
        verifyotp = (Button) findViewById(R.id.verifyotp);
        resendotp = (Button) findViewById(R.id.resendotp);
        nummm = (EditText) findViewById(R.id.numsix);
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendverficationcode();
            }
        });
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneotp = nummm.getText().toString();
                if (phoneotp != null) {
                    verifyotpofuser(phoneotp);
                }
            }
        });
        generateotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendverficationcode();
                verifyotp.setVisibility(View.VISIBLE);
            }
        });
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String  userrrr=phoneAuthCredential.getSmsCode();
            if(userrrr!=null){
                verifyotpofuser(userrrr);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(otpverfication.this, "Verification Failed", Toast.LENGTH_SHORT).show();
            resendotp.setVisibility(View.VISIBLE);
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            OTP = s;
            token = forceResendingToken;

        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
            resendotp.setVisibility(View.VISIBLE);
        }
    };


    private void sendverficationcode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+918623023652", 60, TimeUnit.SECONDS, otpverfication.this, mcallbacks);
    }

    private void resendverficationcode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+918623023652", 60, TimeUnit.SECONDS, otpverfication.this, mcallbacks, token);
    }


    private void verifyotpofuser(String realcode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP, realcode);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(otpverfication.this, "Created", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(otpverfication.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(otpverfication.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}