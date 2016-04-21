package com.android.myapplication.gpsspy.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


public class GPSContract {

    public static final String CONTENT_AUTHORITY = "com.android.myapplication.gpsspy";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_LOCATION = "location";

    public static final class LocationEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build();

        public static final String TABLE_NAME = "location";

        public static final String COLUMN_TIME = "time";

        public static final String COLUMN_LAT = "lat";

        public static final String COLUMN_LONG = "long";

        public static final String COLUMN_ADDRESS = "address";


        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}
