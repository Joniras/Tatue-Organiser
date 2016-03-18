package com.htl_villach.tatue_rater.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.Classes.StandRating;
import com.htl_villach.tatue_rater.Helper.Database;
import com.htl_villach.tatue_rater.R;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandRatingFragment extends Fragment implements View.OnClickListener{


    private View rootView;
    private Database db;

    public StandRatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_stand_rating, container, false);
        try {
            this.db = Database.newInstance();

            Vector<Stand> staende = new Vector<Stand>();

            for(Abteilung ttmp:db.abteilungen){
                staende.addAll(ttmp.getAb_stande());
            }

            ArrayAdapter<Stand> adapter = new ArrayAdapter<Stand>(getContext(),R.layout.spinner_item,staende);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            ((Spinner)rootView.findViewById(R.id.standSpinner)).setAdapter(adapter);

            ((Button)rootView.findViewById(R.id.btnStandRating)).setOnClickListener(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        StandRating tmp = new StandRating();
        tmp.setAufbau((int) ((RatingBar) rootView.findViewById(R.id.stand_aRating)).getRating());
        tmp.setFreundlichkeit((int) ((RatingBar) rootView.findViewById(R.id.stand_fRating)).getRating());
        tmp.setKompetenz((int) ((RatingBar) rootView.findViewById(R.id.stand_kRating)).getRating());
        Stand  stmp = ((Stand)((Spinner) rootView.findViewById(R.id.standSpinner)).getSelectedItem());
        Log.i("StandRating:", tmp.toString()+" for Stand: "+stmp.getStname());
        db.addStandRating(tmp,stmp);
    }
}
