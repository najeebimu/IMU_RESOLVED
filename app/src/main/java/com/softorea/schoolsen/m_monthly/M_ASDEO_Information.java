package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.MBY_SchoolInformation;
import com.softorea.schoolsen.lists.Roster_List;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Softorea on 9/15/2017.
 */

public class M_ASDEO_Information extends Activity {
    Button back, next;
    TextView monitoringdate, arrivaltime, departuretime;
    EditText asdeoname, asdeono;
    String id;
    TextView changename;
    EditText visitType;
    String visit, startTime;
    String valuereturned;
    DatabaseHandler dbbb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_asdeo_information);
        changename = (TextView) findViewById(R.id.namedeo_asdeo);
        SharedPreferences abc = PreferenceManager.getDefaultSharedPreferences(M_ASDEO_Information.this);
        final String primary = abc.getString("primary", "");
        final String middle = abc.getString("middle", "");
        final String high = abc.getString("high", "");
        final String highsecondary = abc.getString("highsecondary", "");
        if (primary.equals("PrimarySelected")) {
            changename.setText("Name of ASDEO");
        }
        if (middle.equals("MiddleSelected") || high.equals("HighSelected") || highsecondary.equals("HighSecondarySelected")) {
            changename.setText("Name of DEO");
        }

        dbbb = new DatabaseHandler(M_ASDEO_Information.this);
        SharedPreferences aa = getSharedPreferences("abcd", MODE_PRIVATE);
        valuereturned = aa.getString("visitycheck", "");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
        String formattedDate3 = df3.format(calendar.getTime());
        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        startTime = formattedDate3;
        SharedPreferences newsf = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = newsf.edit();
        editor.putString("starting_time", startTime);
        editor.apply();
        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        visitType = (EditText) findViewById(R.id.visitytpeee);
        visitType.setText(valuereturned);
        visitType.setEnabled(false);
        asdeoname = (EditText) findViewById(R.id.asdeo_name);
        asdeono = (EditText) findViewById(R.id.contactno);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        id = preferences.getString("emiscode", "");
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM t_schoolinformation WHERE emis= " + id, null);
        if (c.moveToFirst()) {
            do {
                String column1 = c.getString(c.getColumnIndex("ado_name"));
                String column2 = c.getString(c.getColumnIndex("ado_no"));
                asdeoname.setText(column1);
                asdeono.setText(column2);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        monitoringdate = (TextView) findViewById(R.id.monitoringdate);
        arrivaltime = (TextView) findViewById(R.id.arrivaltime);
        departuretime = (TextView) findViewById(R.id.departuretime);
        final Calendar myCalendar = Calendar.getInstance();
        Intent intent = getIntent();
        id = intent.getStringExtra("data");

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

                monitoringdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        monitoringdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(M_ASDEO_Information.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /*String[] visittypespinner = getResources().getStringArray(R.array.visittype);
        ArrayAdapter<String> type = new ArrayAdapter<String>(M_ASDEO_Information.this, android.R.layout.simple_spinner_dropdown_item, visittypespinner);
        visitType.setAdapter(type);
        visitType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                val
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });*/


        arrivaltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(M_ASDEO_Information.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        arrivaltime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });

        departuretime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(M_ASDEO_Information.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        departuretime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noLength = asdeono.getText().toString();
                if (asdeoname.getText().toString().equals(""))
                {
                    Toast.makeText(M_ASDEO_Information.this, "Please enter name!", Toast.LENGTH_SHORT).show();
                }
                else if (noLength.length() > 0 && noLength.length() < 10)
                {
                    Toast.makeText(M_ASDEO_Information.this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent view_order_intent = new Intent(M_ASDEO_Information.this, M_School_Status.class);
                    view_order_intent.putExtra("data", id);
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                //startActivity(new Intent(M_ASDEO_Information.this, M_School_Status.class));
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_ASDEO_Information.this, MBY_SchoolInformation.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*SchoolInformationDetails schoolinfo = new SchoolInformationDetails();
        schoolinfo.setADOName(asdeoname.getText().toString());
        schoolinfo.setADONo(asdeono.getText().toString());
        dbbb.updateSchoolInfo(schoolinfo,id);*/
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("AsdeovisitType", visitType.getText().toString());
        editor.putString("asdeoname", asdeoname.getText().toString());
        editor.putString("asdeocontactno", asdeono.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String namee = preferences.getString("asdeoname", "");
        String noo = preferences.getString("asdeocontactno", "");
        //asdeono.setText(noo);
       // asdeoname.setText(namee);
        //String valuereturned = preferences.getString("AsdeovisitType", "");
        //visitType.setText(valuereturned);
        /*if (valuereturned.equals("Regular")) {
            visitType.setSelection(0, true);
        } else if (valuereturned.equals("Revisit")) {
            visitType.setSelection(1, true);
        }*/
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_ASDEO_Information.this);
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
