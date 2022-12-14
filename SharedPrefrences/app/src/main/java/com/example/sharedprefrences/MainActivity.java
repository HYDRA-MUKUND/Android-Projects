package com.example.sharedprefrences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    SharedPreferences sharedPreferences;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            sharedPreferences = this.getSharedPreferences("com.example.sharedprefrences", Context.MODE_PRIVATE);


            if(sharedPreferences.getInt("value",0)>0)
            {
                Toast.makeText(this,"DOne",Toast.LENGTH_SHORT).show();
            }
        j++;
        sharedPreferences.edit().putInt("value",j).apply();
    }
}
