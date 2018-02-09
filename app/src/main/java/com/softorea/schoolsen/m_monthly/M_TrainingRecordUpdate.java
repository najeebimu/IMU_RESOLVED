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
import java.util.Locale;

/**
 * Created by arsla on 13/10/2017.
 */

public class M_TrainingRecordUpdate extends Activity {

    EditText name, cnic, title, duration, conductedby;
    Button Save;
    TextView bhs;
    Spinner attendedas;
    DatabaseHandler databasehandler;
    String emis;
    String SLevel;
    int identity;
    String backName, backCnic, backAccNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_traininigrecordupdate);
        databasehandler = new DatabaseHandler(M_TrainingRecordUpdate.this);
        Bundle extras = getIntent().getExtras();
        /*SharedPreferences prefere = getSharedPreferences("new", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefere.edit();
        String Hr_name = prefere.getString("Okk", "");
        if (Hr_name.equals("ok"))
        {
            databasehandler.removeTraining();
            editor.remove("Okk");
            editor.commit();
        }

        if (extras != null) {
            backCnic = extras.getString("backcnic");
            backName = extras.getString("backname");
        }*/
        name = (EditText) findViewById(R.id.namet);
        cnic = (EditText) findViewById(R.id.cnict);
        title = (EditText) findViewById(R.id.nametitle);
        bhs = (TextView) findViewById(R.id.selectyear);
        duration = (EditText) findViewById(R.id.duration);
        conductedby = (EditText) findViewById(R.id.conductedby);
        attendedas = (Spinner) findViewById(R.id.attendedas);
        Save = (Button) findViewById(R.id.save);
        name.setEnabled(false);
        cnic.setEnabled(false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");

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
                DatePickerDialog dialog = new DatePickerDialog(M_TrainingRecordUpdate.this, datee, myCalendarr
                        .get(Calendar.YEAR), myCalendarr.get(Calendar.MONTH), myCalendarr.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

            }
        });
        String[] g = {"Trainer", "Trainee"};
        ArrayAdapter<String> gAdapter = new ArrayAdapter<String>(M_TrainingRecordUpdate.this, android.R.layout.simple_spinner_dropdown_item, g);
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

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().equals("") || duration.getText().toString().equals("")
                        || conductedby.getText().toString().equals("")) {
                    Toast.makeText(M_TrainingRecordUpdate.this, "Fill the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    TrainingRecord schoolinfo = new TrainingRecord();
                    schoolinfo.setTitle(title.getText().toString());
                    schoolinfo.setYear(bhs.getText().toString());
                    schoolinfo.setDuration(duration.getText().toString());
                    schoolinfo.setConductedby(conductedby.getText().toString());
                    schoolinfo.setAttendedas(SLevel);
                    databasehandler.updateTrainingRecord(schoolinfo, emis, identity);
                    Toast.makeText(M_TrainingRecordUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_TrainingRecordUpdate.this, M_TrainingRecordList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();


                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(M_TrainingRecordUpdate.this,M_HumanResourceTeacherPresence.class));
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
            String nameee = extras.getString("namee");
            String cniccc = extras.getString("cnicc");
            String titlee = extras.getString("a");
            String year = extras.getString("b");
            String dur = extras.getString("c");
            String con = extras.getString("d");
            String att = extras.getString("e");
            name.setText(nameee);
            cnic.setText(cniccc);
            title.setText(titlee);
            duration.setText(dur);
            conductedby.setText(con);
            bhs.setText(year);
            //attendedas.setText(Hr_agNo);
            if (att.equals("Trainee")) {
                attendedas.setSelection(1, true);
            } else {
                attendedas.setSelection(0);
            }
        }

    }
}
