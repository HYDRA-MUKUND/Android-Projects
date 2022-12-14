package com.hydra.chattingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
TextView getmesage,mysendmessage;
EditText myinputmsg;
Button button,buttonsecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore=FirebaseFirestore.getInstance();
        getmesage=(TextView)findViewById(R.id.getmessage);
        myinputmsg=(EditText)findViewById(R.id.entermessage);
        mysendmessage=(TextView)findViewById(R.id.mysendwindow);
        button=(Button)findViewById(R.id.button);
        buttonsecond=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendinputmsg();
            }
        });
buttonsecond.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        getmesagedata();
    }
});
    }

    private void getmesagedata() {

        DocumentReference documentReference=firebaseFirestore.collection("MSG").document("msgsender");
        Map<String,Object>map=new HashMap<>();
        map.put("msgtwo",myinputmsg.getText().toString());
        documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                mysendmessage.setText(myinputmsg.getText().toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
           getmesage.setText(value.getString("msgone"));
            }
        });

    }

    private void sendinputmsg() {

        DocumentReference documentReference=firebaseFirestore.collection("MSG").document("msgsender");
        Map<String,Object>map=new HashMap<>();
        map.put("msgone",myinputmsg.getText().toString());
     documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
         @Override
         public void onSuccess(Void aVoid) {
             Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
             mysendmessage.setText(myinputmsg.getText().toString());
         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
         }
     });
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                getmesage.setText(value.getString("msgtwo"));
            }
        });



    }

}