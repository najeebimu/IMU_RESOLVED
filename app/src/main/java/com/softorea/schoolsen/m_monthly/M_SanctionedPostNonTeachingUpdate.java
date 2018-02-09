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

public class M_SanctionedPostNonTeachingUpdate extends Activity {
    Button add, clear;
    Spinner designation;
    EditText postcode, bps, others, noofsanctionedposts;
    DatabaseHandler databasehandler;
    String SLevel = "";
    String emis;
    int identity;
    String occupationvalue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_sanctionednonteachingpostupdate);
        databasehandler = new DatabaseHandler(M_SanctionedPostNonTeachingUpdate.this);
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
                if (bps.getText().toString().equals("") || noofsanctionedposts.getText().toString().equals("")) {
                    Toast.makeText(M_SanctionedPostNonTeachingUpdate.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    //checkAlreadyExist(SLevel);
                    DetailsSanctionedNonteachingPosts schoolinfo = new DetailsSanctionedNonteachingPosts();
                    schoolinfo.setPositioncode(postcode.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setSpecifyothers(others.getText().toString());
                    schoolinfo.setBPS(bps.getText().toString());
                    schoolinfo.setNoOfSanctionedPosts(noofsanctionedposts.getText().toString());
                    databasehandler.updateNonSanctionedPosts(schoolinfo, emis, identity);
                    Toast.makeText(M_SanctionedPostNonTeachingUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_SanctionedPostNonTeachingUpdate.this, M_SanctionedPostNonteachingList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();


                }
            }

           /* private void checkAlreadyExist(String sLevel) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c=db.rawQuery("SELECT * FROM t_sanctionednonteaching WHERE Nonteaching_designation='"+sLevel+"'", null);
                if (c.moveToFirst()&& !designation.getSelectedItem().toString().equals(occupationvalue))
                {
                    Toast.makeText(M_SanctionedPostNonTeachingUpdate.this, "Designation already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else {
                    DetailsSanctionedNonteachingPosts schoolinfo = new DetailsSanctionedNonteachingPosts();
                    schoolinfo.setPositioncode(postcode.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setSpecifyothers(others.getText().toString());
                    schoolinfo.setBPS(bps.getText().toString());
                    schoolinfo.setNoOfSanctionedPosts(noofsanctionedposts.getText().toString());
                    databasehandler.updateNonSanctionedPosts(schoolinfo,emis,identity);
                    Toast.makeText(M_SanctionedPostNonTeachingUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_SanctionedPostNonTeachingUpdate.this,M_SanctionedPostNonteachingList.class));
                    finish();
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
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_SanctionedPostNonTeachingUpdate.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
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
        //startActivity(new Intent(M_SanctionedPostNonTeachingUpdate.this,M_SanctionedPostNonteachingList.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            identity = extras.getInt("ID");
            String noOf = extras.getString("noOf");
            String bpss = extras.getString("bps");
            String other = extras.getString("other");
            occupationvalue = extras.getString("Des");
            String pos = extras.getString("pos");
            postcode.setText(pos);
            bps.setText(bpss);
            others.setText(other);
            noofsanctionedposts.setText(noOf);

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
}
