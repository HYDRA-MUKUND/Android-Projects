package com.example.database2;

import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText name, password;
    Button button,vie,updae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
final sqlite sq=new sqlite(this);
name=(EditText)findViewById(R.id.name);
password=(EditText)findViewById(R.id.password);
button=(Button)findViewById(R.id.button2);
vie=(Button)findViewById(R.id.read);
vie.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Cursor cursor=sq.getdataa();
        int nin=cursor.getColumnIndex("NAME");
        int pin=cursor.getColumnIndex("PASSWORD");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Log.i("Data",cursor.getString(nin)+" "+cursor.getString(pin));
            cursor.moveToNext();
        }
    }
});
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Boolean rexx=sq.Insert(name.getText().toString(),password.getText().toString());
        if(rexx){
            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
        }
    }
});


    }

}