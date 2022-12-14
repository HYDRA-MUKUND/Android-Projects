package com.example.mylocations;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
public void upa(Location location,String title)
{  if(location!=null) {
    LatLng sydney = new LatLng(location.getLatitude(),location.getLongitude());
    mMap.clear();
    mMap.addMarker(new MarkerOptions().position(sydney).title(title));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
}

}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location last=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent=getIntent();
        if(intent.getIntExtra("location",0)==0)
        {
            locationManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
            locationListener=new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    upa(location,"Your Location");
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

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location last=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                upa(last,"Your Last Location");

            }
        }
        else {
            Location placeLocation = new Location(LocationManager.GPS_PROVIDER);
            placeLocation.setLatitude(MainActivity.openit.get(intent.getIntExtra("location",0)).latitude);
            placeLocation.setLongitude(MainActivity.openit.get(intent.getIntExtra("location",0)).longitude);

            upa(placeLocation, MainActivity.addresses.get(intent.getIntExtra("location",0)));
        }
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                String adddre="";
                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address>list=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    if(list.get(0).getAdminArea()!=null)
                    {
                        adddre+=list.get(0).getAdminArea()+", ";
                    }
                    if(list.get(0).getSubAdminArea()!=null)
                    {
                        adddre+=list.get(0).getSubAdminArea()+", ";
                    }
                    if(list.get(0).getLocality()!=null)
                    {
                        adddre+=list.get(0).getLocality();
                    }
                    if(adddre.equals("")){
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        adddre=simpleDateFormat.format(new Date());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(adddre);

                mMap.clear();
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                MainActivity.addresses.add(adddre);
MainActivity.openit.add(latLng);
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }
        });

    }

}
