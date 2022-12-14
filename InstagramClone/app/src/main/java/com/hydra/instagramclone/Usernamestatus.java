package com.hydra.instagramclone;



import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



public class Usernamestatus {


    FirebaseFirestore firebaseFirestore;
    Usernamestatus(){
        firebaseFirestore=FirebaseFirestore.getInstance();

    }

    public void verifyusername() {
        CollectionReference collectionReference=firebaseFirestore.collection("Users Data");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        Registrationofuser.arrayList.add(queryDocumentSnapshot.getString("Username"));

                    }
                }
            }
        });

    }
}
