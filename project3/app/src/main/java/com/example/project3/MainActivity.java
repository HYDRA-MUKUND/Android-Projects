package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Math;
public class MainActivity extends AppCompatActivity {

    public void clickme(View view)
    { int flag1=0,flag2=0,counter=1,triangularno=1;
    String msg;
        EditText editText=(EditText)findViewById(R.id.editText);
        int number=Integer.parseInt(editText.getText().toString());
        double result=Math.sqrt(number);
        if(result==Math.floor(result))
        {
            flag1=1;
        }
        while (triangularno<number)
        {
            counter++;
            triangularno=counter+triangularno;
        }
        if(triangularno==number)
        {
            flag2=1;
        }
        if(flag1==0 && flag2==0)
        {
            msg="Not Triangular and Square ";
        }
        else if(flag1==1 && flag2 ==1)
        {
            msg="Triangular and Square";
        }
        else if(flag1==1 && flag2==0)
        {
            msg="Only Square";
        }
        else {
            msg="Only Triangular";
        }
        Toast jg=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
     View kk=jg.getView();
     kk.setBackgroundColor(Color.RED);
        TextView bhai=(TextView)kk.findViewById(android.R.id.message);
        bhai.setTextColor(Color.BLACK);
      jg.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
