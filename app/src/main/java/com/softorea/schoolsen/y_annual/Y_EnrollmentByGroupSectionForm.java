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
import com.softorea.schoolsen.models.DetailsEnrollmentAgeGirls;
import com.softorea.schoolsen.models.DetailsEnrollmentGroupSection;

/**
 * Created by arsla on 05/10/2017.
 */

public class Y_EnrollmentByGroupSectionForm extends Activity {
    EditText et1, et2;
    DatabaseHandler databasehandler;
    DetailsEnrollmentGroupSection details;
    String emis,classNumber;
    TextView mainTitle;
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_enrollment_bygroup_sectionform);
        SharedPreferences preferencess = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferencess.getString("emiscode", "");
        databasehandler = new DatabaseHandler(Y_EnrollmentByGroupSectionForm.this);
        et1 = (EditText) findViewById(R.id.totalenrol);
        et2 = (EditText) findViewById(R.id.noofsections);
        mainTitle = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.back);
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle!=null)
        {
            classNumber = dataBundle.getString("className");
            mainTitle.setText(classNumber);
        }

        /*DetailsEnrollmentGroupSection grpsections = databasehandler.getEAgegirls(emis, classNumber);

        try {
            String totalenrollment = grpsections.getTotalEnrollment();
            String noOfSections = grpsections.getNoofSections();



            if (!totalenrollment.equals("Null") && !totalenrollment.equals("")) {
                et1.setText(totalenrollment);
            }
            if (!noOfSections.equals("Null") && !noOfSections.equals("")) {
                et2.setText(noOfSections);
            }



        } catch (IndexOutOfBoundsException e) {

        }*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_EnrollmentByGroupSectionForm.this, Y_EnrollmentByGroupSectionMain.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_EnrollmentByGroupSectionForm.this);
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
