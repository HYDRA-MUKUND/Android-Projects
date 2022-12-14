package com.example.notemaker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class Notes extends AppCompatActivity {
EditText editText;
int id;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getWindow().setStatusBarColor(Color.parseColor("#7E3517"));
        editText=(EditText)findViewById(R.id.editText);
        Intent intent=getIntent();
        id=intent.getIntExtra("notes",-1);
        if(id!=-1)
        {
            editText.setText(MainActivity.arrayList.get(id));
        }
        else{
            MainActivity.arrayList.add("-> ");
            id=MainActivity.arrayList.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            MainActivity.arrayList.set(id,String.valueOf(charSequence));
            MainActivity.arrayAdapter.notifyDataSetChanged();
                HashSet<String> set1=new HashSet<>(MainActivity.arrayList);
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences
                        ("com.example.notemaker", Context.MODE_PRIVATE);
                sharedPreferences.edit().putStringSet("store",set1).apply();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });}}
