package com.softorea.schoolsen.gcsschools;

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

public class M_SchoolVisits extends Activity {

    Button add, clear;
    TextView visitDate;
    EditText specifyothertext;
    DatabaseHandler databasehandler;
    LinearLayout otherslayout;
    Spinner visitBy;
    String SLevel = "";
    String emis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efragment_schoolvisit);
        databasehandler = new DatabaseHandler(M_SchoolVisits.this);
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
                if (visitDate.getText().toString().equals("")) {
                    Toast.makeText(M_SchoolVisits.this, "Fill the fields!", Toast.LENGTH_SHORT).show();
                } else {

                    String Visitdate = visitDate.getText().toString();
                    String Visitother = specifyothertext.getText().toString();
                    databasehandler.saveSchoolVisitBy(new DetailsSchoolVisit(emis, Visitdate, SLevel, Visitother));
                    Toast.makeText(M_SchoolVisits.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_SchoolVisits.this, M_SchoolVisitList.class));
                    finish();
                    visitBy.setSelection(0, true);
                    SLevel = visitBy.getSelectedItem().toString();
                    visitDate.setText("");
                    specifyothertext.setText("");
                    //checkAlreadyExist(SLevel,visitDate.getText().toString(),specifyothertext.getText().toString());


                }
            }

            /*private void checkAlreadyExist(String sLevel, String s,String others) {

                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_schoolvisitby WHERE visitby='" + sLevel + "'" +"AND visitdate='"+ s + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(M_SchoolVisits.this, "Data already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                } else {
                    String Visitdate = visitDate.getText().toString();
                    String Visitother = specifyothertext.getText().toString();
                    databasehandler.saveSchoolVisitBy(new DetailsSchoolVisit(emis,Visitdate, SLevel,Visitother));
                    Toast.makeText(M_SchoolVisits.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_SchoolVisits.this,M_SchoolVisitList.class));
                    finish();
                    visitBy.setSelection(0,true);
                    SLevel =visitBy.getSelectedItem().toString();
                    visitDate.setText("");
                    specifyothertext.setText("");
                }
            }*/
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitDate.setText("Select Date");
                visitBy.setSelection(0, true);

            }
        });

        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.administrative_options_two);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_SchoolVisits.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
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
                    specifyothertext.setText("");
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
                DatePickerDialog dialog = new DatePickerDialog(M_SchoolVisits.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("schoolvisitby", SLevel);
        editor.putString("schoolvisitdate", visitDate.getText().toString());
        editor.putString("schoolvisitOTHERS", specifyothertext.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String namee = preferences.getString("schoolvisitby", "");
        String cnicc = preferences.getString("schoolvisitdate", "");
        String oth = preferences.getString("schoolvisitOTHERS", "");
        specifyothertext.setText(oth);
        visitDate.setText(cnicc);
        if (namee.equals("Others")) {
            visitBy.setSelection(4, true);
        } else if (namee.equals("Field Assistant")) {
            visitBy.setSelection(3, true);
        } else if (namee.equals("DPO")) {
            visitBy.setSelection(2, true);
        } else if (namee.equals("EEF")) {
            visitBy.setSelection(1, true);
        } else {
            visitBy.setSelection(0, true);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M_SchoolVisits.this, M_SchoolVisitList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}
