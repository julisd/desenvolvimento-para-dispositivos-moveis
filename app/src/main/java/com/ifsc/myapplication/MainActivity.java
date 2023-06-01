package com.ifsc.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity {

    TextView txtSensor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensor.findViewById(R.id.txtSensor);
        Location l = lastKnowLocation();
        if (l != null) {
            Toast.makeText(this, "GPS location:" + l.getLatitude() + "- " + l.getLongitude(), Toast.LENGTH_SHORT).show();
            txtSensor.setText("Location:" + l.getLatitude() + "-" + l.getLongitude());
        } else {
            String str = "Localização não encontrada";
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            txtSensor.setText(str);
        }

        updateLocation();
    }

    public Location lastKnowLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location lastknowLocation = null;

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            lastknowLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lastknowLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }
        return lastknowLocation;
    }

    public void updateLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location lastknowLocation = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 1,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        txtSensor.setText("Location:" + location.getLatitude() + "-" + location.getLongitude());

                    }
                });
    }

}