package com.htl_villach.tatue_rater.Helper;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jonas on 14.03.2016.
 */
public class ControllerSync extends AsyncTask<String, Void, String> {

    private static final String URI_FIX = "http://10.0.0.8:8080/TatueOrganiser/";

    @Override
    protected String doInBackground(String... command) {
        BufferedReader reader = null;
        String content = null;
        URL url = null;

        try {
            url = new URL(URI_FIX + "api/abteilungen/staende" );

            // send data to server
            URLConnection conn = url.openConnection();

            // get data from server
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            content = sb.toString();
        } catch (Exception ex) {
            content = "error in doInBackground: " + ex.getMessage();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
        return content;
    }
}
