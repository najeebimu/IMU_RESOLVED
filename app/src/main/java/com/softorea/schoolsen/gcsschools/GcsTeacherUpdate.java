package com.softorea.schoolsen.gcsschools;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.m_monthly.NonTeacherwebservicemainlistupdate;
import com.softorea.schoolsen.models.GcsTeacherDetails;

/**
 * Created by Softorea on 10/2/2017.
 */

public class GcsTeacherUpdate extends Activity {
    Button update;
    Spinner teacherreplacementgender;
    EditText teachername, teachercnic, teacherno, teachergender, teacherreplacementname;
    DatabaseHandler databasehandler;
    String SLevel = "";
    String emis;
    int identity;
    RadioGroup teachergroup;
    RadioGroup absentgroup;
    RadioGroup replacementgrp;

    String teachergroupstr = "Null";
    String absentgrpstr = "Null";
    String replacementgrpstr = "Null";

    LinearLayout absentLayout, replacementLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eteacherpresence_dialog);
        databasehandler = new DatabaseHandler(GcsTeacherUpdate.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");

        absentLayout = (LinearLayout) findViewById(R.id.layout_absent);
        replacementLayout = (LinearLayout) findViewById(R.id.layout_replacement);
        teachergroup = (RadioGroup) findViewById(R.id.teacher_group_rd);
        absentgroup = (RadioGroup) findViewById(R.id.rg_absent);
        replacementgrp = (RadioGroup) findViewById(R.id.rg_replacment);
        teachername = (EditText) findViewById(R.id.tname);
        teachercnic = (EditText) findViewById(R.id.tcnic);
        teacherno = (EditText) findViewById(R.id.tphone);
        teachergender = (EditText) findViewById(R.id.tdesignation);
        teacherreplacementname = (EditText) findViewById(R.id.replace_name);
        teacherreplacementgender = (Spinner) findViewById(R.id.replace_gender);
        update = (Button) findViewById(R.id.btn_savedialoge);

        teachergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int chechId) {

                if (chechId == R.id.rd_present) {
                    teachergroupstr = "Present";
                    absentLayout.setVisibility(View.GONE);
                    replacementgrp.clearCheck();
                    absentgroup.clearCheck();
                    absentgrpstr = "Null";
                    replacementLayout.setVisibility(View.GONE);
                    teacherreplacementname.setText("");
                    teacherreplacementgender.setSelection(0);
                } else if (chechId == R.id.rd_absent) {
                    teachergroupstr = "Absent";
                    absentgrpstr = "Null";
                    absentLayout.setVisibility(View.VISIBLE);
                }
                else if (chechId == R.id.rd_resigned) {
                    teachergroupstr = "Resigned";
                    absentLayout.setVisibility(View.GONE);
                    replacementgrp.clearCheck();
                    absentgroup.clearCheck();
                    absentgrpstr = "Null";
                    replacementLayout.setVisibility(View.GONE);
                    teacherreplacementname.setText("");
                    teacherreplacementgender.setSelection(0);
                }

            }
        });

        replacementgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int chechId) {

                if (chechId == R.id.rbtn_rep_yes) {
                    replacementgrpstr = "Yes";
                    replacementLayout.setVisibility(View.VISIBLE);
                } else if (chechId == R.id.rbtn_rep_no) {
                    replacementgrpstr = "No";
                    replacementLayout.setVisibility(View.GONE);
                    teacherreplacementname.setText("");
                    teacherreplacementgender.setSelection(0);
                }

            }
        });

        absentgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_leave) {
                    absentgrpstr = "Leave";
                } else if (checkedId == R.id.rd_duty) {
                    absentgrpstr = "Duty";
                } else if (checkedId == R.id.rd_unauth) {
                    absentgrpstr = "Un-Authorized";
                } else if (checkedId == R.id.rd_latecomer) {
                    absentgrpstr = "Late Comer";
                } else if (checkedId == R.id.rd_suspend) {
                    absentgrpstr = "Suspended";
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teachername.getText().toString().equals("") || teachercnic.getText().toString().equals("")
                        || teacherno.getText().toString().equals("") || teachergender.getText().toString().equals("")) {
                    Toast.makeText(GcsTeacherUpdate.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                } else if (teachergroupstr.equals("Null"))
                {
                    Toast.makeText(GcsTeacherUpdate.this, "Please Mark Attendance", Toast.LENGTH_SHORT).show();
                }
                else if (teachergroupstr.equals("Absent") && absentgrpstr.equals("Null"))
                {
                    Toast.makeText(GcsTeacherUpdate.this, "Select status details", Toast.LENGTH_SHORT).show();
                }
                else {
                    //checkAlreadyExist(SLevel);
                    GcsTeacherDetails schoolinfo = new GcsTeacherDetails();
                    schoolinfo.setTeachername(teachername.getText().toString());
                    schoolinfo.setTeacherno(teacherno.getText().toString());
                    schoolinfo.setTeachercnic(teachercnic.getText().toString());
                    schoolinfo.setTeachergender(teachergender.getText().toString());
                    schoolinfo.setReplacementname(teacherreplacementname.getText().toString());
                    schoolinfo.setReplacementgender(SLevel);
                    schoolinfo.setAttendance(teachergroupstr);
                    schoolinfo.setTeacherattendancedetails(absentgrpstr);
                    schoolinfo.setReplacementavailable(replacementgrpstr);
                    databasehandler.updategcsteachers(schoolinfo, emis, identity);
                    Toast.makeText(GcsTeacherUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(GcsTeacherUpdate.this, GcsteacherList.class));
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

        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(GcsTeacherUpdate.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        teacherreplacementgender.setAdapter(schoolLevelAdapter);
        teacherreplacementgender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = teacherreplacementgender.getSelectedItem().toString();

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
            String tname = extras.getString("tname");
            String tgender = extras.getString("tgender");
            String tcnic = extras.getString("tcnic");
            String tno = extras.getString("tno");
            String tatt = extras.getString("tatt");
            String tattdetails = extras.getString("tattdetails");
            String treplacement = extras.getString("treplacement");
            String trepname = extras.getString("trepname", "");
            String trepgender = extras.getString("trepgender");
            teachername.setText(tname);
            teachergender.setText(tgender);
            teachercnic.setText(tcnic);
            teacherno.setText(tno);
            teacherreplacementname.setText(trepname);

            if (trepgender != null) {
                if (trepgender.equals("Male")) {
                    teacherreplacementgender.setSelection(1, true);
                } else if (trepgender.equals("Female")) {
                    teacherreplacementgender.setSelection(2, true);
                } else {
                    teacherreplacementgender.setSelection(0, true);
                }
            } else {

            }

            if (treplacement != null) {
                if (treplacement.equals("Yes")) {
                    replacementgrp.check(R.id.rbtn_rep_yes);
                    replacementLayout.setVisibility(View.VISIBLE);
                } else if (treplacement.equals("No")) {
                    replacementgrp.check(R.id.rbtn_rep_no);
                    replacementLayout.setVisibility(View.GONE);
                } else {

                }
            } else {

            }

            if (tatt != null) {
                if (tatt.equals("Present")) {
                    teachergroup.check(R.id.rd_present);
                    absentLayout.setVisibility(View.GONE);
                } else if (tatt.equals("Absent")) {
                    teachergroup.check(R.id.rd_absent);
                    absentLayout.setVisibility(View.VISIBLE);
                }
                else if (tatt.equals("Resigned")) {
                    teachergroup.check(R.id.rd_resigned);
                    absentLayout.setVisibility(View.GONE);
                }
                else {

                }
            } else {

            }


            if (tattdetails != null) {
                if (tattdetails.equals("Leave")) {
                    absentgroup.check(R.id.rd_leave);
                } else if (tattdetails.equals("Duty")) {
                    absentgroup.check(R.id.rd_duty);
                } else if (tattdetails.equals("Un-Authorized")) {
                    absentgroup.check(R.id.rd_unauth);
                } else if (tattdetails.equals("Late Comer")) {
                    absentgroup.check(R.id.rd_latecomer);
                } else if (tattdetails.equals("Suspended")) {
                    absentgroup.check(R.id.rd_suspend);
                } else {

                }
            } else {

            }


        }
    }
}
