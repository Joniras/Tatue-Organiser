package com.htl_villach.tatue_rater;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Rechteck;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.Helper.Database;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Vector;


public class MapFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Canvas canvas;
    private Bitmap bg;
    private View rootView;
    private Vector<Abteilung> staende;

    public MapFragment() {

    }

    private void drawAbteilung(Abteilung tmp) {
        Paint paint = new Paint();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        paint.setColor(Color.parseColor("#ef67e4"));
        int offset = 50;


        paint.setColor(this.getMatColor("600"));
        for (Stand stmp : tmp.getAb_stande()) {
            Rechteck shape = stmp.getShape();
            if(shape != null) {
                Rect a = new Rect();
                float height = shape.b.y - shape.a.y;
                Log.i("height",height+"");
                float left = shape.a.x;
                float top = (float) (shape.a.y);

                float right = shape.b.x;
                float bottom = shape.b.y;

                canvas.drawRect(left,top+offset,right,bottom+offset, paint);
            }else{
                Log.i(stmp.getStname(),"has no shape");
            }
        }

        RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.map_canvas);
        rl.setBackground(new BitmapDrawable(getContext().getResources(), bg));
    }


    private int getMatColor(String typeColor)
    {
        int returnColor = Color.BLACK;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", rootView.getContext().getPackageName());

        if (arrayId != 0)
        {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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

        rootView = inflater.inflate(R.layout.fragment_map, container, false);

        bg = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bg);

        Database db = null;
        try {
            db = Database.newInstance();

            db.loadAll();
            this.staende = db.abteilungen;
            ArrayAdapter<Abteilung> adapter = new ArrayAdapter<Abteilung>(getContext(),android.R.layout.simple_spinner_item,staende);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            ((Spinner)rootView.findViewById(R.id.abtSpinner)).setAdapter(adapter);
            ((Spinner)rootView.findViewById(R.id.abtSpinner)).setOnItemSelectedListener(this);

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

    @Override
    public void onClick(View v) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        drawAbteilung(staende.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
