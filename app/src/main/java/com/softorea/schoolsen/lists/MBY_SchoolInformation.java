package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.m_monthly.M_ASDEO_Information;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 9/11/2017.
 */

public class MBY_SchoolInformation extends Activity {
    LinearLayout primaryLevel, MiddleLevel, HighLevel, HighSecondaryLevel;
    Spinner schoolLevel, gender, location, zone, building_ownership, typeOfupgradation;
    Button back, next;
    EditText sdedoofficname, schoolemiscode, name, ddocode, district, tehsil, ucname, nano, pkno, circleofficename;
    EditText village_council_name, village_cityname, locality_name, streetname, school_phoneno, landdonated;
    String id;
    TextView upgradation_primary, upgradation_middle, upgradation_high, upgradation_highsecondary;
    String UpgradationType;
    DatabaseHandler db;
    String BuildingOwnership;
    String SLevel, SLevel2, SLevel3, SLevel4;
    String startTime;
    String column2, column3, column8, column9;
    GPSTracker gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mby_school_information);
        gps = new GPSTracker(MBY_SchoolInformation.this);
        primaryLevel = (LinearLayout) findViewById(R.id.primaryLevelLayout);
        MiddleLevel = (LinearLayout) findViewById(R.id.MiddleLevelLayout);
        HighLevel = (LinearLayout) findViewById(R.id.highLevelLayout);
        HighSecondaryLevel = (LinearLayout) findViewById(R.id.highSecondaryLevelLayout);


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
        sdedoofficname = (EditText) findViewById(R.id.sdeoofficename);
        name = (EditText) findViewById(R.id.schoolname);
        ddocode = (EditText) findViewById(R.id.ddo_code);
        district = (EditText) findViewById(R.id.district);
        district.setEnabled(false);
        district.setClickable(false);
        tehsil = (EditText) findViewById(R.id.tehsil);
        ucname = (EditText) findViewById(R.id.unioncouncil);
        nano = (EditText) findViewById(R.id.national_constituency);
        pkno = (EditText) findViewById(R.id.provincial_constituency);
        circleofficename = (EditText) findViewById(R.id.circleofficename);
        village_council_name = (EditText) findViewById(R.id.villagecouncilname);
        village_cityname = (EditText) findViewById(R.id.villagecityname);
        locality_name = (EditText) findViewById(R.id.localityname);
        streetname = (EditText) findViewById(R.id.streetname);
        school_phoneno = (EditText) findViewById(R.id.schoolphone_number);
        upgradation_primary = (TextView) findViewById(R.id.primaryyear);
        upgradation_middle = (TextView) findViewById(R.id.middleyear);
        upgradation_high = (TextView) findViewById(R.id.highyear);
        upgradation_highsecondary = (TextView) findViewById(R.id.highsecondaryyear);
        landdonated = (EditText) findViewById(R.id.landallocated);

        schoolLevel = (Spinner) findViewById(R.id.school_level);
        gender = (Spinner) findViewById(R.id.gender);
        gender.setEnabled(false);
        gender.setClickable(false);
        location = (Spinner) findViewById(R.id.locationcensus);
        zone = (Spinner) findViewById(R.id.census_schoolzone);
        building_ownership = (Spinner) findViewById(R.id.ownership_building);
        typeOfupgradation = (Spinner) findViewById(R.id.type_of_upgradation);
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
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MBY_SchoolInformation.this);
                SharedPreferences.Editor editor = preferences.edit();
                if (SLevel2.equals("Mosque")) {
                    primaryLevel.setVisibility(View.VISIBLE);
                    MiddleLevel.setVisibility(View.GONE);
                    HighLevel.setVisibility(View.GONE);
                    HighSecondaryLevel.setVisibility(View.GONE);
                    editor.putString("primary", "PrimarySelected");
                    editor.remove("middle");
                    editor.remove("high");
                    editor.remove("highsecondary");
                    editor.apply();
                } else if (SLevel2.equals("Primary")) {
                    primaryLevel.setVisibility(View.VISIBLE);
                    MiddleLevel.setVisibility(View.GONE);
                    HighLevel.setVisibility(View.GONE);
                    HighSecondaryLevel.setVisibility(View.GONE);
                    editor.putString("primary", "PrimarySelected");
                    editor.remove("middle");
                    editor.remove("high");
                    editor.remove("highsecondary");
                    editor.apply();
                } else if (SLevel2.equals("Middle")) {
                    primaryLevel.setVisibility(View.VISIBLE);
                    MiddleLevel.setVisibility(View.VISIBLE);
                    HighLevel.setVisibility(View.GONE);
                    HighSecondaryLevel.setVisibility(View.GONE);
                    editor.putString("middle", "MiddleSelected");
                    editor.remove("primary");
                    editor.remove("high");
                    editor.remove("highsecondary");
                    editor.apply();
                } else if (SLevel2.equals("High")) {
                    primaryLevel.setVisibility(View.VISIBLE);
                    MiddleLevel.setVisibility(View.VISIBLE);
                    HighLevel.setVisibility(View.VISIBLE);
                    HighSecondaryLevel.setVisibility(View.GONE);
                    editor.putString("high", "HighSelected");
                    editor.remove("primary");
                    editor.remove("middle");
                    editor.remove("highsecondary");
                    editor.apply();

                } else {
                    primaryLevel.setVisibility(View.VISIBLE);
                    MiddleLevel.setVisibility(View.VISIBLE);
                    HighLevel.setVisibility(View.VISIBLE);
                    HighSecondaryLevel.setVisibility(View.VISIBLE);
                    editor.putString("highsecondary", "HighSecondarySelected");
                    editor.remove("middle");
                    editor.remove("high");
                    editor.remove("primary");
                    editor.apply();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        /*String[] genderSpinner = {"Boys", "Girls"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, genderSpinner);
        gender.setAdapter(genderAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = gender.getSelectedItem().toString();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MBY_SchoolInformation.this);
                SharedPreferences.Editor editor = preferences.edit();
                if (SLevel.equals("Boys")) {
                    editor.putString("boys", "BoysSelected");
                    editor.remove("girls");
                    editor.apply();
                } else {
                    editor.putString("girls", "GirlsSelected");
                    editor.remove("boys");
                    editor.apply();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });*/
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
                column2 = c.getString(c.getColumnIndex("gender"));
                column3 = c.getString(c.getColumnIndex("schoollevel"));
                String column5 = c.getString(c.getColumnIndex("district"));
                String column6 = c.getString(c.getColumnIndex("tehsil"));
                String column7 = c.getString(c.getColumnIndex("ucname"));
                column8 = c.getString(c.getColumnIndex("location"));
                column9 = c.getString(c.getColumnIndex("schoolzone"));
                String column10 = c.getString(c.getColumnIndex("nano"));
                String column11 = c.getString(c.getColumnIndex("pkno"));
                String column12 = c.getString(c.getColumnIndex("circleofficename"));
                name.setText(column1);
                if (column2.equals("GIRLS")) {
                    gender.setSelection(1, true);
                } else {
                    gender.setSelection(0, true);
                }
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

                district.setText(column5);
                tehsil.setText(column6);
                ucname.setText(column7);

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
                Intent view_order_intent = new Intent(MBY_SchoolInformation.this, M_ASDEO_Information.class);
                view_order_intent.putExtra("data", id);
                startActivity(view_order_intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
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

                int year = myCalendar.get(Calendar.YEAR);
                upgradation_primary.setText("" + year);
            }

        };

        upgradation_primary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(MBY_SchoolInformation.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

            }
        });

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
                upgradation_middle.setText("" + year);
            }

        };

        upgradation_middle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(MBY_SchoolInformation.this, datee, myCalendarr
                        .get(Calendar.YEAR), myCalendarr.get(Calendar.MONTH), myCalendarr.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

            }
        });

        final Calendar myCalenda = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dat = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalenda.set(Calendar.YEAR, year);
                myCalenda.set(Calendar.MONTH, monthOfYear);
                myCalenda.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                int year = myCalenda.get(Calendar.YEAR);
                upgradation_high.setText("" + year);
            }

        };

        upgradation_high.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(MBY_SchoolInformation.this, dat, myCalenda
                        .get(Calendar.YEAR), myCalenda.get(Calendar.MONTH), myCalenda.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

            }
        });

        final Calendar cal = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                int year = cal.get(Calendar.YEAR);
                upgradation_highsecondary.setText("" + year);
            }

        };

        upgradation_highsecondary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(MBY_SchoolInformation.this, d, cal
                        .get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

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
        primaryLevel = (LinearLayout) findViewById(R.id.primaryLevelLayout);
        MiddleLevel = (LinearLayout) findViewById(R.id.MiddleLevelLayout);
        HighLevel = (LinearLayout) findViewById(R.id.highLevelLayout);
        HighSecondaryLevel = (LinearLayout) findViewById(R.id.highSecondaryLevelLayout);


        String[] buildingownership = getResources().getStringArray(R.array.building_ownership);
        ArrayAdapter<String> buildingadapter = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, buildingownership);
        building_ownership.setAdapter(buildingadapter);
        building_ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                BuildingOwnership = building_ownership.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] typeOffUpgradation = getResources().getStringArray(R.array.upgradationtype);
        ArrayAdapter<String> type = new ArrayAdapter<String>(MBY_SchoolInformation.this, android.R.layout.simple_spinner_dropdown_item, typeOffUpgradation);
        typeOfupgradation.setAdapter(type);
        typeOfupgradation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                UpgradationType = typeOfupgradation.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SchoolInformationDetails schoolinfo = new SchoolInformationDetails();
        schoolinfo.setSchoolname(name.getText().toString());
        schoolinfo.setGender(column2);
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
        editor.putString("gender", column2);
        editor.putString("schoollevel", SLevel2);
        editor.putString("ddocode", ddocode.getText().toString());
        editor.putString("districtname", district.getText().toString());
        editor.putString("tehsilname", tehsil.getText().toString());
        editor.putString("ucnamename", ucname.getText().toString());
        //editor.putString("ddo_code", ddocode.getText().toString());
        editor.putString("locationn", SLevel3);
        editor.putString("zoneschool", SLevel4);
        editor.putString("nano", nano.getText().toString());
        editor.putString("pkno", pkno.getText().toString());
        editor.putString("circloffice", circleofficename.getText().toString());
        editor.putString("villagecouncilname", village_council_name.getText().toString());
        editor.putString("villagecityname", village_cityname.getText().toString());
        editor.putString("localityname", locality_name.getText().toString());
        editor.putString("streetname", streetname.getText().toString());
        editor.putString("schoolphoneno", school_phoneno.getText().toString());
        editor.putString("upgraprimary", upgradation_primary.getText().toString());
        editor.putString("upgramiddle", upgradation_middle.getText().toString());
        editor.putString("upgrahigh", upgradation_high.getText().toString());
        editor.putString("upgrahighsec", upgradation_highsecondary.getText().toString());
        editor.putString("landdonated", landdonated.getText().toString());
        editor.putString("upgradationtype", UpgradationType);
        editor.putString("buildingownership", BuildingOwnership);
        editor.putString("sdeoofficename", sdedoofficname.getText().toString());
        editor.apply();
        //}
        //else
        //{

        //    }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String code = preferences.getString("emiscodee", "");
        String codeback = preferences.getString("emiscode", "");
        /*SLevel2 = schoolLevel.getSelectedItem().toString();
        if (SLevel2.equals("Mosque")) {
            primaryLevel.setVisibility(View.VISIBLE);
            MiddleLevel.setVisibility(View.GONE);
            HighLevel.setVisibility(View.GONE);
            HighSecondaryLevel.setVisibility(View.GONE);
            editor.putString("primary", "PrimarySelected");
            editor.remove("middle");
            editor.remove("high");
            editor.remove("highsecondary");
            editor.apply();
        }
        if (SLevel2.equals("Primary")) {
            primaryLevel.setVisibility(View.VISIBLE);
            MiddleLevel.setVisibility(View.GONE);
            HighLevel.setVisibility(View.GONE);
            HighSecondaryLevel.setVisibility(View.GONE);
            editor.putString("primary", "PrimarySelected");
            editor.remove("middle");
            editor.remove("high");
            editor.remove("highsecondary");
            editor.apply();
        }
        if (SLevel2.equals("Middle")) {
            primaryLevel.setVisibility(View.VISIBLE);
            MiddleLevel.setVisibility(View.VISIBLE);
            HighLevel.setVisibility(View.GONE);
            HighSecondaryLevel.setVisibility(View.GONE);
            editor.putString("middle", "MiddleSelected");
            editor.remove("primary");
            editor.remove("high");
            editor.remove("highsecondary");
            editor.apply();
        }
        if (SLevel2.equals("High")) {
            primaryLevel.setVisibility(View.VISIBLE);
            MiddleLevel.setVisibility(View.VISIBLE);
            HighLevel.setVisibility(View.VISIBLE);
            HighSecondaryLevel.setVisibility(View.GONE);
            editor.putString("high", "HighSelected");
            editor.remove("primary");
            editor.remove("middle");
            editor.remove("highsecondary");
            editor.apply();

        } else {
            primaryLevel.setVisibility(View.VISIBLE);
            MiddleLevel.setVisibility(View.VISIBLE);
            HighLevel.setVisibility(View.VISIBLE);
            HighSecondaryLevel.setVisibility(View.VISIBLE);
            editor.putString("highsecondary", "HighSecondarySelected");
            editor.remove("middle");
            editor.remove("high");
            editor.remove("primary");
            editor.apply();
        }*/

        if (codeback.equals(code)) {
            String vcname = preferences.getString("villagecouncilname", "");
            String vcityname = preferences.getString("villagecityname", "");
            String locality = preferences.getString("localityname", "");
            String street = preferences.getString("streetname", "");
            String schoolphone = preferences.getString("schoolphoneno", "");
            String pirm = preferences.getString("upgraprimary", "");
            String midd = preferences.getString("upgramiddle", "");
            String high = preferences.getString("upgrahigh", "");
            String highsec = preferences.getString("upgrahighsec", "");
            String landdonatedd = preferences.getString("landdonated", "");
            String upgradationtype = preferences.getString("upgradationtype", "");
            String buildingownership = preferences.getString("buildingownership", "");
            String sdeo = preferences.getString("sdeoofficename", "");
            String ddo_cde = preferences.getString("ddocode", "");
            ddocode.setText(ddo_cde);
            sdedoofficname.setText(sdeo);
            village_council_name.setText(vcname);
            village_cityname.setText(vcityname);
            locality_name.setText(locality);
            streetname.setText(street);
            school_phoneno.setText(schoolphone);
            upgradation_primary.setText(pirm);
            upgradation_middle.setText(midd);
            upgradation_high.setText(high);
            upgradation_highsecondary.setText(highsec);
            landdonated.setText(landdonatedd);
            if (buildingownership.equals("Shelterless")) {
                building_ownership.setSelection(3, true);
            } else if (buildingownership.equals("Donated")) {
                building_ownership.setSelection(1, true);
            } else if (buildingownership.equals("Rented")) {
                building_ownership.setSelection(2, true);
            } else if (buildingownership.equals("Adjusted")) {
                building_ownership.setSelection(4, true);
            } else {
                building_ownership.setSelection(0, true);
            }
            if (upgradationtype.equals("Non Developmental")) {
                typeOfupgradation.setSelection(1, true);
            } else {
                typeOfupgradation.setSelection(0, true);
            }
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
            e1.remove("gender");
            e1.remove("schoollevel");
            e1.remove("ddocode");
            e1.remove("districtname");
            e1.remove("tehsilname");
            e1.remove("ucnamename");
            e1.remove("locationn");
            e1.remove("zoneschool");
            e1.remove("nano");
            e1.remove("pkno");
            e1.remove("circloffice");
            e1.remove("villagecouncilname");
            e1.remove("villagecityname");
            e1.remove("localityname");
            e1.remove("streetname");
            e1.remove("schoolphoneno");
            e1.remove("upgraprimary");
            e1.remove("upgramiddle");
            e1.remove("upgrahigh");
            e1.remove("upgrahighsec");
            e1.remove("landdonated");
            e1.remove("upgradationtype");
            e1.remove("buildingownership");
            e1.remove("sdeoofficename");
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