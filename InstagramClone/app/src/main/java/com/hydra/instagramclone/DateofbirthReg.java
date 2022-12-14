package com.hydra.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.text.TextUtils;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateofbirthReg extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
private EditText dateofbirth;
private TextView nextdateofbirth,alredyaloog;
private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
private String uuname,yyyyname;
    private FirebaseFirestore firebaseFirestore;
private final String [] monthsarray={"January","February","March","April","May","June","July","August","September","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateofbirth_reg);
        Intent intenrt=getIntent();
        yyyyname=intenrt.getStringExtra("Yname");
        uuname=intenrt.getStringExtra("Uname");
        getWindow().setStatusBarColor(Color.parseColor("#F2357CC8"));
      ImageView  imageVi=(ImageView)findViewById(R.id.calenderfrag);
        dateofbirth=(EditText)findViewById(R.id.dateofbirth);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        alredyaloog=(TextView) findViewById(R.id.alredayhaveloginbirth);
        alredyaloog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DateofbirthReg.this,LoginAct.class);
                startActivity(intent);
                finish();
            }
        });
        nextdateofbirth=(TextView)findViewById(R.id.nextdateofbirth);
         imageVi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 DatePickerDialog datePickerDialog=new DatePickerDialog(
                         DateofbirthReg.this,DateofbirthReg.this,
                         Calendar.getInstance().get(Calendar.YEAR),
                         Calendar.getInstance().get(Calendar.MONTH),
                         Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

                 );
                 datePickerDialog.show();
             }
         });
         nextdateofbirth.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 almostdonemethod();
             }
         });
    }

    private void almostdonemethod() {
        if(TextUtils.isEmpty(dateofbirth.getText().toString())){
            dateofbirth.setError("please select Birthdate");
            return;
        }
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Creating Account...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            DocumentReference documentReference = firebaseFirestore.collection("Users Data").document(firebaseUser.getUid());
            Map<String, Object> map = new HashMap<>();
            map.put("Name of person", yyyyname);
            map.put("Username",uuname);
            map.put("Date of Birth", dateofbirth.getText().toString());

            documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(DateofbirthReg.this, HelloStarrMain.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(DateofbirthReg.this)
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Calendar calendar=Calendar.getInstance();
        String dob=calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH)+" "+calendar.get(Calendar.DAY_OF_MONTH)+", "+calendar.get(Calendar.YEAR);
        dateofbirth.setText(dob);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
      dateofbirth.setText(monthsarray[i1]+" "+String.valueOf(i2)+ ", "+String.valueOf(i));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager immone = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        immone.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(DateofbirthReg.this,Registrationofuser.class);
        startActivity(intent);
        finish();
    }
}