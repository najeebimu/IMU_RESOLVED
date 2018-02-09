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

public class M_TeacherAppointedBy extends Activity {

    Button clear, add;
    DetailsAppointedBy details;
    EditText name, cnic, appointedby;
    ArrayList<DetailsAppointedBy> addas = new ArrayList<DetailsAppointedBy>();
    CustomAdapterApointedBy cusadapter;
    DatabaseHandler databasehandler;

    TextView appointment_date;

    String emis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_teacher_appointedby);
        databasehandler = new DatabaseHandler(M_TeacherAppointedBy.this);
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
                DatePickerDialog dialog = new DatePickerDialog(M_TeacherAppointedBy.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appointedbys = cnic.getText().toString();
                if (name.getText().toString().equals("") || appointedby.getText().toString().equals("")) {
                    Toast.makeText(M_TeacherAppointedBy.this, "Fill the fields", Toast.LENGTH_SHORT).show();
                } else if (appointedbys == null || appointedbys.length() < 13) {
                    Toast.makeText(M_TeacherAppointedBy.this, "Cnic is not valid", Toast.LENGTH_SHORT).show();

                } else {
                    //checkAlreadyExist(appointedbys);
                    String namee = name.getText().toString();
                    String cnicc = cnic.getText().toString();
                    String AppointedBy = appointedby.getText().toString();
                    String Appointeddate = appointment_date.getText().toString();
                    databasehandler.saveAppointedBy(new DetailsAppointedBy(emis, namee, cnicc, AppointedBy, Appointeddate));
                    Toast.makeText(M_TeacherAppointedBy.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_TeacherAppointedBy.this, M_TeacherAppointedByList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();

                    name.setText("");
                    cnic.setText("");
                    appointedby.setText("");
                    appointment_date.setText("");
                }
            }

            /*private void checkAlreadyExist(String appointedbys) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_appointedby WHERE appointedcnic='" + appointedbys + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(M_TeacherAppointedBy.this, "CNIC already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                } else {
                    String namee = name.getText().toString();
                    String cnicc = cnic.getText().toString();
                    String AppointedBy = appointedby.getText().toString();
                    String Appointeddate = appointment_date.getText().toString();
                    databasehandler.saveAppointedBy(new DetailsAppointedBy(emis, namee, cnicc, AppointedBy, Appointeddate));
                    Toast.makeText(M_TeacherAppointedBy.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_TeacherAppointedBy.this, M_TeacherAppointedByList.class));
                    finish();

                    name.setText("");
                    cnic.setText("");
                    appointedby.setText("");
                    appointment_date.setText("");
                }
            }*/
        });
    }

    private void SavingInDb() {
        addas.clear();
        final DatabaseHandler databaseHandler = new DatabaseHandler(this);
        final ArrayList<ArrayList<Object>> data = databaseHandler.abcAppointedBy();

        for (int p = 0; p < data.size(); p++) {
            details = new DetailsAppointedBy();
            ArrayList<Object> baris = data.get(p);
            details.setName(baris.get(0).toString());
            details.setAppointedby(baris.get(1).toString());
            addas.add(details);
        }
        cusadapter = new CustomAdapterApointedBy(M_TeacherAppointedBy.this, addas);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, M_TeacherAppointedByList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("appointedname", name.getText().toString());
        editor.putString("appointedcnic", cnic.getText().toString());
        editor.putString("appointedby", appointedby.getText().toString());
        editor.putString("appointeddate", appointment_date.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String namee = preferences.getString("appointedname", "");
        String cnicc = preferences.getString("appointedcnic", "");
        String proxyname = preferences.getString("appointedby", "");
        String proxycnic = preferences.getString("appointeddate", "");
        name.setText(namee);
        cnic.setText(cnicc);
        appointedby.setText(proxyname);
        appointment_date.setText(proxycnic);
    }
}
