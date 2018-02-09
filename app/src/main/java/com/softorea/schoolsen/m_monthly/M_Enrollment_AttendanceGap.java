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
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.y_annual.HeadSignature;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_Enrollment_AttendanceGap extends Activity {
    TextView tundamit, tkachi, tone, ttwo, tthree, tfour, tfive, tsix, tseven, teight, tnine, tten, televen, ttwleve;
    Button back, next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_enrollment);
        tundamit = (TextView) findViewById(R.id.unadmitted);
        tkachi = (TextView) findViewById(R.id.kachi);
        tone = (TextView) findViewById(R.id.classone);
        ttwo = (TextView) findViewById(R.id.classtwo);
        tthree = (TextView) findViewById(R.id.classthree);
        tfour = (TextView) findViewById(R.id.classfour);
        tfive = (TextView) findViewById(R.id.classfive);
        tsix = (TextView) findViewById(R.id.classsix);
        tseven = (TextView) findViewById(R.id.classseven);
        teight = (TextView) findViewById(R.id.classeight);
        tnine = (TextView) findViewById(R.id.classnine);
        tten = (TextView) findViewById(R.id.classten);
        televen = (TextView) findViewById(R.id.classeelven);
        ttwleve = (TextView) findViewById(R.id.classtwelve);

        tundamit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class Unadmitted");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tkachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class Kachi");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 1");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        ttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 2");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 3");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 4");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 5");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 6");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 7");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        teight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 8");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tnine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 9");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        tten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 10");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        televen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 11");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        ttwleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M_Enrollment_AttendanceGap.this, M_Enrollment_AttendanceMain.class);
                intent.putExtra("className", "Class 12");
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String primary = preferences.getString("primary", "");
        String middle = preferences.getString("middle", "");
        String high = preferences.getString("high", "");
        String highsec = preferences.getString("highsecondary", "");
        if (primary.equals("PrimarySelected")) {
            tsix.setVisibility(View.GONE);
            tseven.setVisibility(View.GONE);
            teight.setVisibility(View.GONE);
            tnine.setVisibility(View.GONE);
            tten.setVisibility(View.GONE);
            televen.setVisibility(View.GONE);
            ttwleve.setVisibility(View.GONE);
        } else if (middle.equals("MiddleSelected")) {
            tnine.setVisibility(View.GONE);
            tten.setVisibility(View.GONE);
            televen.setVisibility(View.GONE);
            ttwleve.setVisibility(View.GONE);
        } else if (high.equals("HighSelected")) {
            televen.setVisibility(View.GONE);
            ttwleve.setVisibility(View.GONE);

        }
        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value = preferences.getString("case4", "");
                if (value.equals("case4found")) {
                    startActivity(new Intent(M_Enrollment_AttendanceGap.this, HeadSignature.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {
                    startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_SchoolVisitList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value = preferences.getString("case4", "");
                if (value.equals("case4found")) {
                    startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_ProxyTeacherList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {
                    startActivity(new Intent(M_Enrollment_AttendanceGap.this, M_TeacherAppointedByList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
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
