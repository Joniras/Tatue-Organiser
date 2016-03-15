package com.htl_villach.tatue_rater.Helper;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Guide;
import com.htl_villach.tatue_rater.Classes.Stand;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jonas on 14.03.2016.
 */
public class Database extends Application implements Serializable {
    private static Database database;
    private ControllerSync controller;
    public   Vector<Abteilung> abteilungen;
    public Vector<Guide> guides;

    private Database() {
    }

    public static Database newInstance() throws Exception{
        if(Database.database == null) {
            database = new Database();
        }
        return database;
    }

    public Vector<Abteilung> getAbteilungen() throws ExecutionException, InterruptedException {

        Gson gson = new Gson();
        controller = new ControllerSync();
        controller.execute("api/abteilungen/staende");
        String strFromWebService = controller.get();
        Log.i("asdfasdf",strFromWebService);
        Vector<Abteilung> abteilungen = gson.fromJson(strFromWebService,new TypeToken<Vector<Abteilung>>(){}.getType());
        this.abteilungen = abteilungen;
        return abteilungen;
    }

    public void loadAll() throws ExecutionException, InterruptedException {

        Gson gson = new Gson();
        ControllerSync Guidecontroller = new ControllerSync();
        Guidecontroller.execute("api/guides");

        ControllerSync Abteilungcontroller = new ControllerSync();
        Abteilungcontroller.execute("api/abteilungen/staende");

        String strFromWebService = Abteilungcontroller.get();
        Vector<Abteilung> abteilungen = gson.fromJson(strFromWebService,new TypeToken<Vector<Abteilung>>(){}.getType());
        this.abteilungen = abteilungen;

        strFromWebService = Guidecontroller.get();
        Vector<Guide> guides = gson.fromJson(strFromWebService,new TypeToken<Vector<Guide>>(){}.getType());
        this.guides = guides;
    }

}