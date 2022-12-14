package com.example.databases1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase=this.openOrCreateDatabase("ORA",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS event (eventname VARCHAR(20),year INT(5))");
        sqLiteDatabase.execSQL("INSERT INTO event (eventname,year) VALUES('Pubg Matches',2020)");
        sqLiteDatabase.execSQL("INSERT INTO event (eventname,year) VALUES('Sports',2010)");
        sqLiteDatabase.execSQL("INSERT INTO event (eventname,year) VALUES('Singing',2019)");
        sqLiteDatabase.execSQL("INSERT INTO event (eventname,year) VALUES('Fighting',2018)");
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM event WHERE year==2010",null);
        int name=cursor.getColumnIndex("eventname");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Log.i("Eventname",cursor.getString(name));
            cursor.moveToNext();
        }



    }
}
