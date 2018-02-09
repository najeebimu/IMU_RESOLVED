package com.softorea.schoolsen.gcsschools;

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
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.GPSTracker;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_Enrollment_AttendanceGap extends Activity {
    TextView tundamit, tkachi, tone, ttwo, tthree, tfour, tfive;
    Button back, next;
    GPSTracker gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_enrollment);
        gps = new GPSTracker(M_Enrollment_AttendanceGap.this);
        tundamit = (TextView) findViewById(R.id.unadmitted);
        tkachi = (TextView) findViewById(R.id.kachi);
        tone = (TextView) findViewById(R.id.classone);
        ttwo = (TextView) findViewById(R.id.classtwo);
        tthree = (TextView) findViewById(R.id.classthree);
        tfour = (TextView) findViewById(R.id.classfour);
        tfive = (TextView) findViewById(R.id.classfive);

        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            double accuracy = gps.getAccuracy();
            String lat = String.valueOf(latitude);
            String lng = String.valueOf(longitude);
            String acc = String.valueOf(accuracy);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("lat3", lat);
            editor.putString("lng3", lng);
            editor.putString("acc3", acc);
            editor.apply();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Lat: " + lat + "\nLong: " + lng + " " + acc, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            //gps.showSettingsAlert();
        }

        tundamit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapUnadmitted.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tkachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapKachi.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapOne.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        ttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapTwo.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapThree.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapFour.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceGapFive.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(M_Enrollment_AttendanceGap.this, com.softorea.schoolsen.gcsschools.HeadSignature.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGap.this, com.softorea.schoolsen.gcsschools.M_SchoolVisitList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_Enrollment_AttendanceGap.this);
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
}
