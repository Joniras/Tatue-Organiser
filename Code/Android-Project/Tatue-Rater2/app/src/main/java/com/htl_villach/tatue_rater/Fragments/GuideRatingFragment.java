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
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.htl_villach.tatue_rater.Classes.Guide;
import com.htl_villach.tatue_rater.Classes.GuideRating;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.Classes.StandRating;
import com.htl_villach.tatue_rater.Helper.Database;
import com.htl_villach.tatue_rater.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GuideRatingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GuideRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideRatingFragment extends Fragment implements View.OnClickListener {


    private View rootView;
    private Database db;

    public GuideRatingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuideRatingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuideRatingFragment newInstance(String param1, String param2) {
        GuideRatingFragment fragment = new GuideRatingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        try {
            this.db = Database.newInstance();
            rootView = inflater.inflate(R.layout.fragment_guide_rating, container, false);
            ArrayAdapter<Guide> gadapter = new ArrayAdapter<Guide>(getContext(),R.layout.spinner_item,db.guides);
            gadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            ((Spinner)rootView.findViewById(R.id.guideSpinner)).setAdapter(gadapter);
            ((Button)rootView.findViewById(R.id.btnGuideRating)).setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        GuideRating tmp = new GuideRating();
        tmp.setFreundlichkeit((int) ((RatingBar) rootView.findViewById(R.id.guide_fRating)).getRating());
        tmp.setKompetenz((int) ((RatingBar) rootView.findViewById(R.id.guide_kRating)).getRating());
        Guide stmp = ((Guide)((Spinner) rootView.findViewById(R.id.guideSpinner)).getSelectedItem());
        Log.i("GuideRating:", tmp.toString() + " for Guide: " + stmp.getS_id());
        db.addGuideRating(tmp,stmp);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
