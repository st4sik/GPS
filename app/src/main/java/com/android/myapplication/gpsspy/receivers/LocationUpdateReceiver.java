package com.android.myapplication.gpsspy.receivers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.android.myapplication.gpsspy.data.GPSContract;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
            locationValues.put(GPSContract.LocationEntry.COLUMN_ADDRESS, revGeocode(context, location));
            locationValues.put(GPSContract.LocationEntry.COLUMN_LAT, location.getLatitude());
            locationValues.put(GPSContract.LocationEntry.COLUMN_LONG, location.getLongitude());
            locationValues.put(GPSContract.LocationEntry.COLUMN_TIME, getDateFromUNIX(location.getTime()));
            Log.d(LOG_TAG, "LOCATION ");
            CharSequence text = "" + location.getLatitude() + location.getLongitude();
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            context.getContentResolver().insert(GPSContract.LocationEntry.CONTENT_URI, locationValues);
        }
    }


    private String revGeocode(Context context, Location location) {
        if (location == null) return "";
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        StringBuilder sb = new StringBuilder();
        Geocoder gc = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = gc.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                    sb.append(address.getAddressLine(i)).append("\n");
                sb.append(address.getLocality()).append("\n");
                sb.append(address.getPostalCode()).append("\n");
                sb.append(address.getCountryName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getDateFromUNIX(long uTime) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(uTime);
        int D = c.get(Calendar.DAY_OF_MONTH);
        int M = c.get(Calendar.MONTH);
        int Y = c.get(Calendar.YEAR);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        return new StringBuilder()
                .append(D).append(".").append(M + 1).append(".").append(Y)
                .append(" ").append(h).append(":").append(m)
                .toString();
    }

}
