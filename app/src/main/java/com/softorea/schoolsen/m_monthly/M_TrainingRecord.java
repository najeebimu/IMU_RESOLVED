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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.TrainingRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by arsla on 13/10/2017.
 */

public class M_TrainingRecord extends Activity {

    EditText title, duration, conductedby;
    Button Save;
    TextView bhs;
    Spinner attendedas;
    DatabaseHandler databasehandler;
    String emis;
    String SLevel;
    String backName, backCnic, backAccNo;
    String isCLicked;
    int identity;
    String label = " ";
    String name, cnic;
    Spinner teacherlist;

    String tname, tfname, tgender, tmarital, tbps, tcnic, tno, tacc, tdob, tlevel, tsubject, tdateoffirst, tdistrict, thighest, tdesigfirst,
            tdesig, tuc, tanydisab, ttypedisab, tatt, tattdetailss, tdatesince, ttrasnfer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_traininigrecord);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        databasehandler = new DatabaseHandler(M_TrainingRecord.this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            identity = extras.getInt("tid");

        }
        teacherlist = (Spinner) findViewById(R.id.teacherlist);
        title = (EditText) findViewById(R.id.nametitle);
        bhs = (TextView) findViewById(R.id.selectyear);
        duration = (EditText) findViewById(R.id.duration);
        conductedby = (EditText) findViewById(R.id.conductedby);
        attendedas = (Spinner) findViewById(R.id.attendedas);
        Save = (Button) findViewById(R.id.save);
        loadSpinnerData();

        final Calendar myCalendarr = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener datee = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarr.set(Calendar.YEAR, year);
                myCalendarr.set(Calendar.MONTH, monthOfYear);
                myCalendarr.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                int year = myCalendarr.get(Calendar.YEAR);
                bhs.setText("" + year);
            }

        };


        bhs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_TrainingRecord.this, datee, myCalendarr
                        .get(Calendar.YEAR), myCalendarr.get(Calendar.MONTH), myCalendarr.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

            }
        });
        String[] g = {"Trainer", "Trainee"};
        ArrayAdapter<String> gAdapter = new ArrayAdapter<String>(M_TrainingRecord.this, android.R.layout.simple_spinner_dropdown_item, g);
        attendedas.setAdapter(gAdapter);
        attendedas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = attendedas.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        teacherlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                label = parent.getItemAtPosition(position).toString();
                //String name = label.substring(label.indexOf(':') - 1);
                //String cnic = label.substring(label.indexOf(':') + 1);
                String[] parts = label.split(":");
                name = parts[0];
                cnic = parts[1];


                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), name + " " + cnic, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (label.equals(" ")) {
                    Toast.makeText(M_TrainingRecord.this, "Please add teacher first", Toast.LENGTH_SHORT).show();
                } else if (title.getText().toString().equals("") || duration.getText().toString().equals("")
                        || conductedby.getText().toString().equals("")) {
                    Toast.makeText(M_TrainingRecord.this, "Fill the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    String titlee = title.getText().toString();
                    String yearr = bhs.getText().toString();
                    //String yearr = "";
                    String durationn = duration.getText().toString();
                    String conductedbyy = conductedby.getText().toString();
                    String attendedass = SLevel;

                    databasehandler.saveTrainingRecord(new TrainingRecord(emis, name, cnic, titlee, yearr, durationn, conductedbyy, attendedass, identity));
                    Toast.makeText(M_TrainingRecord.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    title.setText("");
                    duration.setText("");
                    conductedby.setText("");
                    attendedas.setSelection(0, true);
                    bhs.setText("Select year");
                    startActivity(new Intent(M_TrainingRecord.this, M_TrainingRecordList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();


                }
            }
        });
    }

    private void loadSpinnerData() {
        // database handler
        //DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = databasehandler.getAllLabels(emis);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        teacherlist.setAdapter(dataAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M_TrainingRecord.this, M_TrainingRecordList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Training_title", title.getText().toString());
        editor.putString("Training_year", bhs.getText().toString());
        editor.putString("Training_duration", duration.getText().toString());
        editor.putString("Training_conductedby", conductedby.getText().toString());
        editor.putString("Training_attendedas", SLevel);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String Hr_name = preferences.getString("Training_title", "");
        String Hr_fathername = preferences.getString("Training_year", "");
        String Hr_cellno = preferences.getString("Training_duration", "");
        String Hr_bps = preferences.getString("Training_conductedby", "");
        String Hr_agNo = preferences.getString("Training_attendedas", "");
        title.setText(Hr_name);
        bhs.setText(Hr_fathername);
        duration.setText(Hr_cellno);
        conductedby.setText(Hr_bps);
        //attendedas.setText(Hr_agNo);

    }


}
