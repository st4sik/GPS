package com.android.myapplication.gpsspy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

public class LocationUpdateReceiver extends BroadcastReceiver {
    public LocationUpdateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_LOCATION_CHANGED;

        Location location = (Location) intent.getExtras().get(key);

        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            long time = location.getTime();
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
