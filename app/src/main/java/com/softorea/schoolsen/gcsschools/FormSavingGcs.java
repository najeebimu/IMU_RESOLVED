package com.softorea.schoolsen.gcsschools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.CryptoUtils;
import com.softorea.schoolsen.lists.EnrollmentAttStrgcs;
import com.softorea.schoolsen.lists.GPSTracker;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsSchoolVisit;
import com.softorea.schoolsen.models.FormModel;
import com.softorea.schoolsen.models.GcsTeacherDetails;
import com.softorea.schoolsen.models.RosterDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;


public class FormSavingGcs extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    static String uuu1, uuu2, uuu3, uuu4, uuu5, kkk1, kkk2, kkk3, kkk4, kkk5, one1, one2, one3, one4, one5, two1, two2, two3, two4, two5, three1, three2, three3, three4, three5, four1, four2, four3,
            four4, four5, five1, five2, five3, five4, five5, six1, six2, six3, six4, six5, seven1, seven2, seven3, seven4, seven5, eee1, eee2, eee3, eee4, eee5, nnn1, nnn2, nnn3, nnn4,
            nnn5, ttt1, ttt2, ttt3, ttt4, ttt5, eeeleven1, eeeleven2, eeeleven3, eeelevven4, eeeleven5, tttwelve1, tttwelve2, tttwelve3, tttwelve4, tttwelve5;
    public ArrayList<DetailsSchoolVisit> schoolVisits;
    public ArrayList<GcsTeacherDetails> teacherdetails;
    String savedschools = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=updateSavedSchools_v1";
    String srcw;
    FormModel objform;
    EnrollmentAttStrgcs cls1 = new EnrollmentAttStrgcs();
    DatabaseHandler db;
    Button saveButtton;
    String formId;
    String emisCode;
    String monitorLogin;
    String currentDate, currentdateforxml;
    SimpleDateFormat sdf, sdff;
    String fileName, surveyidfile;
    RosterDetails roaster;
    String endtime;
    String time,finalremarks;
    SharedPreferences prefs;
    String key = "67E5DE12EC86119F";
    String survayId;
    String startingTime, endingTime;
    GPSTracker gpsTracker;
    String versionName = "0";
    String reasonvalue, checkvalue, illegalvalue, occupationvalue;
    String schoolemiscode, schoolname, level, district, tehsil, ucname, location, zone, nano, pkno, circle_officename;
    String headinstitute, headcell, headDesig;
    DatabaseHandler databasehandler;
    DetailsSchoolVisit schoolvis;
    GcsTeacherDetails tdetails;
    String id, e;
    String lat, lat2, lat3, lng1, lng2, lng3, acc1, acc2, acc3;
    GPSTracker gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_form);
        objform = new FormModel();
        Random r = new Random();
        gps = new GPSTracker(FormSavingGcs.this);


        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            double accuracy = gps.getAccuracy();
            lat3 = String.valueOf(latitude);
            lng3 = String.valueOf(longitude);
            acc3 = String.valueOf(accuracy);
        } else {

        }
        cls1.execute(this);


        uuu1 = EnrollmentAttStrgcs.unadmit_1;
        uuu2 = EnrollmentAttStrgcs.unadmit_2;
        uuu3 = EnrollmentAttStrgcs.unadmit_3;
        uuu4 = EnrollmentAttStrgcs.unadmit_4;
        uuu5 = EnrollmentAttStrgcs.unadmit_5;
        kkk1 = EnrollmentAttStrgcs.kachi1;
        kkk2 = EnrollmentAttStrgcs.kachi2;
        kkk3 = EnrollmentAttStrgcs.kachi3;
        kkk4 = EnrollmentAttStrgcs.kachi4;
        kkk5 = EnrollmentAttStrgcs.kachi5;
        one1 = EnrollmentAttStrgcs.One1;
        one2 = EnrollmentAttStrgcs.One2;
        one3 = EnrollmentAttStrgcs.One3;
        one4 = EnrollmentAttStrgcs.One4;
        one5 = EnrollmentAttStrgcs.One5;
        two1 = EnrollmentAttStrgcs.two1;
        two2 = EnrollmentAttStrgcs.two2;
        two3 = EnrollmentAttStrgcs.two3;
        two4 = EnrollmentAttStrgcs.two4;
        two5 = EnrollmentAttStrgcs.two5;

        three1 = EnrollmentAttStrgcs.three1;
        three2 = EnrollmentAttStrgcs.three2;
        three3 = EnrollmentAttStrgcs.three3;
        three4 = EnrollmentAttStrgcs.three4;
        three5 = EnrollmentAttStrgcs.three5;
        four1 = EnrollmentAttStrgcs.four1;
        four2 = EnrollmentAttStrgcs.four2;
        four3 = EnrollmentAttStrgcs.four3;
        four4 = EnrollmentAttStrgcs.four4;
        four5 = EnrollmentAttStrgcs.four5;
        five1 = EnrollmentAttStrgcs.five1;
        five2 = EnrollmentAttStrgcs.five2;
        five3 = EnrollmentAttStrgcs.five3;
        five4 = EnrollmentAttStrgcs.five4;
        five5 = EnrollmentAttStrgcs.five5;


        SharedPreferences preferencess = PreferenceManager.getDefaultSharedPreferences(this);
        id = preferencess.getString("emiscode", "");
        lat = preferencess.getString("lat1", "");
        lat2 = preferencess.getString("lat2", "");
        lng1 = preferencess.getString("lng1", "");
        lng2 = preferencess.getString("lng2", "");
        acc1 = preferencess.getString("acc1", "");
        acc2 = preferencess.getString("acc2", "");
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase dbd = database.getReadableDatabase();
        Cursor cursorform = dbd.rawQuery("SELECT * FROM t_roster WHERE emis= " + id, null);
        if (cursorform.moveToFirst()) {
            do {
                formId = cursorform.getString(cursorform.getColumnIndex("RosterformId"));
            } while (cursorform.moveToNext());
        }
        cursorform.close();
        dbd.close();
        databasehandler = new DatabaseHandler(FormSavingGcs.this);
        schoolVisits = databasehandler.getVisitBy(id);
        teacherdetails = databasehandler.Gcslistteacher(id);
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        finalremarks = preferences.getString("finalremarksgcs", "");
        reasonvalue = preferences.getString("reasonOthers", "");
        checkvalue = preferences.getString("checkvalue", "");
        illegalvalue = preferences.getString("illegalkey", "");
        occupationvalue = preferences.getString("occupation_occupied", "");

        headinstitute = preferences.getString("headofinstitution", "");
        headcell = preferences.getString("headcellno", "");
        headDesig = preferences.getString("headdesignation", "");

        final String a = preferences.getString("electriAvailable", "");
        final String b = preferences.getString("electricFunction", "");
        final String c = preferences.getString("waterAvail", "");
        final String d = preferences.getString("waterFunction", "");
        final String g = preferences.getString("toiletAvail", "");
        final String h = preferences.getString("toiletFunction", "");
        final String classroominput = preferences.getString("classroominput", "");
        final String classavailable = preferences.getString("classavailable", "");
        final String varandainput = preferences.getString("varandainput", "");

        final String school_register = preferences.getString("school_register", "");
        final String physical_present = preferences.getString("physical_present", "");

        saveButtton = (Button) findViewById(R.id.saveform);
        db = new DatabaseHandler(this);
        // String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //endtime = currentDateTimeString;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefss = getSharedPreferences(
                MyPREFERENCES, Context.MODE_PRIVATE);

        try {
            versionName = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0).versionName;
        } catch (Exception eE) {
            versionName = "0";
            eE.printStackTrace();
        }
        emisCode = prefs.getString("emiscode", "");
        startingTime = prefs.getString("starting_time", "");
        monitorLogin = prefss.getString("username", "");
        sdf = new SimpleDateFormat("ddMMyyyy");
        currentDate = sdf.format(new Date());
        sdff = new SimpleDateFormat("dd/MM/yyyy");
        currentdateforxml = sdff.format(new Date());
        //formId = "18123";
        fileName = emisCode + "_" + monitorLogin + "_" + time;

        //Getting SchoolInformation
        schoolemiscode = prefs.getString("emiscodee", "");
        schoolname = prefs.getString("school_name", "");
        level = prefs.getString("schoollevel", "");
        district = prefs.getString("districtname", "");
        tehsil = prefs.getString("tehsilname", "");
        ucname = prefs.getString("ucnamename", "");
        location = prefs.getString("locationn", "");
        zone = prefs.getString("zoneschool", "");
        nano = prefs.getString("nano", "");
        pkno = prefs.getString("pkno", "");
        circle_officename = prefs.getString("circloffice", "");

       /* SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursorform= db.rawQuery("SELECT * FROM t_roster WHERE emis= " + id, null);
        if (cursorform.moveToFirst()) {
            do {
                formId = cursorform.getString(cursorform.getColumnIndex("RosterformId"));
            } while (cursorform.moveToNext());
        }
        cursorform.close();
        db.close();*/


        saveButtton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(FormSavingGcs.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormSavingGcs.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return;
                }
                saveButtton.setClickable(true);
                String mytime = new SimpleDateFormat("HHmmss",
                        Locale.getDefault()).format(new Date());
                String timeforxml = new SimpleDateFormat("HH:mm:ss",
                        Locale.getDefault()).format(new Date());

                endtime = mytime;

                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/data/");
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        Log.e("TravellerLog :: ",
                                "Problem creating Image folder");

                    }
                }
                surveyidfile = emisCode + "_" + monitorLogin + "_" + currentDate + "_" + endtime + "_" + formId;
                //String id = prefs.getString("id", "null");
                //String id = "1010";
                File newxmlfile = new File(file, surveyidfile + ".xml");
                try {

                    newxmlfile.createNewFile();
                    survayId = newxmlfile.getName();

                } catch (IOException e) {
                    Log.e("IOException", "exception in createNewFile() method");
                }
                FileOutputStream fileos = null;

                try {
                    fileos = new FileOutputStream(newxmlfile);
                } catch (FileNotFoundException e) {
                    Log.e("FileNotFoundException",
                            "can't create FileOutputStream");
                }
                XmlSerializer serializer = Xml.newSerializer();

                try {
                    serializer.setOutput(fileos, "UTF-8");
                    serializer.startDocument(null, Boolean.valueOf(true));
                    serializer
                            .setFeature(
                                    "http://xmlpull.org/v1/doc/features.html#indent-output",
                                    true);

                    serializer.startTag(null, "SCHOOLINFORMATION");
                    serializer.startTag(null, "STARTTIME");
                    try {
                        serializer.text(startingTime);
                    } catch (Exception e) {

                    }
                    serializer.endTag(null, "STARTTIME");

                    serializer.startTag(null, "AppVersion");
                    try {
                        serializer.text(versionName);
                    } catch (Exception e) {

                    }
                    serializer.endTag(null, "AppVersion");

                    serializer.startTag(null, "FormId");
                    try {
                        serializer.text(formId);
                    } catch (Exception e) {

                    }
                    serializer.endTag(null, "FormId");

                    serializer.startTag(null, "MonitorLogin");
                    try {
                        serializer.text(monitorLogin);
                    } catch (Exception e) {

                    }
                    serializer.endTag(null, "MonitorLogin");

                    serializer.startTag(null, "SorveyNo");
                    try {
                        serializer.text(survayId);
                    } catch (Exception e) {

                    }
                    serializer.endTag(null, "SorveyNo");

                    /*serializer.startTag(null, "TIMECHANGED");
                    try{
                        serializer.text("00:20:17");
                    }
                    catch (Exception e)
                    {

                    }
                    serializer.endTag(null, "TIMECHANGED");*/

                    serializer.startTag(null, "ENDTIME");
                    try {
                        serializer.text(timeforxml);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ENDTIME");

                    serializer.startTag(null, "MONITORINGDATE");
                    try {
                        serializer.text(currentdateforxml);
                    } catch (Exception e) {

                    }
                    serializer.endTag(null, "MONITORINGDATE");


                    serializer.startTag(null, "Latitude");
                    try {
                        serializer.text(lat);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Latitude");

                    serializer.startTag(null, "Longitude");
                    try {
                        serializer.text(lng1);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Longitude");

                    serializer.startTag(null, "Accuracy");
                    try {
                        serializer.text(acc1);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Accuracy");


                    /*serializer.startTag(null, "Latitude");
                    serializer.text(objform.getLat());
                    serializer.endTag(null, "Latitude");

                    serializer.startTag(null, "Longitude");
                    serializer.text(objform.getLng());
                    serializer.endTag(null, "Longitude");*

                    serializer.startTag(null, "accuracy");
                    serializer.text(objform.getLaccuracy());
                    serializer.endTag(null, "accuracy");*/


                    serializer.startTag(null, "EmisCode");
                    try {
                        serializer.text(schoolemiscode);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "EmisCode");

                    serializer.startTag(null, "SchoolName");
                    try {
                        serializer.text(schoolname);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "SchoolName");

                    serializer.startTag(null, "LEVEL");
                    try {
                        serializer.text(level);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "LEVEL");

                    serializer.startTag(null, "District");
                    try {
                        serializer.text(district);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "District");

                    serializer.startTag(null, "Tehsil");
                    try {
                        serializer.text(tehsil);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Tehsil");

                    serializer.startTag(null, "UCName");
                    try {
                        serializer.text(ucname);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "UCName");

                    serializer.startTag(null, "Location");
                    try {
                        serializer.text(location);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Location");

                    serializer.startTag(null, "Season");
                    try {
                        serializer.text(zone);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Season");

                    serializer.startTag(null, "NANO");
                    try {
                        serializer.text(nano);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "NANO");

                    serializer.startTag(null, "PKNO");
                    try {
                        serializer.text(pkno);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "PKNO");

                    serializer.startTag(null, "CircleOfficeNo");
                    try {
                        serializer.text(circle_officename);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "CircleOfficeNo");

                    // /////////////////School Statu Screen/

                    serializer.startTag(null, "SchoolStatusReason");
                    try {
                        serializer.text(checkvalue);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "SchoolStatusReason");

                    serializer.startTag(null, "SchoolStatusReasonOther");
                    try {
                        serializer.text(reasonvalue);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "SchoolStatusReasonOther");

                    // //////////////LOCATION

                    serializer.startTag(null, "Locations");

                    serializer.startTag(null, "Location");

                    serializer.startTag(null, "Latitude");
                    try {
                        serializer.text(lat);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Latitude");

                    serializer.startTag(null, "Longitude");
                    try {
                        serializer.text(lng1);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Longitude");

                    serializer.startTag(null, "Accuracy");
                    try {
                        serializer.text(acc1);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Accuracy");

                    serializer.startTag(null, "Mock_Location");
                    try {
                        serializer.text("");
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Mock_Location");


                    serializer.endTag(null, "Location");

                    serializer.startTag(null, "Location");

                    serializer.startTag(null, "Latitude");
                    try {
                        serializer.text(lat2);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Latitude");

                    serializer.startTag(null, "Longitude");
                    try {
                        serializer.text(lng2);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Longitude");

                    serializer.startTag(null, "Accuracy");
                    try {
                        serializer.text(acc2);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Accuracy");

                    serializer.startTag(null, "Mock_Location");
                    try {
                        serializer.text("");
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Mock_Location");


                    serializer.endTag(null, "Location");

                    serializer.startTag(null, "Location");

                    serializer.startTag(null, "Latitude");
                    try {
                        serializer.text(lat3);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Latitude");

                    serializer.startTag(null, "Longitude");
                    try {
                        serializer.text(lng3);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Longitude");

                    serializer.startTag(null, "Accuracy");
                    try {
                        serializer.text(acc3);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Accuracy");

                    serializer.startTag(null, "Mock_Location");
                    try {
                        serializer.text("");
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Mock_Location");

                    serializer.endTag(null, "Location");

                    serializer.endTag(null, "Locations");

                    /*serializer.startTag(null, "buildingavailibility");
                    try {serializer.text(illegalvalue);}catch (Exception e) { }
                    serializer.endTag(null, "buildingavailibility");*/

                    serializer.startTag(null, "buildingavailibility");
                    try {
                        serializer.text(illegalvalue);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "buildingavailibility");

                    serializer.startTag(null, "buildingType");
                    try {
                        serializer.text(occupationvalue);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "buildingType");

                    serializer.startTag(null, "ClassAvailability");
                    try {
                        serializer.text(classavailable);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ClassAvailability");

                    if (classavailable.equals("Yes")) {
                        serializer.startTag(null, "Classes");
                        try {
                            serializer.text(classroominput);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Classes");
                    } else {

                    }

                    serializer.startTag(null, "Veranda");
                    try {
                        serializer.text(varandainput);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Veranda");

                    serializer.startTag(null, "ToiletAvailability");
                    try {
                        serializer.text(g);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ToiletAvailability");

                    serializer.startTag(null, "ToiletFunctionality");
                    try {
                        serializer.text(h);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ToiletFunctionality");

                    serializer.startTag(null, "WaterAvailability");
                    try {
                        serializer.text(c);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WaterAvailability");

                    serializer.startTag(null, "WaterFunctionality");
                    try {
                        serializer.text(d);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WaterFunctionality");

                    serializer.startTag(null, "ElectricityAvailabality");
                    try {
                        serializer.text(a);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ElectricityAvailabality");

                    serializer.startTag(null, "ElectricityFuctionality");
                    try {
                        serializer.text(b);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ElectricityFuctionality");


                    ////TEACHER PRESENCE////


                    serializer.startTag(null, "TEACHERPRESENCE");

                    for (int i = 0; i < teacherdetails
                            .size(); i++) {
                        tdetails = teacherdetails.get(i);
                        serializer.startTag(null, "TEACHER");

                        serializer.startTag(null, "Name");
                        try {
                            serializer.text(tdetails.getTeachername());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Name");

                        serializer.startTag(null, "Gender");
                        try {
                            serializer.text(tdetails.getTeachergender());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Gender");

                        serializer.startTag(null, "ContactNo");
                        try {
                            serializer.text(tdetails.getTeacherno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ContactNo");

                        serializer.startTag(null, "CNIC");
                        try {
                            serializer.text(tdetails.getTeachercnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "CNIC");

                        serializer.startTag(null, "Status");
                        try {
                            serializer.text(tdetails.getAttendance());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Status");

                        if (tdetails.getAttendance().equals("Absent")) {

                            serializer.startTag(null, "StatusDetails");
                            try {
                                serializer.text(tdetails.getTeacherattendancedetails());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "StatusDetails");


                            serializer.startTag(null, "ReplacementAvailable");
                            try {
                                serializer.text(tdetails.getReplacementavailable());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ReplacementAvailable");

                            if (tdetails.getReplacementavailable().equals("Yes")) {

                                serializer.startTag(null, "ReplacementName");
                                try {
                                    serializer.text(tdetails.getReplacementname());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "ReplacementName");

                                serializer.startTag(null, "ReplacementGender");
                                try {
                                    serializer.text(tdetails.getReplacementgender());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "ReplacementGender");
                            } else {

                            }
                        } else {

                        }

                        serializer.endTag(null, "TEACHER");
                    }

                    serializer.endTag(null, "TEACHERPRESENCE");

                    serializer.startTag(null, "HeadMasterName");
                    try {
                        serializer.text(headinstitute);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "HeadMasterName");

                    serializer.startTag(null, "Designation");
                    try {
                        serializer.text(headDesig);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Designation");

                    serializer.startTag(null, "SchoolHeadmasterContactNo");
                    try {
                        serializer.text(headcell);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "SchoolHeadmasterContactNo");

                    serializer.startTag(null, "TotalTeacher");
                    try {
                        serializer.text(school_register);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "TotalTeacher");

                    serializer.startTag(null, "TotalTeacherPresent");
                    try {
                        serializer.text(physical_present);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "TotalTeacherPresent");


                    //school visit

                    serializer.startTag(null, "SchoolVisits");

                    for (int i = 0; i < schoolVisits
                            .size(); i++) {

                        schoolvis = schoolVisits.get(i);
                        serializer.startTag(null, "Visit");

                        serializer.startTag(null, "VisitDate");
                        try {
                            serializer.text(schoolvis.getVisitdate());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "VisitDate");

                        serializer.startTag(null, "Designation");
                        try {
                            serializer.text(schoolvis.getVisitby());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Designation");

                        serializer.startTag(null, "DesignationOther");
                        try {
                            serializer.text(schoolvis.getDesignationother());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DesignationOther");

                        serializer.endTag(null, "Visit");

                    }

                    serializer.endTag(null, "SchoolVisits");


                    //ENROLLMENT ATTENDANCE GAP////
                    serializer.startTag(null, "STUDENTENROLLMENTATTANDANCEGAP");

                    serializer.startTag(null, "ENROLLMENT");

                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("UnAdmitted");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(uuu1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(uuu2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(uuu3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(uuu4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.startTag(null, "ENROLLMENT");

                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("Kachi");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(kkk1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(kkk2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(kkk3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(kkk4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.startTag(null, "ENROLLMENT");

                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("Class 1");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(one1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(one2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(one3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(one4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.startTag(null, "ENROLLMENT");

                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("Class 2");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(two1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(two2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(two3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(two4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.startTag(null, "ENROLLMENT");

                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("Class 3");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(three1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(three2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(three3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(three4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.startTag(null, "ENROLLMENT");
                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("Class 4");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(four1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(four2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(four3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(four4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.startTag(null, "ENROLLMENT");
                    serializer.startTag(null, "CLASSNAME");
                    try {
                        serializer.text("Class 5");
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "CLASSNAME");

                    serializer.startTag(null, "STUDENTAPREGISTER");
                    try {
                        serializer.text(five1);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPREGISTER");

                    serializer.startTag(null, "STUDENTAPHEADCOUNT");
                    try {
                        serializer.text(five2);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "STUDENTAPHEADCOUNT");

                    serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                    try {
                        serializer.text(five3);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                    serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                    try {
                        serializer.text(five4);
                    } catch (Exception e) {
                    }
                    ;
                    serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                    serializer.endTag(null, "ENROLLMENT");

                    serializer.endTag(null, "STUDENTENROLLMENTATTANDANCEGAP");

                    serializer.startTag(null, "REMARKS");
                    try {
                        serializer.text(finalremarks);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "REMARKS");



                    serializer.endTag(null, "SCHOOLINFORMATION");
                    serializer.endDocument();

                    serializer.flush();

                    /*SharedPreferences.Editor edit1 = prefs.edit();

                    edit1.putString("filling", "0");
                    edit1.putString("againfilling", "0");
                    edit1.commit();*/

                    Toast.makeText(FormSavingGcs.this, "File Saved Successfully",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                    new PostDataAsyncTaskSaveForm(formId).execute();
                    databasehandler.removeRosteremis(emisCode);

                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(
                                new FileOutputStream(new File(
                                        "/sdcard/save_object.bin")));

                        oos.writeObject("");
                        oos.flush();
                        oos.close();
                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }

                    try {
                        //PreferencesData.saveString(getActivity(),
                        //      "timeChanged", "false");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    fileos.close();
                   /* String emisCode = (prefs.getString("formemiscode", ""));
                    String dmoName = prefs.getString("formdmoname", "");
                    int month = prefs.getInt("formmonth", -1);
                    int year = prefs.getInt("formyear", -1);
                    String district = prefs.getString("formdistrict", "");
                    String monitorName = prefs.getString("formmonitorname", "");
                    String visitType = prefs.getString("formvisittype", "");
                    roaster = new Roaster();
                    roaster.setEmisCode(emisCode + "");
                    roaster.setDmoName(dmoName);
                    roaster.setMonth(month);
                    roaster.setYear(year);
                    roaster.setDistrict(district);
                    roaster.setMonitorName(monitorName);
                    roaster.setVisitype(visitType);

                    db.updateRoaster(roaster);

                    FormModel.formsaved = 1;*/

                    /*byte[] encryptedText = CryptoUtils.encrypt(key, newxmlfile);

                    newxmlfile.delete();*/

                    byte[] encryptedText = CryptoUtils.encrypt(key, newxmlfile);

                    // newxmlfile.delete();

                    File encryptfile = new File(newxmlfile, "");
                    try {
                        encryptfile.createNewFile();
                        FileOutputStream fileStream = new FileOutputStream(encryptfile);
                        fileStream.write(encryptedText);

                    } catch (IOException e) {
                        Log.d("Error on create File", "exception in createNewFile() method");
                    }

                } catch (Exception e) {
                    Toast.makeText(FormSavingGcs.this, "Error while saving", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);

                    //newxmlfile.delete();

                    Log.e("Exception", "error occurred while creating xml file");
                }

            }
        });
    }

    public class PostDataAsyncTaskSaveForm extends AsyncTask<String, String, String> {
        private String data;

        public PostDataAsyncTaskSaveForm(String textone) {
            data = textone;


        }

        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String postReceiverUrl = savedschools;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("id", data));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        Log.d("Data", "No Data Found");

                    } else {
                        try {

                        } catch (Exception e) {
                            Log.d("Error", e.getMessage().toString());
                        }
                    }
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //hidepDialog();
            Log.v("SuccesS", "Response: " + s);
            //finish();
            //startActivity(new Intent(Roster_List.this,Roster_List.class))

        }

    }
}
