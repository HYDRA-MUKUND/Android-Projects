package com.hydra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void logoutact(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}