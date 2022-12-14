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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



import java.util.HashMap;
import java.util.Map;

public class PersonDetails extends AppCompatActivity {
    TextView oraganizeactivity;
    FirebaseFirestore firebaseFirestore;
    ProgressBar PPprogressBar;

    EditText actname, actDetails, actdatetime, actlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        getSupportActionBar().hide();


        PPprogressBar = (ProgressBar) findViewById(R.id.progressBarforactivity);
        actlink = (EditText) findViewById(R.id.activitylink);
        actdatetime = (EditText) findViewById(R.id.activitydate);
        actDetails = (EditText) findViewById(R.id.activityDetails);
        actname = (EditText) findViewById(R.id.activityname);
        oraganizeactivity = (TextView) findViewById(R.id.organizetheactivity);
        firebaseFirestore = FirebaseFirestore.getInstance();

        oraganizeactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oraganizeactivitymethod();
            }
        });
    }

    private void oraganizeactivitymethod() {
        String Aname = actname.getText().toString();
        String Adetails = actDetails.getText().toString();
        String Adate = actdatetime.getText().toString();
        String Alink = actlink.getText().toString();

        if (TextUtils.isEmpty(Aname)) {
            actname.setError("Invalid name");
            return;
        }
        if (TextUtils.isEmpty(Adetails)) {
            actDetails.setError("Invalid details");
            return;
        }
        if (TextUtils.isEmpty(Adate)) {
            actdatetime.setError("Invalid details");
            return;
        }
        if (TextUtils.isEmpty(Alink)) {
            actlink.setError("Invalid");
            return;
        }
        PPprogressBar.setVisibility(View.VISIBLE);


        DocumentReference aAdocumentReference = firebaseFirestore.collection("Guest Lectures").document("Activity Details");
        Map<String, Object> mapss = new HashMap<>();
        mapss.put("Activity name", Aname);
        mapss.put("Activity Details", Adetails);
        mapss.put("Date and time", Adate);
        mapss.put("Meeting link", Alink);
aAdocumentReference.set(mapss).addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void aVoid) {
        PPprogressBar.setVisibility(View.GONE);
        Toast.makeText(PersonDetails.this, "Activity Scheduled Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(PersonDetails.this, Admininfo.class);
        startActivity(intent);
        finish();
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        PPprogressBar.setVisibility(View.GONE);
        Toast.makeText(PersonDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
});


    }




    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PersonDetails.this,Adminwindow.class);
        startActivity(intent);
        finish();
    }
}

