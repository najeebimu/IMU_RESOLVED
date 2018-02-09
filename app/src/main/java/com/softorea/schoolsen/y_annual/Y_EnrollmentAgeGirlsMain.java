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
import com.softorea.schoolsen.models.DetailsEnrollmentAgeBoys;
import com.softorea.schoolsen.models.DetailsEnrollmentAgeGirls;
import com.softorea.schoolsen.models.DetailsEnrollmentAttendaanceGap;

/**
 * Created by Arslan on 12/7/2017.
 */

public class Y_EnrollmentAgeGirlsMain extends Activity {
    DatabaseHandler databasehandler;
    DetailsEnrollmentAgeGirls details;
    String emis,classNumber;
    TextView mainTitle;
    Button back;
    EditText age3, age4, age5, age6, age7, age8, age9, age10, age11, age12, age13, age14,
            age15, age16, age17, age18, age19, age20, age21, total, repeaters, nomuslims;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_enrollment_agegirls);
        SharedPreferences preferencess = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferencess.getString("emiscode", "");
        databasehandler = new DatabaseHandler(Y_EnrollmentAgeGirlsMain.this);
        age3 = (EditText) findViewById(R.id.agethree);
        age4 = (EditText) findViewById(R.id.agefour);
        age5 = (EditText) findViewById(R.id.agefive);
        age6 = (EditText) findViewById(R.id.agesix);
        age7 = (EditText) findViewById(R.id.ageseven);
        age8 = (EditText) findViewById(R.id.ageeight);
        age9 = (EditText) findViewById(R.id.agenine);
        age10 = (EditText) findViewById(R.id.ageten);
        age11 = (EditText) findViewById(R.id.ageeleven);
        age12 = (EditText) findViewById(R.id.agetwelve);
        age13 = (EditText) findViewById(R.id.agethirteen);
        age14 = (EditText) findViewById(R.id.agefourteen);
        age15 = (EditText) findViewById(R.id.agefifteen);
        age16 = (EditText) findViewById(R.id.agesixteen);
        age17 = (EditText) findViewById(R.id.ageseventeen);
        age18 = (EditText) findViewById(R.id.ageeighteen);
        age19 = (EditText) findViewById(R.id.ageninteen);
        age20 = (EditText) findViewById(R.id.agetwenty);
        age21 = (EditText) findViewById(R.id.agetwentyone);
        total = (EditText) findViewById(R.id.agetotal);
        repeaters = (EditText) findViewById(R.id.agerepeaters);
        nomuslims = (EditText) findViewById(R.id.agenonmuslims);

        mainTitle = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.back);
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle!=null)
        {
            classNumber = dataBundle.getString("className");
            mainTitle.setText(classNumber);
        }

        DetailsEnrollmentAgeGirls eag = databasehandler.getEAgegirls(emis, classNumber);

        try {
            String ageboys3 = eag.getAge3();
            String ageboys4 = eag.getAge4();
            String ageboys5 = eag.getAge5();
            String ageboys6 = eag.getAge6();
            String ageboys7 = eag.getAge7();
            String ageboys8 = eag.getAge8();
            String ageboys9 = eag.getAge9();
            String ageboys10 = eag.getAge10();
            String ageboys11 = eag.getAge11();
            String ageboys12 = eag.getAge12();
            String ageboys13 = eag.getAge13();
            String ageboys14 = eag.getAge14();
            String ageboys15 = eag.getAge15();
            String ageboys16 = eag.getAge16();
            String ageboys17 = eag.getAge17();
            String ageboys18 = eag.getAge18();
            String ageboys19 = eag.getAge19();
            String ageboys20 = eag.getAge20();
            String ageboys21 = eag.getAge21();
            String ageboysrepeaters = eag.getRepeaters();
            String ageboysnonmuslims = eag.getNonmuslims();


            if (!ageboys3.equals("Null") && !ageboys3.equals("")) {
                age3.setText(ageboys3);
            }
            if (!ageboys4.equals("Null") && !ageboys4.equals("")) {
                age4.setText(ageboys4);
            }
            if (!ageboys5.equals("Null") && !ageboys5.equals("")) {
                age5.setText(ageboys5);
            }
            if (!ageboys6.equals("Null") && !ageboys6.equals("")) {
                age6.setText(ageboys6);
            }
            if (!ageboys7.equals("Null") && !ageboys7.equals("Null")) {
                age7.setText(ageboys7);
            }
            if (!ageboys8.equals("Null") && !ageboys8.equals("")) {
                age8.setText(ageboys8);
            }
            if (!ageboys9.equals("Null") && !ageboys9.equals("")) {
                age9.setText(ageboys9);
            }
            if (!ageboys10.equals("Null") && !ageboys10.equals("")) {
                age10.setText(ageboys10);
            }
            if (!ageboys11.equals("Null") && !ageboys11.equals("")) {
                age11.setText(ageboys11);
            }
            if (!ageboys12.equals("Null") && !ageboys12.equals("Null")) {
                age12.setText(ageboys12);
            }
            if (!ageboys13.equals("Null") && !ageboys13.equals("")) {
                age13.setText(ageboys13);
            }
            if (!ageboys14.equals("Null") && !ageboys14.equals("")) {
                age14.setText(ageboys14);
            }
            if (!ageboys15.equals("Null") && !ageboys15.equals("")) {
                age15.setText(ageboys15);
            }
            if (!ageboys16.equals("Null") && !ageboys16.equals("")) {
                age16.setText(ageboys16);
            }
            if (!ageboys17.equals("Null") && !ageboys17.equals("Null")) {
                age17.setText(ageboys17);
            }
            if (!ageboys18.equals("Null") && !ageboys18.equals("")) {
                age18.setText(ageboys18);
            }
            if (!ageboys19.equals("Null") && !ageboys19.equals("")) {
                age19.setText(ageboys19);
            }
            if (!ageboys20.equals("Null") && !ageboys20.equals("")) {
                age20.setText(ageboys20);
            }
            if (!ageboys21.equals("Null") && !ageboys21.equals("")) {
                age21.setText(ageboys21);
            }
            if (!ageboysrepeaters.equals("Null") && !ageboysrepeaters.equals("Null")) {
                repeaters.setText(ageboysrepeaters);
            }

            if (!ageboysnonmuslims.equals("Null") && !ageboysnonmuslims.equals("Null")) {
                nomuslims.setText(ageboysnonmuslims);
            }


        } catch (IndexOutOfBoundsException e) {

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details = new DetailsEnrollmentAgeGirls();

                details.setEmisCode(emis);
                details.setClassName(classNumber);
                details.setAge3(age3.getText().toString());
                details.setAge4(age4.getText().toString());
                details.setAge5(age5.getText().toString());
                details.setAge6(age6.getText().toString());
                details.setAge7(age7.getText().toString());
                details.setAge8(age8.getText().toString());
                details.setAge9(age9.getText().toString());
                details.setAge10(age10.getText().toString());
                details.setAge11(age11.getText().toString());
                details.setAge12(age12.getText().toString());
                details.setAge13(age13.getText().toString());
                details.setAge14(age14.getText().toString());
                details.setAge15(age15.getText().toString());
                details.setAge16(age16.getText().toString());
                details.setAge17(age17.getText().toString());
                details.setAge18(age18.getText().toString());
                details.setAge19(age19.getText().toString());
                details.setAge20(age20.getText().toString());
                details.setAge21(age21.getText().toString());
                details.setRepeaters(repeaters.getText().toString());
                details.setNonmuslims(nomuslims.getText().toString());
                databasehandler.saveEAgegirls(details);
                startActivity(new Intent(Y_EnrollmentAgeGirlsMain.this, Y_EnrollmentAgeGirls.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_EnrollmentAgeGirlsMain.this);
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
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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

    @Override
    protected void onPause() {
        super.onPause();
        details = new DetailsEnrollmentAgeGirls();

        details.setEmisCode(emis);
        details.setClassName(classNumber);
        details.setAge3(age3.getText().toString());
        details.setAge4(age4.getText().toString());
        details.setAge5(age5.getText().toString());
        details.setAge6(age6.getText().toString());
        details.setAge7(age7.getText().toString());
        details.setAge8(age8.getText().toString());
        details.setAge9(age9.getText().toString());
        details.setAge10(age10.getText().toString());
        details.setAge11(age11.getText().toString());
        details.setAge12(age12.getText().toString());
        details.setAge13(age13.getText().toString());
        details.setAge14(age14.getText().toString());
        details.setAge15(age15.getText().toString());
        details.setAge16(age16.getText().toString());
        details.setAge17(age17.getText().toString());
        details.setAge18(age18.getText().toString());
        details.setAge19(age19.getText().toString());
        details.setAge20(age20.getText().toString());
        details.setAge21(age21.getText().toString());
        details.setRepeaters(repeaters.getText().toString());
        details.setNonmuslims(nomuslims.getText().toString());
        databasehandler.saveEAgegirls(details);
    }
}
