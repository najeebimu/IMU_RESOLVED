package com.softorea.schoolsen.y_annual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsEnrollmentSpecialBoys;
import com.softorea.schoolsen.models.DetailsEnrollmentSpecialGirls;

/**
 * Created by arsla on 05/10/2017.
 */

public class Y_SpecialGirlsForm extends Activity {
    DatabaseHandler databasehandler;
    DetailsEnrollmentSpecialGirls details;
    String emis,classNumber;
    Button back;
    TextView mainTitle;
    EditText fullvisual, partialvisual, fullhearing, partialhearing, fullspeech, partialspeech, handarm, legfoot, mental;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_specialgirlsform);
        SharedPreferences preferencess = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferencess.getString("emiscode", "");
        databasehandler = new DatabaseHandler(Y_SpecialGirlsForm.this);
        mainTitle = (TextView) findViewById(R.id.title);
        fullvisual = (EditText) findViewById(R.id.fullvisual);
        partialvisual = (EditText) findViewById(R.id.partialvisual);
        fullhearing = (EditText) findViewById(R.id.fullhearing);
        partialhearing = (EditText) findViewById(R.id.partialhearing);
        fullspeech = (EditText) findViewById(R.id.fullspeech);
        partialspeech = (EditText) findViewById(R.id.partialspeech);
        handarm = (EditText) findViewById(R.id.handarm);
        legfoot = (EditText) findViewById(R.id.legfoot);
        mental = (EditText) findViewById(R.id.mental);

        back = (Button) findViewById(R.id.back);

        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle!=null)
        {
            classNumber = dataBundle.getString("className");
            mainTitle.setText(classNumber);
        }

        DetailsEnrollmentSpecialGirls specialgirls = databasehandler.getSpecialgirls(emis, classNumber);

        try {
            String full_visual = specialgirls.getFullVisual();
            String partial_visual = specialgirls.getPartialVisual();
            String full_hearing = specialgirls.getFullHearing();
            String partial_hearing = specialgirls.getPartialHearing();
            String full_speech = specialgirls.getFullSpeech();
            String partial_speech = specialgirls.getPartialSpeech();
            String hand_arm = specialgirls.getHandarm();
            String leg_foot = specialgirls.getLegfoot();
            String mentalstr = specialgirls.getMental();


            if (!full_visual.equals("Null") && !full_visual.equals("")) {
                fullvisual.setText(full_visual);
            }
            if (!partial_visual.equals("Null") && !partial_visual.equals("")) {
                partialvisual.setText(partial_visual);
            }
            if (!full_hearing.equals("Null") && !full_hearing.equals("")) {
                fullhearing.setText(full_hearing);
            }
            if (!partial_hearing.equals("Null") && !partial_hearing.equals("")) {
                partialhearing.setText(partial_hearing);
            }
            if (!full_speech.equals("Null") && !full_speech.equals("Null")) {
                fullspeech.setText(full_speech);
            }
            if (!partial_speech.equals("Null") && !partial_speech.equals("")) {
                partialspeech.setText(partial_speech);
            }
            if (!hand_arm.equals("Null") && !hand_arm.equals("")) {
                handarm.setText(hand_arm);
            }
            if (!leg_foot.equals("Null") && !leg_foot.equals("")) {
                legfoot.setText(leg_foot);
            }
            if (!mentalstr.equals("Null") && !mentalstr.equals("")) {
                mental.setText(mentalstr);
            }

        } catch (IndexOutOfBoundsException e) {

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_SpecialGirlsForm.this, Y_SpecialGirlsMain.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        details = new DetailsEnrollmentSpecialGirls();

        details.setEmisCode(emis);
        details.setClassName(classNumber);
        details.setFullVisual(fullvisual.getText().toString());
        details.setPartialVisual(partialvisual.getText().toString());
        details.setFullHearing(fullhearing.getText().toString());
        details.setPartialHearing(partialhearing.getText().toString());
        details.setFullSpeech(fullspeech.getText().toString());
        details.setPartialSpeech(partialspeech.getText().toString());
        details.setHandarm(handarm.getText().toString());
        details.setLegfoot(legfoot.getText().toString());
        details.setMental(mental.getText().toString());
        databasehandler.saveSpecialGirls(details);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_SpecialGirlsForm.this);
        builder1.setMessage("Are you sure to go to roster screen?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
