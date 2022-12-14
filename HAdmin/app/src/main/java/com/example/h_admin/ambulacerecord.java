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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ambulacerecord extends AppCompatActivity {
    ImageView gototypes;
TextView totcounttt;
    ListView locationsss;
    SearchView searchVieww;
    ArrayAdapter<String> arrayAdapterr;
    ArrayList<String> Onlynoofpatients = new ArrayList<String>();
    ArrayList<String> Onlylocations=new ArrayList<String>();
    ArrayList<String> OnlyDates=new ArrayList<String>();
    ArrayList<String> mixxx = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ambulacerecord);
        getWindow().setStatusBarColor(Color.parseColor("#2B65EC"));
        gototypes = (ImageView) findViewById(R.id.gototypes);
        searchVieww = (SearchView) findViewById(R.id.searchviewwww);

        locationsss = (ListView) findViewById(R.id.listdatadd);

        String login_url = "http:/192.168.43.6/readambdata.php";
        gototypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gototypes.setEnabled(false);
                gobacktotypes();
            }
        });
        searchVieww.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (mixxx.contains(s)) {
                    arrayAdapterr.getFilter().filter(s);
                } else {
                    Toast.makeText(ambulacerecord.this, "Not Found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                arrayAdapterr.getFilter().filter(s);


                return false;
            }
        });
        locationsss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int x = position;

                Intent intamb = new Intent(ambulacerecord.this, Seepatrecords.class);
                intamb.putExtra("locationP", Onlylocations.get(x));
                intamb.putExtra("datesP", OnlyDates.get(x));
                intamb.putExtra("patientsP", Onlynoofpatients.get(x));

                startActivity(intamb);
                finish();
            }
        });


        getJSON(login_url);
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
            OnlyDates.add(obj.getString("tdate"));
            Onlylocations.add(obj.getString("location"));
            Onlynoofpatients.add(obj.getString("noofpat"));

            mixxx.add(obj.getString("location")+" ( "+obj.getString("tdate")+" )");
        }

        arrayAdapterr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mixxx);
        locationsss.setAdapter(arrayAdapterr);


    }


    private void gobacktotypes() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    gototypes.setEnabled(true);
    }

}
