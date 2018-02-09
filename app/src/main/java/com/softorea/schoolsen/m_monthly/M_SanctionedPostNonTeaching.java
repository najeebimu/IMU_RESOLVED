package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsSanctionedNonteachingPosts;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_SanctionedPostNonTeaching extends Activity {
    Button add, clear;
    Spinner designation;
    EditText postcode, bps, others, noofsanctionedposts;
    DatabaseHandler databasehandler;
    String SLevel = "";
    String emis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_sanctionednonteachingpost);
        databasehandler = new DatabaseHandler(M_SanctionedPostNonTeaching.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        postcode = (EditText) findViewById(R.id.postcode);
        bps = (EditText) findViewById(R.id.bps);
        others = (EditText) findViewById(R.id.others);
        noofsanctionedposts = (EditText) findViewById(R.id.noofsanctioned);
        designation = (Spinner) findViewById(R.id.pteacherfor_designation);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bps.getText().toString().equals("") || noofsanctionedposts.getText().toString().equals("")
                        ) {
                    Toast.makeText(M_SanctionedPostNonTeaching.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    //checkAlreadyExist(SLevel);
                    String PositionCode = postcode.getText().toString();
                    String bpss = bps.getText().toString();
                    String subjectName = others.getText().toString();
                    String noOfSanctionedPosts = noofsanctionedposts.getText().toString();
                    databasehandler.saveSanctionedNonTeaching(new DetailsSanctionedNonteachingPosts(emis, PositionCode, SLevel, bpss, subjectName, noOfSanctionedPosts));
                    Toast.makeText(M_SanctionedPostNonTeaching.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_SanctionedPostNonTeaching.this, M_SanctionedPostNonteachingList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                    postcode.setText("");
                    bps.setText("");
                    others.setText("");
                    noofsanctionedposts.setText("");
                    designation.setSelection(0);
                    SLevel = designation.getSelectedItem().toString();


                }
            }

          /*  private void checkAlreadyExist(String sLevel) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_sanctionednonteaching WHERE Nonteaching_designation='" + sLevel + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(M_SanctionedPostNonTeaching.this, "Designation already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                } else {
                    String PositionCode = postcode.getText().toString();
                    String bpss = bps.getText().toString();
                    String subjectName = others.getText().toString();
                    String noOfSanctionedPosts = noofsanctionedposts.getText().toString();
                    databasehandler.saveSanctionedNonTeaching(new DetailsSanctionedNonteachingPosts(emis,PositionCode, SLevel,bpss,subjectName,noOfSanctionedPosts));
                    Toast.makeText(M_SanctionedPostNonTeaching.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_SanctionedPostNonTeaching.this,M_SanctionedPostNonteachingList.class));
                    finish();
                    postcode.setText("");
                    bps.setText("");
                    others.setText("");
                    noofsanctionedposts.setText("");
                    designation.setSelection(0);
                    SLevel = designation.getSelectedItem().toString();
                }
            }*/
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postcode.setText("");
                bps.setText("");
                others.setText("");
                noofsanctionedposts.setText("");
                designation.setSelection(0, true);

            }
        });

        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.nonteacherdesignation);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_SanctionedPostNonTeaching.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        designation.setAdapter(schoolLevelAdapter);
        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = designation.getSelectedItem().toString();
                if (SLevel.equals("Others")) {
                    others.setVisibility(View.VISIBLE);
                } else {
                    others.setVisibility(View.GONE);
                    others.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M_SanctionedPostNonTeaching.this, M_SanctionedPostNonteachingList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("POSITIONCODEDesignationNon", SLevel);
        editor.putString("postcodevalueNon", postcode.getText().toString());
        editor.putString("bpsvaluepostNon", bps.getText().toString());
        editor.putString("otherss", others.getText().toString());
        editor.putString("noOfsanValueNon", noofsanctionedposts.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String occupationvalue = preferences.getString("POSITIONCODEDesignationNon", "");
        String b = preferences.getString("postcodevalueNon", "");
        String bother = preferences.getString("otherss", "");
        String c = preferences.getString("bpsvaluepostNon", "");
        String d = preferences.getString("noOfsanValueNon", "");
        postcode.setText(b);
        bps.setText(c);
        others.setText(bother);
        noofsanctionedposts.setText(d);
        if (occupationvalue.equals("Others")) {
            designation.setSelection(19, true);
        } else if (occupationvalue.equals("Cook")) {
            designation.setSelection(1, true);
        } else if (occupationvalue.equals("Caller")) {
            designation.setSelection(2, true);
        } else if (occupationvalue.equals("Hostel Warden")) {
            designation.setSelection(3, true);
        } else if (occupationvalue.equals("Work Shop Attendant")) {
            designation.setSelection(4, true);
        } else if (occupationvalue.equals("Lab Assistant")) {
            designation.setSelection(5, true);
        } else if (occupationvalue.equals("Lab Attendant")) {
            designation.setSelection(6, true);
        } else if (occupationvalue.equals("Chowkidar")) {
            designation.setSelection(7, true);
        } else if (occupationvalue.equals("Driver")) {
            designation.setSelection(8, true);
        } else if (occupationvalue.equals("J/Clerk")) {
            designation.setSelection(9, true);
        } else if (occupationvalue.equals("Mali")) {
            designation.setSelection(10, true);
        } else if (occupationvalue.equals("Naib Qasid")) {
            designation.setSelection(11, true);
        } else if (occupationvalue.equals("S/Clerk")) {
            designation.setSelection(12, true);
        } else if (occupationvalue.equals("Store Keeper")) {
            designation.setSelection(13, true);
        } else if (occupationvalue.equals("Assistant Store Keeper")) {
            designation.setSelection(14, true);
        } else if (occupationvalue.equals("Sweeper")) {
            designation.setSelection(15, true);
        } else if (occupationvalue.equals("Water Carrier")) {
            designation.setSelection(16, true);
        } else if (occupationvalue.equals("Librarian")) {
            designation.setSelection(17, true);
        } else if (occupationvalue.equals("Bearer")) {
            designation.setSelection(18, true);
        } else {
            designation.setSelection(0, true);
        }
    }
}
