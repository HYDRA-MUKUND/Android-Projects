package com.hydra.ieee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class StudentWindow extends AppCompatActivity {
ImageView personsimage;
FirebaseFirestore firebaseFirestore;
StorageReference storageReference;

TextView Perquili,Pername,ActDetails,ActName,ActDateTime,JoinMeeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_window);
        getSupportActionBar().hide();

        personsimage=(ImageView)findViewById(R.id.loadpersonimages);
        Perquili=(TextView)findViewById(R.id.loadqulificationdetails);
        Pername=(TextView)findViewById(R.id.loadpersonname);
        ActDateTime=(TextView)findViewById(R.id.loaddateandtime);
        ActName=(TextView)findViewById(R.id.loadnameofact);
        JoinMeeting=(TextView)findViewById(R.id.joinmeeting);

        ActDetails=(TextView)findViewById(R.id.loaddetailsofact);
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        JoinMeeting.setClickable(true);
        JoinMeeting.setMovementMethod(LinkMovementMethod.getInstance());

        StorageReference filereforence=storageReference.child("Profile.jpg");
        filereforence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(personsimage);
            }
        });
        DocumentReference AdocumentReference=firebaseFirestore.collection("Guest Lectures").document( "Activity Details");
        AdocumentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ActDateTime.setText(value.getString("Date and time"));
                ActDetails.setText(value.getString("Activity Details"));
                ActName.setText(value.getString("Activity name"));
                String link=value.getString("Meeting link");
                String texti = "<a href='"+link+"'> JOIN MEETING </a>";
                JoinMeeting.setText(Html.fromHtml(texti));

            }
        });

        DocumentReference PdocumentReference=firebaseFirestore.collection("Guest Lectures").document( "Person Details");
        PdocumentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Pername.setText(value.getString("Person's name"));

                Perquili.setText(value.getString("Qulification Details"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Types.class);
        startActivity(intent);
        finish();
    }
}