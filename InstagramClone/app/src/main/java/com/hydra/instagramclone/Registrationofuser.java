package com.hydra.instagramclone;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class Registrationofuser extends AppCompatActivity implements View.OnKeyListener{
private EditText usernamereg,namereg;
private FirebaseAuth firebaseAuth;
   static ArrayList<String> arrayList=new ArrayList<>();
private FirebaseFirestore firebaseFirestore;
private TextView nextuserdata,usernamestatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationofuser);
        getWindow().setStatusBarColor(Color.parseColor("#F2357CC8"));
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        namereg=(EditText)findViewById(R.id.namereg);
        TextView alredylog=(TextView)findViewById(R.id.alredayhaveloginok);

        usernamestatus=(TextView)findViewById(R.id.usernamestatus);
        usernamereg=(EditText)findViewById(R.id.usernamereg);
        nextuserdata=(TextView)findViewById(R.id.nextusername);
         usernamereg.setOnKeyListener(this);
         alredylog.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(Registrationofuser.this,LoginAct.class);
                 startActivity(intent);
                 finish();
             }
         });
        usernamereg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Usernamestatus usernamests=new Usernamestatus();
                usernamests.verifyusername();
                if (editable.toString().equals("")) {
                    usernamestatus.setTextColor(Color.parseColor("#72493D3D"));
                    usernamestatus.setText("Your username should be unique");
                    nextuserdata.setEnabled(false);
                    nextuserdata.setBackgroundResource(R.drawable.buttonblur);
                    nextuserdata.setTextColor(Color.parseColor("#A1D4EF"));


                }
                else {
                    if(arrayList.contains(editable.toString())){
                        usernamestatus.setTextColor(Color.RED);
                        usernamestatus.setText("Someone has already taken this username, try different one");
                        nextuserdata.setEnabled(false);
                        nextuserdata.setBackgroundResource(R.drawable.buttonblur);
                        nextuserdata.setTextColor(Color.parseColor("#A1D4EF"));

                    }
                     else {

                        usernamestatus.setTextColor(Color.parseColor("#028a0f"));
                        usernamestatus.setText("This username is available");
                        nextuserdata.setEnabled(true);
                        nextuserdata.setBackgroundResource(R.drawable.button);
                        nextuserdata.setTextColor(Color.parseColor("#FFFFFF"));

                    }
                }
            }
        });
        nextuserdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userdatavalidation();
            }
        });

    }

    private void userdatavalidation() {

        if(TextUtils.isEmpty(namereg.getText().toString())){
            namereg.setError("Invalid name");
            return;
        }
        if(TextUtils.isEmpty(usernamereg.getText().toString())){
            usernamereg.setError("Invalid username");
            return;
        }

            saveusersdata();




    }

    private void saveusersdata() {
        Intent intent=new Intent(this,DateofbirthReg.class);
        intent.putExtra("Yname",namereg.getText().toString());
        intent.putExtra("Uname",usernamereg.getText().toString());
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Registrationofuser.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager immh = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        immh.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i== KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            userdatavalidation();
        }
        return false;
    }
}