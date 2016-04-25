package com.android.myapplication.gpsspy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.myapplication.gpsspy.tasks.ArchiveTask;

public class NetworkChangedReceiver extends BroadcastReceiver {
    public NetworkChangedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean connectedWifi = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo networkInfo : netInfo) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                if (networkInfo.isConnected()) connectedWifi = true;
                break;
            }
        }

        if (connectedWifi) {
            ArchiveTask archiveTask = new ArchiveTask(context);
            archiveTask.execute();

        }
    }
}
