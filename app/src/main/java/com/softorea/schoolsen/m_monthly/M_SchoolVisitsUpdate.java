package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsSchoolVisit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_SchoolVisitsUpdate extends Activity {

    Button add, clear;
    TextView visitDate;
    EditText specifyothertext;
    DatabaseHandler databasehandler;
    LinearLayout otherslayout;
    Spinner visitBy;
    String SLevel = "";
    String emis;
    int identity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_schoolvisitupdate);
        databasehandler = new DatabaseHandler(M_SchoolVisitsUpdate.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        otherslayout = (LinearLayout) findViewById(R.id.specifyothers);
        specifyothertext = (EditText) findViewById(R.id.othertext);
        visitDate = (TextView) findViewById(R.id.visit_date);
        visitBy = (Spinner) findViewById(R.id.visit_designation);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = specifyothertext.getText().toString();
                if (visitDate.getText().toString().equals("")) {
                    Toast.makeText(M_SchoolVisitsUpdate.this, "Fill the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    DetailsSchoolVisit schoolinfo = new DetailsSchoolVisit();
                    schoolinfo.setVisitby(SLevel);
                    schoolinfo.setVisitdate(visitDate.getText().toString());
                    schoolinfo.setDesignationother(specifyothertext.getText().toString());
                    databasehandler.updateschoolVisity(schoolinfo, emis, identity);
                    Toast.makeText(M_SchoolVisitsUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_SchoolVisitsUpdate.this, M_SchoolVisitList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitDate.setText("Select Date");
                visitBy.setSelection(0, true);

            }
        });

        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.designationoptionss);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_SchoolVisitsUpdate.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        visitBy.setAdapter(schoolLevelAdapter);
        visitBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = visitBy.getSelectedItem().toString();
                if (SLevel.equals("Others")) {
                    otherslayout.setVisibility(View.VISIBLE);
                } else {
                    otherslayout.setVisibility(View.GONE);
                }

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

                visitDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        visitDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_SchoolVisitsUpdate.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

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
            String namee = extras.getString("visitby");
            String vseats = extras.getString("visitdate");
            String by = extras.getString("other");
            visitDate.setText(vseats);
            specifyothertext.setText(by);
            if (namee.equals("DEO")) {
                visitBy.setSelection(1, true);
            } else if (namee.equals("DDEO")) {
                visitBy.setSelection(2, true);
            } else if (namee.equals("SDEO")) {
                visitBy.setSelection(3, true);
            } else if (namee.equals("ASDEO")) {
                visitBy.setSelection(4, true);
            }
            else if (namee.equals("Others")) {
                visitBy.setSelection(5, true);
            }else {
                visitBy.setSelection(0, true);
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M_SchoolVisitsUpdate.this, M_SchoolVisitList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}
