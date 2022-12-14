package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    public void out(int totall){
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int j=1;j<=10;j++){
            arrayList.add(Integer.toString(j*totall));
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       listView=findViewById(R.id.listview);
        out(1);

        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        final SeekBar seekBar2=(SeekBar)findViewById(R.id.seekBar2);
        seekBar2.setMax(20);


        seekBar.setMax(20);
        seekBar.setProgress(1);
         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 Log.i("ok",Integer.toString(i));
                int total;
                  int min=1;
                  if(i<min){
                      total=min;
                  }
               else{
                   total=i;
                  }
               out(total);
               seekBar2.setProgress(total);
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });






    }


}
