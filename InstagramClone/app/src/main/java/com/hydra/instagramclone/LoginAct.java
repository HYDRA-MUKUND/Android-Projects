package com.hydra.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class LoginAct extends AppCompatActivity implements View.OnKeyListener{
private TextView loginwithemail,loginwithmobile;
private EditText loginemail,loginpass;
   private TextView newwuser;
private FirebaseAuth firebaseAuth;
private ImageView passhideandshow;
private ProgressBar progressBarforemaillogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(Color.parseColor("#F2357CC8"));
        firebaseAuth=FirebaseAuth.getInstance();
      newwuser =(TextView)findViewById(R.id.newuseralternative);

        progressBarforemaillogin=(ProgressBar)findViewById(R.id.progressBaremaillogin);
        loginwithemail=(TextView)findViewById(R.id.LoginWithEmail);
        loginwithemail.setEnabled(false);
        loginwithmobile=(TextView)findViewById(R.id.LoginWithPhone);

        loginemail=(EditText)findViewById(R.id.loginemail);
        loginpass=(EditText)findViewById(R.id.loginpassword);

        loginpass.setOnKeyListener(this);
        loginemail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    loginemail.setHint("");
                }
                else
                {   loginemail.setHint("Email");
                }}
        });
        loginpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    loginpass.setHint("");
                }
                else
                {   loginpass.setHint("Password");
                }}

        });
      loginwithmobile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(LoginAct.this,FirstTimeMobile.class);
              startActivity(intent);
              finish();
          }
      });
        passhideandshow=(ImageView)findViewById(R.id.passwordhideandshowgg);
        loginemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if(loginemail.getText().toString().length()>0 && loginpass.getText().toString().length()>0){
                 loginwithemail.setEnabled(true);
                 loginwithemail.setBackgroundResource(R.drawable.button);
                 loginwithemail.setTextColor(Color.parseColor("#ffffff"));
              }
             else {
                  loginwithemail.setEnabled(false);
                  loginwithemail.setBackgroundResource(R.drawable.buttonblur);

                  loginwithemail.setTextColor(Color.parseColor("#A1D4EF"));

              }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        loginpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(loginemail.getText().toString().length()>0 && loginpass.getText().toString().length()>0){
                    loginwithemail.setEnabled(true);
                    loginwithemail.setBackgroundResource(R.drawable.button);
                    loginwithemail.setTextColor(Color.parseColor("#ffffff"));


                }
                else { loginwithemail.setEnabled(false);
                    loginwithemail.setBackgroundResource(R.drawable.buttonblur);

                    loginwithemail.setTextColor(Color.parseColor("#A1D4EF"));


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passhideandshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passhideandshowmethod();
            }
        });
        loginwithemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailloginchecker();
            }
        });
        newwuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginAct.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void passhideandshowmethod() {
        if(loginpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){

           passhideandshow.setImageResource(R.drawable.view);
           loginpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); loginpass.setSelection(loginpass.getText().toString().length());

        }
        else {


            passhideandshow.setImageResource(R.drawable.hide);
            loginpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            loginpass.setSelection(loginpass.getText().toString().length());
        }

    }

    private void emailloginchecker() {
        loginpass.setEnabled(false);
        loginemail.setEnabled(false);
        loginpass.setCursorVisible(false);
         if(TextUtils.isEmpty(loginemail.getText().toString())){
             loginemail.setError("Invalid email");
             loginpass.setEnabled(true);
             loginemail.setEnabled(true);
             loginpass.setCursorVisible(true);
             return;
         }
        if(TextUtils.isEmpty(loginpass.getText().toString())){
            loginpass.setEnabled(true);
            loginemail.setEnabled(true);
            loginpass.setCursorVisible(true);
            loginpass.setError("Invalid password");
            return;
        }
        loginwithemail.setBackgroundResource(R.drawable.buttonblur);
        loginwithemail.setTextColor(Color.parseColor("#8CC9FF"));
        progressBarforemaillogin.setVisibility(View.VISIBLE);
        signwithemail(loginemail.getText().toString(),loginpass.getText().toString());


    }

    private void signwithemail(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBarforemaillogin.setVisibility(View.GONE);
                    Toast.makeText(LoginAct.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                loginemail.setEnabled(true);
                loginpass.setEnabled(true);
                loginpass.setCursorVisible(true);
                loginwithemail.setBackgroundResource(R.drawable.button);
                loginwithemail.setTextColor(Color.parseColor("#FFFFFF"));
                progressBarforemaillogin.setVisibility(View.GONE);

                new AlertDialog.Builder(LoginAct.this)
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
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
       if(i== KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
           emailloginchecker();
       }

       return false;
    }

}