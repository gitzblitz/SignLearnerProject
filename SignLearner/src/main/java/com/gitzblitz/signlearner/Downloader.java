package com.gitzblitz.signlearner;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;



/**
 * Created by User on 2013/10/31.
 */
public class Downloader {
    private static String myTag = "course";

    static final int POST_PROGRESS = 1;


    public static void DownloadFromUrl(String URL, FileOutputStream fos){
        try {
            URL url = new URL(URL);

            long startTime = System.currentTimeMillis();
            Log.d(myTag,"download beginning");

            URLConnection ucon = url.openConnection();

            Log.i(myTag,"Opened Connection");


            // Inputstreams to read from url
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.i(myTag, "Got InputStream and BufferedInputStream");

            //outputstreams to write to file.

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            Log.i(myTag,"Got FileOutputstream and BufferedOutputStream");


            byte data[] = new byte[1024];
            int count;

            while ((count = bis.read(data)) != -1){

                bos.write(data, 0, count);
            }

                //flush the streams
                bos.flush();
                bos.close();

                Log.d(myTag,"download ready in" + ((System.currentTimeMillis()- startTime)) + " milisec");





        }catch (IOException e){
            Log.d(myTag, "Error: " + e);
        }
    }
}
