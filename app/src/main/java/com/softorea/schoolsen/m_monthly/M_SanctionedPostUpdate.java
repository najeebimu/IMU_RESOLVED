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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsSanctionedPosts;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_SanctionedPostUpdate extends Activity {
    Button add, clear;
    Spinner designation, subjectlist;
    EditText postcode, bps, noofsanctionedposts, specifyOther;
    DatabaseHandler databasehandler;
    LinearLayout l1;
    String SLevel = "";
    String subjectdropdown;
    String emis;
    int identity;
    String occupationvalue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_sanctionedpostupdate);
        l1 = (LinearLayout) findViewById(R.id.subjectlayout);
        databasehandler = new DatabaseHandler(M_SanctionedPostUpdate.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        specifyOther = (EditText) findViewById(R.id.specifyother);
        postcode = (EditText) findViewById(R.id.postcode);
        bps = (EditText) findViewById(R.id.bps);
        subjectlist = (Spinner) findViewById(R.id.subjectdropdown);
        noofsanctionedposts = (EditText) findViewById(R.id.noofsanctioned);
        designation = (Spinner) findViewById(R.id.sanctioned_teaching_designation);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bps.getText().toString().equals("") || noofsanctionedposts.getText().toString().equals("")) {
                    Toast.makeText(M_SanctionedPostUpdate.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    //checkAlreadyExist(SLevel);
                    DetailsSanctionedPosts schoolinfo = new DetailsSanctionedPosts();
                    schoolinfo.setPositioncode(postcode.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setSpecifyothers(specifyOther.getText().toString());
                    schoolinfo.setSubject(subjectdropdown);
                    schoolinfo.setBPS(bps.getText().toString());
                    schoolinfo.setNoOfSanctionedPosts(noofsanctionedposts.getText().toString());
                    databasehandler.updateSanctionedPosts(schoolinfo, emis, identity);
                    Toast.makeText(M_SanctionedPostUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_SanctionedPostUpdate.this, M_SanctionedPostList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();

                }
            }

            /*private void checkAlreadyExist(String sLevel) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c=db.rawQuery("SELECT * FROM t_sanctionedteaching WHERE teaching_designation='"+sLevel+"'", null);
                if (c.moveToFirst()&& !designation.getSelectedItem().toString().equals(occupationvalue))
                {
                    Toast.makeText(M_SanctionedPostUpdate.this, "Designation already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else {
                    DetailsSanctionedPosts schoolinfo = new DetailsSanctionedPosts();
                    schoolinfo.setPositioncode(postcode.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setSpecifyothers(specifyOther.getText().toString());
                    schoolinfo.setSubject(subjectdropdown);
                    schoolinfo.setBPS(bps.getText().toString());
                    schoolinfo.setNoOfSanctionedPosts(noofsanctionedposts.getText().toString());
                    databasehandler.updateSanctionedPosts(schoolinfo, emis,identity);
                    Toast.makeText(M_SanctionedPostUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_SanctionedPostUpdate.this, M_SanctionedPostList.class));
                    finish();
                }
            }*/
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postcode.setText("");
                bps.setText("");
                subjectlist.setSelection(0, true);
                noofsanctionedposts.setText("");
                designation.setSelection(0, true);

            }
        });

        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.teacherdesignation_options);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_SanctionedPostUpdate.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        designation.setAdapter(schoolLevelAdapter);
        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = designation.getSelectedItem().toString();
                if (SLevel.equals("Senior Subject Specialist") || SLevel.equals("Subject Specialist")) {
                    l1.setVisibility(View.VISIBLE);
                } else {
                    l1.setVisibility(View.GONE);
                }

                if (SLevel.equals("Others")) {
                    specifyOther.setVisibility(View.VISIBLE);
                }
                if (!SLevel.equals("Others")) {
                    specifyOther.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] aa = getResources().getStringArray(R.array.subjectlistt);
        ArrayAdapter<String> abc = new ArrayAdapter<String>(M_SanctionedPostUpdate.this, android.R.layout.simple_spinner_dropdown_item, aa);
        subjectlist.setAdapter(abc);
        subjectlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                subjectdropdown = subjectlist.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(M_SanctionedPostUpdate.this, M_SanctionedPostList.class));
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
            String a = extras.getString("a");
            String b = extras.getString("b");
            String e = extras.getString("c");
            String d = extras.getString("d");
            occupationvalue = extras.getString("e");
            String f = extras.getString("f");
            postcode.setText(f);
            bps.setText(b);
            noofsanctionedposts.setText(a);
            specifyOther.setText(d);
            if (occupationvalue.equals("Others")) {
                designation.setSelection(35, true);
            } else if (occupationvalue.equals("Principal(B-20)")) {
                designation.setSelection(1, true);
            } else if (occupationvalue.equals("Principal(B-19)")) {
                designation.setSelection(2, true);
            } else if (occupationvalue.equals("Principal(B-18)")) {
                designation.setSelection(3, true);
            } else if (occupationvalue.equals("Vice Principal(B-18)")) {
                designation.setSelection(4, true);
            } else if (occupationvalue.equals("AT")) {
                designation.setSelection(5, true);
            } else if (occupationvalue.equals("CT")) {
                designation.setSelection(6, true);
            } else if (occupationvalue.equals("D.P.E")) {
                designation.setSelection(7, true);
            } else if (occupationvalue.equals("DM")) {
                designation.setSelection(8, true);
            } else if (occupationvalue.equals("Head Master/Mistress")) {
                designation.setSelection(9, true);
            } else if (occupationvalue.equals("IT Teacher")) {
                designation.setSelection(10, true);
            } else if (occupationvalue.equals("Senior IT Teacher")) {
                designation.setSelection(11, true);
            } else if (occupationvalue.equals("Librarian")) {
                designation.setSelection(12, true);
            } else if (occupationvalue.equals("PET")) {
                designation.setSelection(13, true);
            } else if (occupationvalue.equals("Principal")) {
                designation.setSelection(14, true);
            } else if (occupationvalue.equals("PSHT")) {
                designation.setSelection(15, true);
            } else if (occupationvalue.equals("PST")) {
                designation.setSelection(16, true);
            } else if (occupationvalue.equals("Qari/Qaria")) {
                designation.setSelection(17, true);
            } else if (occupationvalue.equals("SS")) {
                designation.setSelection(18, true);
            } else if (occupationvalue.equals("TT")) {
                designation.setSelection(19, true);
            } else if (occupationvalue.equals("Vice Principal")) {
                designation.setSelection(20, true);
            } else if (occupationvalue.equals("computer lab in-charge")) {
                designation.setSelection(21, true);
            } else if (occupationvalue.equals("SST")) {
                designation.setSelection(22, true);
            } else if (occupationvalue.equals("SST(Bio-Chemistry)")) {
                designation.setSelection(23, true);
            } else if (occupationvalue.equals("SST(Math-Physics)")) {
                designation.setSelection(24, true);
            } else if (occupationvalue.equals("SET")) {
                designation.setSelection(25, true);
            } else if (occupationvalue.equals("Senior Subject Specialist")) {
                designation.setSelection(26, true);
            } else if (occupationvalue.equals("Subject Specialist")) {
                designation.setSelection(27, true);
            } else if (occupationvalue.equals("Senior AT")) {
                designation.setSelection(28, true);
            } else if (occupationvalue.equals("Senior CT")) {
                designation.setSelection(29, true);
            } else if (occupationvalue.equals("Senior DM")) {
                designation.setSelection(30, true);
            } else if (occupationvalue.equals("Senior PET")) {
                designation.setSelection(31, true);
            } else if (occupationvalue.equals("Senior PST")) {
                designation.setSelection(32, true);
            } else if (occupationvalue.equals("Senior Qari")) {
                designation.setSelection(33, true);
            } else if (occupationvalue.equals("Senior TT")) {
                designation.setSelection(34, true);
            } else {
                designation.setSelection(0, true);
            }


            if (e.equals("English")) {
                subjectlist.setSelection(0, true);
            } else if (e.equals("Urdu")) {
                subjectlist.setSelection(1, true);
            } else if (e.equals("Islamiat)")) {
                subjectlist.setSelection(2, true);
            } else if (e.equals("Pak Studies")) {
                subjectlist.setSelection(3, true);
            } else if (e.equals("History/Civics")) {
                subjectlist.setSelection(4, true);
            } else if (e.equals("Economics")) {
                subjectlist.setSelection(5, true);
            } else if (e.equals("Statistics")) {
                subjectlist.setSelection(6, true);
            } else if (e.equals("Pashto")) {
                subjectlist.setSelection(7, true);
            } else if (e.equals("Home Economics")) {
                subjectlist.setSelection(8, true);
            } else if (e.equals("Maths")) {
                subjectlist.setSelection(9, true);
            } else if (e.equals("Physics")) {
                subjectlist.setSelection(10, true);
            } else if (e.equals("Chemistry")) {
                subjectlist.setSelection(11, true);
            } else if (e.equals("Biology")) {
                subjectlist.setSelection(12, true);
            } else {
                subjectlist.setSelection(13, true);
            }
        }


    }
}
