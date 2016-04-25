package com.android.myapplication.gpsspy.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.android.myapplication.gpsspy.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by stri0214 on 22.04.2016.
 */
public class ArchiveTask extends AsyncTask<Void, String, String> {


    private static final int BUFFER_SIZE = 1024;
    private final Context context;

    public ArchiveTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        String pathDB = Environment.getDataDirectory().getPath() +
                "//data//com.android.myapplication.gpsspy//" + "gps.db";
        String fileZIP = pathDB + ".zip";
        try {
            zipFile(pathDB, fileZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(fileZIP);

        Uri path = Uri.fromFile(file);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("vnd.android.cursor.dir/email");
        String to[] = {"ryabokonsm@mail.ru"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_STREAM, path);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(emailIntent);
        Log.d("ffffff", "ffffffffff");

        deletePrivateFile(pathDB);

        return null;
    }

    private void zipFile(String pathDB, String fileZIP) throws IOException {
        BufferedInputStream origin;
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileZIP)));
        try {
            byte data[] = new byte[BUFFER_SIZE];
            FileInputStream fi = new FileInputStream(pathDB);
            origin = new BufferedInputStream(fi, BUFFER_SIZE);
            try {
                ZipEntry entry = new ZipEntry(pathDB.substring(pathDB.lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int c;
                while ((c = origin.read(data, 0, BUFFER_SIZE)) != -1) out.write(data, 0, c);
            } finally {
                origin.close();
            }
        } finally {
            out.close();
        }
    }

    void deletePrivateFile(String Name) {
        File data = Environment.getDataDirectory();
        File file = new File(data, Name);
        file.delete();
    }

}
