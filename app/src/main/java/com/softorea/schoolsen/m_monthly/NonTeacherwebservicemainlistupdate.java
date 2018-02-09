package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsNonTeacherwebservice;
import com.softorea.schoolsen.models.DetailsTeacherwebservice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 10/2/2017.
 */

public class NonTeacherwebservicemainlistupdate extends Activity {
    Button update;
    EditText teachername, teachercnic, teacherno, teachergender;
    DatabaseHandler databasehandler;
    String SLevel = "";
    String emis;
    int identity;
    RadioGroup teachergroup;
    RadioGroup absentgroup;
    RadioGroup transfergroup;
    String teachergroupstr = "Null";
    String absentgrpstr = "Null";
   // String transfergrpstr = "Null";

    EditText transferOutSchool;
    LinearLayout absentLayout,transferLayout;
    TextView txtDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonteacherwebservicedialog);
        databasehandler = new DatabaseHandler(NonTeacherwebservicemainlistupdate.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        txtDate = (TextView) findViewById(R.id.txt_selected_date);
        absentLayout = (LinearLayout) findViewById(R.id.layout_absent);
        transferLayout = (LinearLayout) findViewById(R.id.layout_transferout);
        teachergroup = (RadioGroup) findViewById(R.id.teacher_group_rd);
        absentgroup = (RadioGroup) findViewById(R.id.rg_absent);
        transfergroup = (RadioGroup) findViewById(R.id.rg_transfrout);
        teachername = (EditText) findViewById(R.id.tname);
        teachercnic = (EditText) findViewById(R.id.tcnic);
        teacherno = (EditText) findViewById(R.id.tphone);
        teachergender = (EditText) findViewById(R.id.tdesignation);
        update = (Button) findViewById(R.id.btn_savedialoge);
        transferOutSchool = (EditText) findViewById(R.id.edit_schoolname);
        teachergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int chechId) {

                if (chechId == R.id.rd_present) {
                    teachergroupstr = "Present";
                    absentLayout.setVisibility(View.GONE);
                    absentgroup.clearCheck();
                    transferLayout.setVisibility(View.GONE);
                    transfergroup.clearCheck();
                    transferOutSchool.setVisibility(View.GONE);
                    absentgrpstr = "Null";
                    transferOutSchool.setText("");
                    txtDate.setText("");
                } else if (chechId == R.id.rd_absent) {
                    teachergroupstr = "Absent";
                    absentLayout.setVisibility(View.VISIBLE);
                    transferLayout.setVisibility(View.GONE);
                    transfergroup.clearCheck();
                    absentgroup.clearCheck();
                    absentgrpstr = "Null";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                }
                else if (chechId == R.id.rd_trans_out) {
                    teachergroupstr = "Transfer Out";
                    transferLayout.setVisibility(View.VISIBLE);
                    absentLayout.setVisibility(View.GONE);
                    absentgroup.clearCheck();
                    transfergroup.clearCheck();
                    absentgrpstr = "Null";
                    transferOutSchool.setVisibility(View.GONE);
                    txtDate.setText("");
                }
                else if (chechId == R.id.rd_resigned) {
                    teachergroupstr = "Resigned";
                    transferLayout.setVisibility(View.GONE);
                    absentLayout.setVisibility(View.GONE);
                    absentgroup.clearCheck();
                    transfergroup.clearCheck();
                    absentgrpstr = "Null";
                    transferOutSchool.setVisibility(View.GONE);
                    txtDate.setText("");
                }
            }
        });


        absentgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_leave) {
                    absentgrpstr = "Leave";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_duty) {
                    absentgrpstr = "Duty";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_unauth) {
                    absentgrpstr = "Un-Authorized";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_latecomer) {
                    absentgrpstr = "Late Comer";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_suspend) {
                    absentgrpstr = "Suspended";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                }

            }
        });

        transfergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_shown_out) {
                    absentgrpstr = "Tranfer Order Shown";
                    transferOutSchool.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rd_ntshown_out) {
                    absentgrpstr = "Tranfer Order Not Shown";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_retired) {
                    absentgrpstr = "Retired";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_died) {
                    absentgrpstr = "Died";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                } else if (checkedId == R.id.rd_rmservice) {
                    absentgrpstr = "Removal from service";
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutSchool.setText("");
                }

            }
        });

        final Calendar txtdate = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener datea = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                txtdate.set(Calendar.YEAR, year);
                txtdate.set(Calendar.MONTH, monthOfYear);
                txtdate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                txtDate.setText(sdf.format(txtdate.getTime()));
            }

        };

        txtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(NonTeacherwebservicemainlistupdate.this, datea, txtdate
                        .get(Calendar.YEAR), txtdate.get(Calendar.MONTH), txtdate.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teachername.getText().toString().equals("") || teachercnic.getText().toString().equals("")
                        || teacherno.getText().toString().equals("") || teachergender.getText().toString().equals("")) {
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                }  else if (teachergroupstr.equals("Null"))
                {
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Please Mark Attendance", Toast.LENGTH_SHORT).show();
                }
                else if (teachergroupstr.equals("Absent") && absentgrpstr.equals("Null"))
                {
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Select status details", Toast.LENGTH_SHORT).show();
                }
                else if (teachergroupstr.equals("Absent") && absentgrpstr.equals("Un-Authorized")
                        && txtDate.getText().toString().equals("")) {
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Select Date!", Toast.LENGTH_SHORT).show();
                }
                else if (teachergroupstr.equals("Transfer Out") && absentgrpstr.equals("Null"))
                {
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Select status details", Toast.LENGTH_SHORT).show();
                }
                else if (teachergroupstr.equals("Transfer Out") && absentgrpstr.equals("Tranfer Order Shown")
                        && transferOutSchool.getText().toString().equals("")) {
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Enter School Name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //checkAlreadyExist(SLevel);
                    DetailsNonTeacherwebservice schoolinfo = new DetailsNonTeacherwebservice();
                    schoolinfo.setTeachername(teachername.getText().toString());
                    schoolinfo.setTeacherno(teacherno.getText().toString());
                    schoolinfo.setTeachercnic(teachercnic.getText().toString());
                    schoolinfo.setTeachergender(teachergender.getText().toString());
                    schoolinfo.setAttendance(teachergroupstr);
                    schoolinfo.setTeacherattendancedetails(absentgrpstr);
                    schoolinfo.setAttendancedatesince(txtDate.getText().toString());
                    schoolinfo.setAttendancetrasnferschool(transferOutSchool.getText().toString());
                    databasehandler.updateNonteacherwebservice(schoolinfo, emis, identity);
                    Toast.makeText(NonTeacherwebservicemainlistupdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    //startActivity(new Intent(NonTeacherwebservicemainlistupdate.this, NonTeacherWebserviceMainList.class));
                    //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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
            String tattdate = extras.getString("tattdatesince");
            String tattdetailstrasnfer = extras.getString("tatttrasnferout");
            teachername.setText(tname);
            teachergender.setText(tgender);
            teachercnic.setText(tcnic);
            teacherno.setText(tno);
            transferOutSchool.setText(tattdetailstrasnfer);
            txtDate.setText(tattdate);

            if (tatt != null) {
                if (tatt.equals("Present")) {
                    teachergroup.check(R.id.rd_present);
                    absentLayout.setVisibility(View.GONE);
                } else if (tatt.equals("Absent")) {
                    teachergroup.check(R.id.rd_absent);
                    absentLayout.setVisibility(View.VISIBLE);
                    transferLayout.setVisibility(View.GONE);
                }
                else if (tatt.equals("Transfer Out")) {
                    teachergroup.check(R.id.rd_trans_out);
                    transferLayout.setVisibility(View.VISIBLE);
                    absentLayout.setVisibility(View.GONE);
                } else if (tatt.equals("Resigned")) {
                    teachergroup.check(R.id.rd_resigned);
                    transferLayout.setVisibility(View.GONE);
                    absentLayout.setVisibility(View.GONE);
                }else {

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
                }
                else if (tattdetails.equals("Tranfer Order Shown")) {
                    transfergroup.check(R.id.rd_shown_out);
                }
                else if (tattdetails.equals("Tranfer Order Not Shown")) {
                    transfergroup.check(R.id.rd_ntshown_out);
                }
                else if (tattdetails.equals("Retired")) {
                    transfergroup.check(R.id.rd_retired);
                }
                else if (tattdetails.equals("Died")) {
                    transfergroup.check(R.id.rd_died);
                }
                else if (tattdetails.equals("Removal from service")) {
                    transfergroup.check(R.id.rd_rmservice);
                }
                else {

                }
            } else {

            }


        }
    }
}
