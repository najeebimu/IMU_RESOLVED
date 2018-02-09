package com.softorea.schoolsen.y_annual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by arsla on 05/10/2017.
 */

public class Y_EnrollmentByGroupSectionTen extends Activity {
    Button back;
    TextView science, arts, compscience;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_enrollment_bygroup_sectionten);
        science = (TextView) findViewById(R.id.science);
        arts = (TextView) findViewById(R.id.arts);
        compscience = (TextView) findViewById(R.id.compscience);

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_EnrollmentByGroupSectionTen.this, Y_EnrollmentByGroupSectionTenScience.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        arts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_EnrollmentByGroupSectionTen.this, Y_EnrollmentByGroupSectionTenArts.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        compscience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_EnrollmentByGroupSectionTen.this, Y_EnrollmentByGroupSectionTenCompScience.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        back = (Button) findViewById(R.id.ebtn_left);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_EnrollmentByGroupSectionTen.this, Y_EnrollmentByGroupSectionMain.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_EnrollmentByGroupSectionTen.this);
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
