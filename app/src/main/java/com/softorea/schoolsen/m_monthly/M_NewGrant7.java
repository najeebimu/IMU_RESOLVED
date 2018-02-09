package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.allegro.finance.tradukisto.ValueConverters;

/**
 * Created by Softorea on 11/6/2017.
 */

public class M_NewGrant7 extends Activity {

    String fundStr = "";
    String amountCorrectStr="";
    String gradingStr="";
    String recordShownStrr="";
    String recordShownStr="";
    String workCompleteStr="";
    String workGradingStr="";
    Spinner fundspinner,amountcorrect;
    EditText amountenter;
    LinearLayout firstlayout,workstartedlay;
    TextView amountenterinwords;
    Spinner workstarted;
    String workStr="";
    LinearLayout mainlay;

    TextView grantamount, grantyear, startdate, mainGrantTitle;
    Spinner typeSpinner;
    String SLevel;
    EditText financial, remarks;
    LinearLayout gradingLayout;
    RadioGroup recordShowngroup, workCompletedGroup, workGradingGroup;
    RadioButton recordYes, recordNo, workCompleteYes, workCompleteNo, gradingA, gradingB, gradingC, gradingD;
    Button back, next;
    Grant grant;
    TextView labeltext;
    String emis;
    DatabaseHandler databasehandler;

    String typecolumn, yearcolumn, amountcolumn;
    int grantIdcolumn;
    LinearLayout finalGradingLay;
    Spinner gradingSpinner;

    Spinner recordShownSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_conditional_grant7);
        amountenterinwords = (TextView) findViewById(R.id.txt_amountenter);
        amountcorrect = (Spinner) findViewById(R.id.amountcorrect);
        fundspinner = (Spinner) findViewById(R.id.fundsreceived);
        firstlayout = (LinearLayout) findViewById(R.id.firstLayout);
        workstartedlay = (LinearLayout) findViewById(R.id.occupied_bylayout);
        amountenter = (EditText) findViewById(R.id.amountreceivedenter);


        recordShownSpinner = (Spinner) findViewById(R.id.recordshownspinner);

        gradingSpinner = (Spinner) findViewById(R.id.gradingno);
        finalGradingLay = (LinearLayout) findViewById(R.id.gradingLay);
        workstarted = (Spinner) findViewById(R.id.workstarted);
        mainlay = (LinearLayout) findViewById(R.id.mainlayout);
        mainGrantTitle = (TextView) findViewById(R.id.grant_title);
        grantamount = (TextView) findViewById(R.id.txt_amount);
        grantyear = (TextView) findViewById(R.id.txt_grant_year);
        startdate = (TextView) findViewById(R.id.btn_grant_date);
        labeltext = (TextView) findViewById(R.id.txt_financial);
        typeSpinner = (Spinner) findViewById(R.id.sp_type);
        financial = (EditText) findViewById(R.id.edit_financial);
        remarks = (EditText) findViewById(R.id.remarks);
        gradingLayout = (LinearLayout) findViewById(R.id.grading_layout);
        recordShowngroup = (RadioGroup) findViewById(R.id.rg_record_shown);
        workCompletedGroup = (RadioGroup) findViewById(R.id.rg_work_completed);
        workGradingGroup = (RadioGroup) findViewById(R.id.rg_work_grading);
        recordYes = (RadioButton) findViewById(R.id.rd_record_shown);
        recordNo = (RadioButton) findViewById(R.id.rg_record_not_shown);
        workCompleteYes = (RadioButton) findViewById(R.id.rd_yes);
        workCompleteNo = (RadioButton) findViewById(R.id.rd_no);
        gradingA = (RadioButton) findViewById(R.id.rd_a);
        gradingB = (RadioButton) findViewById(R.id.rd_b);
        gradingC = (RadioButton) findViewById(R.id.rd_c);
        gradingD = (RadioButton) findViewById(R.id.rd_d);
        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next);

        String[] fundd = getResources().getStringArray(R.array.illegal_occupation);
        ArrayAdapter<String> fundadapter = new ArrayAdapter<String>(M_NewGrant7.this, android.R.layout.simple_spinner_dropdown_item, fundd);
        fundspinner.setAdapter(fundadapter);
        fundspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                fundStr = fundspinner.getSelectedItem().toString();
                if (fundStr.equals("No"))
                {
                    mainlay.setVisibility(View.GONE);
                    firstlayout.setVisibility(View.GONE);
                    workstartedlay.setVisibility(View.GONE);
                    workstarted.setSelection(0);
                    amountcorrect.setSelection(0);
                    amountenter.setText("");
                    startdate.setText("");
                    typeSpinner.setSelection(0);
                    gradingSpinner.setSelection(0);
                    financial.setText("");
                    remarks.setText("");
                    recordShownSpinner.setSelection(0);
                }
                else
                {
                    firstlayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] amountcorrects = getResources().getStringArray(R.array.checkconditions);
        ArrayAdapter<String> amountcorrectsatr = new ArrayAdapter<String>(M_NewGrant7.this, android.R.layout.simple_spinner_dropdown_item, amountcorrects);
        amountcorrect.setAdapter(amountcorrectsatr);
        amountcorrect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                amountCorrectStr = amountcorrect.getSelectedItem().toString();
                if (amountCorrectStr.equals("Yes"))
                {
                    amountenter.setVisibility(View.GONE);
                    amountenter.setText("");
                    workstartedlay.setVisibility(View.VISIBLE);
                }
                else if (amountCorrectStr.equals("No"))
                {
                    amountenter.setVisibility(View.VISIBLE);
                    workstartedlay.setVisibility(View.VISIBLE);
                }
                else
                {
                    amountenter.setVisibility(View.GONE);
                    amountenter.setText("");
                    workstartedlay.setVisibility(View.GONE);
                    workstarted.setSelection(0);
                    mainlay.setVisibility(View.GONE);
                    startdate.setText("");
                    typeSpinner.setSelection(0);
                    gradingSpinner.setSelection(0);
                    financial.setText("");
                    remarks.setText("");
                    recordShownSpinner.setSelection(0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        String[] recordsh = getResources().getStringArray(R.array.illegal_occupation);
        ArrayAdapter<String> recordadpatr = new ArrayAdapter<String>(M_NewGrant7.this, android.R.layout.simple_spinner_dropdown_item, recordsh);
        recordShownSpinner.setAdapter(recordadpatr);
        recordShownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                recordShownStrr = recordShownSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] work = getResources().getStringArray(R.array.illegal_occupation);
        ArrayAdapter<String> workadapter = new ArrayAdapter<String>(M_NewGrant7.this, android.R.layout.simple_spinner_dropdown_item, work);
        workstarted.setAdapter(workadapter);

        workstarted.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                workStr = workstarted.getSelectedItem().toString();
                if (workStr.equals("Yes"))
                {
                    mainlay.setVisibility(View.VISIBLE);
                }
                else
                {
                    mainlay.setVisibility(View.GONE);
                    startdate.setText("");
                    typeSpinner.setSelection(0);
                    gradingSpinner.setSelection(0);
                    financial.setText("");
                    remarks.setText("");
                    recordShownSpinner.setSelection(0);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] workgr = getResources().getStringArray(R.array.grading);
        ArrayAdapter<String> workgradapter = new ArrayAdapter<String>(M_NewGrant7.this, android.R.layout.simple_spinner_dropdown_item, workgr);
        gradingSpinner.setAdapter(workgradapter);
        gradingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                gradingStr = gradingSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        databasehandler = new DatabaseHandler(M_NewGrant7.this);
        //grant =  databasehandler.getgrantt(emis);

        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase db = database.getReadableDatabase();
        String queryFac1 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                + " AND " + "w_grant_id='" + "7" + "'";
        Cursor c = db.rawQuery(queryFac1, null);
        if (c.moveToFirst()) {
            do {
                typecolumn = c.getString(c.getColumnIndex("w_type"));
                yearcolumn = c.getString(c.getColumnIndex("w_year"));
                amountcolumn = c.getString(c.getColumnIndex("w_amount"));

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        grantyear.setText(yearcolumn);
        grantamount.setText(amountcolumn);
        mainGrantTitle.setText(typecolumn);

        String[] science = getResources().getStringArray(R.array.grant_other);
        ArrayAdapter<String> sciencee = new ArrayAdapter<String>(M_NewGrant7.this, android.R.layout.simple_spinner_dropdown_item, science);
        typeSpinner.setAdapter(sciencee);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = typeSpinner.getSelectedItem().toString();
                /*if (SLevel.equals("Completed") || SLevel.equals("Installed"))
                {
                    finalGradingLay.setVisibility(View.VISIBLE);
                }
                else
                {
                    finalGradingLay.setVisibility(View.GONE);
                    gradingSpinner.setSelection(0);
                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        Grant grantinner = databasehandler.getgrant(emis, typecolumn, yearcolumn, amountcolumn, 7);

        try {
            String startDate_str = grantinner.getStartDate();
            String type_str = grantinner.getType();
            String financial_str = grantinner.getFinancial();
            String workstatus_str = grantinner.getWorkStatus();
            String workgrading_str = grantinner.getWorkGrading();
            String remarks_str = grantinner.getRemarks();
            String recordstatus_str = grantinner.getRecordStatus();

            String funds = grantinner.getFundsReceived();
            String amountcorrectt = grantinner.getAmmountCorrect();
            String amountenterr = grantinner.getAmmountEnter();

            if (!startDate_str.equals("Null") && !startDate_str.equals("")) {
                startdate.setText(startDate_str);
            }
            if (!financial_str.equals("Null")
                    && !financial_str.equals("")) {
                financial.setText(financial_str);
            }
            if (!remarks_str.equals("Null") && !remarks_str.equals("Null")) {
                remarks.setText(remarks_str);
            }

            if (!amountenterr.equals("Null") && !amountenterr.equals("Null")) {
                amountenter.setText(amountenterr);
            }

            if (type_str != null) {
                if (type_str.equals("Installed")) {
                    typeSpinner.setSelection(1, true);
                } else if (type_str.equals("Not Installed")) {
                    typeSpinner.setSelection(2, true);
                } else {
                    typeSpinner.setSelection(0, true);
                }
            } else {
                typeSpinner.setSelection(0, true);
            }

            if (funds != null) {
                if (funds.equals("Yes")) {
                    //workCompleteYes.setChecked(true);
                    fundspinner.setSelection(1);
                    firstlayout.setVisibility(View.VISIBLE);
                    //workstartedlay.setVisibility(View.VISIBLE);
                    //mainlay.setVisibility(View.VISIBLE);
                } else
                {
                    //workCompleteNo.setChecked(true);
                    fundspinner.setSelection(0);
                    mainlay.setVisibility(View.GONE);
                    firstlayout.setVisibility(View.GONE);
                    workstartedlay.setVisibility(View.GONE);
                }
            }

            if (amountcorrectt != null) {
                if (amountcorrectt.equals("None")) {
                    //workCompleteYes.setChecked(true);
                    amountcorrect.setSelection(0);
                    amountenter.setVisibility(View.GONE);
                    workstartedlay.setVisibility(View.GONE);
                    //workstartedlay.setVisibility(View.VISIBLE);
                    //mainlay.setVisibility(View.VISIBLE);
                }
                else if (amountcorrectt.equals("No")) {
                    //workCompleteYes.setChecked(true);
                    amountcorrect.setSelection(2);
                    amountenter.setVisibility(View.VISIBLE);
                    workstartedlay.setVisibility(View.VISIBLE);
                    //workstartedlay.setVisibility(View.VISIBLE);
                    //mainlay.setVisibility(View.VISIBLE);
                }
                else
                {
                    amountcorrect.setSelection(1);
                    //workCompleteNo.setChecked(true);
                    amountenter.setVisibility(View.GONE);
                    workstartedlay.setVisibility(View.VISIBLE);
                }
            }

            if (workstatus_str != null) {
                if (workstatus_str.equals("Yes")) {
                    //workCompleteYes.setChecked(true);
                    workstarted.setSelection(1);
                    mainlay.setVisibility(View.VISIBLE);
                } else{
                    //workCompleteNo.setChecked(true);
                    mainlay.setVisibility(View.GONE);
                    workstarted.setSelection(0);
                }
            }
            if (recordstatus_str != null) {
                if (recordstatus_str.equals("Yes")) {
                    recordShownSpinner.setSelection(1);
                } else {
                    recordShownSpinner.setSelection(0);
                }
            } else {
                recordShownSpinner.setSelection(0);
            }
            if (workgrading_str != null) {
                if (workgrading_str.equals("A")) {
                    gradingSpinner.setSelection(1);
                } else if (workgrading_str.equals("B")) {
                    gradingSpinner.setSelection(2);
                } else if (workgrading_str.equals("C")) {
                    gradingSpinner.setSelection(3);
                } else if (workgrading_str.equals("D")) {
                    gradingSpinner.setSelection(4);
                } else {
                    gradingSpinner.setSelection(0);
                }
            } else {
                gradingSpinner.setSelection(0);
            }

        } catch (IndexOutOfBoundsException e) {

        }

        financial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValueConverters moneyConverters = ValueConverters.ENGLISH_INTEGER;
                try {
                    labeltext.setText(moneyConverters.asWords(Integer.parseInt(s.toString())));
                } catch (Exception e) {
                    labeltext.setText("");
                }
            }
        });

        amountenter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValueConverters moneyConverters = ValueConverters.ENGLISH_INTEGER;
                try {
                    amountenterinwords.setText(moneyConverters.asWords(Integer.parseInt(s.toString())));
                } catch (Exception e) {
                    amountenterinwords.setText("");
                }
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
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                startdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        startdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_NewGrant7.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        recordShowngroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_record_shown) {
                    recordShownStr = "Yes";
                } else if (checkedId == R.id.rg_record_not_shown) {
                    recordShownStr = "No";
                }

            }
        });

        workCompletedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_yes) {
                    workCompleteStr = "Completed";
                    gradingLayout.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rd_no) {
                    workCompleteStr = "NotCompleted";
                    gradingLayout.setVisibility(View.GONE);
                }

            }
        });

        workGradingGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_a) {
                    workGradingStr = "A";
                } else if (checkedId == R.id.rd_b) {
                    workGradingStr = "B";
                } else if (checkedId == R.id.rd_c) {
                    workGradingStr = "C";
                } else if (checkedId == R.id.rd_d) {
                    workGradingStr = "D";
                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grant = new Grant();

                grant.setEmisCode(emis);
                grant.setGrantType(typecolumn);
                grant.setYear(yearcolumn);
                grant.setAmount(amountcolumn);
                grant.setFaceCode(7);
                grant.setStartDate(startdate.getText().toString());
                grant.setFinancial(financial.getText().toString());
                grant.setType(SLevel);
                grant.setWorkStatus(workStr);
                grant.setWorkGrading(gradingStr);
                grant.setRemarks(remarks.getText().toString());
                grant.setRecordStatus(recordShownStrr);
                grant.setFundsReceived(fundStr);
                grant.setAmmountCorrect(amountCorrectStr);
                grant.setAmmountEnter(amountenter.getText().toString());
                databasehandler.saveGrant(grant);

                SQLiteDatabase db = databasehandler.getReadableDatabase();
                String queryFac6 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "6" + "'";
                String queryFac5 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "5" + "'";
                String queryFac4 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "4" + "'";
                String queryFac3 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "3" + "'";
                String queryFac2 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "2" + "'";
                String queryFac1 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "1" + "'";

                Cursor cfac6 = db.rawQuery(queryFac6, null);
                Cursor cfac5 = db.rawQuery(queryFac5, null);
                Cursor cfac4 = db.rawQuery(queryFac4, null);
                Cursor cfac3 = db.rawQuery(queryFac3, null);
                Cursor cfac2 = db.rawQuery(queryFac2, null);
                Cursor cfac1 = db.rawQuery(queryFac1, null);

                if (cfac6.moveToFirst()) {
                    startActivity(new Intent(M_NewGrant7.this, M_NewGrant6.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac5.moveToFirst()) {
                    startActivity(new Intent(M_NewGrant7.this, M_NewGrant5.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac4.moveToFirst()) {
                    startActivity(new Intent(M_NewGrant7.this, M_NewGrant4.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac3.moveToFirst()) {
                    startActivity(new Intent(M_NewGrant7.this, M_NewGrant3.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac2.moveToFirst()) {
                    startActivity(new Intent(M_NewGrant7.this, M_NewGrant2.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac1.moveToFirst()) {
                    startActivity(new Intent(M_NewGrant7.this, M_NewGrant1.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {

                    startActivity(new Intent(M_NewGrant7.this, M_SchoolFunctioning.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = grantamount.getText().toString();
                String s1 = financial.getText().toString();

                if (fundStr.equals("Yes") && amountCorrectStr.equals("None"))
                {
                    Toast.makeText(M_NewGrant7.this, "Please select amount correct", Toast.LENGTH_SHORT).show();
                }

                else if (fundStr.equals("Yes") && workStr.equals("Yes") && startdate.getText().toString().equals(""))
                {
                    Toast.makeText(M_NewGrant7.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }
                else if (fundStr.equals("Yes") && (workStr.equals("Yes") && SLevel.equals("Select Type")))
                {
                    Toast.makeText(M_NewGrant7.this, "Please Specify Physical Progress type", Toast.LENGTH_SHORT).show();
                }
                /*else if (workStr.equals("Yes") && SLevel.equals("Completed") && gradingStr.equals("None"))
                {
                    Toast.makeText(M_NewGrant7.this, "Work Grading is missing", Toast.LENGTH_SHORT).show();
                }
                else if (workStr.equals("Yes") && SLevel.equals("Installed") && gradingStr.equals("None"))
                {
                    Toast.makeText(M_NewGrant7.this, "Work Grading is missing", Toast.LENGTH_SHORT).show();
                }*/
                else if (workStr.equals("Yes") && s1.equals(""))
                {
                    Toast.makeText(M_NewGrant7.this, "Financial Progress is missing", Toast.LENGTH_SHORT).show();
                }
                else if(!s1.equals("") && Integer.parseInt(s1) > Integer.parseInt(s))
                {
                    Toast.makeText(M_NewGrant7.this, "Financial amount cannot be greater than amount granted!", Toast.LENGTH_SHORT).show();
                }
                else {
                    grant = new Grant();

                    grant.setEmisCode(emis);
                    grant.setGrantType(typecolumn);
                    grant.setYear(yearcolumn);
                    grant.setAmount(amountcolumn);
                    grant.setFaceCode(7);
                    grant.setStartDate(startdate.getText().toString());
                    grant.setFinancial(financial.getText().toString());
                    grant.setType(SLevel);
                    grant.setWorkStatus(workStr);
                    grant.setWorkGrading(gradingStr);
                    grant.setRemarks(remarks.getText().toString());
                    grant.setRecordStatus(recordShownStrr);
                    grant.setFundsReceived(fundStr);
                    grant.setAmmountCorrect(amountCorrectStr);
                    grant.setAmmountEnter(amountenter.getText().toString());
                    databasehandler.saveGrant(grant);

                    SQLiteDatabase db = databasehandler.getReadableDatabase();
                    String queryFac8 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                            + " AND " + "w_grant_id='" + "8" + "'";

                    Cursor cfac8 = db.rawQuery(queryFac8, null);
                    if (cfac8.moveToFirst()) {
                        startActivity(new Intent(M_NewGrant7.this, M_NewGrant8.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else {
                        startActivity(new Intent(M_NewGrant7.this, M_VacantPositionsList.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_NewGrant7.this);
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
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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
