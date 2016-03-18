package com.htl_villach.tatue_rater.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Helper.Database;
import com.htl_villach.tatue_rater.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment implements View.OnClickListener {
    private View rootView;

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        (rootView.findViewById(R.id.btnStart)).setOnClickListener(this);

        ArrayAdapter<Abteilung> adapter = null;
        try {
            List<Abteilung> abteilungen = new ArrayList<Abteilung>();

            for(Abteilung atmp: Database.newInstance().abteilungen){
                if(atmp.getAb_quiz() !=null){
                    abteilungen.add(atmp);
                }
            }


            adapter = new ArrayAdapter<Abteilung>(getContext(), R.layout.spinner_item,abteilungen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner) rootView.findViewById(R.id.abtSpinner)).setAdapter(adapter);
        // Inflate the layout for this fragment
        return rootView;
    }
    public void onAbteilungChosen(Abteilung a) {
        if (mListener != null) {
            mListener.onAbteilungChosen(a);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {


        onAbteilungChosen((Abteilung)((Spinner)rootView.findViewById(R.id.abtSpinner)).getSelectedItem());


    }

    public interface OnFragmentInteractionListener {

        void onAbteilungChosen(Abteilung abt);
    }
}
