package com.android.myapplication.gpsspy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.android.myapplication.gpsspy.data.GPSContract.LocationEntry;

public class GPSDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = GPSDbHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "gps.db";

    public GPSDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " +
                LocationEntry.TABLE_NAME + " (" +
                LocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LocationEntry.COLUMN_TIME + " TEXT " +
                LocationEntry.COLUMN_LAT + " REAL " +
                LocationEntry.COLUMN_LONG + " REAL " +
                LocationEntry.COLUMN_ADDRESS + " TEXT " + " );";

        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);

        Log.d(LOG_TAG, "Table " + LocationEntry.TABLE_NAME + " has been created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        final String SQL_DROP_LOCATION_TABLE = "DROP TABLE IF EXISTS" + LocationEntry.TABLE_NAME;

        sqLiteDatabase.execSQL(SQL_DROP_LOCATION_TABLE);

        Log.d(LOG_TAG, "Table " + LocationEntry.TABLE_NAME + " has been dropped ");

        onCreate(sqLiteDatabase);
    }
}
