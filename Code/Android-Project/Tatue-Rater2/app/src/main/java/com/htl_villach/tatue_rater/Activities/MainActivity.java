package com.htl_villach.tatue_rater.Activities;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.htl_villach.tatue_rater.Classes.Abteilung;
import com.htl_villach.tatue_rater.Classes.Frage;
import com.htl_villach.tatue_rater.Fragments.MapFragment;
import com.htl_villach.tatue_rater.Fragments.QuestionFragment;
import com.htl_villach.tatue_rater.Fragments.QuizFragment;
import com.htl_villach.tatue_rater.Fragments.RatingsFragment;
import com.htl_villach.tatue_rater.Fragments.ScoreFragment;
import com.htl_villach.tatue_rater.Helper.AsyncResponse;
import com.htl_villach.tatue_rater.Helper.AsyncResponseItem;
import com.htl_villach.tatue_rater.Helper.Database;
import com.htl_villach.tatue_rater.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse, QuestionFragment.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener , ScoreFragment.OnFragmentInteractionListener, QuestionFragment.OnQuestionCancel{

    private ProgressDialog dialog;
    private Fragment shownFragment;
    private String IP = "";
    private List<Frage> fragen;
    private int actFrage = 0;
    private int score;
    private int quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showDialogandLoad();


        // get fragment manager
        shownFragment = new MapFragment();
        shownFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, shownFragment).commit();


    }

    private void showDialogandLoad(){
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Daten werden geladen");
        dialog.setCancelable(false);
        dialog.show();

        try {

            Database db = Database.newInstance();
            db.setContext(this);
            db.loadAll(this,IP);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_game || id == R.id.nav_map || id == R.id.nav_ratings) {
            shownFragment = new MapFragment();
            if (id == R.id.nav_ratings) {
                shownFragment = new RatingsFragment();
            } else if (id == R.id.nav_game) {
                shownFragment = new QuizFragment();
            }
            shownFragment.setArguments(getIntent().getExtras());
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, shownFragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void processFinish(AsyncResponseItem output) {
        ((MapFragment)shownFragment).initView();
        dialog.hide();
    }

    public void getNewIP() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Verbindung fehlgeschlagen, neue IP ?");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        try {
            input.setText(Database.newInstance().getIP());
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Mit neuer IP!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 IP = input.getText().toString();
                dialog.cancel();
                showDialogandLoad();
            }
        });

        builder.setNegativeButton("Nochmal!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                IP = "";
                dialog.cancel();
                showDialogandLoad();
            }
        });

        dialog.hide();
        builder.show();
    }


    @Override
    public void onQuestionAnswered(boolean a) {
        this.score+=(a?1:0);
        showNextQuestion();

    }


    private Frage getNextQuestion() {
        Log.i("neue frage: ",fragen.get(actFrage).toString());
        return fragen.get(actFrage++);
    }

    @Override
    public void onAbteilungChosen(Abteilung abt) {

        this.fragen = abt.getAb_quiz().fragen;
        this.quizID = abt.getAb_quiz().q_id;
       showNextQuestion();
    }

    public void showNextQuestion(){
        android.support.v4.app.FragmentTransaction ttr = getSupportFragmentManager().beginTransaction();
        ttr.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        Fragment tmp;
        if(actFrage >= fragen.size()){
            tmp = new ScoreFragment();
            Bundle args = new Bundle();
            args.putSerializable("Score",score);
            args.putInt("QuizID",quizID);
            tmp.setArguments(args);
            actFrage = 0;
            score = 0;
        }else{
            tmp = new QuestionFragment();
            Bundle args = new Bundle();
            args.putSerializable("Frage",getNextQuestion());
            tmp.setArguments(args);
        }

        ttr.replace(R.id.fragment_container, tmp).commit();
    }

    @Override
    public void scoreSent() {
        android.support.v4.app.FragmentTransaction ttr = getSupportFragmentManager().beginTransaction();
        ttr.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        ttr.replace(R.id.fragment_container, new QuizFragment()).commit();
    }


    @Override
    public void cancelQuiz() {
        Toast.makeText(this,"Schade...",Toast.LENGTH_SHORT).show();
        scoreSent();
    }
}
