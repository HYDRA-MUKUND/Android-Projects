package com.example.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

import androidx.annotation.Nullable;


public class sqlite extends SQLiteOpenHelper {
    public static final String Dbname="HYDRA";
    public static final String Id="ID";
    public static final String Name="NAME";
    public static final String Password="PASSWORD";
    public static final String Tname="TABLENAME";

    public sqlite(@Nullable Context context) {
        super(context, Dbname, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Tname + "("
                + Id + " INTEGER PRIMARY KEY," + Name + " TEXT,"
                + Password + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);      }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS "+Tname);
          onCreate(sqLiteDatabase);
    }
    public Boolean Insert(String namee,String passwordd){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name,namee);
        contentValues.put(Password,passwordd);
      long res=  sqLiteDatabase.insert(Tname,null,contentValues);
      if(res==-1){
          return false;
      }
      else {
          return true;
      }
    }
    public Cursor getdataa(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+Tname,null);
        return cursor;
    }
}
