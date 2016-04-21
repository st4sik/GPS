package com.android.myapplication.gpsspy.receivers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.android.myapplication.gpsspy.Utils.StringUtils;
import com.android.myapplication.gpsspy.data.GPSContract;

public class LocationUpdateReceiver extends BroadcastReceiver {

    public static final String LOG_TAG = LocationUpdateReceiver.class.getSimpleName();

    public LocationUpdateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_LOCATION_CHANGED;

        Location location = (Location) intent.getExtras().get(key);

        if (location != null) {

            ContentValues locationValues = new ContentValues();
            locationValues.put(GPSContract.LocationEntry.COLUMN_ADDRESS, StringUtils.revGeocode(context, location));
            locationValues.put(GPSContract.LocationEntry.COLUMN_LAT, location.getLatitude());
            locationValues.put(GPSContract.LocationEntry.COLUMN_LONG, location.getLongitude());
            locationValues.put(GPSContract.LocationEntry.COLUMN_TIME, StringUtils.getDateFromUNIX(location.getTime()));
            Log.d(LOG_TAG, "LOCATION ");
            CharSequence text = "" + location.getLatitude() + location.getLongitude();
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            context.getContentResolver().insert(GPSContract.LocationEntry.CONTENT_URI, locationValues);
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

}
