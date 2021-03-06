package com.android.myapplication.gpsspy.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class GPSProvider extends ContentProvider {

    public static final String LOG_TAG = GPSProvider.class.getSimpleName();

    private GPSDbHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new GPSDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;

        long _id = db.insert(GPSContract.LocationEntry.TABLE_NAME, null, contentValues);
        if (_id > 0) {
            returnUri = GPSContract.LocationEntry.buildLocationUri(_id);
            Log.d(LOG_TAG, "Row " + returnUri.getPath());
        } else {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }
}
