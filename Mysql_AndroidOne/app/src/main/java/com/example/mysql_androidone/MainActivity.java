package com.example.mysql_androidone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText name,age,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        number=(EditText)findViewById(R.id.mobile_no);


    }
    public  void  savedata(View view){
        String us_name=name.getText().toString();

        String us_age=age.getText().toString();
        String us_number=number.getText().toString();
        String key1="save";
        mysqldata obj=new mysqldata(this);
        obj.execute(key1,us_name,us_age,us_number);


    }
}
