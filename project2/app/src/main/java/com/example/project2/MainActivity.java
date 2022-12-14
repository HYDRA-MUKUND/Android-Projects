package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
public class MainActivity extends AppCompatActivity {
    Random mr=new Random();
    int x= mr.nextInt(21);



    public void clickme (View view) {

        EditText editText = (EditText) findViewById(R.id.editText);
        int z = Integer.parseInt(editText.getText().toString());
        if (z > x)
        {
            Toast.makeText(this, "Higher", Toast.LENGTH_SHORT).show();
        }
        else if(z<x)
        {
            Toast.makeText(this, "Lower", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "YOu are Right", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
