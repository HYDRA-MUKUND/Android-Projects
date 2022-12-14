package com.example.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    TextView textView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.add);
        locationManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
        locationListener  =new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                String addd="";

                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addresses.get(0)!=null){
                        if(addresses.get(0).getThoroughfare()!=null)
                        {
                            addd+=addresses.get(0).getThoroughfare()+ ", ";
                        }
                        if(addresses.get(0).getLocality()!=null)
                        {
                            addd+=addresses.get(0).getLocality()+ ", ";
                        }
                        if(addresses.get(0).getAdminArea()!=null){
                            addd+=addresses.get(0).getAdminArea()+ ", ";
                        }
                        if(addresses.get(0).getPostalCode()!=null)
                        {
                            addd+=addresses.get(0).getPostalCode();
                        }


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                  textView.setText(addd);

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
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location loc=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }
    }


}
