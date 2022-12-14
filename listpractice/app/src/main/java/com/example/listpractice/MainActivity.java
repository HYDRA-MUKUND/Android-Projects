package com.example.listpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.listView);
        ArrayList<String> obj1=new ArrayList<String>();

        obj1.add("HYDRA");
        obj1.add("OK");
        obj1.add("XQF");
        obj1.add("FNATIC");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,obj1);
        listView.setAdapter(arrayAdapter);
    }
}
