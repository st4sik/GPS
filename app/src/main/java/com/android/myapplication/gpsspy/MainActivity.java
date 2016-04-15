package com.android.myapplication.gpsspy;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.android.myapplication.gpsspy.receivers.LocationUpdateReceiver;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String svcName = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(svcName);

        String provider = LocationManager.PASSIVE_PROVIDER;

        int time = 60000;
        int dst = 25;

        int flags= PendingIntent.FLAG_CANCEL_CURRENT;

        Intent intent=new Intent(this, LocationUpdateReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,flags);

        locationManager.requestLocationUpdates(provider,time,dst,pendingIntent);

        finish();
    }
}
