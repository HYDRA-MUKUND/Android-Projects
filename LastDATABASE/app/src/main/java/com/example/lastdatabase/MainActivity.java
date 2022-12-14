package com.example.lastdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText us,pas;
    Button login,newuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Database objj=new Database(MainActivity.this);
 us=(EditText)findViewById(R.id.username);
 pas=(EditText)findViewById(R.id.password);
 login=(Button)findViewById(R.id.login);
 newuser=(Button)findViewById(R.id.newuser);
 login.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         boolean flag=true;
         Cursor cursor=objj.getALL();
         int iduser=cursor.getColumnIndex("USERNAME");
         int idpass=cursor.getColumnIndex("PASSWORD");

         cursor.moveToFirst();
         while (!cursor.isAfterLast()){
         if(cursor.getString(iduser).equals(us.getText().toString()) && cursor.getString(idpass).equals(pas.getText().toString())){
             Toast.makeText(MainActivity.this, "LOGIN SUCESSFULL", Toast.LENGTH_SHORT).show();
             flag=false;
             break;
         }

             cursor.moveToNext();
         }
         if(flag==true){
             Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
         }
     }
 });
 newuser.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent intent=new Intent(MainActivity.this,Main2Activity.class);
         startActivity(intent);
     }
 });
    }
}
