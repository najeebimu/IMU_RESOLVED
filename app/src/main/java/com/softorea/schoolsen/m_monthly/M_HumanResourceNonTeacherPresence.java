package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.NonTeacherNewDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_HumanResourceNonTeacherPresence extends Activity {
    TextView dateofbirth, dateoffirst;
    Button save;
    DatabaseHandler databasehandler;
    EditText staffName, staffCnic, staffNo;
    Spinner teacherDesignation, gender, maritalstatus, DesignationAs1st, PresentDesignation, DistrictOfDomicile;
    EditText Name, FatherName, bps, cellno, agNo, nicNo, highestqualificationLevel, highestqualificationSubject, dateOfFirst, HighestQualification, UC;
    Button btncancel;
    Date datecurrentobj;
    SimpleDateFormat sdf;
    RadioGroup teacherStatus;
    LinearLayout layoutAbsent;
    LinearLayout layoutTransferin;
    LinearLayout layoutNewApoint;
    LinearLayout layoutTransferOut;
    ImageView btnCalender;
    String DomicileValue;
    int bolteachStatus = 0;
    int bolteachstatusdetail = 0;
    // **********************************************************************************************\\
    TextView txtDate;
    //EditText teacherNameInput;
    //EditText teacherPhoneInput;
    //EditText teacherCnicInput;
    Spinner staffType, disabilitySpinner, typeofdisabilityspinner, teachingdesignationspresentspinner,TransferIn;
    LinearLayout teaching, nonteaching, teachingpresentdesignation, nonteachingpresentdesignation, typeofdisability;
String transferInStr;
    RadioGroup absentGroup;
    RadioGroup transferinGroup;
    RadioGroup transferOutGroup;
    RadioGroup newAppointmentGroup;

    String DesigAsValue, PresentNonValue, FirstNonValue, genderValue, maritalValue, Disab, TypeDisab;
    Spinner NonteacherDesignation, NonteacherDesignationpresent;
    String tStatus = "Null";
    String tStatusDetail = "Null";
    int position;
    String SLevel, staffTypeStr;
    int datebol = 0;
    RadioButton rdstatusPresent;
    RadioButton rdstatusAbsent;
    RadioButton rdstatusTransferout;
    RadioButton rdstatusResigned;
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
    String emis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_human_resource_nonteacherpresence);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        staffType = (Spinner) findViewById(R.id.stafftype);
        TransferIn = (Spinner) findViewById(R.id.teacherlist);
        teachingdesignationspresentspinner = (Spinner) findViewById(R.id.teachingdesignationspresent);
        disabilitySpinner = (Spinner) findViewById(R.id.disability);
        typeofdisabilityspinner = (Spinner) findViewById(R.id.typeofdisability);
        NonteacherDesignation = (Spinner) findViewById(R.id.nonteachingdesignations);
        NonteacherDesignationpresent = (Spinner) findViewById(R.id.nonteachingdesignationspresent);
        teaching = (LinearLayout) findViewById(R.id.teacherlayout);
        nonteaching = (LinearLayout) findViewById(R.id.nonteacherlayout);
        teachingpresentdesignation = (LinearLayout) findViewById(R.id.teacherlayoutpresentdesignation);
        nonteachingpresentdesignation = (LinearLayout) findViewById(R.id.nonteacherlayoutpresentdesignation);
        typeofdisability = (LinearLayout) findViewById(R.id.typeofdisabilitylayout);
        staffName = (EditText) findViewById(R.id.nameofstaff);
        staffCnic = (EditText) findViewById(R.id.nic_no);
        staffNo = (EditText) findViewById(R.id.cellno);


        gender = (Spinner) findViewById(R.id.gender);
        maritalstatus = (Spinner) findViewById(R.id.maritalstatus);
        DesignationAs1st = (Spinner) findViewById(R.id.teachingdesignations);
        FatherName = (EditText) findViewById(R.id.fathername);
        bps = (EditText) findViewById(R.id.bps);
        agNo = (EditText) findViewById(R.id.account_personal_no);
        highestqualificationLevel = (EditText) findViewById(R.id.quaification_level);
        highestqualificationSubject = (EditText) findViewById(R.id.quaification_subject);
        DistrictOfDomicile = (Spinner) findViewById(R.id.domicile_district);
        HighestQualification = (EditText) findViewById(R.id.highest_qualification);
        UC = (EditText) findViewById(R.id.uc);

        rdstatusPresent = (RadioButton) findViewById(R.id.rd_present);
        rdstatusAbsent = (RadioButton) findViewById(R.id.rd_absent);
        rdstatusTransferout = (RadioButton) findViewById(R.id.rd_trans_out);
        rdstatusResigned = (RadioButton) findViewById(R.id.rd_resigned);
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
        save = (Button) findViewById(R.id.save
        );
        databasehandler = new DatabaseHandler(M_HumanResourceNonTeacherPresence.this);

        String[] staffTypee = {"Non Teaching"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, staffTypee);
        staffType.setAdapter(genderAdapter);
        staffType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String SLevel = staffType.getSelectedItem().toString();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(M_HumanResourceNonTeacherPresence.this);
                SharedPreferences.Editor editor = preferences.edit();
                if (SLevel.equals("Teaching")) {
                    teaching.setVisibility(View.VISIBLE);
                    teachingpresentdesignation.setVisibility(View.VISIBLE);
                    nonteaching.setVisibility(View.GONE);
                    nonteachingpresentdesignation.setVisibility(View.GONE);
                } else if (SLevel.equals("Non Teaching")) {
                    teaching.setVisibility(View.GONE);
                    teachingpresentdesignation.setVisibility(View.GONE);
                    nonteaching.setVisibility(View.VISIBLE);
                    nonteachingpresentdesignation.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        String[] disability = {"No", "Yes"};
        ArrayAdapter<String> disabilityobject = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, disability);
        disabilitySpinner.setAdapter(disabilityobject);
        disabilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Disab = disabilitySpinner.getSelectedItem().toString();
                if (Disab.equals("No")) {
                    typeofdisability.setVisibility(View.GONE);
                } else if (Disab.equals("Yes")) {
                    typeofdisability.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] transferIn = getResources().getStringArray(R.array.illegal_occupation);
        ArrayAdapter<String> transferInobject = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, transferIn);
        TransferIn.setAdapter(transferInobject);
        TransferIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                transferInStr = TransferIn.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] dmicile = getResources().getStringArray(R.array.DistrictValues);
        ArrayAdapter<String> dmicileadpter = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, dmicile);
        DistrictOfDomicile.setAdapter(dmicileadpter);
        DistrictOfDomicile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                DomicileValue = DistrictOfDomicile.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] presentdesignation = getResources().getStringArray(R.array.teacherdesignation_options);
        ArrayAdapter<String> presentdesignationobject = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, presentdesignation);
        teachingdesignationspresentspinner.setAdapter(presentdesignationobject);
        teachingdesignationspresentspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = teachingdesignationspresentspinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] designationfirst = getResources().getStringArray(R.array.teacherdesignation_options);
        ArrayAdapter<String> presentdesignationobjec = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, designationfirst);
        DesignationAs1st.setAdapter(presentdesignationobjec);
        DesignationAs1st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                DesigAsValue = DesignationAs1st.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] nonpresentdesignation = getResources().getStringArray(R.array.nonteacherdesignation);
        ArrayAdapter<String> nonpresentdesignationobject = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, nonpresentdesignation);
        NonteacherDesignationpresent.setAdapter(nonpresentdesignationobject);
        NonteacherDesignationpresent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                PresentNonValue = NonteacherDesignationpresent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] nondesignationfirst = getResources().getStringArray(R.array.nonteacherdesignation);
        ArrayAdapter<String> nonpresentdesignationobjec = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, nondesignationfirst);
        NonteacherDesignation.setAdapter(nonpresentdesignationobjec);
        NonteacherDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                FirstNonValue = NonteacherDesignation.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] genderh = getResources().getStringArray(R.array.gender_infoo);
        ArrayAdapter<String> gn = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, genderh);
        gender.setAdapter(gn);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                genderValue = gender.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] marital = getResources().getStringArray(R.array.maritalstatus);
        ArrayAdapter<String> mar = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, marital);
        maritalstatus.setAdapter(mar);
        maritalstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                maritalValue = maritalstatus.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] typedisa = getResources().getStringArray(R.array.typeofdisability);
        ArrayAdapter<String> typedi = new ArrayAdapter<String>(M_HumanResourceNonTeacherPresence.this, android.R.layout.simple_spinner_dropdown_item, typedisa);
        typeofdisabilityspinner.setAdapter(typedi);
        typeofdisabilityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                TypeDisab = typeofdisabilityspinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateofbirth.setText(sdf.format(myCalendar.getTime()));
            }

        };

        dateofbirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_HumanResourceNonTeacherPresence.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        final Calendar myCalendarR = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateE = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarR.set(Calendar.YEAR, year);
                myCalendarR.set(Calendar.MONTH, monthOfYear);
                myCalendarR.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateoffirst.setText(sdf.format(myCalendarR.getTime()));
            }

        };

        dateoffirst.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_HumanResourceNonTeacherPresence.this, dateE, myCalendarR
                        .get(Calendar.YEAR), myCalendarR.get(Calendar.MONTH), myCalendarR.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cniclength = staffCnic.getText().toString();

                if (staffName.getText().toString().equals("") || staffCnic.getText().toString().equals("") || staffNo.getText().toString().equals("")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                } else if (cniclength == null || cniclength.length() < 13) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Cnic is not valid", Toast.LENGTH_SHORT).show();

                } else if (tStatus.equals("Null")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Attendance status cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Absent") && tStatusDetail.equals("Null")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Fill the Absent details", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Absent") && tStatusDetail.equals("Tranfer Order Shown")
                        && tStatusDetail.equals("Tranfer Order Not Shown")
                        && tStatusDetail.equals("Removal from service")
                        && tStatusDetail.equals("Retired") && tStatusDetail.equals("Died")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Fill the Absent details", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Absent") && tStatusDetail.equals("Un-Authorized")
                        && txtDate.getText().toString().equals("")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Select Date!", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Transfer Out") && tStatusDetail.equals("Null")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Fill the transfer details", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Transfer Out") && tStatusDetail.equals("Tranfer Order Shown")
                        && transferOutSchool.getText().toString().equals("")) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Enter School Name!", Toast.LENGTH_SHORT).show();
                } else {
                    checkAlreadyExist(staffCnic.getText().toString(), emis);


                }
            }

            private void checkAlreadyExist(String s, String emis) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_nonteacherInfo WHERE nonteachercnic='" + s + "'" + " AND emis='" + emis + "'", null);
                Cursor c1 = db.rawQuery("SELECT * FROM NonTeacherwebservice WHERE NonTeacherCnicwebservice='" + s + "'" + " AND emis='" + emis + "'", null);

                //Cursor c = db.rawQuery("SELECT * FROM t_nonteacherInfo WHERE nonteachercnic='" + s + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "CNIC already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else if (c1.moveToFirst()) {
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "CNIC already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else {
                    String namee = staffName.getText().toString();
                    String fathername = FatherName.getText().toString();
                    String bpss = bps.getText().toString();
                    String staffno = staffNo.getText().toString();
                    String accnoo = agNo.getText().toString();
                    String staffcnic = staffCnic.getText().toString();
                    String datebirth = dateofbirth.getText().toString();
                    String level = highestqualificationLevel.getText().toString();
                    String subject = highestqualificationSubject.getText().toString();
                    String datefirst = dateoffirst.getText().toString();
                    String district = DomicileValue;
                    String highestqua = HighestQualification.getText().toString();
                    String uc = UC.getText().toString();
                    String DATESINCEE = txtDate.getText().toString();
                    String trassnfer = transferOutSchool.getText().toString();
                    databasehandler.saveNonTeacherNew(new NonTeacherNewDetails(emis, namee, fathername, genderValue, maritalValue,
                            bpss, staffno, accnoo, staffcnic, datebirth, level, subject, datefirst, district, highestqua, FirstNonValue,
                            PresentNonValue, uc, Disab, TypeDisab, tStatus, transferInStr, tStatusDetail, DATESINCEE, trassnfer));
                    Toast.makeText(M_HumanResourceNonTeacherPresence.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_HumanResourceNonTeacherPresence.this, M_NonTeacherPresenceList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                    staffName.setText("");
                    FatherName.setText("");
                    bps.setText("");
                    agNo.setText("");
                    staffNo.setText("");
                    staffCnic.setText("");
                    dateofbirth.setText("");
                    highestqualificationLevel.setText("");
                    highestqualificationSubject.setText("");
                    dateoffirst.setText("");
                    DistrictOfDomicile.setSelection(0, true);
                    HighestQualification.setText("");
                    UC.setText("");
                    txtDate.setText("");
                    transferOutSchool.setText("");
                    staffType.setSelection(0, true);
                    //teacherDesignation.setSelection(0,true);
                    DesignationAs1st.setSelection(0, true);
                    teachingdesignationspresentspinner.setSelection(0, true);
                    gender.setSelection(0, true);
                    maritalstatus.setSelection(0, true);
                    disabilitySpinner.setSelection(0, true);
                    typeofdisabilityspinner.setSelection(0, true);
                    rdstatusPresent.setChecked(false);
                    rdstatusAbsent.setChecked(false);

                    rdstatusTransferout.setChecked(false);
                    rddetailleave.setChecked(false);
                    rddetailduty.setChecked(false);
                    rddetailunath.setChecked(false);
                    rddetaillate.setChecked(false);
                    rdshownout.setChecked(false);
                    rdntshownout.setChecked(false);

                    rdDied.setChecked(false);
                    rdRetired.setChecked(false);

                    rdRemoval.setChecked(false);
                    rdSuspended.setChecked(false);
                }
            }
        });

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
                }
                else if (checkedId == R.id.rd_resigned) {
                    tStatus = "Resigned";
                    layoutTransferOut.setVisibility(View.GONE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutGroup.clearCheck();
                    bolteachstatusdetail = 0;
                }
                else if (checkedId == R.id.rd_new_appoint) {
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
                DatePickerDialog dialog = new DatePickerDialog(M_HumanResourceNonTeacherPresence.this, datea, txtdate
                        .get(Calendar.YEAR), txtdate.get(Calendar.MONTH), txtdate.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_HumanResourceNonTeacherPresence.this);
        builder1.setMessage("Are you sure to discard saved changes?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        staffName.setText("");
                        FatherName.setText("");
                        bps.setText("");
                        agNo.setText("");
                        staffNo.setText("");
                        staffCnic.setText("");
                        dateofbirth.setText("");
                        highestqualificationLevel.setText("");
                        highestqualificationSubject.setText("");
                        dateoffirst.setText("");
                        DistrictOfDomicile.setSelection(0, true);
                        HighestQualification.setText("");
                        UC.setText("");
                        txtDate.setText("");
                        transferOutSchool.setText("");
                        staffType.setSelection(0, true);
                        //teacherDesignation.setSelection(0,true);
                        NonteacherDesignation.setSelection(0, true);
                        NonteacherDesignationpresent.setSelection(0, true);
                        gender.setSelection(0, true);
                        maritalstatus.setSelection(0, true);
                        disabilitySpinner.setSelection(0, true);
                        typeofdisabilityspinner.setSelection(0, true);
                        teacherStatus.clearCheck();
                        absentGroup.clearCheck();
                        transferOutGroup.clearCheck();
                        startActivity(new Intent(M_HumanResourceNonTeacherPresence.this, M_NonTeacherPresenceList.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();

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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("NonHr_name", staffName.getText().toString());
        editor.putString("NonHr_fathername", FatherName.getText().toString());
        editor.putString("NonHr_bps", bps.getText().toString());
        editor.putString("NonHr_cellno", staffNo.getText().toString());
        editor.putString("NonHr_agNo", agNo.getText().toString());
        editor.putString("NonHr_nicno", staffCnic.getText().toString());
        editor.putString("NonHr_dob", dateofbirth.getText().toString());
        editor.putString("NonHr_level", highestqualificationLevel.getText().toString());
        editor.putString("NonHr_subject", highestqualificationSubject.getText().toString());
        editor.putString("NonHr_dateofFirst", dateoffirst.getText().toString());
        editor.putString("NonHr_district", DomicileValue);
        editor.putString("NonHr_highestqualification", HighestQualification.getText().toString());
        editor.putString("NonHr_UC", UC.getText().toString());
        editor.putString("NonHr_datesince", txtDate.getText().toString());
        editor.putString("NonHr_schoolname", transferOutSchool.getText().toString());
        editor.putString("NonHr_stafftypespinner", staffTypeStr);
        editor.putString("NonHr_teachingdesigpresent", SLevel);
        editor.putString("NonHr_teachingdesigfirst", DesigAsValue);
        editor.putString("NonHr_nonteachingdesigpresent", PresentNonValue);
        editor.putString("NonHr_nonteachingdesigfirst", FirstNonValue);
        editor.putString("NonHr_genderspin", genderValue);
        editor.putString("NonHr_maritalvalue", maritalValue);
        editor.putString("NonHr_disability", Disab);
        editor.putString("NonHr_disabilitytype", TypeDisab);
        editor.putString("NonHr_status", tStatus);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String Hr_name = preferences.getString("NonHr_name", "");
        String Hr_fathername = preferences.getString("NonHr_fathername", "");
        String Hr_cellno = preferences.getString("NonHr_cellno", "");
        String Hr_bps = preferences.getString("NonHr_bps", "");
        String Hr_agNo = preferences.getString("NonHr_agNo", "");
        String Hr_nicno = preferences.getString("NonHr_nicno", "");
        String Hr_dob = preferences.getString("NonHr_dob", "");
        String Hr_level = preferences.getString("NonHr_level", "");
        String Hr_subject = preferences.getString("NonHr_subject", "");
        String Hr_dateoffirst = preferences.getString("NonHr_dateofFirst", "");
        String Hr_district = preferences.getString("NonHr_district", "");
        String Hr_hq = preferences.getString("NonHr_highestqualification", "");
        String Hr_uc = preferences.getString("NonHr_UC", "");
        String Hr_datesince = preferences.getString("NonHr_datesince", "");
        String Hr_schoolname = preferences.getString("NonHr_schoolname", "");
        String Hr_staff = preferences.getString("NonHr_stafftypespinner", "");
        String Hr_teachingdesigpre = preferences.getString("NonHr_teachingdesigpresent", "");
        String Hr_teachingdesigfirst = preferences.getString("NonHr_teachingdesigfirst", "");
        String Hr_nonteachingdesigpre = preferences.getString("NonHr_nonteachingdesigpresent", "");
        String Hr_nonteachingdesigfirst = preferences.getString("NonHr_nonteachingdesigfirst", "");
        String Hr_gender = preferences.getString("NonHr_genderspin", "");
        String Hr_marital = preferences.getString("NonHr_maritalvalue", "");
        String Hr_disablity = preferences.getString("NonHr_disability", "");
        String Hr_disablitytype = preferences.getString("NonHr_disabilitytype", "");
        String Hr_status = preferences.getString("NonHr_status", "");
        staffCnic.setText(Hr_nicno);
        staffName.setText(Hr_name);
        FatherName.setText(Hr_fathername);
        staffNo.setText(Hr_cellno);
        bps.setText(Hr_bps);
        agNo.setText(Hr_agNo);
        dateofbirth.setText(Hr_dob);
        highestqualificationLevel.setText(Hr_level);
        highestqualificationSubject.setText(Hr_subject);
        dateoffirst.setText(Hr_dateoffirst);
        // DistrictOfDomicile.setText(Hr_district);
        HighestQualification.setText(Hr_hq);
        UC.setText(Hr_uc);
        txtDate.setText(Hr_datesince);
        transferOutSchool.setText(Hr_schoolname);
        if (Hr_marital.equals("Widowed")) {
            maritalstatus.setSelection(3, true);
        } else if (Hr_marital.equals("Married")) {
            maritalstatus.setSelection(1, true);
        } else if (Hr_marital.equals("Divorced/Separated")) {
            maritalstatus.setSelection(2, true);
        } else {
            maritalstatus.setSelection(0, true);
        }
        if (Hr_gender.equals("Female")) {
            gender.setSelection(1, true);
        } else {
            gender.setSelection(0, true);
        }
        if (Hr_staff.equals("Teaching")) {
            staffType.setSelection(0, true);
        } else if (Hr_staff.equals("Non Teaching")) {
            staffType.setSelection(1, true);
        }
        if (Hr_disablity.equals("No")) {
            disabilitySpinner.setSelection(0, true);
        } else if (Hr_disablity.equals("Yes")) {
            disabilitySpinner.setSelection(1, true);
        }
        if (Hr_status.equals("Absent")) {
            rdstatusAbsent.setChecked(true);
        } else if (Hr_status.equals("Present")) {
            rdstatusPresent.setChecked(true);
        }  else if (Hr_status.equals("Transfer Out")) {
            rdstatusTransferout.setChecked(true);
        }
        else if (Hr_status.equals("Resigned")) {
            rdstatusResigned.setChecked(true);
        }
        else {

        }


        if (Hr_nonteachingdesigfirst.equals("Others")) {
            NonteacherDesignation.setSelection(19, true);
        } else if (Hr_nonteachingdesigfirst.equals("Cook")) {
            NonteacherDesignation.setSelection(1, true);
        } else if (Hr_nonteachingdesigfirst.equals("Caller")) {
            NonteacherDesignation.setSelection(2, true);
        } else if (Hr_nonteachingdesigfirst.equals("Hostel Warden")) {
            NonteacherDesignation.setSelection(3, true);
        } else if (Hr_nonteachingdesigfirst.equals("Work Shop Attendant")) {
            NonteacherDesignation.setSelection(4, true);
        } else if (Hr_nonteachingdesigfirst.equals("Lab Assistant")) {
            NonteacherDesignation.setSelection(5, true);
        } else if (Hr_nonteachingdesigfirst.equals("Lab Attendant")) {
            NonteacherDesignation.setSelection(6, true);
        } else if (Hr_nonteachingdesigfirst.equals("Chowkidar")) {
            NonteacherDesignation.setSelection(7, true);
        } else if (Hr_nonteachingdesigfirst.equals("Driver")) {
            NonteacherDesignation.setSelection(8, true);
        } else if (Hr_nonteachingdesigfirst.equals("J/Clerk")) {
            NonteacherDesignation.setSelection(9, true);
        } else if (Hr_nonteachingdesigfirst.equals("Mali")) {
            NonteacherDesignation.setSelection(10, true);
        } else if (Hr_nonteachingdesigfirst.equals("Naib Qasid")) {
            NonteacherDesignation.setSelection(11, true);
        } else if (Hr_nonteachingdesigfirst.equals("S/Clerk")) {
            NonteacherDesignation.setSelection(12, true);
        } else if (Hr_nonteachingdesigfirst.equals("Store Keeper")) {
            NonteacherDesignation.setSelection(13, true);
        } else if (Hr_nonteachingdesigfirst.equals("Assistant Store Keeper")) {
            NonteacherDesignation.setSelection(14, true);
        } else if (Hr_nonteachingdesigfirst.equals("Sweeper")) {
            NonteacherDesignation.setSelection(15, true);
        } else if (Hr_nonteachingdesigfirst.equals("Water Carrier")) {
            NonteacherDesignation.setSelection(16, true);
        } else if (Hr_nonteachingdesigfirst.equals("Librarian")) {
            NonteacherDesignation.setSelection(17, true);
        } else if (Hr_nonteachingdesigfirst.equals("Bearer")) {
            NonteacherDesignation.setSelection(18, true);
        } else {
            NonteacherDesignation.setSelection(0, true);
        }


        if (Hr_nonteachingdesigpre.equals("Others")) {
            NonteacherDesignationpresent.setSelection(19, true);
        } else if (Hr_nonteachingdesigpre.equals("Cook")) {
            NonteacherDesignationpresent.setSelection(1, true);
        } else if (Hr_nonteachingdesigpre.equals("Caller")) {
            NonteacherDesignationpresent.setSelection(2, true);
        } else if (Hr_nonteachingdesigpre.equals("Hostel Warden")) {
            NonteacherDesignationpresent.setSelection(3, true);
        } else if (Hr_nonteachingdesigpre.equals("Work Shop Attendant")) {
            NonteacherDesignationpresent.setSelection(4, true);
        } else if (Hr_nonteachingdesigpre.equals("Lab Assistant")) {
            NonteacherDesignationpresent.setSelection(5, true);
        } else if (Hr_nonteachingdesigpre.equals("Lab Attendant")) {
            NonteacherDesignationpresent.setSelection(6, true);
        } else if (Hr_nonteachingdesigpre.equals("Chowkidar")) {
            NonteacherDesignationpresent.setSelection(7, true);
        } else if (Hr_nonteachingdesigpre.equals("Driver")) {
            NonteacherDesignationpresent.setSelection(8, true);
        } else if (Hr_nonteachingdesigpre.equals("J/Clerk")) {
            NonteacherDesignationpresent.setSelection(9, true);
        } else if (Hr_nonteachingdesigpre.equals("Mali")) {
            NonteacherDesignationpresent.setSelection(10, true);
        } else if (Hr_nonteachingdesigpre.equals("Naib Qasid")) {
            NonteacherDesignationpresent.setSelection(11, true);
        } else if (Hr_nonteachingdesigpre.equals("S/Clerk")) {
            NonteacherDesignationpresent.setSelection(12, true);
        } else if (Hr_nonteachingdesigpre.equals("Store Keeper")) {
            NonteacherDesignationpresent.setSelection(13, true);
        } else if (Hr_nonteachingdesigpre.equals("Assistant Store Keeper")) {
            NonteacherDesignationpresent.setSelection(14, true);
        } else if (Hr_nonteachingdesigpre.equals("Sweeper")) {
            NonteacherDesignationpresent.setSelection(15, true);
        } else if (Hr_nonteachingdesigpre.equals("Water Carrier")) {
            NonteacherDesignationpresent.setSelection(16, true);
        } else if (Hr_nonteachingdesigpre.equals("Librarian")) {
            NonteacherDesignationpresent.setSelection(17, true);
        } else if (Hr_nonteachingdesigpre.equals("Bearer")) {
            NonteacherDesignationpresent.setSelection(18, true);
        } else {
            NonteacherDesignationpresent.setSelection(0, true);
        }


        if (Hr_district.equals("TORGHAR")) {
            DistrictOfDomicile.setSelection(24, true);
        } else if (Hr_district.equals("BANNU")) {
            DistrictOfDomicile.setSelection(1, true);
        } else if (Hr_district.equals("BATTAGRAM")) {
            DistrictOfDomicile.setSelection(2, true);
        } else if (Hr_district.equals("BUNNER")) {
            DistrictOfDomicile.setSelection(3, true);
        } else if (Hr_district.equals("Charsadda")) {
            DistrictOfDomicile.setSelection(4, true);
        } else if (Hr_district.equals("Chitral")) {
            DistrictOfDomicile.setSelection(5, true);
        } else if (Hr_district.equals("D.I.Khan")) {
            DistrictOfDomicile.setSelection(6, true);
        } else if (Hr_district.equals("DIR BALA")) {
            DistrictOfDomicile.setSelection(7, true);
        } else if (Hr_district.equals("DIR PAYAN")) {
            DistrictOfDomicile.setSelection(8, true);
        } else if (Hr_district.equals("HANGU")) {
            DistrictOfDomicile.setSelection(9, true);
        } else if (Hr_district.equals("HARIPUR")) {
            DistrictOfDomicile.setSelection(10, true);
        } else if (Hr_district.equals("Karak")) {
            DistrictOfDomicile.setSelection(11, true);
        } else if (Hr_district.equals("KOHAT")) {
            DistrictOfDomicile.setSelection(12, true);
        } else if (Hr_district.equals("Kohistan")) {
            DistrictOfDomicile.setSelection(13, true);
        } else if (Hr_district.equals("LAKKI")) {
            DistrictOfDomicile.setSelection(14, true);
        } else if (Hr_district.equals("MALAKAND")) {
            DistrictOfDomicile.setSelection(15, true);
        } else if (Hr_district.equals("MANSEHRA")) {
            DistrictOfDomicile.setSelection(16, true);
        } else if (Hr_district.equals("MARDAN")) {
            DistrictOfDomicile.setSelection(17, true);
        } else if (Hr_district.equals("Nowshera")) {
            DistrictOfDomicile.setSelection(18, true);
        } else if (Hr_district.equals("PESHAWAR")) {
            DistrictOfDomicile.setSelection(19, true);
        } else if (Hr_district.equals("SHANGLA")) {
            DistrictOfDomicile.setSelection(20, true);
        } else if (Hr_district.equals("SWABI")) {
            DistrictOfDomicile.setSelection(21, true);
        } else if (Hr_district.equals("SWAT")) {
            DistrictOfDomicile.setSelection(22, true);
        } else if (Hr_district.equals("TANK")) {
            DistrictOfDomicile.setSelection(23, true);
        } else {
            DistrictOfDomicile.setSelection(0, true);
        }

        if (Hr_disablitytype.equals("Leg/foot")) {
            typeofdisabilityspinner.setSelection(4, true);
        } else if (Hr_disablitytype.equals("Partial hearing")) {
            typeofdisabilityspinner.setSelection(1, true);
        } else if (Hr_disablitytype.equals("Partial speech")) {
            typeofdisabilityspinner.setSelection(2, true);
        } else if (Hr_disablitytype.equals("Hand/arm")) {
            typeofdisabilityspinner.setSelection(3, true);
        } else {
            typeofdisabilityspinner.setSelection(0, true);
        }


    }

}
