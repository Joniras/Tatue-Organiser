package com.htl_villach.tatue_rater.Helper;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jonas on 14.03.2016.
 */
public class ControllerASync extends AsyncTask<String, Void, AsyncResponseItem> {

    SharedPreferences prefs;
// then you use =

    //private static final String URI_FIX = "http://10.0.0.8:8080/TatueOrganiser/";
    private static final String URI_PREFIX = "http://";
    private static final String URI_POSTFIX = "/TatueOrganiser/";
    public AsyncResponse delegate = null;


    @Override
    protected AsyncResponseItem doInBackground(String... input) {
        BufferedReader reader = null;
        String content = null;
        URL url = null;
        String postData ="";
        Log.i("getting: ", input[2]);
        RequestMethod metod = RequestMethod.valueOf(input[1]);
        if(input.length == 5){
            postData = input[4];
        }

        //


        try {
            Log.i("URL: ", URI_PREFIX +input[0] + URI_POSTFIX + input[2]);
            url = new URL( URI_PREFIX +input[0] + URI_POSTFIX + input[2]);

            // send data to server

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(metod == RequestMethod.POST) {
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));

                byte[] outputInBytes = postData.getBytes("UTF-8");
                OutputStream os = conn.getOutputStream();
                os.write(outputInBytes);
                os.close();
            }
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

        Log.i("finished: " ,input[2]);

        return new AsyncResponseItem(content,AsyncResponseType.valueOf(input[3]));
    }


    @Override
    protected void onPostExecute(AsyncResponseItem result) {

        Log.i("finished: " ,result.getResponse());
        delegate.processFinish(result);
    }

    public void postData(String valueIWantToSend) {
        // Create a new HttpClient and Post Header

    }
}
