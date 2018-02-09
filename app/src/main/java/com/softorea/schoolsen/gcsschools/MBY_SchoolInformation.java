package com.softorea.schoolsen.gcsschools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.GPSTracker;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.lists.SchoolInformationDetails;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Softorea on 9/11/2017.
 */

public class MBY_SchoolInformation extends Activity {
    Spinner schoolLevel, location, zone;
    Button back, next;
    EditText schoolemiscode, name, district, tehsil, ucname, nano, pkno, circleofficename;
    String id;
    DatabaseHandler db;
    String SLevel, SLevel2, SLevel3, SLevel4;
    String startTime;
    String column2, column3, column8, column9;
    GPSTracker gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efragment_school_information);
        gps = new GPSTracker(MBY_SchoolInformation.this);


        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            double accuracy = gps.getAccuracy();
            String lat = String.valueOf(latitude);
            String lng = String.valueOf(longitude);
            String acc = String.valueOf(accuracy);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("lat1", lat);
            editor.putString("lng1", lng);
            editor.putString("acc1", acc);
            editor.apply();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Lat: " + lat + "\nLong: " + lng + " " + acc, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            //gps.showSettingsAlert();
        }
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        startTime = currentDateTimeString;
        db = new DatabaseHandler(MBY_SchoolInformation.this);
        name = (EditText) findViewById(R.id.schoolname);
        district = (EditText) findViewById(R.id.district);
        district.setEnabled(false);
        district.setClickable(false);
        tehsil = (EditText) findViewById(R.id.tehsil);
        ucname = (EditText) findViewById(R.id.unioncouncil);
        nano = (EditText) findViewById(R.id.national_constituency);
        pkno = (EditText) findViewById(R.id.provincial_constituency);
        circleofficename = (EditText) findViewById(R.id.circleofficename);

        schoolLevel = (Spinner) findViewById(R.id.school_level);
        schoolLevel.setEnabled(false);
        schoolLevel.setClickable(false);
        location = (Spinner) findViewById(R.id.locationcensus);
        zone = (Spinner) findViewById(R.id.census_schoolzone);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        id = preferences.getString("emiscode", "");
        String[] schoolLevelSpinner = getResources().getStringArray(R.array.school_level);

        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, schoolLevelSpinner);
        schoolLevel.setAdapter(schoolLevelAdapter);
        schoolLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel2 = schoolLevel.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] g = {"Urban", "Rural"};
        ArrayAdapter<String> gAdapter = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, g);
        location.setAdapter(gAdapter);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel3 = location.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] g1 = {"Summer", "Winter"};
        ArrayAdapter<String> g1Adapter = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, g1);
        zone.setAdapter(g1Adapter);
        zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel4 = zone.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM t_schoolinformation WHERE emis= " + id, null);
        if (c.moveToFirst()) {
            do {
                SharedPreferences qer = PreferenceManager.getDefaultSharedPreferences(MBY_SchoolInformation.this);
                SharedPreferences.Editor editor = qer.edit();
                String column1 = c.getString(c.getColumnIndex("schoolname"));
                column3 = c.getString(c.getColumnIndex("schoollevel"));
                //String column4 = c.getString(c.getColumnIndex("ddocode"));
                String column5 = c.getString(c.getColumnIndex("district"));
                String column6 = c.getString(c.getColumnIndex("tehsil"));
                String column7 = c.getString(c.getColumnIndex("ucname"));
                column8 = c.getString(c.getColumnIndex("location"));
                column9 = c.getString(c.getColumnIndex("schoolzone"));
                String column10 = c.getString(c.getColumnIndex("nano"));
                String column11 = c.getString(c.getColumnIndex("pkno"));
                String column12 = c.getString(c.getColumnIndex("circleofficename"));
                name.setText(column1);
                if (column3.equals("Higher Secondary")) {
                    schoolLevel.setSelection(4, true);

                } else if (column3.equals("Primary")) {
                    schoolLevel.setSelection(1, true);
                } else if (column3.equals("Middle")) {
                    schoolLevel.setSelection(2, true);
                } else if (column3.equals("High")) {
                    schoolLevel.setSelection(3, true);
                } else {
                    schoolLevel.setSelection(0, true);
                }
                if (column8.equals("Rural")) {
                    location.setSelection(1, true);
                } else {
                    location.setSelection(0, true);
                }
                if (column9.equals("Winter")) {
                    zone.setSelection(1, true);
                } else {
                    zone.setSelection(0, true);
                }
                Log.e("Values", column3);
               /* if (column2.equals("Boys")) {
                    gender.setSelection(0, true);
                } if (column2.equals("Girls")) {
                    gender.setSelection(1, true);
                }
                if (column3.equals("Mosque")) {
                    schoolLevel.setSelection(0, true);
                }
                if (column3.equals("Primary")) {
                    schoolLevel.setSelection(1, true);
                }
                if (column3.equals("Middle")) {
                    schoolLevel.setSelection(2, true);
                }
                if (column3.equals("High")) {
                    schoolLevel.setSelection(3, true);
                }
                if (column3.equals("High Secondary")) {
                    schoolLevel.setSelection(4, true);
                }*/

                //ddocode.setText(column4);
                district.setText(column5);
                tehsil.setText(column6);
                ucname.setText(column7);

                /*if (column8.equals("Urban")) {
                    location.setSelection(0, true);
                }
                if (column8.equals("Rural"))
                {
                    location.setSelection(1, true);
                }
                if (column9.equals("Summer")) {
                    zone.setSelection(0, true);
                }
                if (column9.equals("Winter"))
                {
                    zone.setSelection(1, true);
                }*/
                nano.setText(column10);
                pkno.setText(column11);
                circleofficename.setText(column12);


            } while (c.moveToNext());
        }
        c.close();
        db.close();
        Log.e("FINALVALUES", column2 + column3 + column8 + column9);

        schoolemiscode = (EditText) findViewById(R.id.schoolemiscode);
        schoolemiscode.setText(id);
        schoolemiscode.setEnabled(false);
        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_order_intent = new Intent(MBY_SchoolInformation.this, M_School_Status.class);
                startActivity(view_order_intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                //startActivity(new Intent(MBY_SchoolInformation.this, M_ASDEO_Information.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MBY_SchoolInformation.this);
                builder1.setMessage("Are you sure to go to roster screen?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                                ComponentName cn = intent.getComponent();
                                Intent mainIntent = IntentCompat.makeRestartActivityTask(cn);
                                startActivity(mainIntent);
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
        });


        String[] buildingownership = getResources().getStringArray(R.array.building_ownership);
        ArrayAdapter<String> buildingadapter = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, buildingownership);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SchoolInformationDetails schoolinfo = new SchoolInformationDetails();
        schoolinfo.setSchoolname(name.getText().toString());
        schoolinfo.setLevel(SLevel2);
        //schoolinfo.setDdocode(ddocode.getText().toString());
        schoolinfo.setDistrict(district.getText().toString());
        schoolinfo.setTehsil(tehsil.getText().toString());
        schoolinfo.setUcname(ucname.getText().toString());
        schoolinfo.setLocation(SLevel3);
        schoolinfo.setSchoolzone(SLevel4);
        schoolinfo.setNano(nano.getText().toString());
        schoolinfo.setPkno(pkno.getText().toString());
        schoolinfo.setCircleofficename(circleofficename.getText().toString());
        db.updateSchoolInfo(schoolinfo, id);

        //Toast.makeText(this, "Level" + SLevel2, Toast.LENGTH_SHORT).show();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String code = preferences.getString("newdata", "");
        //if (!code.equals("New Data Found")) {
        editor.putString("emiscodee", schoolemiscode.getText().toString());
        editor.putString("school_name", name.getText().toString());
        editor.putString("gender", SLevel);
        editor.putString("schoollevel", SLevel2);
        editor.putString("districtname", district.getText().toString());
        editor.putString("tehsilname", tehsil.getText().toString());
        editor.putString("ucnamename", ucname.getText().toString());
        //editor.putString("ddo_code", ddocode.getText().toString());
        editor.putString("locationn", SLevel3);
        editor.putString("zoneschool", SLevel4);
        editor.putString("nano", nano.getText().toString());
        editor.putString("pkno", pkno.getText().toString());
        editor.putString("circloffice", circleofficename.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String code = preferences.getString("emiscodee", "");
        String codeback = preferences.getString("emiscode", "");
        if (codeback.equals(code)) {

        } else {
            SharedPreferences preferencess = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
            SharedPreferences.Editor editorr = preferencess.edit();
            editorr.clear();
            editorr.commit();

            SharedPreferences asy = getSharedPreferences("Newthings", MODE_PRIVATE);
            SharedPreferences.Editor editorrr = asy.edit();
            editorrr.clear();
            editorrr.commit();

            SharedPreferences s1 = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor e1 = s1.edit();
            e1.remove("school_name");
            e1.remove("schoollevel");
            e1.remove("districtname");
            e1.remove("tehsilname");
            e1.remove("ucnamename");
            e1.remove("locationn");
            e1.remove("zoneschool");
            e1.remove("nano");
            e1.remove("pkno");
            e1.remove("circloffice");
            e1.commit();


        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MBY_SchoolInformation.this);
        builder1.setMessage("Are you sure to go to roster screen?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                        ComponentName cn = intent.getComponent();
                        Intent mainIntent = IntentCompat.makeRestartActivityTask(cn);
                        startActivity(mainIntent);
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