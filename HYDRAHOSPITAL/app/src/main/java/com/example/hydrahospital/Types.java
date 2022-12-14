package com.example.hydrahospital;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;



import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Types extends AppCompatActivity {
ImageView ambulance,normal;
    String addd="";
    String loc_latitude="";
    String loc_longitude="";
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);
        ambulance=(ImageView)findViewById(R.id.ambulance);
       normal=(ImageView)findViewById(R.id.safe);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                addd="";
                loc_longitude="";
                loc_latitude="";
                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    loc_latitude=String.valueOf(location.getLatitude());
                    loc_longitude=String.valueOf(location.getLongitude());

                    List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    if(addresses.get(0)!=null){
                        if(addresses.get(0).getSubThoroughfare()!=null)
                        {
                            addd+=addresses.get(0).getSubThoroughfare()+ ", ";
                        }
                        if(addresses.get(0).getThoroughfare()!=null)
                        {
                            addd+=addresses.get(0).getThoroughfare()+ ", ";
                        }
                        if(addresses.get(0).getSubLocality()!=null)
                        {
                            addd+=addresses.get(0).getSubLocality()+ ", ";
                        }
                        if(addresses.get(0).getLocality()!=null)
                        {
                            addd+=addresses.get(0).getLocality()+ ", ";
                        }
                        if(addresses.get(0).getSubAdminArea()!=null)
                        {
                            addd+=addresses.get(0).getSubAdminArea()+ ", ";
                        }

                        if(addresses.get(0).getAdminArea()!=null){
                            addd+=addresses.get(0).getAdminArea()+ ", ";
                        }
                        if(addresses.get(0).getPostalCode()!=null)
                        {
                            addd+=addresses.get(0).getPostalCode();
                        }


                    }
                    if(addd.equals("")){
                        addd="Unanamed Location";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,10 , locationListener);
        }
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                safenormal() ;
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ambulancecall();

            }
        });
    }



public  void Ambulancecall() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        new AlertDialog.Builder(this)
                .setIcon(R.drawable.alert)
                .setTitle("Required..!!")
                .setMessage("Please give location access permission")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(Types.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        if (addd != null && !addd.equals("")) {
                           Intent intamb=new Intent(getApplicationContext(), com.example.hydrahospital.ambulance.class);
                            intamb.putExtra("addressT",addd);
                            intamb.putExtra("longitudeT",loc_longitude);
                            intamb.putExtra("latitudeT",loc_latitude);
                           startActivity(intamb);
                        }
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    } else {
        if (addd.equals("")) {
            addd = "Unnamed Location";
        }

        Intent intamb=new Intent(getApplicationContext(), com.example.hydrahospital.ambulance.class);
        intamb.putExtra("addressT",addd);
        intamb.putExtra("longitudeT",loc_longitude);
        intamb.putExtra("latitudeT",loc_latitude);
        startActivity(intamb);

    }
}

    public void safenormal() {
        Intent intent=new Intent(Types.this,Appointment.class);
        startActivity(intent);
    }


}
