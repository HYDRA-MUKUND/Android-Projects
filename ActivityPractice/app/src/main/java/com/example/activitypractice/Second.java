package com.example.activitypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent inten=getIntent();
        String sto=inten.getStringExtra("name");
        Toast.makeText(this, sto, Toast.LENGTH_SHORT).show();
    }
}
