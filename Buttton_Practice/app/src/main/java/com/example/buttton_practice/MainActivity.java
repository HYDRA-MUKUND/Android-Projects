package com.example.buttton_practice;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button button;
EditText name,number;
RadioButton but;
RadioGroup groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        groups=(RadioGroup)findViewById(R.id.group);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                but=(RadioButton)findViewById(groups.getCheckedRadioButtonId());
                Toast.makeText(MainActivity.this, but.getText(), Toast.LENGTH_SHORT).show();
               String ps_name=name.getText().toString();
               String ps_number=number.getText().toString();
               if(ps_name.equals("") ){
                   name.setHintTextColor(Color.parseColor("#E41B17"));
                   name.setHint(" invalid ");
               }
               else {
                   int ps_n=ps_name.length();
                   boolean num_check=true;
                   for (int i = 0; i < ps_n; i++) {
                       if(Character.isDigit(ps_name.charAt(i))){
                           num_check=false;
                           break;
                       }}
                   if(num_check)
                   {

                           Toast.makeText(MainActivity.this, "Name is OK", Toast.LENGTH_SHORT).show();


                   }
                   else{
                       name.setHintTextColor(Color.parseColor("#E41B17"));
                       name.setText("");
                       name.setHint(" invalid ");
                   }


               }
                if(ps_number.equals("") ){
                    number.setHintTextColor(Color.parseColor("#E41B17"));

                    number.setHint(" invalid ");
                }
                else {
                    int ps_n=ps_number.length();
                    boolean num_check=true;
                    for (int i = 0; i < ps_n; i++) {
                        if(!Character.isDigit(ps_number.charAt(i))){
                            num_check=false;
                            break;
                        }}
                    if(num_check)
                    {
                        if(ps_n==10) {

                            Toast.makeText(MainActivity.this, "Number is OK", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            number.setHintTextColor(Color.parseColor("#E41B17"));
                            number.setText("");
                            number.setHint(" invalid ");
                        }

                    }
                    else{
                        number.setHintTextColor(Color.parseColor("#E41B17"));
                        number.setText("");
                        number.setHint(" invalid ");
                    }


                }
            }
        });
    }
public void onread(View v){
    Intent inn=new Intent(this,Main3Activity.class);
    startActivity(inn);
}

}
