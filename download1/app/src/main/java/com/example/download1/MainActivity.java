package com.example.download1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showChangeLanguageDialog() {
        final String [] languagess={"मराठी","Engilsh"};
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Language..");
        builder.setSingleChoiceItems(languagess, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){

                    setLocal("mr");
                    recreate();
                }
                else if(i==1)
                {

                    setLocal("en");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog= null;
        dialog.create();

        dialog.show();
    }

    private void setLocal(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My Lang",lang);
        editor.apply();



    }
    public  void putlocal(){
        SharedPreferences  pref=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String languages=pref.getString("My Languages","");
        setLocal(languages);
    }


}
