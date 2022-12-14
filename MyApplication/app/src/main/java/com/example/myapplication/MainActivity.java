package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public  void clickme(View view){
    Intent obj=getPackageManager().getLaunchIntentForPackage("com.whatsapp");
    if(obj!=null){
        startActivity(obj);
    }
    else{
        Toast.makeText(this, "Not Present", Toast.LENGTH_SHORT).show();
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
