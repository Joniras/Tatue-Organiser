package com.htl_villach.tatue_rater;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Guide;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.Helper.Database;
import com.htl_villach.tatue_rater.R;

import java.util.Vector;


public class RatingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View rootView;
    private Vector<Stand> staende;
    private Vector<Guide> guides;

    public RatingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RatingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RatingsFragment newInstance(String param1, String param2) {
        RatingsFragment fragment = new RatingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ratings, container, false);
        Database db = null;
        try {
            db = Database.newInstance();

            Vector<Abteilung> abteilungen = db.abteilungen;
            this.guides = db.guides;

            Vector<Stand> staende = new Vector<Stand>();

            for(Abteilung ttmp:abteilungen){
                staende.addAll(ttmp.getAb_stande());
            }

            ArrayAdapter<Stand> adapter = new ArrayAdapter<Stand>(getContext(),android.R.layout.simple_spinner_item,staende);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            ((Spinner)rootView.findViewById(R.id.standSpinner)).setAdapter(adapter);

            ArrayAdapter<Guide> adapter2 = new ArrayAdapter<Guide>(getContext(),android.R.layout.simple_spinner_item,guides);
            adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            ((Spinner)rootView.findViewById(R.id.guideSpinner)).setAdapter(adapter2);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
