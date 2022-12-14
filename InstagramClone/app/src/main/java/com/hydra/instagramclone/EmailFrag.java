package com.hydra.instagramclone;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class EmailFrag extends Fragment implements View.OnKeyListener {
    private EditText emailauther, passwordauther;
    private TextView NextEmail;
    private ProgressDialog progressDialog;

private FirebaseAuth firebaseAuth;
private ImageView passhidesh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_email, container, false);

        firebaseAuth=FirebaseAuth.getInstance();
        emailauther = (EditText) v.findViewById(R.id.emailauther);
        passwordauther = (EditText) v.findViewById(R.id.passwordauther);
        passhidesh=(ImageView)v.findViewById(R.id.passwordhideandshowgg);
        passwordauther.setOnKeyListener(this);
        NextEmail = (TextView) v.findViewById(R.id.nextemail);
        NextEmail.setEnabled(false);

        emailauther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(emailauther.getText().toString().length()>0 && passwordauther.getText().toString().length()>0){
                    NextEmail.setEnabled(true);
                    NextEmail.setBackgroundResource(R.drawable.button);
                    NextEmail.setTextColor(Color.parseColor("#ffffff"));


                }
                else { NextEmail.setEnabled(false);
                    NextEmail.setBackgroundResource(R.drawable.buttonblur);

                    NextEmail.setTextColor(Color.parseColor("#A1D4EF"));


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordauther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(emailauther.getText().toString().length()>0 && passwordauther.getText().toString().length()>0){
                    NextEmail.setEnabled(true);
                    NextEmail.setBackgroundResource(R.drawable.button);
                    NextEmail.setTextColor(Color.parseColor("#ffffff"));


                }
                else { NextEmail.setEnabled(false);
                    NextEmail.setBackgroundResource(R.drawable.buttonblur);

                    NextEmail.setTextColor(Color.parseColor("#A1D4EF"));


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        NextEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataValidationMethodforEmail();
            }
        });
        passhidesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passhideandshowmethods();
            }
        });
        return v;
    }

    private void DataValidationMethodforEmail() {
        if (TextUtils.isEmpty(emailauther.getText().toString())) {
            emailauther.setError("Invalid email");
            return;
        }
        if (passwordauther.getText().toString().length() < 6) {
            passwordauther.setError("At least 6 characters");
            return;
        }
        progressDialog=new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        progressDialog.show();
        Usercreation();


    }

    private void Usercreation() {
         firebaseAuth.createUserWithEmailAndPassword(emailauther.getText().toString(),passwordauther.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     progressDialog.dismiss();
                     Intent i=new Intent(getActivity(),Registrationofuser.class);
                     startActivity(i);
                         getActivity().finish();
                 }
                 else {
                     progressDialog.dismiss();
                   new AlertDialog.Builder(getActivity())
                           .setTitle("ERROR")
                           .setMessage(task.getException().getMessage())
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {
                                   dialogInterface.dismiss();
                               }
                           }).show();
                 }
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 progressDialog.dismiss();
                 progressDialog.dismiss();
                 new AlertDialog.Builder(getActivity())
                         .setTitle("ERROR")
                         .setMessage(e.getMessage())
                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 dialogInterface.dismiss();
                             }
                         }).show();
             }
         });
    }
    private void passhideandshowmethods() {
        if(passwordauther.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){

            passhidesh.setImageResource(R.drawable.view);
            passwordauther.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordauther.setSelection(passwordauther.getText().toString().length());

        }
        else {


            passhidesh.setImageResource(R.drawable.hide);
           passwordauther.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordauther.setSelection(passwordauther.getText().toString().length());
        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i== KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            DataValidationMethodforEmail();
        }
        return false;
    }
}