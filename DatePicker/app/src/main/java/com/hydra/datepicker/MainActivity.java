package com.hydra.datepicker;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView dateinfo, dateselector;
EditText editText;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateinfo = (TextView) findViewById(R.id.dateinfo);
        dateselector = (TextView) findViewById(R.id.hello);
       editText=(EditText)findViewById(R.id.editTextTextPassword);
        imageView=(ImageView)findViewById(R.id.showorhide);
dateselector.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        databaseListdd();
    }
});
    }

    private void databaseListdd() {
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();

    }
public void onclickit(View view){
        if(editText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){

            imageView.setImageResource(android.R.drawable.checkbox_on_background);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
        }
    else {
            imageView.setImageResource(android.R.drawable.checkbox_off_background);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
    }
}
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
dateinfo.setText(i2+"/"+i1+"/"+i);
    }
}
