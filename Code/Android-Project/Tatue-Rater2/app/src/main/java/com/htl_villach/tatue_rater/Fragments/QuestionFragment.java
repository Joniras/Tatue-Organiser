package com.htl_villach.tatue_rater.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.htl_villach.tatue_rater.Classes.Antwort;
import com.htl_villach.tatue_rater.Classes.Frage;
import com.htl_villach.tatue_rater.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private OnQuestionCancel cListener;
    private Antwort antwort;
    private View rootView;
    private Antwort richtigeAntwort;

    public QuestionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_question, container, false);
        Bundle bundle = this.getArguments();
        Frage myFrage = (Frage)bundle.getSerializable("Frage");
        ((TextView)rootView.findViewById(R.id.txtQuestion)).setText(myFrage.text);
        ((Button)rootView.findViewById(R.id.btnAnswer1)).setText(myFrage.antworten.get(0).text);
        ((Button)rootView.findViewById(R.id.btnAnswer2)).setText(myFrage.antworten.get(1).text);
        ((Button)rootView.findViewById(R.id.btnAnswer3)).setText(myFrage.antworten.get(2).text);
        ((Button)rootView.findViewById(R.id.btnAnswer4)).setText(myFrage.antworten.get(3).text);
        ((Button)rootView.findViewById(R.id.btnAnswer1)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.btnAnswer2)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.btnAnswer3)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.btnAnswer4)).setOnClickListener(this);


        ((Button)rootView.findViewById(R.id.btnCancel)).setOnClickListener(this);

        richtigeAntwort = myFrage.getrichtigeAntwort();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onQuestionAnswered(boolean wasRight) {
        if (mListener != null) {
            mListener.onQuestionAnswered(wasRight);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            cListener = (OnQuestionCancel) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        cListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            cListener.cancelQuiz();
        }else {
            onQuestionAnswered(((Button) rootView.findViewById(v.getId())).getText().equals(richtigeAntwort.text));
        }

    }

    public interface OnFragmentInteractionListener {

        void onQuestionAnswered(boolean wasRight);
    }

    public interface OnQuestionCancel{
        void cancelQuiz();
    }
}
