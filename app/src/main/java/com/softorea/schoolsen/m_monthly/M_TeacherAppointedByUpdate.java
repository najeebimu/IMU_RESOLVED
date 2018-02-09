package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterApointedBy;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsAppointedBy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_TeacherAppointedByUpdate extends Activity {

    Button clear, add;
    DetailsAppointedBy details;
    EditText name, cnic, appointedby;
    ArrayList<DetailsAppointedBy> addas = new ArrayList<DetailsAppointedBy>();
    CustomAdapterApointedBy cusadapter;
    DatabaseHandler databasehandler;

    int identity;
    TextView appointment_date;

    String emis, vseats;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_teacher_appointedbyupdate);
        databasehandler = new DatabaseHandler(M_TeacherAppointedByUpdate.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        name = (EditText) findViewById(R.id.pteacheroo_name);
        cnic = (EditText) findViewById(R.id.pteacher_cnic);
        appointedby = (EditText) findViewById(R.id.pteacher_appointedby);
        appointment_date = (TextView) findViewById(R.id.teacher_apointment_date);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                cnic.setText("");
                appointedby.setText("");
                appointment_date.setText("Select Date");
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

                appointment_date.setText(sdf.format(myCalendar.getTime()));
            }

        };

        appointment_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_TeacherAppointedByUpdate.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cnicId = cnic.getText().toString();
                if (name.getText().toString().equals("") || appointedby.getText().toString().equals("")) {
                    Toast.makeText(M_TeacherAppointedByUpdate.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                } else if (cnicId == null || cnicId.length() < 13) {
                    Toast.makeText(M_TeacherAppointedByUpdate.this, "Cnic is not valid", Toast.LENGTH_SHORT).show();

                } else {
                    //checkAlreadyExist(cnicId);
                    DetailsAppointedBy schoolinfo = new DetailsAppointedBy();
                    schoolinfo.setName(name.getText().toString());
                    schoolinfo.setCnic(cnic.getText().toString());
                    schoolinfo.setAppointedby(appointedby.getText().toString());
                    schoolinfo.setAppointedDate(appointment_date.getText().toString());
                    databasehandler.updateTeacherAppointedby(schoolinfo, emis, identity);
                    Toast.makeText(M_TeacherAppointedByUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_TeacherAppointedByUpdate.this, M_TeacherAppointedByList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }

            private void checkAlreadyExist(String cnicId) {

                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_appointedby WHERE appointedcnic='" + cnicId + "'", null);
                if (c.moveToFirst() && !cnic.getText().toString().equals(vseats)) {
                    Toast.makeText(M_TeacherAppointedByUpdate.this, "CNIC already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                } else {
                    DetailsAppointedBy schoolinfo = new DetailsAppointedBy();
                    schoolinfo.setName(name.getText().toString());
                    schoolinfo.setCnic(cnic.getText().toString());
                    schoolinfo.setAppointedby(appointedby.getText().toString());
                    schoolinfo.setAppointedDate(appointment_date.getText().toString());
                    databasehandler.updateTeacherAppointedby(schoolinfo, emis, identity);
                    Toast.makeText(M_TeacherAppointedByUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_TeacherAppointedByUpdate.this, M_TeacherAppointedByList.class));
                    finish();

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        //startActivity(new Intent(this,M_TeacherAppointedByList.class));
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
            String namee = extras.getString("nme");
            vseats = extras.getString("cnc");
            String by = extras.getString("apintby");
            String datee = extras.getString("apintdate");
            name.setText(namee);
            cnic.setText(vseats);
            appointedby.setText(by);
            appointment_date.setText(datee);
        }
    }
}
