package com.htl_villach.tatue_rater;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Rechteck;
import com.htl_villach.tatue_rater.Classes.Stand;
import com.htl_villach.tatue_rater.Helper.Database;

import java.util.Vector;


public class MapFragment extends Fragment implements  AdapterView.OnItemSelectedListener {

    private Canvas canvas;
    private Bitmap bg;
    private View rootView;
    private Vector<Abteilung> abteilungen;

    public MapFragment() {

    }

    private void drawAbteilung(Abteilung tmp) {
        Paint standPaint = new Paint();
        Paint textPaint = new Paint();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        int offset = 100;

        TypedValue typedValue = new TypedValue();
        TypedArray ta = getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        int colorAccent = ta.getColor(0, 0);
        ta = getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimaryDark });
        int colorPrimaryDark = ta.getColor(0, 0);
        ta.recycle();
        textPaint.setTextSize(15);
        textPaint.setColor(colorPrimaryDark);
        textPaint.setTypeface(Typeface.DEFAULT);
        standPaint.setColor(colorAccent);



        for (Stand stmp : tmp.getAb_stande()) {
            Rechteck shape = stmp.getShape();
            if(shape != null) {
                float height = shape.b.y - shape.a.y;
                Log.i("height",height+"");
                float left = shape.a.x;
                float top =  (shape.a.y);

                float right = shape.b.x;
                float bottom = shape.b.y;

                canvas.drawRect(left, top + offset, right, bottom + offset, standPaint);

            }else{
                Log.i(stmp.getStname(),"has no shape");
            }
        }

        for (Stand stmp : tmp.getAb_stande()) {
            Rechteck shape = stmp.getShape();
            if(shape != null) {
                float left = shape.a.x;
                float top = (shape.a.y);
                float width = (shape.b.x-shape.a.x)/2;
                float textSize = textPaint.measureText(stmp.getStname())/2;
                canvas.drawText(stmp.getStname(), left+width-textSize, top+offset-2, textPaint);

            }else{
                Log.i(stmp.getStname(),"has no shape");
            }
        }


        RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.map_canvas);
        rl.setBackground(new BitmapDrawable(getContext().getResources(), bg));
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_map, container, false);

        bg = Bitmap.createBitmap(400, 450, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bg);
        return rootView;
    }

    public void initView(){
        try {
            Database db = Database.newInstance();

            this.abteilungen = db.abteilungen;
            this.initSpinner();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initSpinner(){
        ArrayAdapter<Abteilung> adapter = new ArrayAdapter<>(getContext(),R.layout.spinner_item,abteilungen);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ((Spinner)rootView.findViewById(R.id.abtSpinner)).setAdapter(adapter);
        ((Spinner)rootView.findViewById(R.id.abtSpinner)).setOnItemSelectedListener(this);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        drawAbteilung(abteilungen.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
