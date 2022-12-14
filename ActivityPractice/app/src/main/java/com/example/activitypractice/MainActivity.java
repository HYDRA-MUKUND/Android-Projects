package com.example.activitypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String>arrayList=new ArrayList<String>();
        arrayList.add("HYDRa");
        arrayList.add("sdfsdfsddsf");
        arrayList.add("HYdsffffDRa");
        arrayList.add("HYwwwDRa");
        arrayList.add("HYgdfgdfDRa");
        arrayList.add("HkjasjasYDRa");
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_gallery_item,arrayList);

        listView.setAdapter(arrayAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent=new Intent(getApplicationContext(),Second.class);
               intent.putExtra("name",arrayList.get(i).toString());
               startActivity(intent);
           }
       });


    }
}
