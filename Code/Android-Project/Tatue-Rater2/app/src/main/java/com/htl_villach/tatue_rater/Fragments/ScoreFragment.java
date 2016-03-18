package com.htl_villach.tatue_rater.Fragments;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.htl_villach.tatue_rater.Activities.MainActivity;
import com.htl_villach.tatue_rater.Classes.Frage;
import com.htl_villach.tatue_rater.Classes.GewinnspielDaten;
import com.htl_villach.tatue_rater.Helper.Database;
import com.htl_villach.tatue_rater.R;
import com.plattysoft.leonids.ParticleSystem;


public class ScoreFragment extends Fragment implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;
    private View rootView;
    private GewinnspielDaten gewinnspieldaten;
    private int quizid;

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_score, container, false);
        // Inflate the layout for this fragment
        this.gewinnspieldaten = new GewinnspielDaten();
        Bundle bundle = this.getArguments();
         gewinnspieldaten.setScore(bundle.getInt("Score"));
        quizid = bundle.getInt("QuizID");
        ((TextView)rootView.findViewById(R.id.txtScore)).setText(gewinnspieldaten.getScore() + " Punkte");
        ((Button)rootView.findViewById(R.id.btnCancel)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.btnMakewith)).setOnClickListener(this);


        new ParticleSystem(getActivity(), 80,R.mipmap.confetti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 90)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90)
                .emit(rootView, 5,5000);

        return rootView;
    }

    public void onFieldsFilled() {
        if (mListener != null) {
            mListener.scoreSent();
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

        if(v.getId() == R.id.btnMakewith){
            try {
                Database.newInstance().addGewinnspielDaten(this.gewinnspieldaten,quizid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onFieldsFilled();
    }

    public interface OnFragmentInteractionListener {
        void scoreSent();
    }
}
