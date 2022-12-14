package com.example.notemaker;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
public class MainActivity extends AppCompatActivity {
ListView listView;
SharedPreferences sharedPreferences;
static ArrayList<String> arrayList=new ArrayList<>();
static ArrayAdapter arrayAdapter;
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menubar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.additem) {
            Intent intent = new Intent(getApplicationContext(), Notes.class);
            startActivity(intent);
            return true;
        } else {
            return false;
        } }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.parseColor("#7E3517"));
        listView=(ListView)findViewById(R.id.listview);
        sharedPreferences=getApplicationContext().getSharedPreferences(
                "com.example.notemaker", Context.MODE_PRIVATE);
        HashSet<String> set= (HashSet<String>) sharedPreferences.getStringSet
                ("store",null);
        if(set==null){
            arrayList.add("make notes"); }
        else {
            arrayList=new ArrayList(set); }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.
                simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),Notes.class);
                intent.putExtra("notes",i);
                startActivity(intent);
            }});
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            final int x=i;
              new AlertDialog.Builder(MainActivity.this)
                      .setIcon(R.drawable.complain)
                      .setTitle("  Are you sure to Delete..?")
                      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              arrayList.remove(x);
                              arrayAdapter.notifyDataSetChanged();
                              HashSet<String> set=new HashSet<>(MainActivity.arrayList);
                              sharedPreferences.edit().putStringSet("store",set).apply();
                          }
                      })
                      .setNegativeButton("No",null)
                      .show();
              return true; }
        }); }}
