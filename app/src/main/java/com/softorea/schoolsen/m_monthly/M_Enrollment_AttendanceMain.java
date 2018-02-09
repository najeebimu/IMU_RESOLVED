package com.softorea.schoolsen.m_monthly;

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
import com.softorea.schoolsen.models.DetailsEnrollmentAttendaanceGap;

/**
 * Created by Arslan on 12/7/2017.
 */

public class M_Enrollment_AttendanceMain extends Activity {
    DatabaseHandler databasehandler;
    DetailsEnrollmentAttendaanceGap details;
    String emis,classNumber;
    TextView mainTitle;
    Button back;
    EditText girlsinboys, boysingirls;
    EditText students_enrolled, students_present_perhead, students_present_perregister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_enrollmentattendancegap_main);
        SharedPreferences preferencess = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferencess.getString("emiscode", "");
        databasehandler = new DatabaseHandler(M_Enrollment_AttendanceMain.this);
        girlsinboys = (EditText) findViewById(R.id.girls_inboys);
        boysingirls = (EditText) findViewById(R.id.boys_ingirls);
        students_enrolled = (EditText) findViewById(R.id.students_enrolled);
        students_present_perhead = (EditText) findViewById(R.id.students_presentshead);
        students_present_perregister = (EditText) findViewById(R.id.students_presentsattendance);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String boy = preferences.getString("boys", "");
        String girl = preferences.getString("girls", "");
        if (boy.equals("BoysSelected")) {
            boysingirls.setVisibility(View.GONE);
        }
        if (girl.equals("GirlsSelected")) {
            girlsinboys.setVisibility(View.GONE);
        }
        mainTitle = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.back);
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle!=null)
        {
            classNumber = dataBundle.getString("className");
            mainTitle.setText(classNumber);
        }

        DetailsEnrollmentAttendaanceGap eag = databasehandler.getEAG(emis, classNumber);

        try {
            String stdEnrolled = eag.getStudentsEnrolled();
            String stdperHead = eag.getStudentsPresentHead();
            String stdperRegister = eag.getStudentsPresentRegister();
            String eagGirlsEnrolled = eag.getGirlsEnrolled();
            String eagBoysEnrolled = eag.getBoysEnrolled();

            if (!stdEnrolled.equals("Null") && !stdEnrolled.equals("")) {
                students_enrolled.setText(stdEnrolled);
            }
            if (!stdperHead.equals("Null") && !stdperHead.equals("")) {
                students_present_perhead.setText(stdperHead);
            }
            if (!stdperRegister.equals("Null") && !stdperRegister.equals("")) {
                students_present_perregister.setText(stdperRegister);
            }
            if (!eagGirlsEnrolled.equals("Null") && !eagGirlsEnrolled.equals("")) {
                girlsinboys.setText(eagGirlsEnrolled);
            }
            if (!eagBoysEnrolled.equals("Null") && !eagBoysEnrolled.equals("Null")) {
                boysingirls.setText(eagBoysEnrolled);
            }


        } catch (IndexOutOfBoundsException e) {

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details = new DetailsEnrollmentAttendaanceGap();

                details.setEmisCode(emis);
                details.setClassName(classNumber);
                details.setStudentsEnrolled(students_enrolled.getText().toString());
                details.setStudentsPresentHead(students_present_perhead.getText().toString());
                details.setStudentsPresentRegister(students_present_perregister.getText().toString());
                details.setGirlsEnrolled(girlsinboys.getText().toString());
                details.setBoysEnrolled(boysingirls.getText().toString());
                databasehandler.saveEAG(details);
                startActivity(new Intent(M_Enrollment_AttendanceMain.this, M_Enrollment_AttendanceGap.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_Enrollment_AttendanceMain.this);
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
        details = new DetailsEnrollmentAttendaanceGap();

        details.setEmisCode(emis);
        details.setClassName(classNumber);
        details.setStudentsEnrolled(students_enrolled.getText().toString());
        details.setStudentsPresentHead(students_present_perhead.getText().toString());
        details.setStudentsPresentRegister(students_present_perregister.getText().toString());
        details.setGirlsEnrolled(girlsinboys.getText().toString());
        details.setBoysEnrolled(boysingirls.getText().toString());
        databasehandler.saveEAG(details);
    }
}
