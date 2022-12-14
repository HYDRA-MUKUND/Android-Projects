package com.hydra.ieee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Adminwindow extends AppCompatActivity {
ImageView imageViewofperson;
TextView chooseimage,uploaddata;
FirebaseFirestore firebaseFirestore;
    ProgressBar PPprogressBar;
StorageReference storageReference;
EditText Pername,PerContact,PerQulification,PerSubject;
Uri uriofpersonimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminwindow);
        getSupportActionBar().hide();

        storageReference= FirebaseStorage.getInstance().getReference();
    imageViewofperson=(ImageView)findViewById(R.id.guestimage);
    chooseimage=(TextView)findViewById(R.id.chooseimage);
        PPprogressBar=(ProgressBar)findViewById(R.id.progressBarforperson);
    PerContact=(EditText)findViewById(R.id.personsContact);
        PerQulification=(EditText)findViewById(R.id.personQulification);
        Pername=(EditText)findViewById(R.id.activityname);
        PerSubject=(EditText)findViewById(R.id.personssubject);
        uploaddata=(TextView)findViewById(R.id.uploadimage);


        firebaseFirestore=FirebaseFirestore.getInstance();

    chooseimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            uploadimagemethod();
        }
    });
uploaddata.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        uploaddatamethod();
    }
});
    }

    private void uploaddatamethod() {
        String Pname=Pername.getText().toString();
        String Pcontact=PerContact.getText().toString();
        String PQuli=PerQulification.getText().toString();
        String Psubject=PerSubject.getText().toString();

        if(TextUtils.isEmpty(Pname)){
            Pername.setError("Invalid name");
            return;
        }
        if(TextUtils.isEmpty(Psubject)){
            PerSubject.setError("Invalid details");
            return;
        }
        if(TextUtils.isEmpty(PQuli)){
            PerQulification.setError("Invalid details");
            return;
        }
        if(TextUtils.isEmpty(Pcontact)){
            PerContact.setError("Invalid");
            return;
        }

        if(!Uri.EMPTY.equals(uriofpersonimage)){

         uploadtofirebase(uriofpersonimage,Pname,Pcontact,Psubject,PQuli);

        }
        else {
            Toast.makeText(this, "Please Choose image", Toast.LENGTH_SHORT).show();
            return;
        }



    }

    private void uploadimagemethod() {

            Intent gallryint=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallryint,1000);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            if( resultCode== Activity.RESULT_OK){
                Uri imageuri=data.getData();
                imageViewofperson.setImageURI(imageuri);
uriofpersonimage=imageuri;
            }
        }

    }


    private void uploadtofirebase(Uri imageuri,String pname,String pcontact,String subject,String quli) {
        PPprogressBar.setVisibility(View.VISIBLE);
        StorageReference filerefrence=storageReference.child("Profile.jpg");

        filerefrence.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                DocumentReference AdocumentReference=firebaseFirestore.collection("Guest Lectures").document("Person Details");
                Map<String,Object> map=new HashMap<>();
                map.put("Person's name",pname);
                map.put("Contact Details",pcontact);
                map.put("Qulification Details",quli);
                map.put("Expert in",subject);

                AdocumentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        PPprogressBar.setVisibility(View.GONE);
                        Toast.makeText(Adminwindow.this, "Details Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(Adminwindow.this,PersonDetails.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        PPprogressBar.setVisibility(View.GONE);
                        Toast.makeText(Adminwindow.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                PPprogressBar.setVisibility(View.GONE);
                Toast.makeText(Adminwindow.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Admininfo.class);
        startActivity(intent);
        finish();
    }
}

