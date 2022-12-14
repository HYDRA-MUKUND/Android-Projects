package com.example.lastdatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class Database extends SQLiteOpenHelper {
    public  static  final String DBNAME="DATA";
    public  static  final String TABLENAME="TABLENAME";
    public  static  final String ID="ID";
    public  static  final String MOBILE="MOBILE";
    public  static  final String USERNAME="USERNAME";
    public  static  final String PASSWORD="PASSWORD";

    public Database(@Nullable Context context ) {
        super(context, DBNAME, null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLENAME+"("+ID + " INTEGER PRIMARY KEY,"+ MOBILE +" VARCHAR(20),"+USERNAME+ " VARCHAR(20),"+PASSWORD +" VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
onCreate(sqLiteDatabase);
    }
    public  boolean insertValues(String mobileno,String username,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(MOBILE,mobileno);
        contentValues.put(USERNAME,username);
        contentValues.put(PASSWORD,password);
        long check=sqLiteDatabase.insert(TABLENAME,null,contentValues);
    if(check==-1){
        return false;
    }
    else {
        return true;
    }
    }
   public  Cursor getALL(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT "+USERNAME+","+PASSWORD+" FROM "+TABLENAME,null);
       return cursor;
   }


}
