package com.hydra.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

public class FirstTimeMobile extends AppCompatActivity implements View.OnKeyListener {
    private EditText phonenumberreg;
    private TextView phonenextbuttton,newusss;
   private CountryCodePicker countryCodePickerforreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_mobile);
        getWindow().setStatusBarColor(Color.parseColor("#F2357CC8"));
        phonenumberreg=(EditText) findViewById(R.id.phonelogin);
        phonenextbuttton=(TextView) findViewById(R.id.nextotplog);
        phonenextbuttton.setEnabled(false);
        newusss=(TextView)findViewById(R.id.newuserokkk);
        countryCodePickerforreg=(CountryCodePicker) findViewById(R.id.countrycodepickerlog);
        phonenumberreg.setOnKeyListener(this);


         newusss.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(FirstTimeMobile.this,MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         });
        phonenextbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextactivitytoverifyotp();
            }
        });









        phonenumberreg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(phonenumberreg.getText().toString().length()>0){
                  phonenextbuttton.setEnabled(true);
                    phonenextbuttton.setBackgroundResource(R.drawable.button);
                    phonenextbuttton.setTextColor(Color.parseColor("#ffffff"));
                }
                else if(phonenumberreg.getText().toString().equals("")){
                    phonenextbuttton.setEnabled(false);
                    phonenextbuttton.setBackgroundResource(R.drawable.buttonblur);
                    phonenextbuttton.setTextColor(Color.parseColor("#A1D4EF"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void nextactivitytoverifyotp() {
        if(TextUtils.isEmpty(phonenumberreg.getText().toString()) || phonenumberreg.getText().toString().length()<10){
            phonenumberreg.setError("Must be 10 digit");
            return;
        }
        else {
            Intent intent = new Intent(this, Loginusingotppp.class);
            intent.putExtra("phonenumberkeyy",phonenumberreg.getText().toString() );
            intent.putExtra("countrycodee",countryCodePickerforreg.getDefaultCountryCode().toString());
            startActivity(intent);
            finish();

        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imyilm = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imyilm.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(FirstTimeMobile.this,LoginAct.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i== KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            nextactivitytoverifyotp();
        }

        return false;
    }
}