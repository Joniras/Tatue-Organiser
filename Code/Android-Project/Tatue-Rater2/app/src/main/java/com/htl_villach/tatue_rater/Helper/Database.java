package com.htl_villach.tatue_rater.Helper;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Guide;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.MapFragment;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jonas on 14.03.2016.
 */
public class Database extends Application implements Serializable, AsyncResponse {
    private static Database database;
    private ControllerSync controller;
    public   Vector<Abteilung> abteilungen;
    public Vector<Guide> guides;
    private AsyncResponse asyncresponse;

    private Database() {
    }

    public static Database newInstance() throws Exception{
        if(Database.database == null) {
            database = new Database();
        }
        return database;
    }


    public void loadAll(AsyncResponse asyncResponse) {


        this.asyncresponse = asyncResponse;

        Gson gson = new Gson();
        ControllerSync Guidecontroller = new ControllerSync();
        Guidecontroller.delegate = this;
        Guidecontroller.execute("api/guides",AsyncResponseType.GUIDES.toString());

        ControllerSync Abteilungcontroller = new ControllerSync();
        Abteilungcontroller.delegate = this;
        Abteilungcontroller.execute("api/abteilungen/staende",AsyncResponseType.ABTEILUNGEN.toString());


    }

    @Override
    public void processFinish(AsyncResponseItem output) {
        Gson gson = new Gson();

        switch(output.getType()){
            case ABTEILUNGEN:
                Log.i("finished:","Abteilungen");
                Vector<Abteilung> abteilungen = gson.fromJson(output.getResponse(),new TypeToken<Vector<Abteilung>>(){}.getType());
                this.abteilungen = abteilungen;
                break;

            case GUIDES:
                Log.i("finished:","Guides");
                Vector<Guide> guides = gson.fromJson(output.getResponse(),new TypeToken<Vector<Guide>>(){}.getType());
                this.guides = guides;
                break;
        }

        if(abteilungen!=null && guides != null){
            this.asyncresponse.processFinish(new AsyncResponseItem("",AsyncResponseType.ALL));
        }

    }
}