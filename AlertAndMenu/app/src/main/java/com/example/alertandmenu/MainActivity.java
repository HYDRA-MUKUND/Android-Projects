package com.example.alertandmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        imageView=(ImageView)findViewById(R.id.imageView11);
        imageView.animate().translationX(-500);
        sharedPreferences=this.getSharedPreferences("com.example.alertandmenu", Context.MODE_PRIVATE);
        String check=sharedPreferences.getString("value","No");
        if(check.equals("No"))
        {
        new AlertDialog.Builder(this)
                .setTitle("Choose your language!!")
                .setIcon(android.R.drawable.alert_dark_frame)

                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textView.setText("Cat");
                        sharedPreferences.edit().putString("value","Cat").apply();
                    }
                })
                .setPositiveButton("Marathi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textView.setText("मांजर");
                        sharedPreferences.edit().putString("value","मांजर").apply();
                    }
                })
                .show();

    }
        else {
            textView.setText(check);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
          case R.id.marathi:
              textView.setText("मांजर");
              sharedPreferences.edit().putString("value","मांजर").apply();
              return true;

          case R.id.english:
              textView.setText("Cat");
              sharedPreferences.edit().putString("value","Cat").apply();
              return  true;

          default:
              return super.onOptionsItemSelected(item);

      }
    }
    public  void onclick(View view){
        imageView.animate().alpha(1).translationXBy(4000).setDuration(7000);
    }
}

