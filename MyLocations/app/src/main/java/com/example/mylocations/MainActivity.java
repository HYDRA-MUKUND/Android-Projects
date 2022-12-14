package com.example.mylocations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String>addresses=new ArrayList<String>();
    static  ArrayList<LatLng>openit=new ArrayList<LatLng>();
   static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.listview);
        addresses.add("Add Locations");
        openit.add(new LatLng(0,0));
      arrayAdapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,addresses);
     listView.setAdapter(arrayAdapter);
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
             intent.putExtra("location",i);
             startActivity(intent);
         }
     });
    }
}
