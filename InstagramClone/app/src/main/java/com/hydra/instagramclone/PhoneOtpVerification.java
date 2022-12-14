package com.hydra.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneOtpVerification extends AppCompatActivity implements View.OnKeyListener {
private TextView resendotp,countdown,alreadylogin,verifyotp;
private ProgressBar progressbarforOTP;
private EditText enterotp;
private FirebaseAuth firebaseAuth;

private PhoneAuthProvider.ForceResendingToken token;
   private String phonenumber;
   private String recievedOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_otp_verification);
        getWindow().setStatusBarColor(Color.parseColor("#F2357CC8"));
        countdown=(TextView)findViewById(R.id.CountdoTimer);
       progressbarforOTP=(ProgressBar)findViewById(R.id.progressBarforOTpVerify);
        firebaseAuth=FirebaseAuth.getInstance();
        Intent intent=getIntent();
        phonenumber="+"+intent.getStringExtra("countrycode")+intent.getStringExtra("phonenumberkey");
        sendotpmethod();
        alreadylogin=(TextView)findViewById(R.id.alredayhavelogino);
        enterotp=(EditText)findViewById(R.id.Enterotp);
        enterotp.setOnKeyListener(this);


        resendotp=(TextView) findViewById(R.id.ResendOTP);
       verifyotp=(TextView)findViewById(R.id.VerifyOTP);
       verifyotp.setEnabled(false);

        new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long l) {
                countdown.setText((String.valueOf(l/1000)));
            }

            @Override
            public void onFinish() {
                resendotp.setVisibility(View.VISIBLE);
            }
        }.start();
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendotpmethod();
            }
        });
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyotpmethod();
            }
        });
        alreadylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PhoneOtpVerification.this,LoginAct.class);
                startActivity(intent);
                finish();
            }
        });
        enterotp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(enterotp.getText().toString().length()>0){
                    verifyotp.setEnabled(true);
                   verifyotp.setBackgroundResource(R.drawable.button);
                    verifyotp.setTextColor(Color.parseColor("#ffffff"));
                }
                else if(enterotp.getText().toString().equals("")){
                    verifyotp.setEnabled(false);
                    verifyotp.setBackgroundResource(R.drawable.buttonblur);
                    verifyotp.setTextColor(Color.parseColor("#A1D4EF"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {

               automaticotp(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            token=forceResendingToken;
            recievedOTP=s;
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
            resendotp.setVisibility(View.VISIBLE);
        }
    };

    private void createuserauth(PhoneAuthCredential phoneAuthCredential) {

        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressbarforOTP.setVisibility(View.GONE);
                    if(task.getResult().getAdditionalUserInfo().isNewUser()) {
                        Intent i=new Intent(PhoneOtpVerification.this,Registrationofuser.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Intent i=new Intent(PhoneOtpVerification.this,HelloStarrMain.class);
                        startActivity(i);
                        finish();

                    }

                }
                else {
                    progressbarforOTP.setVisibility(View.GONE);
                    verifyotp.setEnabled(true);
                    enterotp.setEnabled(true);
                    verifyotp.setBackgroundResource(R.drawable.button);
                    verifyotp.setTextColor(Color.parseColor("#FFFFFF"));
                    resendotp.setEnabled(true);
                    resendotp.setBackgroundResource(R.drawable.button);
                    resendotp.setTextColor(Color.parseColor("#FFFFFF"));
                    new AlertDialog.Builder(PhoneOtpVerification.this)
                            .setTitle("ERROR")
                            .setMessage(task.getException().getMessage())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressbarforOTP.setVisibility(View.GONE);
                enterotp.setEnabled(true);
                verifyotp.setEnabled(true);
                verifyotp.setBackgroundResource(R.drawable.button);
                verifyotp.setTextColor(Color.parseColor("#FFFFFF"));
                resendotp.setEnabled(true);
                resendotp.setBackgroundResource(R.drawable.button);
                resendotp.setTextColor(Color.parseColor("#FFFFFF"));
                new AlertDialog.Builder(PhoneOtpVerification.this)
                        .setTitle("ERROR")
                        .setMessage(e.getMessage())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });

    }

    private void verifyotpmethod() {

        if(TextUtils.isEmpty(enterotp.getText().toString()) || enterotp.getText().toString().length()<6 ){
            enterotp.setError("Must be 6 digit");
            return;
        }
        enterotp.setEnabled(false);
        resendotp.setEnabled(false);
        resendotp.setBackgroundResource(R.drawable.buttonblur);
        resendotp.setTextColor(Color.parseColor("#A1D4EF"));
        progressbarforOTP.setVisibility(View.VISIBLE);
        verifyotp.setBackgroundResource(R.drawable.buttonblur);
        verifyotp.setEnabled(false);
        verifyotp.setTextColor(Color.parseColor("#A1D4EF"));
        try {
            PhoneAuthCredential Credential = PhoneAuthProvider.getCredential(recievedOTP, enterotp.getText().toString());
            createuserauth(Credential);
        }catch (Exception e){
            new AlertDialog.Builder(PhoneOtpVerification.this)
                    .setTitle("Technical Error")
                    .setMessage(e.getMessage())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        }
    }
    private void automaticotp(String getcodeotp){
        try{
        PhoneAuthCredential Credential=PhoneAuthProvider.getCredential(recievedOTP,getcodeotp);
        createuserauth(Credential);
    }catch (Exception e){
            new AlertDialog.Builder(PhoneOtpVerification.this)
                    .setTitle("Technical Error")
                    .setMessage(e.getMessage())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        }
    }

    private void resendotpmethod() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber,45, TimeUnit.SECONDS,this,mcallbacks,token);
        new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long l) {
                countdown.setText((String.valueOf(l/1000)));
            }

            @Override
            public void onFinish() {
                resendotp.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    private void sendotpmethod() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber,45, TimeUnit.SECONDS,this,mcallbacks);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PhoneOtpVerification.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imjjm = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imjjm.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i== KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            verifyotpmethod();
        }
        return false;
    }
}