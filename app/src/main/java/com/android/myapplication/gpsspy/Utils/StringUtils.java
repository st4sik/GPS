package com.android.myapplication.gpsspy.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by stri0214 on 15.04.2016.
 */
public class StringUtils {

    static public String revGeocode(Context context, Location location) {
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

    static public String getDateFromUNIX(long uTime) {
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
