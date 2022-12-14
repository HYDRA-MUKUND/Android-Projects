package com.example.hydrahospital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Appointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
  ImageView gototypes;
  EditText ps_name,ps_mobile,ps_age;
  Button addappointment;
  RatingBar ps_ratbar;
  TextView ps_dob,ps_dobshow;
  CheckBox ps_accidental,ps_nonaccidental;
  RadioButton ps_male,ps_female;
  RadioGroup ps_radiogroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        gototypes=(ImageView)findViewById(R.id.gototypes);
        ps_age=(EditText)findViewById(R.id.ps_age);
        ps_name=(EditText)findViewById(R.id.ps_name);
        ps_mobile=(EditText)findViewById(R.id.ps_mobile_no);
        ps_dob=(TextView) findViewById(R.id.ps_birthdate);
ps_dobshow=(TextView)findViewById(R.id.setpsbirth);
        addappointment=(Button)findViewById(R.id.ps_save);
        ps_ratbar=(RatingBar)findViewById(R.id.ratingBar);
        ps_accidental=(CheckBox)findViewById(R.id.accidental);
        ps_nonaccidental=(CheckBox)findViewById(R.id.nonaccidental);
        ps_radiogroup=(RadioGroup)findViewById(R.id.ps_radiogroup);
        ps_male=(RadioButton)findViewById(R.id.malegrp);
        ps_female=(RadioButton)findViewById(R.id.femalegrp);
        ps_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetDatemethod();
            }
        });
        gototypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gototypes.setEnabled(false);
              gototypesss();
            }
        });
        addappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addtomysqldata();
            }
        });
    }

    private void SetDatemethod() {
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }

    private void addtomysqldata() {
        int Count = 0;
        String us_name = ps_name.getText().toString();
        if (us_name.equals("")) {
            ps_name.setHintTextColor(Color.parseColor("#E41B17"));
            ps_name.setHint(" invalid ");
        } else {
            int cname = us_name.length();
            boolean num_check = true;
            for (int i = 0; i < cname; i++) {
                if (Character.isDigit(us_name.charAt(i))) {
                    num_check = false;
                    break;
                }
            }
            if (num_check) {

                Count++;


            } else {
                ps_name.setHintTextColor(Color.parseColor("#E41B17"));
                ps_name.setText("");
                ps_name.setHint(" invalid name ");
            }
        }
//Done for name


        String us_birthdate = ps_dobshow.getText().toString();
        if (us_birthdate.equals("")) {
            ps_dobshow.setHintTextColor(Color.parseColor("#E41B17"));

            ps_dobshow.setHint(" invalid ");
        } else {

            Count++;
        }

        //Done for DOB

        String us_age = ps_age.getText().toString();
        if (us_age.equals("")) {
            ps_age.setHintTextColor(Color.parseColor("#E41B17"));

            ps_age.setHint(" invalid ");
        } else {
            int cage = us_age.length();
            boolean num_check = true;
            for (int i = 0; i < cage; i++) {
                if (!Character.isDigit(us_age.charAt(i))) {
                    num_check = false;
                    break;
                }
            }
            if (num_check) {
                Count++;
            } else {
                ps_age.setHintTextColor(Color.parseColor("#E41B17"));
                ps_age.setText("");
                ps_age.setHint(" invalid");
            }
        }


//Done for Age


        String us_mobile = ps_mobile.getText().toString();
        if (us_mobile.equals("")) {
            ps_mobile.setHintTextColor(Color.parseColor("#E41B17"));

            ps_mobile.setHint(" invalid ");
        } else {
            int cmobile = us_mobile.length();
            boolean num_check = true;
            for (int i = 0; i < cmobile; i++) {
                if (!Character.isDigit(us_mobile.charAt(i))) {
                    num_check = false;
                    break;
                }
            }
            if (num_check) {
                if (cmobile == 10) {
                    Count++;
                } else {
                    ps_mobile.setHintTextColor(Color.parseColor("#E41B17"));
                    ps_mobile.setText("");
                    ps_mobile.setHint("must be 10 digit");
                }

            } else {
                ps_mobile.setHintTextColor(Color.parseColor("#E41B17"));
                ps_mobile.setText("");
                ps_mobile.setHint("must be number");
            }


        }
        String us_gender = "";
        if (ps_male.isChecked()) {
            us_gender = "Male";
            Count++;
        } else if (ps_female.isChecked()) {
            us_gender = "Female";
            Count++;
        } else {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();

        } 

        String us_type = "";
        if (ps_accidental.isChecked()) {
            us_type = "ACCIDENTAL";
            Count++;
        } else if (ps_nonaccidental.isChecked()) {
            us_type = "NON-ACCIDENTAL";
            Count++;
        }

        String us_condition = "";
        double us_calculate_rating = ps_ratbar.getRating();
        if (us_calculate_rating >= 4.0) {
            us_condition = "Critical";

        } else if (us_calculate_rating >= 3.0) {
            us_condition = "Serious";

        } else if (us_calculate_rating >= 2.0) {
            us_condition = "Fair";
        } else {
            us_condition = "Good";
        }

        if (Count == 6) {
            Mysql_Database mysql_database = new Mysql_Database(this);
            mysql_database.execute("mydata", us_name, us_age, us_birthdate, us_mobile, us_gender, us_type, us_condition);
        }
        else {
            new AlertDialog.Builder(this)
                    .setTitle("Warning")
                    .setMessage(" Please fill all details..!")
                    .setIcon(R.drawable.alert)

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })

                    .show();
        }
    }



    public  void gototypesss(){
     super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        gototypes.setEnabled(true);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
ps_dobshow.setText(i2+"/"+i1+"/"+i);
    }
}
