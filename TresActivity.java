package com.example.lab2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class TresActivity extends AppCompatActivity {

    private Button startBtn, stopBtn;
    private TextView latView, lonView;
    private LocationManager lokalizator;

    private LocationListener sluchacz = new LocationListener() {
        @Override
        public void onLocationChanged(Location loc) {
            latView.setText("Szerokość: " + loc.getLatitude());
            lonView.setText("Długość: " + loc.getLongitude());
        }
        @Override public void onStatusChanged(String s, int i, Bundle b) {}
        @Override public void onProviderEnabled(String s) {}
        @Override public void onProviderDisabled(String s) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres);

        startBtn = findViewById(R.id.btnStart);
        stopBtn = findViewById(R.id.btnStop);
        latView = findViewById(R.id.tvSzer);
        lonView = findViewById(R.id.tvDlug);

        lokalizator = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        stopBtn.setEnabled(false);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(TresActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    lokalizator.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, sluchacz);
                    startBtn.setEnabled(false);
                    stopBtn.setEnabled(true);
                } else {
                    ActivityCompat.requestPermissions(TresActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lokalizator.removeUpdates(sluchacz);
                startBtn.setEnabled(true);
                stopBtn.setEnabled(false);
            }
        });
    }
}
