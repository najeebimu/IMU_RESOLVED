package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterTrainingRecord;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.TeacherNewDetails;
import com.softorea.schoolsen.models.TrainingRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_HumanResourceTeacherPresenceUpdate extends Activity {
    TrainingRecord details;

    String isValueExists;
    int identity;
    ArrayList<TrainingRecord> addas = new ArrayList<TrainingRecord>();
    CustomAdapterTrainingRecord cusadapter;
    ArrayList<TrainingRecord> teacherList;
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
    int bolteachStatus = 0;
    String DomicileValue;
    int bolteachstatusdetail = 0;
    // **********************************************************************************************\\
    TextView txtDate;
    //EditText teacherNameInput;
    //EditText teacherPhoneInput;
    //EditText teacherCnicInput;
    String tcnic;
    Spinner staffType, disabilitySpinner, typeofdisabilityspinner, teachingdesignationspresentspinner,TransferIn;
    LinearLayout teaching, nonteaching, teachingpresentdesignation, nonteachingpresentdesignation, typeofdisability;
    RadioGroup absentGroup;
    RadioGroup transferinGroup;
    RadioGroup transferOutGroup;
    RadioGroup newAppointmentGroup;
    String DesigAsValue, PresentNonValue, FirstNonValue, genderValue, maritalValue, Disab, TypeDisab;
    Spinner NonteacherDesignation, NonteacherDesignationpresent;
    String tStatus = "Null";
    String tStatusDetail = "Null";
    String trasnferInStr;
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
    String emis, backCnic, backName, backAccountNo;
    TextView t1;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_human_resource_teacherpresenceupdate);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            identity = extras.getInt("ID");
        }

        t1 = (TextView) findViewById(R.id.trecord);
        databasehandler = new DatabaseHandler(M_HumanResourceTeacherPresenceUpdate.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        TransferIn = (Spinner) findViewById(R.id.teacherlist);
        staffType = (Spinner) findViewById(R.id.stafftype);
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
        //dateSince = (TextView) findViewById(R.id.txt_selected_date);
        //transferOrderSchoolName = (EditText) findViewById(R.id.edit_schoolname);
        //teacherNameInput = (EditText) findViewById(R.id.tname);
        //teacherPhoneInput = (EditText) findViewById(R.id.tphone);
        //teacherCnicInput = (EditText) findViewById(R.id.tcnic);
        txtDate = (TextView) findViewById(R.id.txt_selected_date);
        absentGroup = (RadioGroup) findViewById(R.id.rg_absent);
        transferinGroup = (RadioGroup) findViewById(R.id.rg_transfrin);
        transferOutGroup = (RadioGroup) findViewById(R.id.rg_transfrout);
        newAppointmentGroup = (RadioGroup) findViewById(R.id.rg_newapoint);
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
        transferOutSchool = (EditText) findViewById(R.id.edit_schoolname);
        layoutAbsent = (LinearLayout) findViewById(R.id.layout_absent);
        layoutTransferin = (LinearLayout) findViewById(R.id.layout_transferin);
        layoutNewApoint = (LinearLayout) findViewById(R.id.layout_newapoint);
        layoutTransferOut = (LinearLayout) findViewById(R.id.layout_transferout);
        teacherStatus = (RadioGroup) findViewById(R.id.teacher_group_rd);
        dateofbirth = (TextView) findViewById(R.id.datebirth);
        dateoffirst = (TextView) findViewById(R.id.date_of_first);

        backCnic = staffCnic.getText().toString();
        backName = staffName.getText().toString();
        backAccountNo = agNo.getText().toString();

        //teacherList = databasehandler.getTrainingRecord(emis,identity);


        save = (Button) findViewById(R.id.save
        );
        listcontent = (ListView) findViewById(R.id.listView);
        // SavingInDb();

        String[] staffTypee = {"Teaching"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, staffTypee);
        staffType.setAdapter(genderAdapter);
        staffType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                staffTypeStr = staffType.getSelectedItem().toString();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(M_HumanResourceTeacherPresenceUpdate.this);
                SharedPreferences.Editor editor = preferences.edit();
                if (staffTypeStr.equals("Teaching")) {
                    teaching.setVisibility(View.VISIBLE);
                    teachingpresentdesignation.setVisibility(View.VISIBLE);
                    nonteaching.setVisibility(View.GONE);
                    nonteachingpresentdesignation.setVisibility(View.GONE);
                } else if (staffTypeStr.equals("Non Teaching")) {
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
        ArrayAdapter<String> disabilityobject = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, disability);
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

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*if (staffName.getText().toString().equals("")|| staffCnic.getText().toString().equals(""))
                    {
                        Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Fill Name and Cnic First", Toast.LENGTH_SHORT).show();
                    }
                    else {*/

                Intent view_order_intent = new Intent(M_HumanResourceTeacherPresenceUpdate.this, M_TrainingRecord.class);
                //intent.putExtra("backcnic", staffCnic.getText().toString());
                view_order_intent.putExtra("tid", identity);
                view_order_intent.putExtra("checkvalue", isValueExists);

                   /* view_order_intent.putExtra("tname", staffName.getText().toString());
                    view_order_intent.putExtra("tfather", FatherName.getText().toString());
                    view_order_intent.putExtra("tgender", genderValue);
                    view_order_intent.putExtra("tmarital", maritalValue);
                    view_order_intent.putExtra("tbps", bps.getText().toString());
                    view_order_intent.putExtra("tcnic", staffCnic.getText().toString());
                    view_order_intent.putExtra("tno", staffNo.getText().toString());
                    view_order_intent.putExtra("tacc", agNo.getText().toString());
                    view_order_intent.putExtra("tdob", dateofbirth.getText().toString());
                    view_order_intent.putExtra("tlevel", highestqualificationLevel.getText().toString());
                    view_order_intent.putExtra("tsubject", highestqualificationSubject.getText().toString());
                    view_order_intent.putExtra("tdateoffirst", dateoffirst.getText().toString());
                    view_order_intent.putExtra("tdistrict", DomicileValue);
                    view_order_intent.putExtra("thighest", HighestQualification.getText().toString());
                    view_order_intent.putExtra("tdesigfirst", DesigAsValue);
                    view_order_intent.putExtra("tdesig", SLevel);
                    view_order_intent.putExtra("tuc", UC.getText().toString());
                    view_order_intent.putExtra("tanydisab", Disab);
                    view_order_intent.putExtra("ttypedisab", TypeDisab);
                    view_order_intent.putExtra("tatt", tStatus);
                    view_order_intent.putExtra("tattdetailss", tStatusDetail);
                    view_order_intent.putExtra("tdatesince", txtDate.getText().toString());
                    view_order_intent.putExtra("ttrasnfer", transferOutSchool.getText().toString());*/
                startActivity(view_order_intent);
                finish();
                //}
            }
        });
        String[] presentdesignation = getResources().getStringArray(R.array.teacherdesignation_options);
        ArrayAdapter<String> presentdesignationobject = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, presentdesignation);
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

        String[] dmicile = getResources().getStringArray(R.array.DistrictValuesTeacher);
        ArrayAdapter<String> dmicileadpter = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, dmicile);
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
        String[] designationfirst = getResources().getStringArray(R.array.teacherdesignation_options);
        ArrayAdapter<String> presentdesignationobjec = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, designationfirst);
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
        ArrayAdapter<String> nonpresentdesignationobject = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, nonpresentdesignation);
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
        ArrayAdapter<String> nonpresentdesignationobjec = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, nondesignationfirst);
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
        ArrayAdapter<String> gn = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, genderh);
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
        ArrayAdapter<String> mar = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, marital);
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

        String[] transferIn = getResources().getStringArray(R.array.illegal_occupation);
        ArrayAdapter<String> transferInobject = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, transferIn);
        TransferIn.setAdapter(transferInobject);
        TransferIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                trasnferInStr = TransferIn.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] typedisa = getResources().getStringArray(R.array.typeofdisability);
        ArrayAdapter<String> typedi = new ArrayAdapter<String>(M_HumanResourceTeacherPresenceUpdate.this, android.R.layout.simple_spinner_dropdown_item, typedisa);
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
                DatePickerDialog dialog = new DatePickerDialog(M_HumanResourceTeacherPresenceUpdate.this, date, myCalendar
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
                DatePickerDialog dialog = new DatePickerDialog(M_HumanResourceTeacherPresenceUpdate.this, dateE, myCalendarR
                        .get(Calendar.YEAR), myCalendarR.get(Calendar.MONTH), myCalendarR.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cniclength = staffCnic.getText().toString();
                if (staffName.getText().toString().equals("") || staffCnic.getText().toString().equals("") || staffCnic.getText().toString().equals("")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                } else if (cniclength == null || cniclength.length() < 13) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Cnic is not valid", Toast.LENGTH_SHORT).show();

                } else if (tStatus.equals("Null")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Attendance status cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Absent") && tStatusDetail.equals("Null")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Fill the Absent details", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Absent") && tStatusDetail.equals("Tranfer Order Shown")
                        && tStatusDetail.equals("Tranfer Order Not Shown")
                        && tStatusDetail.equals("Removal from service")
                        && tStatusDetail.equals("Retired") && tStatusDetail.equals("Died")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Fill the Absent details", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Absent") && tStatusDetail.equals("Un-Authorized")
                        && txtDate.getText().toString().equals("")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Select Date!", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Transfer Out") && tStatusDetail.equals("Null")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Fill the transfer details", Toast.LENGTH_SHORT).show();
                } else if (tStatus.equals("Transfer Out") && tStatusDetail.equals("Tranfer Order Shown")
                        && transferOutSchool.getText().toString().equals("")) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Enter School Name!", Toast.LENGTH_SHORT).show();
                } else {
                    //try {
                    checkAlreadyExist(staffCnic.getText().toString(), emis);


                    //}

                            /*catch (Exception e)
                            {
                                e.printStackTrace();
                            }*/

                }
            }

            private void checkAlreadyExist(String s, String emis) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_teacherInfo WHERE teachercnic='" + s + "'" + " AND emis='" + emis + "'", null);
                if (c.moveToFirst() && !staffCnic.getText().toString().equals(tcnic)) {
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "CNIC already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else {

                    TeacherNewDetails schoolinfo = new TeacherNewDetails();
                    schoolinfo.setTeachername(staffName.getText().toString());
                    schoolinfo.setTeacherfathername(FatherName.getText().toString());
                    schoolinfo.setTeachergender(genderValue);
                    schoolinfo.setTeachermarital(maritalValue);
                    schoolinfo.setTeacherbps(bps.getText().toString());
                    schoolinfo.setTeacherno(staffNo.getText().toString());
                    schoolinfo.setTeacheraccountno(agNo.getText().toString());
                    schoolinfo.setTeachercnic(staffCnic.getText().toString());
                    schoolinfo.setTeacherdob(dateofbirth.getText().toString());
                    schoolinfo.setTeacherhighestlevel(highestqualificationLevel.getText().toString());
                    schoolinfo.setTeacherhigestsubject(highestqualificationSubject.getText().toString());
                    schoolinfo.setTeacherdateoffirst(dateoffirst.getText().toString());
                    schoolinfo.setTeacherdistrict(DomicileValue);
                    schoolinfo.setTeacherhighestqualification(HighestQualification.getText().toString());
                    schoolinfo.setTeacherdesigasfirst(DesigAsValue);
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setTeacheruc(UC.getText().toString());
                    schoolinfo.setTeacheranydisablity(Disab);
                    schoolinfo.setTeachertypedisablity(TypeDisab);
                    schoolinfo.setAttendance(tStatus);
                    schoolinfo.setAttendanceTrasnferIn(trasnferInStr);
                    schoolinfo.setTeacherattendancedetails(tStatusDetail);
                    schoolinfo.setAttendancedatesince(txtDate.getText().toString());
                    schoolinfo.setAttendancetrasnferschool(transferOutSchool.getText().toString());
                    databasehandler.updateTeacherNew(schoolinfo, emis, identity);
                    Toast.makeText(M_HumanResourceTeacherPresenceUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_HumanResourceTeacherPresenceUpdate.this, M_TeacherPresenceList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();

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
                    tStatusDetail = "Null";
                    layoutAbsent.setVisibility(View.VISIBLE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutTransferOut.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    absentGroup.clearCheck();
                    transferOutGroup.clearCheck();
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
                    tStatusDetail = "Null";
                    layoutTransferOut.setVisibility(View.VISIBLE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutGroup.clearCheck();
                    absentGroup.clearCheck();
                    bolteachstatusdetail = 0;
                }
                else if (checkedId == R.id.rd_resigned) {
                    tStatus = "Resigned";
                    tStatusDetail = "Null";
                    layoutTransferOut.setVisibility(View.GONE);
                    layoutTransferin.setVisibility(View.GONE);
                    layoutAbsent.setVisibility(View.GONE);
                    layoutNewApoint.setVisibility(View.GONE);
                    bolteachStatus = 1;
                    transferOutSchool.setVisibility(View.GONE);
                    transferOutGroup.clearCheck();
                    absentGroup.clearCheck();
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
                DatePickerDialog dialog = new DatePickerDialog(M_HumanResourceTeacherPresenceUpdate.this, datea, txtdate
                        .get(Calendar.YEAR), txtdate.get(Calendar.MONTH), txtdate.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });


    }

    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcTrainingRecord();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new TrainingRecord();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            // details.setBackCnic(teacherList.get(p).getBackCnic());
            //details.setBackName(teacherList.get(p).getBackName());
            details.setTitle(teacherList.get(p).getTitle());
            details.setYear(teacherList.get(p).getYear());
            details.setDuration(teacherList.get(p).getDuration());
            details.setConductedby(teacherList.get(p).getConductedby());
            details.setAttendedas(teacherList.get(p).getAttendedas());
            addas.add(details);
        }
        //SharedPreferences preferences = getSharedPreferences("new", MODE_PRIVATE);
        //String Hr_name = preferences.getString("Okk", "");
        //cusadapter = new CustomAdapterTrainingRecord(M_HumanResourceTeacherPresenceUpdate.this, addas);
        //if (Hr_name.equals("ok"))
        //{

        //}
        //else {
        //listcontent.setAdapter(cusadapter);
        //}
        /*listcontent.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });*/
        /*listcontent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                final String visitdate = addas.get(position).getTitle();
                final String visitby = addas.get(position).getYear();
                final String duration = addas.get(position).getDuration();
                final String conductedby = addas.get(position).getConductedby();
                final String attendedas = addas.get(position).getAttendedas();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_HumanResourceTeacherPresenceUpdate.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeTrainingRecord(visitdate,visitby,duration,conductedby,attendedas);
                                finish();
                                startActivity(new Intent(M_HumanResourceTeacherPresenceUpdate.this,M_HumanResourceTeacherPresenceUpdate.class));
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
        });*/
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, M_TeacherPresenceList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_training_record, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.trainingrecord:
                if (staffName.getText().toString().equals("")|| staffCnic.getText().toString().equals(""))
                {
                    Toast.makeText(M_HumanResourceTeacherPresence.this, "Fill Name and Cnic First", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(M_HumanResourceTeacherPresence.this, M_TrainingRecord.class);
                    intent.putExtra("backcnic", staffCnic.getText().toString());
                    intent.putExtra("backname", staffName.getText().toString());
                    startActivity(intent);
                    finish();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    protected void onPause() {
        super.onPause();

        /*SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Hr_name", staffName.getText().toString());
        editor.putString("Hr_fathername", FatherName.getText().toString());
        editor.putString("Hr_bps", bps.getText().toString());
        editor.putString("Hr_cellno", staffNo.getText().toString());
        editor.putString("Hr_agNo", agNo.getText().toString());
        editor.putString("Hr_nicno", staffCnic.getText().toString());
        editor.putString("Hr_dob", dateofbirth.getText().toString());
        editor.putString("Hr_level", highestqualificationLevel.getText().toString());
        editor.putString("Hr_subject", highestqualificationSubject.getText().toString());
        editor.putString("Hr_dateofFirst", dateoffirst.getText().toString());
        editor.putString("Hr_district", DomicileValue);
        editor.putString("Hr_highestqualification", HighestQualification.getText().toString());
        editor.putString("Hr_UC", UC.getText().toString());
        editor.putString("Hr_datesince", txtDate.getText().toString());
        editor.putString("Hr_schoolname", transferOutSchool.getText().toString());
        editor.putString("Hr_stafftypespinner", staffTypeStr);
        editor.putString("Hr_teachingdesigpresent", SLevel);
        editor.putString("Hr_teachingdesigfirst", DesigAsValue);
        editor.putString("Hr_nonteachingdesigpresent", PresentNonValue);
        editor.putString("Hr_nonteachingdesigfirst", FirstNonValue);
        editor.putString("Hr_genderspin", genderValue);
        editor.putString("Hr_maritalvalue", maritalValue);
        editor.putString("Hr_disability", Disab);
        editor.putString("Hr_disabilitytype", TypeDisab);
        editor.putString("Hr_status", tStatus);
        editor.apply();*/
    }

    @Override
    protected void onResume() {


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            //isValueExists = extras.getString("clicked");
            identity = extras.getInt("ID");
            String tname = extras.getString("tname");
            String tfather = extras.getString("tfather");
            String Hr_gender = extras.getString("tgender");
            String Hr_marital = extras.getString("tmarital");
            String tbps = extras.getString("tbps");
            tcnic = extras.getString("tcnic");
            String tno = extras.getString("tno");
            String tacc = extras.getString("tacc");
            String tdob = extras.getString("tdob");
            String tlevel = extras.getString("tlevel");
            String tsubject = extras.getString("tsubject");
            String tdateoffirst = extras.getString("tdateoffirst");
            String thighest = extras.getString("thighest");
            String Hr_teachingdesigfirst = extras.getString("tdesigfirst");
            String Hr_teachingdesigpre = extras.getString("tdesig");
            String tuc = extras.getString("tuc");
            String Hr_disablity = extras.getString("tanydisab");
            String Hr_disablitytype = extras.getString("ttypedisab");
            String Hr_district = extras.getString("tdistrict");
            String Hr_status = extras.getString("tatt");
            String Hr_transferIn = extras.getString("tattTrasnferIn");
            String tattdetailss = extras.getString("tattdetailss");
            String tdatesince = extras.getString("tdatesince");
            String ttrasnfer = extras.getString("ttrasnfer");

            if (Hr_disablitytype != null) {
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
            } else {
                typeofdisabilityspinner.setSelection(0, true);
            }
            if (Hr_transferIn != null) {
                if (Hr_transferIn.equals("Yes")) {
                    TransferIn.setSelection(1);
                } else {
                    TransferIn.setSelection(0);
                }
            }
            else {
                TransferIn.setSelection(0);
                }

            if (Hr_status.equals("Absent")) {
                rdstatusAbsent.setChecked(true);
            } else if (Hr_status.equals("Present")) {
                rdstatusPresent.setChecked(true);
            } else if (Hr_status.equals("Transfer Out")) {
                rdstatusTransferout.setChecked(true);
            }
            else if (Hr_status.equals("Resigned")) {
                rdstatusResigned.setChecked(true);
            }
            else {

            }

            if (tattdetailss.equals("Tranfer Order Shown")) {
                rdshownout.setChecked(true);
            } else if (tattdetailss.equals("Tranfer Order Not Shown")) {
                rdntshownout.setChecked(true);
            } else if (tattdetailss.equals("Retired")) {
                transferOutGroup.check(R.id.rd_retired);
            } else if (tattdetailss.equals("Died")) {
                transferOutGroup.check(R.id.rd_died);
            } else if (tattdetailss.equals("Removal from service")) {
                transferOutGroup.check(R.id.rd_rmservice);
            } else if (tattdetailss.equals("Leave")) {
                absentGroup.check(R.id.rd_leave);
            } else if (tattdetailss.equals("Duty")) {
                absentGroup.check(R.id.rd_duty);
            } else if (tattdetailss.equals("Un-Authorized")) {
                absentGroup.check(R.id.rd_unauth);
            } else if (tattdetailss.equals("Late Comer")) {
                absentGroup.check(R.id.rd_latecomer);
            } else if (tattdetailss.equals("Suspended")) {
                absentGroup.check(R.id.rd_suspend);
            } else {

            }
            staffCnic.setText(tcnic);
            staffName.setText(tname);
            FatherName.setText(tfather);
            staffNo.setText(tno);
            bps.setText(tbps);
            agNo.setText(tacc);
            dateofbirth.setText(tdob);
            highestqualificationLevel.setText(tlevel);
            highestqualificationSubject.setText(tsubject);
            dateoffirst.setText(tdateoffirst);
            // DistrictOfDomicile.setText(Hr_district);
            HighestQualification.setText(thighest);
            UC.setText(tuc);
            txtDate.setText(tdatesince);
            transferOutSchool.setText(ttrasnfer);
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
            /*if (Hr_staff.equals("Teaching")) {
                staffType.setSelection(0, true);
            } else if (Hr_staff.equals("Non Teaching")) {
                staffType.setSelection(1, true);
            }*/
            if (Hr_disablity.equals("No")) {
                disabilitySpinner.setSelection(0, true);
            } else if (Hr_disablity.equals("Yes")) {
                disabilitySpinner.setSelection(1, true);
            }


            if (Hr_teachingdesigfirst.equals("Others")) {
                DesignationAs1st.setSelection(35, true);
            } else if (Hr_teachingdesigfirst.equals("Principal(B-20)")) {
                DesignationAs1st.setSelection(1, true);
            } else if (Hr_teachingdesigfirst.equals("Principal(B-19)")) {
                DesignationAs1st.setSelection(2, true);
            } else if (Hr_teachingdesigfirst.equals("Principal(B-18)")) {
                DesignationAs1st.setSelection(3, true);
            } else if (Hr_teachingdesigfirst.equals("Vice Principal(B-18)")) {
                DesignationAs1st.setSelection(4, true);
            } else if (Hr_teachingdesigfirst.equals("AT")) {
                DesignationAs1st.setSelection(5, true);
            } else if (Hr_teachingdesigfirst.equals("CT")) {
                DesignationAs1st.setSelection(6, true);
            } else if (Hr_teachingdesigfirst.equals("D.P.E")) {
                DesignationAs1st.setSelection(7, true);
            } else if (Hr_teachingdesigfirst.equals("DM")) {
                DesignationAs1st.setSelection(8, true);
            } else if (Hr_teachingdesigfirst.equals("Head Master/Mistress")) {
                DesignationAs1st.setSelection(9, true);
            } else if (Hr_teachingdesigfirst.equals("IT Teacher")) {
                DesignationAs1st.setSelection(10, true);
            } else if (Hr_teachingdesigfirst.equals("Senior IT Teacher")) {
                DesignationAs1st.setSelection(11, true);
            } else if (Hr_teachingdesigfirst.equals("Librarian")) {
                DesignationAs1st.setSelection(12, true);
            } else if (Hr_teachingdesigfirst.equals("PET")) {
                DesignationAs1st.setSelection(13, true);
            } else if (Hr_teachingdesigfirst.equals("Principal")) {
                DesignationAs1st.setSelection(14, true);
            } else if (Hr_teachingdesigfirst.equals("PSHT")) {
                DesignationAs1st.setSelection(15, true);
            } else if (Hr_teachingdesigfirst.equals("PST")) {
                DesignationAs1st.setSelection(16, true);
            } else if (Hr_teachingdesigfirst.equals("Qari/Qaria")) {
                DesignationAs1st.setSelection(17, true);
            } else if (Hr_teachingdesigfirst.equals("SS")) {
                DesignationAs1st.setSelection(18, true);
            } else if (Hr_teachingdesigfirst.equals("TT")) {
                DesignationAs1st.setSelection(19, true);
            } else if (Hr_teachingdesigfirst.equals("Vice Principal")) {
                DesignationAs1st.setSelection(20, true);
            } else if (Hr_teachingdesigfirst.equals("computer lab in-charge")) {
                DesignationAs1st.setSelection(21, true);
            } else if (Hr_teachingdesigfirst.equals("SST")) {
                DesignationAs1st.setSelection(22, true);
            } else if (Hr_teachingdesigfirst.equals("SST(Bio-Chemistry)")) {
                DesignationAs1st.setSelection(23, true);
            } else if (Hr_teachingdesigfirst.equals("SST(Math-Physics)")) {
                DesignationAs1st.setSelection(24, true);
            } else if (Hr_teachingdesigfirst.equals("SET")) {
                DesignationAs1st.setSelection(25, true);
            } else if (Hr_teachingdesigfirst.equals("Senior Subject Specialist")) {
                DesignationAs1st.setSelection(26, true);
            } else if (Hr_teachingdesigfirst.equals("Subject Specialist")) {
                DesignationAs1st.setSelection(27, true);
            } else if (Hr_teachingdesigfirst.equals("Senior AT")) {
                DesignationAs1st.setSelection(28, true);
            } else if (Hr_teachingdesigfirst.equals("Senior CT")) {
                DesignationAs1st.setSelection(29, true);
            } else if (Hr_teachingdesigfirst.equals("Senior DM")) {
                DesignationAs1st.setSelection(30, true);
            } else if (Hr_teachingdesigfirst.equals("Senior PET")) {
                DesignationAs1st.setSelection(31, true);
            } else if (Hr_teachingdesigfirst.equals("Senior PST")) {
                DesignationAs1st.setSelection(32, true);
            } else if (Hr_teachingdesigfirst.equals("Senior Qari")) {
                DesignationAs1st.setSelection(33, true);
            } else if (Hr_teachingdesigfirst.equals("Senior TT")) {
                DesignationAs1st.setSelection(34, true);
            } else {
                DesignationAs1st.setSelection(0, true);
            }


            if (Hr_teachingdesigpre.equals("Others")) {
                teachingdesignationspresentspinner.setSelection(35, true);
            } else if (Hr_teachingdesigpre.equals("Principal(B-20)")) {
                teachingdesignationspresentspinner.setSelection(1, true);
            } else if (Hr_teachingdesigpre.equals("Principal(B-19)")) {
                teachingdesignationspresentspinner.setSelection(2, true);
            } else if (Hr_teachingdesigpre.equals("Principal(B-18)")) {
                teachingdesignationspresentspinner.setSelection(3, true);
            } else if (Hr_teachingdesigpre.equals("Vice Principal(B-18)")) {
                teachingdesignationspresentspinner.setSelection(4, true);
            } else if (Hr_teachingdesigpre.equals("AT")) {
                teachingdesignationspresentspinner.setSelection(5, true);
            } else if (Hr_teachingdesigpre.equals("CT")) {
                teachingdesignationspresentspinner.setSelection(6, true);
            } else if (Hr_teachingdesigpre.equals("D.P.E")) {
                teachingdesignationspresentspinner.setSelection(7, true);
            } else if (Hr_teachingdesigpre.equals("DM")) {
                teachingdesignationspresentspinner.setSelection(8, true);
            } else if (Hr_teachingdesigpre.equals("Head Master/Mistress")) {
                teachingdesignationspresentspinner.setSelection(9, true);
            } else if (Hr_teachingdesigpre.equals("IT Teacher")) {
                teachingdesignationspresentspinner.setSelection(10, true);
            } else if (Hr_teachingdesigpre.equals("Senior IT Teacher")) {
                teachingdesignationspresentspinner.setSelection(11, true);
            } else if (Hr_teachingdesigpre.equals("Librarian")) {
                teachingdesignationspresentspinner.setSelection(12, true);
            } else if (Hr_teachingdesigpre.equals("PET")) {
                teachingdesignationspresentspinner.setSelection(13, true);
            } else if (Hr_teachingdesigpre.equals("Principal")) {
                teachingdesignationspresentspinner.setSelection(14, true);
            } else if (Hr_teachingdesigpre.equals("PSHT")) {
                teachingdesignationspresentspinner.setSelection(15, true);
            } else if (Hr_teachingdesigpre.equals("PST")) {
                teachingdesignationspresentspinner.setSelection(16, true);
            } else if (Hr_teachingdesigpre.equals("Qari/Qaria")) {
                teachingdesignationspresentspinner.setSelection(17, true);
            } else if (Hr_teachingdesigpre.equals("SS")) {
                teachingdesignationspresentspinner.setSelection(18, true);
            } else if (Hr_teachingdesigpre.equals("TT")) {
                teachingdesignationspresentspinner.setSelection(19, true);
            } else if (Hr_teachingdesigpre.equals("Vice Principal")) {
                teachingdesignationspresentspinner.setSelection(20, true);
            } else if (Hr_teachingdesigpre.equals("computer lab in-charge")) {
                teachingdesignationspresentspinner.setSelection(21, true);
            } else if (Hr_teachingdesigpre.equals("SST")) {
                teachingdesignationspresentspinner.setSelection(22, true);
            } else if (Hr_teachingdesigpre.equals("SST(Bio-Chemistry)")) {
                teachingdesignationspresentspinner.setSelection(23, true);
            } else if (Hr_teachingdesigpre.equals("SST(Math-Physics)")) {
                teachingdesignationspresentspinner.setSelection(24, true);
            } else if (Hr_teachingdesigpre.equals("SET")) {
                teachingdesignationspresentspinner.setSelection(25, true);
            } else if (Hr_teachingdesigpre.equals("Senior Subject Specialist")) {
                teachingdesignationspresentspinner.setSelection(26, true);
            } else if (Hr_teachingdesigpre.equals("Subject Specialist")) {
                teachingdesignationspresentspinner.setSelection(27, true);
            } else if (Hr_teachingdesigpre.equals("Senior AT")) {
                teachingdesignationspresentspinner.setSelection(28, true);
            } else if (Hr_teachingdesigpre.equals("Senior CT")) {
                teachingdesignationspresentspinner.setSelection(29, true);
            } else if (Hr_teachingdesigpre.equals("Senior DM")) {
                teachingdesignationspresentspinner.setSelection(30, true);
            } else if (Hr_teachingdesigpre.equals("Senior PET")) {
                teachingdesignationspresentspinner.setSelection(31, true);
            } else if (Hr_teachingdesigpre.equals("Senior PST")) {
                teachingdesignationspresentspinner.setSelection(32, true);
            } else if (Hr_teachingdesigpre.equals("Senior Qari")) {
                teachingdesignationspresentspinner.setSelection(33, true);
            } else if (Hr_teachingdesigpre.equals("Senior TT")) {
                teachingdesignationspresentspinner.setSelection(34, true);
            } else {
                teachingdesignationspresentspinner.setSelection(0, true);
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
            }
            else if (Hr_district.equals("FATA")) {
                DistrictOfDomicile.setSelection(25, true);
            }
            else if (Hr_district.equals("FR Region")) {
                DistrictOfDomicile.setSelection(26, true);
            }else {
                DistrictOfDomicile.setSelection(0, true);
            }

        }

        super.onResume();

    }
}
