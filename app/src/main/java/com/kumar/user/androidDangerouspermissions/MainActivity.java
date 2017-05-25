package com.kumar.user.androidDangerouspermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLocation= (TextView) findViewById(R.id.tvDisplay);
    }

    public void buGetLocation(View view) {
CheckUserPermissions();

    }

    void CheckUserPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }

        }
        GetLocation();
    }
    final private int REQUEST_CODE_ASK_PERMISSIONS=123;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode){
                case REQUEST_CODE_ASK_PERMISSIONS:
                    if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        GetLocation();
                    }
                    else {
                        Toast.makeText(this, "Permission is Denied", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            }


    }

    void GetLocation() {
        LocationManager locationManager = (LocationManager) getSystemService
                (LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.
                GPS_PROVIDER);
        tvLocation.setText("Long :" + String.valueOf(location.getLongitude()) +
         "Lat : " + String.valueOf(location.getLatitude()));

    }
}
