package com.hydra.instagramclone;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;


public class Phonefrag extends Fragment implements View.OnKeyListener{

private EditText phonenumberreg;
private TextView phonenextbuttton;
 CountryCodePicker countryCodePickerforreg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_phonefrag, container, false);
       phonenumberreg=(EditText) v.findViewById(R.id.phoneauther);
       phonenextbuttton=(TextView) v.findViewById(R.id.nextotp);
       phonenextbuttton.setEnabled(false);
        countryCodePickerforreg=(CountryCodePicker) v.findViewById(R.id.countrycodepickerreg);
       phonenumberreg.setOnKeyListener(this);

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
       phonenextbuttton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               nextactivitytoverifyotp();
           }
       });

       return v;
    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i== KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            nextactivitytoverifyotp();
        }
        return false;
    }


    private void nextactivitytoverifyotp() {
        if(TextUtils.isEmpty(phonenumberreg.getText().toString()) || phonenumberreg.getText().toString().length()<10){
            phonenumberreg.setError("Must be 10 digit");
            return;
        }
        else {
            Intent intent = new Intent(getActivity(), PhoneOtpVerification.class);
            intent.putExtra("phonenumberkey",phonenumberreg.getText().toString() );
            intent.putExtra("countrycode",countryCodePickerforreg.getDefaultCountryCode().toString());
            startActivity(intent);
            getActivity().finish();

        }

    }
    }