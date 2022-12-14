package com.example.hydrahospital;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ambulance extends AppCompatActivity {
ImageView gototyp,ambuanimation;
Button patcoun;
EditText pat_num;
  String add_of_loc="";
    String lon_of_loc="";
    String lat_of_loc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        Intent ambrecord=getIntent();
        add_of_loc=ambrecord.getStringExtra("addressT");
        lat_of_loc=ambrecord.getStringExtra("latitudeT");
        lon_of_loc=ambrecord.getStringExtra("longitudeT");
gototyp=(ImageView)findViewById(R.id.gototypesss);
pat_num=(EditText)findViewById(R.id.patcount);
patcoun=(Button)findViewById(R.id.ps_amrecord);
ambuanimation=(ImageView)findViewById(R.id.anim);

ambuanimation.animate().translationX(-500);

gototyp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        gototyp.setEnabled(false);
        gototypesback();
    }

});
patcoun.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
patcoun.setEnabled(false);
        donee();
    }
});
    }

    private void donee() {
ambuanimation.animate().alpha(1);
        String patient_count=pat_num.getText().toString();
        if (patient_count.equals("")) {

patcoun.setEnabled(true);
            Toast.makeText(this, "Invalid input..", Toast.LENGTH_SHORT).show();
        } else {
            int ccount = patient_count.length();
            boolean num_check = true;
            for (int i = 0; i < ccount; i++) {
                if (!Character.isDigit(patient_count.charAt(i))) {
                    num_check = false;
                    break;
                }
            }
            if (num_check) {

                ambuanimation.animate().translationXBy(4000).setDuration(6000);
                Toast.makeText(this, "Location Sent..", Toast.LENGTH_SHORT).show();
                senddatatomysql(patient_count);


            } else {patcoun.setEnabled(true);
                Toast.makeText(this, "Invalid input..", Toast.LENGTH_SHORT).show();

                pat_num.setText("");

            }
        }

    }

    private void senddatatomysql(String cou) {
        ambmysql objects=new ambmysql(this);
        objects.execute("myambulance",add_of_loc,lat_of_loc,lon_of_loc,cou);
    }

    private void gototypesback() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
patcoun.setEnabled(true);
        gototyp.setEnabled(true);
    }

}
