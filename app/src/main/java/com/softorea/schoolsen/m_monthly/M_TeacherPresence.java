package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_TeacherPresence extends Activity {
    RadioGroup teacherStatus;
    LinearLayout layoutAbsent;
    LinearLayout layoutTransferin;
    LinearLayout layoutNewApoint;
    LinearLayout layoutTransferOut;
    int bolteachStatus = 0;
    int bolteachstatusdetail = 0;
    TextView txtDate;
    Button save;
    TextView dateofbirth, dateoffirst;
    RadioGroup absentGroup;
    RadioGroup transferinGroup;
    RadioGroup transferOutGroup;
    RadioGroup newAppointmentGroup;

    String tStatus = "Null";
    String tStatusDetail = "Null";
    int position;
    int datebol = 0;
    RadioButton rdstatusPresent;
    RadioButton rdstatusAbsent;
    RadioButton rdstatusTransferout;
    RadioButton rddetailleave;
    RadioButton rddetailduty;
    RadioButton rddetailunath;
    RadioButton rddetaillate;
    RadioButton rdshownout;
    RadioButton rdntshownout;
    RadioButton rdDied;
    RadioButton rdRetired;
    RadioButton rdRemoval;
    RadioButton rdSuspended;
    String currentDate;
    int size;
    Context context;
    EditText transferOutSchool;
    EditText tname, tcnic, tno, tdesignation;

    long idd;
    DatabaseHandler databasehandler;
    String emis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_teacher_presence);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        databasehandler = new DatabaseHandler(M_TeacherPresence.this);
        save = (Button) findViewById(R.id.save);
        tname = (EditText) findViewById(R.id.tname);
        tcnic = (EditText) findViewById(R.id.tcnic);
        tno = (EditText) findViewById(R.id.tphone);
        tdesignation = (EditText) findViewById(R.id.tdesignation);
        rdstatusPresent = (RadioButton) findViewById(R.id.rd_present);
        rdstatusAbsent = (RadioButton) findViewById(R.id.rd_absent);
        rdstatusTransferout = (RadioButton) findViewById(R.id.rd_trans_out);
        rddetailleave = (RadioButton) findViewById(R.id.rd_leave);
        rddetailduty = (RadioButton) findViewById(R.id.rd_duty);
        rddetailunath = (RadioButton) findViewById(R.id.rd_unauth);
        rddetaillate = (RadioButton) findViewById(R.id.rd_latecomer);
        rdSuspended = (RadioButton) findViewById(R.id.rd_suspend);
        rdshownout = (RadioButton) findViewById(R.id.rd_shown_out);
        rdntshownout = (RadioButton) findViewById(R.id.rd_ntshown_out);
        rdDied = (RadioButton) findViewById(R.id.rd_died);
        rdRetired = (RadioButton) findViewById(R.id.rd_retired);
        rdRemoval = (RadioButton) findViewById(R.id.rd_rmservice);
        //teacherNameInput = (EditText) findViewById(R.id.tname);
        //teacherPhoneInput = (EditText) findViewById(R.id.tphone);
        //teacherCnicInput = (EditText) findViewById(R.id.tcnic);
        txtDate = (TextView) findViewById(R.id.txt_selected_date);
        absentGroup = (RadioGroup) findViewById(R.id.rg_absent);
        transferinGroup = (RadioGroup) findViewById(R.id.rg_transfrin);
        transferOutGroup = (RadioGroup) findViewById(R.id.rg_transfrout);
        newAppointmentGroup = (RadioGroup) findViewById(R.id.rg_newapoint);
        transferOutSchool = (EditText) findViewById(R.id.edit_schoolname);
        layoutAbsent = (LinearLayout) findViewById(R.id.layout_absent);
        layoutTransferin = (LinearLayout) findViewById(R.id.layout_transferin);
        layoutNewApoint = (LinearLayout) findViewById(R.id.layout_newapoint);
        layoutTransferOut = (LinearLayout) findViewById(R.id.layout_transferout);
        teacherStatus = (RadioGroup) findViewById(R.id.teacher_group_rd);
        dateofbirth = (TextView) findViewById(R.id.datebirth);
        dateoffirst = (TextView) findViewById(R.id.date_of_first);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String cnic = intent.getStringExtra("cnic");
        String no = intent.getStringExtra("no");
        String designation = intent.getStringExtra("designation");
        idd = intent.getLongExtra("id", 0);
        tname.setText(name);
        tcnic.setText(cnic);
        tno.setText(no);
        tdesignation.setText(designation);

        newAppointmentGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_ofcialshown) {
                            tStatusDetail = "Official order shown";
                            transferOutSchool.setVisibility(View.GONE);
                            bolteachstatusdetail = 1;
                        } else if (checkedId == R.id.rd_ofntshown) {
                            tStatusDetail = "Official order not shown";
                            transferOutSchool.setVisibility(View.GONE);
                            bolteachstatusdetail = 1;
                        }

                    }
                });

        transferOutGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_shown_out) {
                            tStatusDetail = "Tranfer Order Shown";
                            bolteachstatusdetail = 1;
                            transferOutSchool.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.rd_ntshown_out) {
                            tStatusDetail = "Tranfer Order Not Shown";
                            bolteachstatusdetail = 1;
                            transferOutSchool.setVisibility(View.GONE);
                        } else if (checkedId == R.id.rd_retired) {
                            tStatusDetail = "Retired";
                            bolteachstatusdetail = 1;
                            transferOutSchool.setVisibility(View.GONE);
                        } else if (checkedId == R.id.rd_died) {
                            tStatusDetail = "Died";
                            bolteachstatusdetail = 1;
                            transferOutSchool.setVisibility(View.GONE);
                        } else if (checkedId == R.id.rd_rmservice) {
                            tStatusDetail = "Removal from service";
                            bolteachstatusdetail = 1;
                            transferOutSchool.setVisibility(View.GONE);

                        }

                    }
                });

        transferinGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_shown) {
                            tStatusDetail = "Transfer Order Shown";
                            bolteachstatusdetail = 1;
                        } else if (checkedId == R.id.rd_ntshown) {
                            tStatusDetail = "Transfer Order Not Shown";
                            bolteachstatusdetail = 1;
                        }

                    }
                });

        absentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_leave) {
                    tStatusDetail = "Leave";
                    bolteachstatusdetail = 1;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_duty) {
                    tStatusDetail = "Duty";
                    bolteachstatusdetail = 1;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_unauth) {
                    tStatusDetail = "Un-Authorized";
                    bolteachstatusdetail = 1;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_latecomer) {
                    tStatusDetail = "Late Comer";
                    bolteachstatusdetail = 1;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_suspend) {
                    tStatusDetail = "Suspended";
                    bolteachstatusdetail = 1;
                    transferOutSchool.setVisibility(View.GONE);
                }

            }
        });

        teacherStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("TSTATUS", tStatus);
                if (checkedId == R.id.rd_absent) {
                    tStatus = "Absent";
                    layoutAbsent.setVisibility(View.VISIBLE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutTransferOut.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    absentGroup.clearCheck();
                    bolteachstatusdetail = 0;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_trans_in) {
                    tStatus = "Transfer In";
                    layoutTransferin.setVisibility(View.VISIBLE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutTransferOut.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_trans_out) {
                    tStatus = "Transfer Out";
                    layoutTransferOut.setVisibility(View.VISIBLE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutGroup.clearCheck();
                    bolteachstatusdetail = 0;
                } else if (checkedId == R.id.rd_new_appoint) {
                    tStatus = "New Appoint";
                    layoutNewApoint.setVisibility(View.VISIBLE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutTransferOut.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    transferOutSchool.setVisibility(View.GONE);
                } else if (checkedId == R.id.rd_present) {
                    tStatus = "Present";
                    tStatusDetail = "Null";
                    layoutNewApoint.setVisibility(View.GONE);
                    transferOutSchool.setVisibility(View.GONE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutTransferOut.setVisibility(View.GONE);
                    bolteachStatus = 1;
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
                new DatePickerDialog(M_TeacherPresence.this, datea, txtdate
                        .get(Calendar.YEAR), txtdate.get(Calendar.MONTH),
                        txtdate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String teachername = tname.getText().toString();
                String teacherno = tno.getText().toString();
                String teachercnic = tcnic.getText().toString();
                String tdesignationn = tdesignation.getText().toString();
                databasehandler.Teacherupdate(new TeacherNewDetails(emis,teachername, teacherno, teachercnic,tdesignationn,tStatus));
                finish();
                startActivity(new Intent(M_TeacherPresence.this,M_TeacherPresenceList.class));*/
                SQLiteDatabase database = databasehandler.getWritableDatabase();
                String strFilter = "id=" + idd;
                ContentValues args = new ContentValues();
                args.put("teacher_attendance", tStatus);
                database.update("t_teacherInfo", args, strFilter, null);
                finish();
                startActivity(new Intent(M_TeacherPresence.this, M_TeacherPresenceList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });


    }
}

