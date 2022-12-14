package com.example.h_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;



public class patientsrecord extends AppCompatActivity {
    ImageView gototy;
    ListView nameofpat;
    SearchView searchView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<String> onlynum=new ArrayList<String>();
    ArrayList<String> onlyweight=new ArrayList<String>();
    ArrayList<String> onlyapdate=new ArrayList<String>();
    ArrayList<String> onlybirthdate=new ArrayList<String>();
    ArrayList<String> onlyname=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientsrecord);
        getWindow().setStatusBarColor(Color.parseColor("#2B65EC"));
        gototy = (ImageView) findViewById(R.id.gototyp);
        searchView = (SearchView) findViewById(R.id.searchview);
        nameofpat = (ListView) findViewById(R.id.listdata);
        String login_url = "http:/192.168.43.6/practice6.php";
        gototy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gototy.setEnabled(false);
                gotobacknow();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (arrayList.contains(s)) {
                    arrayAdapter.getFilter().filter(s);
                } else {
                    Toast.makeText(patientsrecord.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                arrayAdapter.getFilter().filter(s);


                return false;
            }
        });
        nameofpat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int x=position;
                Intent intamb=new Intent(patientsrecord.this, Updatedata.class);
                intamb.putExtra("nameP",onlyname.get(x));
                intamb.putExtra("numberP",onlynum.get(x));
                intamb.putExtra("apdateP",onlyapdate.get(x));
                intamb.putExtra("birthdateP",onlybirthdate.get(x));
                intamb.putExtra("weightP",onlyweight.get(x));
                startActivity(intamb);
                finish();
            }
        });



        getJSON(login_url);
    }

    private void gotobacknow() {
        super.onBackPressed();
    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            onlynum.add(obj.getString("number"));
            onlyname.add(obj.getString("name"));
            onlyweight.add(obj.getString("weight"));
            onlyapdate.add(obj.getString("apdate"));
            onlybirthdate.add(obj.getString("birthdate"));
            arrayList.add(obj.getString("name")+" ( +91 -"+obj.getString("number")+" )");
        }
         arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        nameofpat.setAdapter(arrayAdapter);
    }




    @Override
    protected void onResume() {
        super.onResume();
        gototy.setEnabled(true);
    }
}

