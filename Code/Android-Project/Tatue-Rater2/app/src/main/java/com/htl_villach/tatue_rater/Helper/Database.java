package com.htl_villach.tatue_rater.Helper;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htl_villach.tatue_rater.Activities.MainActivity;
import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.GewinnspielDaten;
import com.htl_villach.tatue_rater.Classes.Guide;
import com.htl_villach.tatue_rater.Classes.GuideRating;
import com.htl_villach.tatue_rater.Classes.Quiz;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.Classes.StandRating;

import java.io.Serializable;
import java.util.Vector;

public class Database extends Application implements Serializable, AsyncResponse {
    private static Database database;
    public   Vector<Abteilung> abteilungen;
    public Vector<Guide> guides;
    private AsyncResponse asyncresponse;
    private MainActivity context;

    private  String IP = "192.168.195.188:8080";

    private Database() {
    }

    public static Database newInstance() throws Exception{
        if(Database.database == null) {
            database = new Database();
        }
        return database;
    }


    public void loadAll(AsyncResponse asyncResponse,String alternativeIP) {

        if(alternativeIP.length()!=0){
            this.IP = alternativeIP;
        }
        this.asyncresponse = asyncResponse;

        ControllerASync Guidecontroller = new ControllerASync();
        Guidecontroller.delegate = this;
        Guidecontroller.execute(IP,RequestMethod.GET.toString(),"api/guides",AsyncResponseType.GUIDES.toString());

        ControllerASync Abteilungcontroller = new ControllerASync();
        Abteilungcontroller.delegate = this;
        Abteilungcontroller.execute(IP,RequestMethod.GET.toString(),"api/abteilungen/staende",AsyncResponseType.ABTEILUNGEN.toString());

    }

    public void addStandRating(StandRating stRating,Stand st){
        Gson gson = new Gson();
        ControllerASync rcontroller = new ControllerASync();
        rcontroller.delegate = this;
        rcontroller.execute(IP, RequestMethod.POST.toString(),"api/staende/"+st.getSt_id()+"/ratings",AsyncResponseType.POSTRATING.toString(),gson.toJson(stRating, StandRating.class));

    }

    public void addGuideRating(GuideRating gRating, Guide gtmp) {
        Gson gson = new Gson();
        ControllerASync scontroller = new ControllerASync();
        scontroller.delegate = this;
        scontroller.execute(IP, RequestMethod.POST.toString(),"api/guides/"+gtmp.getS_id()+"/ratings",AsyncResponseType.POSTRATING.toString(),gson.toJson(gRating, GuideRating.class));

    }

    public void addGewinnspielDaten(GewinnspielDaten gDaten, int quizid) {
        Gson gson = new Gson();
        ControllerASync scontroller = new ControllerASync();
        scontroller.delegate = this;
        scontroller.execute(IP, RequestMethod.POST.toString(),"api/gewinnspieldaten/"+quizid,AsyncResponseType.POSTGEWINNSPIELDATEN.toString(),gson.toJson(gDaten, GewinnspielDaten.class));

    }

    @Override
    public void processFinish(AsyncResponseItem output) {
        Gson gson = new Gson();
        try {
            if (output.getType() == AsyncResponseType.ABTEILUNGEN || output.getType() == AsyncResponseType.GUIDES) {
                switch (output.getType()) {
                    case ABTEILUNGEN:
                        Log.i("finished:", "Abteilungen");
                        this.abteilungen = gson.fromJson(output.getResponse(), new TypeToken<Vector<Abteilung>>() {
                        }.getType());

                        break;

                    case GUIDES:
                        Log.i("finished:", "Guides");
                        this.guides = gson.fromJson(output.getResponse(), new TypeToken<Vector<Guide>>() {
                        }.getType());
                        break;
                }
                if (abteilungen != null && guides != null) {
                    this.asyncresponse.processFinish(new AsyncResponseItem("", AsyncResponseType.ALL));
                }
            } else if (output.getType() == AsyncResponseType.POSTRATING) {
                Toast.makeText(this.context, "Deine Bewertung wurde gesendet, danke!", Toast.LENGTH_LONG).show();
            }else if(output.getType() == AsyncResponseType.POSTGEWINNSPIELDATEN){
                Toast.makeText(this.context, "Deine daten wurden gespeichert", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.printStackTrace();
            this.context.getNewIP();
        }
    }

    public String getIP() {
        return IP;
    }

    public void setContext(MainActivity applicationContext) {
        this.context = applicationContext;
    }

}