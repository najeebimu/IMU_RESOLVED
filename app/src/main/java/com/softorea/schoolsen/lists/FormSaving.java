package com.softorea.schoolsen.lists;

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
import com.softorea.schoolsen.m_monthly.Grant;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostList;
import com.softorea.schoolsen.models.DetailsAppointedBy;
import com.softorea.schoolsen.models.DetailsEnrollmentAgeBoys;
import com.softorea.schoolsen.models.DetailsEnrollmentAgeGirls;
import com.softorea.schoolsen.models.DetailsEnrollmentAttendaanceGap;
import com.softorea.schoolsen.models.DetailsEnrollmentSpecialBoys;
import com.softorea.schoolsen.models.DetailsEnrollmentSpecialGirls;
import com.softorea.schoolsen.models.DetailsNonTeacherwebservice;
import com.softorea.schoolsen.models.DetailsProxyTeacher;
import com.softorea.schoolsen.models.DetailsSanctionedNonteachingPosts;
import com.softorea.schoolsen.models.DetailsSanctionedPosts;
import com.softorea.schoolsen.models.DetailsSchoolVisit;
import com.softorea.schoolsen.models.DetailsTeacherwebservice;
import com.softorea.schoolsen.models.DetailsVacant;
import com.softorea.schoolsen.models.FormModel;
import com.softorea.schoolsen.models.GcsTeacherDetails;
import com.softorea.schoolsen.models.NonTeacherNewDetails;
import com.softorea.schoolsen.models.ProvisionFreeTextBookModel;
import com.softorea.schoolsen.models.RosterDetails;
import com.softorea.schoolsen.models.TeacherNewDetails;
import com.softorea.schoolsen.models.TrainingRecord;

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


public class FormSaving extends Activity {
    String schoolareaStrScreen,constructionStrScreen,buildindconditionStrScreen,itlabStrScreen,
            commodititesStrScreen,ptcStrScreen,infrastructureStrScreen,guideStrScreen,cleanlinessStrScreen
            ,stipendStrScreen,SanctionedStrScreen,indicatorStrScreen,
            enrollmentageStrScreen,enrollmentgrpStrScreen,disabledStrScreen,securityStrScreen,ftbStrScreen,furnitureStrScreen;
    public static final String MyPREFERENCES = "MyPrefs";
    public ArrayList<DetailsProxyTeacher> proxyTeacherList;
    public ArrayList<Grant> grantlist;
    public ArrayList<DetailsEnrollmentAttendaanceGap> eagList;
    public ArrayList<DetailsEnrollmentAgeBoys> eageboysList;
    public ArrayList<DetailsEnrollmentAgeGirls> eagegirlsList;
    public ArrayList<DetailsEnrollmentSpecialBoys> specialboysList;
    public ArrayList<DetailsEnrollmentSpecialGirls> specialgirlsList;
    public ArrayList<ProvisionFreeTextBookModel> pbooks;
    public ArrayList<DetailsSchoolVisit> schoolVisits;
    public ArrayList<TeacherNewDetails> teacherdetails;
    public ArrayList<NonTeacherNewDetails> nonteacherdetails;
    public ArrayList<TrainingRecord> trainignrecord;
    public ArrayList<DetailsAppointedBy> appointedby;
    public ArrayList<DetailsVacant> vacantpostion;
    public ArrayList<DetailsSanctionedPosts> sanctionedPosts;
    public ArrayList<DetailsSanctionedNonteachingPosts> nonsanctionedPosts;
    public ArrayList<DetailsNonTeacherwebservice> Nonteacherdetailsweb;
    public ArrayList<DetailsTeacherwebservice> teacherdetailsweb;
    String savedschools = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=updateSavedSchools_v1";
    String srcw;
    String high, middle, highsec, primary;
    FormModel objform;
    DatabaseHandler db;
    Button saveButtton;
    String formId;
    String emisCode, finalremarks;
    String monitorLogin;
    String currentDate, currentdateforxml, hq, hw, he, hr, anyother;
    SimpleDateFormat sdf, sdff;
    String fileName, surveyidfile;
    RosterDetails roaster;
    String endtime;
    String time;
    SharedPreferences prefs;
    String key = "67E5DE12EC86119F";
    String survayId;
    String startingTime, endingTime;
    GPSTracker gpsTracker;
    String versionName = "0";
    String visittypee, asdeoname, asdeono, reasonvalue, checkvalue, illegalvalue, typevalue, occupationvalue, BuildingOccupationdate;
    String schoolemiscode, schoolname, gender, level, ddocode, district, tehsil, ucname, villagecouncil, villagecity, locality, street,
            phoneno, location, zone, nano, pkno, circle_officename, sdeoname, ownership, typeOfupgradation, upgradationprimary,
            upgradationmiddle, upgradationhigh, upgradationhighsecondary, totalLand;
    String headinstitute, headcell, headDesig;
    DetailsProxyTeacher details;
    DetailsAppointedBy detailsAppointed;
    DatabaseHandler databasehandler;
    DetailsSchoolVisit schoolvis;
    DetailsVacant dt;
    DetailsSanctionedPosts sp;
    DetailsSanctionedNonteachingPosts nonsp;
    TeacherNewDetails tdetails;
    DetailsTeacherwebservice tdetailsweb;

    DetailsNonTeacherwebservice Nontdetailsweb;

    ProvisionFreeTextBookModel pdetails;
    Grant grantdetails;
    DetailsEnrollmentAttendaanceGap eagdetails;
    DetailsEnrollmentAgeBoys eagedetails;
    DetailsEnrollmentAgeGirls eagegirlsdetails;
    DetailsEnrollmentSpecialBoys specialboysdetails;
    DetailsEnrollmentSpecialGirls specialgirlsdetails;
    NonTeacherNewDetails nontdetails;
    TrainingRecord trecord;
    String id, e;
    String lat, lat2, lat3, lng1, lng2, lng3, acc1, acc2, acc3;
    GPSTracker gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_form);
        objform = new FormModel();
        Random r = new Random();
        gps = new GPSTracker(FormSaving.this);
        pbooks = new ArrayList<ProvisionFreeTextBookModel>();
        grantlist = new ArrayList<Grant>();


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

        SharedPreferences preferencess = PreferenceManager.getDefaultSharedPreferences(this);
        id = preferencess.getString("emiscode", "");
        lat = preferencess.getString("lat1", "");
        lat2 = preferencess.getString("lat2", "");
        lng1 = preferencess.getString("lng1", "");
        lng2 = preferencess.getString("lng2", "");
        acc1 = preferencess.getString("acc1", "");
        acc2 = preferencess.getString("acc2", "");
        final SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase dbd = database.getReadableDatabase();
        Cursor cursorform = dbd.rawQuery("SELECT * FROM t_roster WHERE emis= " + id, null);
        if (cursorform.moveToFirst()) {
            do {
                formId = cursorform.getString(cursorform.getColumnIndex("RosterformId"));
            } while (cursorform.moveToNext());
        }
        cursorform.close();
        dbd.close();

        final String sdeoofcename = preferencess.getString("sdeoofficename", "");
        //formId = preferencess.getString("formidd", "");
        databasehandler = new DatabaseHandler(FormSaving.this);
        proxyTeacherList = databasehandler.getProxyTeacher(id);
        appointedby = databasehandler.getAppointedBy(id);
        schoolVisits = databasehandler.getVisitBy(id);
        vacantpostion = databasehandler.getVacantPosition(id);
        sanctionedPosts = databasehandler.getSanctionedPost(id);
        nonsanctionedPosts = databasehandler.getNonTeachingSanctionedPost(id);
        teacherdetails = databasehandler.getGcsTeacher(id);
        nonteacherdetails = databasehandler.getGcsNonTeacher(id);
        trainignrecord = databasehandler.getTrainingRecord(id);
        pbooks = databasehandler.getftbxml(formId);
        grantlist = databasehandler.getgrantxml(id);
        eagList = databasehandler.geteagxml(id);
        eageboysList = databasehandler.getEAgeboysxml(id);
        eagegirlsList = databasehandler.getEAgegirlsxml(id);
        specialboysList = databasehandler.getspecialboysxml(id);
        specialgirlsList = databasehandler.getspecialgirlsxml(id);

        Nonteacherdetailsweb = databasehandler.NONteacherwebserviceList(id);
        teacherdetailsweb = databasehandler.teacherwebserviceList(id);
        // details = proxyTeacherList.get;
        //Toast.makeText(FormSavingGcs.this, "Value is" + details, Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);

        final String ethicavail_ = preferences.getString("ethicAvail", "");
        final String ethictype_ = preferences.getString("ethicType", "");
        final String topicname = preferences.getString("topicName", "");

        final String ifTeachingAbsent = preferences.getString("case2", "");

        srcw = preferences.getString("electricSrc", "");
        hq = preferences.getString("anyothername2", "");
        hw = preferences.getString("otherUseable2", "");
        he = preferences.getString("otherUnUseable2", "");
        hr = preferences.getString("otherNewRequired2", "");
        anyother = preferences.getString("anyothername", "");
        e = preferences.getString("noOfsanSubject", "");
        finalremarks = preferences.getString("finalremarks", " ");
        final String asdeoyrt = preferences.getString("AsdeovisitType", "");
        final String flagcheck = preferences.getString("flagcheck", "");
        final String grantcheck = preferences.getString("grantcheck", "");
        asdeoname = preferences.getString("asdeoname", "");
        asdeono = preferences.getString("asdeocontactno", "");
        reasonvalue = preferences.getString("reasonOthers", "");
        checkvalue = preferences.getString("checkvalue", "");


        illegalvalue = preferences.getString("illegalkey", "");
        typevalue = preferences.getString("occupation_type", "");
        occupationvalue = preferences.getString("occupation_occupied", "");
        BuildingOccupationdate = preferences.getString("occupation_date", "");

        headinstitute = preferences.getString("headofinstitution", "");
        headcell = preferences.getString("headcellno", "");
        headDesig = preferences.getString("headdesignation", "");

        final String a6groupsection = preferences.getString("6groupsection1", "");
        final String b6groupsection = preferences.getString("6groupsection2", "");
        final String a7groupsection = preferences.getString("7groupsection1", "");
        final String b7groupsection = preferences.getString("7groupsection2", "");
        final String a8groupsection = preferences.getString("8groupsection1", "");
        final String b8groupsection = preferences.getString("8groupsection2", "");
        final String a9Sgroupsection = preferences.getString("9Sgroupsection1", "");
        final String b9Sgroupsection = preferences.getString("9Sgroupsection2", "");
        final String a9Agroupsection = preferences.getString("9Agroupsection1", "");
        final String b9Agroupsection = preferences.getString("9Agroupsection2", "");
        final String a9Cgroupsection = preferences.getString("9Cgroupsection1", "");
        final String b9Cgroupsection = preferences.getString("9Cgroupsection2", "");
        final String a10Sgroupsection = preferences.getString("10Sgroupsection1", "");
        final String b10Sgroupsection = preferences.getString("10Sgroupsection2", "");
        final String a10Agroupsection = preferences.getString("10Agroupsection1", "");
        final String b10Agroupsection = preferences.getString("10Agroupsection2", "");
        final String a10Cgroupsection = preferences.getString("10Cgroupsection1", "");
        final String b10Cgroupsection = preferences.getString("10Cgroupsection2", "");

        final String enrol_11_Medical = preferences.getString("enrol_11_Medical", "");
        final String sec_11_Medical = preferences.getString("sec_11_Medical", "");
        final String enrol_11_Eng = preferences.getString("enrol_11_Eng", "");
        final String sec_11_Eng = preferences.getString("sec_11_Eng", "");
        final String enrol_11_Arts = preferences.getString("enrol_11_Arts", "");
        final String sec_11_Arts = preferences.getString("sec_11_Arts", "");
        final String enrol_11_Inter = preferences.getString("enrol_11_Inter", "");
        final String sec_11_Inter = preferences.getString("sec_11_Inter", "");
        final String enrol_12_Medical = preferences.getString("enrol_12_Medical", "");
        final String sec_12_Medical = preferences.getString("sec_12_Medical", "");
        final String enrol_12_Eng = preferences.getString("enrol_12_Eng", "");
        final String sec_12_Eng = preferences.getString("sec_12_Eng", "");
        final String enrol_12_Arts = preferences.getString("enrol_12_Arts", "");
        final String sec_12_Arts = preferences.getString("sec_12_Arts", "");
        final String enrol_12_Inter = preferences.getString("enrol_12_Inter", "");
        final String sec_12_Inter = preferences.getString("sec_12_Inter", "");

        final String a = preferences.getString("electriAvailable", "");
        final String b = preferences.getString("electricFunction", "");
        final String c = preferences.getString("waterAvail", "");
        final String d = preferences.getString("waterFunction", "");
        final String ee = preferences.getString("watersource", "");
        final String f = preferences.getString("drinkable", "");
        final String g = preferences.getString("toiletAvail", "");
        final String h = preferences.getString("toiletFunction", "");
        final String i = preferences.getString("boundaryAvail", "");
        final String j = preferences.getString("boundaryFunction", "");
        final String k = preferences.getString("stdToilet", "");
        final String l = preferences.getString("TeachToilet", "");
        final String m = preferences.getString("comToilet", "");
        final String n = preferences.getString("stdToiletFunc", "");
        final String o = preferences.getString("teacherToiletFunc", "");
        final String p = preferences.getString("commonToiletFunction", "");

        final String covered = preferences.getString("schoolcoveredaread", "");
        final String uncovered = preferences.getString("schooluncoveredaread", "");
        final String brooms = preferences.getString("basementrooms", "");
        final String grooms = preferences.getString("groundrooms", "");
        final String frooms = preferences.getString("firstrooms", "");
        final String srooms = preferences.getString("secondrooms", "");
        final String trooms = preferences.getString("thirdrooms", "");
        final String SPINNER = preferences.getString("unvoeredaaread", "");
        final String distanceInMeters = preferences.getString("distanceInMeters", "");

        final String coveredd = preferences.getString("pakkaclass", "");
        final String uncoveredd = preferences.getString("pakkaother", "");
        final String broomsd = preferences.getString("kachaclass", "");
        final String groomsd = preferences.getString("kachaothers", "");


        final String noOfClassrooms = preferences.getString("noOfClassrooms", "");
        final String noOfOtherRooms = preferences.getString("noOfOtherRooms", "");
        final String noOfClassroomsMajor = preferences.getString("noOfClassroomsMajor", "");
        final String noOfOtherroomsMajor = preferences.getString("noOfOtherroomsMajor", "");
        final String WHOLENEEDS = preferences.getString("WHOLENEEDS", "");


        final String ITlABexists = preferences.getString("SLevelll", "");
        final String Internet = preferences.getString("InternetFun", "");
        final String labEst = preferences.getString("LabEst", "");
        final String labFun = preferences.getString("LabFun", "");
        final String noOfComputers = preferences.getString("noOfComputers", "");
        final String noOfFunctionalComputers = preferences.getString("noOfFunctionalComputers", "");
        final String firmname = preferences.getString("nameOfFirm", "");

        final String twouse = preferences.getString("twoUseable", "");
        final String twounuse = preferences.getString("twoUnUseable", "");
        final String twonew = preferences.getString("twoNewRequired", "");
        final String threeuse = preferences.getString("threeUseable", "");
        final String threeunuse = preferences.getString("threeUnUseable", "");
        final String threenew = preferences.getString("threeNewRequired", "");
        final String benchuse = preferences.getString("benchesUseable", "");
        final String benchunuse = preferences.getString("benchesUnUseable", "");
        final String benchnew = preferences.getString("benchesNewRequired", "");
        final String chairuse = preferences.getString("chairsUseable", "");
        final String chairunuse = preferences.getString("chairsUnUseable", "");
        final String chairnew = preferences.getString("chairsNewRequired", "");
        final String tabletuse = preferences.getString("tabletchairsUseable", "");
        final String tabletunuse = preferences.getString("tabletchairsUnUseable", "");
        final String tabletnew = preferences.getString("tabletchairsNewRequired", "");
        final String tatsuse = preferences.getString("tatsUseable", "");
        final String tatsunuse = preferences.getString("tatsUnUseable", "");
        final String tatsnew = preferences.getString("tatsNewRequired", "");
        final String fansuse = preferences.getString("fansUseable", "");
        final String fansunuse = preferences.getString("fansUnUseable", "");
        final String fansnew = preferences.getString("fansNewRequired", "");
        final String otheruse = preferences.getString("otherUseable", "");
        final String otherunuse = preferences.getString("otherUnUseable", "");
        final String othernew = preferences.getString("otherNewRequired", "");


        final String ptclast = preferences.getString("PTC_lastelection", "");
        final String ptcdoe = preferences.getString("PTC_DOE", "");
        final String ptcnamee = preferences.getString("PTC_name", "");
        final String ptcnoo = preferences.getString("PTC_no", "");
        final String ptcbanknamee = preferences.getString("PTC_bakname", "");
        final String ptcaccno = preferences.getString("PTC_acoountno", "");
        final String ptcbcode = preferences.getString("PTC_branchcode", "");
        final String ptcblnc = preferences.getString("PTC_balance", "");
        final String ptcamnt = preferences.getString("PTC_amount", "");

        final String amntothers = preferences.getString("PTC_amountothers", "");
        final String expendituress = preferences.getString("PTC_expenditures", "");
        final String presentbalancee = preferences.getString("PTC_presentbalance", "");
        final String lastmnthmetng = preferences.getString("PTC_lastmonthmeeting", "");

        final String ptcestvalue = preferences.getString("PTC_EstablishSpinner", "");
        final String ptctrainedvalue = preferences.getString("PTC_MembersTrainedSpinner", "");
        final String bankstatementshown = preferences.getString("PTC_BankStatementStr", "");


        final String htStr = preferences.getString("htStr", "");
        final String scienceStr = preferences.getString("scienceStr", "");
        final String itLabStr = preferences.getString("itLabStr", "");
        final String staffStr = preferences.getString("staffStr", "");
        final String libStr = preferences.getString("libStr", "");
        final String clerkStr = preferences.getString("clerkStr", "");
        final String examStr = preferences.getString("examStr", "");
        final String playStr = preferences.getString("playStr", "");
        final String oneStr = preferences.getString("oneStr", "");
        final String alternateStr = preferences.getString("alternateStr", "");


        final String rs = preferences.getString("stipend_recordshown", "");
        final String sy = preferences.getString("stipend_year", "");
        final String stipend_schemeName6 = preferences.getString("stipend_SchemeName6", "");
        final String stipend_eligibleStudents6 = preferences.getString("stipend_eligibleStudents6", "");
        final String stipend_EligibleNoStipend6 = preferences.getString("stipend_EligibleNoStipend6", "");
        final String stipend_SchemeName7 = preferences.getString("stipend_SchemeName7", "");
        final String stipend_eligibleStudents7 = preferences.getString("stipend_eligibleStudents7", "");
        final String stipend_EligibleNoStipend7 = preferences.getString("stipend_EligibleNoStipend7", "");
        final String stipend_SchemeName8 = preferences.getString("stipend_SchemeName8", "");
        final String stipend_eligibleStudents8 = preferences.getString("stipend_eligibleStudents8", "");
        final String stipend_EligibleNoStipend8 = preferences.getString("stipend_EligibleNoStipend8", "");
        final String stipend_SchemeName9 = preferences.getString("stipend_SchemeName9", "");
        final String stipend_eligibleStudents9 = preferences.getString("stipend_eligibleStudents9", "");
        final String stipend_EligibleNoStipend9 = preferences.getString("stipend_EligibleNoStipend9", "");
        final String stipend_SchemeName10 = preferences.getString("stipend_SchemeName10", "");
        final String stipend_eligibleStudents10 = preferences.getString("stipend_eligibleStudents10", "");
        final String stipend_EligibleNoStipend10 = preferences.getString("stipend_EligibleNoStipend10", "");
        final String stipend_10remarks = preferences.getString("stipend_remarks", "");
        final String stipend_6remarks = preferences.getString("stipend_total6", "");
        final String stipend_7remarks = preferences.getString("stipend_total7", "");
        final String stipend_8remarks = preferences.getString("stipend_total8", "");
        final String stipend_9remarks = preferences.getString("stipend_total9", "");

        final String teachrguide = preferences.getString("Teachrguide", "");

        final String schCleanN = preferences.getString("schClean", "");
        final String stdCleanN = preferences.getString("stdClean", "");

        final String Facilities_SLevel = preferences.getString("Facilities_SLevel", "");
        final String Facilities_officeStr = preferences.getString("Facilities_officeStr", "");
        final String Facilities_storeStr = preferences.getString("Facilities_storeStr", "");
        final String Facilities_labStr = preferences.getString("Facilities_labStr", "");
        final String Facilities_EntraceStr = preferences.getString("Facilities_EntraceStr", "");
        final String Facilities_BuildingStr = preferences.getString("Facilities_BuildingStr", "");
        final String Facilities_toiletsStr = preferences.getString("Facilities_toiletsStr", "");
        final String Facilities_hcapacity = preferences.getString("Facilities_hcapacity", "");
        final String SM_HEIGHT = preferences.getString("SM_HEIGHT", "");
        final String SM_GUARDS = preferences.getString("SM_GUARDS", "");
        final String SM_CHOWKIDAR = preferences.getString("SM_CHOWKIDAR", "");
        final String SM_WEAPONS = preferences.getString("SM_WEAPONS", "");
        final String SM_METAL = preferences.getString("SM_METAL", "");
        final String SM_BARBED = preferences.getString("SM_BARBED", "");
        final String SM_GLASS = preferences.getString("SM_GLASS", "");
        final String SM_ENTRANCE = preferences.getString("SM_ENTRANCE", "");
        final String SM_SOS = preferences.getString("SM_SOS", "");

        final String Fur_BLACK = preferences.getString("Fur_BLACK", "");
        final String Fur_TABLES = preferences.getString("Fur_TABLES", "");
        final String Fur_CHAIRS = preferences.getString("Fur_CHAIRS", "");
        final String Fur_ALMIRAH = preferences.getString("Fur_ALMIRAH", "");

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
        gender = prefs.getString("gender", "");
        level = prefs.getString("schoollevel", "");
        ddocode = prefs.getString("ddocode", "");
        district = prefs.getString("districtname", "");
        tehsil = prefs.getString("tehsilname", "");
        ucname = prefs.getString("ucnamename", "");
        villagecouncil = prefs.getString("villagecouncilname", "");
        villagecity = prefs.getString("villagecityname", "");
        locality = prefs.getString("localityname", "");
        street = prefs.getString("streetname", "");
        phoneno = prefs.getString("schoolphoneno", "");
        location = prefs.getString("locationn", "");
        zone = prefs.getString("zoneschool", "");
        nano = prefs.getString("nano", "");
        pkno = prefs.getString("pkno", "");
        circle_officename = prefs.getString("circloffice", "");
        ownership = prefs.getString("buildingownership", "");
        typeOfupgradation = prefs.getString("upgradationtype", "");
        upgradationprimary = prefs.getString("upgraprimary", "");
        upgradationmiddle = prefs.getString("upgramiddle", "");
        upgradationhigh = prefs.getString("upgrahigh", "");
        upgradationhighsecondary = prefs.getString("upgrahighsec", "");
        totalLand = prefs.getString("landdonated", "");

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

        SQLiteOpenHelper databasee = new DatabaseHandler(FormSaving.this);
        SQLiteDatabase dbda = databasee.getReadableDatabase();
        Cursor cursorformss = dbda.rawQuery("SELECT * FROM t_screenconfig", null);
        if (cursorformss.moveToFirst()) {
            do {
                schoolareaStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_SchoolArea"));
                constructionStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_NatureOfConstruction"));
                buildindconditionStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_BuildingCondition"));
                itlabStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_ITLab"));
                commodititesStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_Commodities"));
                ptcStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_PTC"));
                infrastructureStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_Infrastructure"));
                guideStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_Guides"));
                cleanlinessStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_Cleanliness"));
                stipendStrScreen = cursorformss.getString(cursorformss.getColumnIndex("Bi_Stipend"));
                disabledStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_DisabledStudent"));
                enrollmentageStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_EnrollmentByAge"));
                enrollmentgrpStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_EnrollmentByGroup"));
                ftbStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_FTB"));
                furnitureStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_Furniture"));
                indicatorStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_Indicator"));
                SanctionedStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_SantionedPosts"));
                securityStrScreen = cursorformss.getString(cursorformss.getColumnIndex("An_SecurityMeasures"));
            } while (cursorformss.moveToNext());
        }
        cursorformss.close();
        dbda.close();


        saveButtton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(FormSaving.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormSaving.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
                    serializer.setFeature(
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

                    serializer.startTag(null, "Gender");
                    try {
                        serializer.text(gender);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Gender");

                    serializer.startTag(null, "LEVEL");
                    try {
                        serializer.text(level);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "LEVEL");

                    serializer.startTag(null, "DDOCode");
                    try {
                        serializer.text(ddocode);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "DDOCode");

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

                    serializer.startTag(null, "Village_CouncilName");
                    try {
                        serializer.text(villagecouncil);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Village_CouncilName");

                    serializer.startTag(null, "Village_CityName");
                    try {
                        serializer.text(villagecity);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Village_CityName");

                    serializer.startTag(null, "LocalityName");
                    try {
                        serializer.text(locality);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "LocalityName");

                    serializer.startTag(null, "StreetName");
                    try {
                        serializer.text(street);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "StreetName");

                    serializer.startTag(null, "PhoneNo");
                    try {
                        serializer.text(phoneno);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "PhoneNo");

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

                    serializer.startTag(null, "SDEO_OfficeName");
                    try {
                        serializer.text(sdeoofcename);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "SDEO_OfficeName");

                    serializer.startTag(null, "BuildingOwnership");
                    try {
                        serializer.text(ownership);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "BuildingOwnership");

                    serializer.startTag(null, "Upgradation_Type");
                    try {
                        serializer.text(typeOfupgradation);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Upgradation_Type");

                    serializer.startTag(null, "Upgradation_Year_Primary");
                    try {
                        serializer.text(upgradationprimary);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Upgradation_Year_Primary");

                    serializer.startTag(null, "Upgradation_Year_Middle");
                    try {
                        serializer.text(upgradationmiddle);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Upgradation_Year_Middle");

                    serializer.startTag(null, "Upgradation_Year_High");
                    try {
                        serializer.text(upgradationhigh);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Upgradation_Year_High");

                    serializer.startTag(null, "Upgradation_Year_HighSecondary");
                    try {
                        serializer.text(upgradationhighsecondary);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "Upgradation_Year_HighSecondary");

                    serializer.startTag(null, "LAND_ALLOCATED");
                    try {
                        serializer.text(totalLand);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "LAND_ALLOCATED");


                    serializer.startTag(null, "visittype");
                    try {
                        serializer.text(asdeoyrt);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "visittype");

                    serializer.startTag(null, "NameOfADO");
                    try {
                        serializer.text(asdeoname);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "NameOfADO");

                    serializer.startTag(null, "ContactNo");
                    try {
                        serializer.text(asdeono);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ContactNo");

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

                    serializer.startTag(null, "EthicActivityAvailable");
                    try {
                        serializer.text(ethicavail_);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "EthicActivityAvailable");

                    serializer.startTag(null, "EthicActivityType");
                    try {
                        serializer.text(ethictype_);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "EthicActivityType");

                    serializer.startTag(null, "TopicName");
                    try {
                        serializer.text(topicname);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "TopicName");

                    serializer.startTag(null, "StatusofOccupation");
                    try {
                        serializer.text(illegalvalue);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "StatusofOccupation");

                    serializer.startTag(null, "OccupiedBy");
                    try {
                        serializer.text(occupationvalue);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "OccupiedBy");

                    serializer.startTag(null, "TimeSinceOccupation");
                    try {
                        serializer.text(BuildingOccupationdate);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "TimeSinceOccupation");


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

                        serializer.startTag(null, "FatherName");
                        try {
                            serializer.text(tdetails.getTeacherfathername());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "FatherName");

                        serializer.startTag(null, "Gender");
                        try {
                            serializer.text(tdetails.getTeachergender());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Gender");

                        serializer.startTag(null, "MaritalStatus");
                        try {
                            serializer.text(tdetails.getTeachermarital());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "MaritalStatus");

                        serializer.startTag(null, "BPS");
                        try {
                            serializer.text(tdetails.getTeacherbps());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BPS");

                        serializer.startTag(null, "ContactNo");
                        try {
                            serializer.text(tdetails.getTeacherno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ContactNo");

                        serializer.startTag(null, "PersonalNo");
                        try {
                            serializer.text(tdetails.getTeacheraccountno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PersonalNo");


                        serializer.startTag(null, "CNIC");
                        try {
                            serializer.text(tdetails.getTeachercnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "CNIC");

                        serializer.startTag(null, "DOB");
                        try {
                            serializer.text(tdetails.getTeacherdob());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DOB");

                        serializer.startTag(null, "H_QualificationLevel");
                        try {
                            serializer.text(tdetails.getTeacherhighestlevel());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationLevel");

                        serializer.startTag(null, "H_QualificationSubject");
                        try {
                            serializer.text(tdetails.getTeacherhigestsubject());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationSubject");

                        serializer.startTag(null, "DateOf1stAppointment");
                        try {
                            serializer.text(tdetails.getTeacherdateoffirst());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateOf1stAppointment");

                        serializer.startTag(null, "District");
                        try {
                            serializer.text(tdetails.getTeacherdistrict());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "District");

                        serializer.startTag(null, "H_ProfessionalQualification");
                        try {
                            serializer.text(tdetails.getTeacherhighestqualification());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_ProfessionalQualification");

                        serializer.startTag(null, "DesigAs_1stAppointment");
                        try {
                            serializer.text(tdetails.getTeacherdesigasfirst());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DesigAs_1stAppointment");

                        serializer.startTag(null, "Designation");
                        try {
                            serializer.text(tdetails.getDesignation());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Designation");

                        serializer.startTag(null, "UC");
                        try {
                            serializer.text(tdetails.getTeacheruc());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UC");

                        serializer.startTag(null, "Disability");
                        try {
                            serializer.text(tdetails.getTeacheranydisablity());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Disability");

                        if (tdetails.getTeacheranydisablity().equals("No")) {
                            serializer.startTag(null, "DisabilityType");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DisabilityType");
                        } else {
                            serializer.startTag(null, "DisabilityType");
                            try {
                                serializer.text(tdetails.getTeachertypedisablity());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DisabilityType");
                        }

                        serializer.startTag(null, "Status");
                        try {
                            serializer.text(tdetails.getAttendance());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Status");

                        serializer.startTag(null, "TransferIn");
                        try {
                            serializer.text(tdetails.getAttendanceTrasnferIn());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TransferIn");

                        serializer.startTag(null, "StatusDetails");
                        try {
                            serializer.text(tdetails.getTeacherattendancedetails());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StatusDetails");


                        serializer.startTag(null, "DateSince");
                        try {
                            serializer.text(tdetails.getAttendancedatesince());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateSince");

                        serializer.startTag(null, "Transfer_School");
                        try {
                            serializer.text(tdetails.getAttendancetrasnferschool());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Transfer_School");
                        serializer.endTag(null, "TEACHER");
                    }

                    if(ifTeachingAbsent.equals("case2found"))
                    {
                        for (int i = 0; i < teacherdetailsweb.size(); i++) {
                        tdetailsweb = teacherdetailsweb.get(i);
                        serializer.startTag(null, "TEACHER");

                        serializer.startTag(null, "Name");
                        try {
                            serializer.text(tdetailsweb.getTeachername());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Name");

                        serializer.startTag(null, "FatherName");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "FatherName");

                        serializer.startTag(null, "Gender");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Gender");

                        serializer.startTag(null, "MaritalStatus");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "MaritalStatus");

                        serializer.startTag(null, "BPS");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BPS");

                        serializer.startTag(null, "ContactNo");
                        try {
                            serializer.text(tdetailsweb.getTeacherno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ContactNo");

                        serializer.startTag(null, "PersonalNo");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PersonalNo");


                        serializer.startTag(null, "CNIC");
                        try {
                            serializer.text(tdetailsweb.getTeachercnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "CNIC");

                        serializer.startTag(null, "DOB");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DOB");

                        serializer.startTag(null, "H_QualificationLevel");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationLevel");

                        serializer.startTag(null, "H_QualificationSubject");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationSubject");

                        serializer.startTag(null, "DateOf1stAppointment");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateOf1stAppointment");

                        serializer.startTag(null, "District");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "District");

                        serializer.startTag(null, "H_ProfessionalQualification");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_ProfessionalQualification");

                        serializer.startTag(null, "DesigAs_1stAppointment");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DesigAs_1stAppointment");

                        serializer.startTag(null, "Designation");
                        try {
                            serializer.text(tdetailsweb.getTeachergender());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Designation");

                        serializer.startTag(null, "UC");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UC");

                        serializer.startTag(null, "Disability");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Disability");

                        serializer.startTag(null, "DisabilityType");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DisabilityType");

                        serializer.startTag(null, "Status");
                        try {
                            serializer.text("Absent");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Status");

                        serializer.startTag(null, "TransferIn");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TransferIn");

                        serializer.startTag(null, "StatusDetails");
                        try {
                            serializer.text("Un-Authorized");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StatusDetails");


                        serializer.startTag(null, "DateSince");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateSince");

                        serializer.startTag(null, "Transfer_School");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Transfer_School");
                        serializer.endTag(null, "TEACHER");
                    }
                    }
                    else
                    {
                        for (int i = 0; i < teacherdetailsweb
                                .size(); i++) {
                            tdetailsweb = teacherdetailsweb.get(i);
                            serializer.startTag(null, "TEACHER");

                            serializer.startTag(null, "Name");
                            try {
                                serializer.text(tdetailsweb.getTeachername());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Name");

                            serializer.startTag(null, "FatherName");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FatherName");

                            serializer.startTag(null, "Gender");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Gender");

                            serializer.startTag(null, "MaritalStatus");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "MaritalStatus");

                            serializer.startTag(null, "BPS");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "BPS");

                            serializer.startTag(null, "ContactNo");
                            try {
                                serializer.text(tdetailsweb.getTeacherno());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ContactNo");

                            serializer.startTag(null, "PersonalNo");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PersonalNo");


                            serializer.startTag(null, "CNIC");
                            try {
                                serializer.text(tdetailsweb.getTeachercnic());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "CNIC");

                            serializer.startTag(null, "DOB");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DOB");

                            serializer.startTag(null, "H_QualificationLevel");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "H_QualificationLevel");

                            serializer.startTag(null, "H_QualificationSubject");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "H_QualificationSubject");

                            serializer.startTag(null, "DateOf1stAppointment");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DateOf1stAppointment");

                            serializer.startTag(null, "District");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "District");

                            serializer.startTag(null, "H_ProfessionalQualification");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "H_ProfessionalQualification");

                            serializer.startTag(null, "DesigAs_1stAppointment");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DesigAs_1stAppointment");

                            serializer.startTag(null, "Designation");
                            try {
                                serializer.text(tdetailsweb.getTeachergender());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Designation");

                            serializer.startTag(null, "UC");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "UC");

                            serializer.startTag(null, "Disability");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Disability");

                            serializer.startTag(null, "DisabilityType");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DisabilityType");

                            serializer.startTag(null, "Status");
                            try {
                                serializer.text(tdetailsweb.getAttendance());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Status");

                            serializer.startTag(null, "TransferIn");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TransferIn");

                            serializer.startTag(null, "StatusDetails");
                            try {
                                serializer.text(tdetailsweb.getTeacherattendancedetails());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "StatusDetails");


                            serializer.startTag(null, "DateSince");
                            try {
                                serializer.text(tdetailsweb.getAttendancedatesince());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DateSince");

                            serializer.startTag(null, "Transfer_School");
                            try {
                                serializer.text(tdetailsweb.getAttendancetrasnferschool());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Transfer_School");
                            serializer.endTag(null, "TEACHER");
                        }
                    }

                    serializer.endTag(null, "TEACHERPRESENCE");

                    if (SanctionedStrScreen.equals("True")) {
                        serializer.startTag(null, "Trainings");

                        for (int j = 0; j < trainignrecord.size(); j++) {

                            trecord = trainignrecord.get(j);
                            serializer.startTag(null, "TrainingRecord");

                            serializer.startTag(null, "CNIC");
                            try {
                                serializer.text(trecord.getCnic());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "CNIC");

                            serializer.startTag(null, "TrainingTitle");
                            try {
                                serializer.text(trecord.getTitle());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TrainingTitle");

                            serializer.startTag(null, "TrainingYear");
                            try {
                                serializer.text(trecord.getYear());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TrainingYear");

                            serializer.startTag(null, "TrainingDuration");
                            try {
                                serializer.text(trecord.getDuration());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TrainingDuration");

                            serializer.startTag(null, "TrainingConductedBy");
                            try {
                                serializer.text(trecord.getConductedby());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TrainingConductedBy");

                            serializer.startTag(null, "TrainingAttendedAs");
                            try {
                                serializer.text(trecord.getAttendedas());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TrainingAttendedAs");
                            serializer.endTag(null, "TrainingRecord");
                        }

                        serializer.endTag(null, "Trainings");
                    }

                    ///NON TEACHER PPRESENCE////

                    serializer.startTag(null, "NONTEACHERPRESENCE");

                    for (int i = 0; i < nonteacherdetails.size(); i++) {
                        nontdetails = nonteacherdetails.get(i);
                        serializer.startTag(null, "NONTEACHER");

                        serializer.startTag(null, "Name");
                        try {
                            serializer.text(nontdetails.getTeachername());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Name");

                        serializer.startTag(null, "FatherName");
                        try {
                            serializer.text(nontdetails.getTeacherfathername());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "FatherName");

                        serializer.startTag(null, "Gender");
                        try {
                            serializer.text(nontdetails.getTeachergender());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Gender");

                        serializer.startTag(null, "MaritalStatus");
                        try {
                            serializer.text(nontdetails.getTeachermarital());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "MaritalStatus");

                        serializer.startTag(null, "BPS");
                        try {
                            serializer.text(nontdetails.getTeacherbps());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BPS");

                        serializer.startTag(null, "ContactNo");
                        try {
                            serializer.text(nontdetails.getTeacherno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ContactNo");

                        serializer.startTag(null, "PersonalNo");
                        try {
                            serializer.text(nontdetails.getTeacheraccountno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PersonalNo");


                        serializer.startTag(null, "CNIC");
                        try {
                            serializer.text(nontdetails.getTeachercnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "CNIC");

                        serializer.startTag(null, "DOB");
                        try {
                            serializer.text(nontdetails.getTeacherdob());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DOB");

                        serializer.startTag(null, "H_QualificationLevel");
                        try {
                            serializer.text(nontdetails.getTeacherhighestlevel());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationLevel");

                        serializer.startTag(null, "H_QualificationSubject");
                        try {
                            serializer.text(nontdetails.getTeacherhigestsubject());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationSubject");

                        serializer.startTag(null, "DateOf1stAppointment");
                        try {
                            serializer.text(nontdetails.getTeacherdateoffirst());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateOf1stAppointment");

                        serializer.startTag(null, "District");
                        try {
                            serializer.text(nontdetails.getTeacherdistrict());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "District");

                        serializer.startTag(null, "H_ProfessionalQualification");
                        try {
                            serializer.text(nontdetails.getTeacherhighestqualification());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_ProfessionalQualification");

                        serializer.startTag(null, "DesigAs_1stAppointment");
                        try {
                            serializer.text(nontdetails.getTeacherdesigasfirst());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DesigAs_1stAppointment");

                        serializer.startTag(null, "Designation");
                        try {
                            serializer.text(nontdetails.getDesignation());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Designation");

                        serializer.startTag(null, "UC");
                        try {
                            serializer.text(nontdetails.getTeacheruc());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UC");

                        serializer.startTag(null, "Disability");
                        try {
                            serializer.text(nontdetails.getTeacheranydisablity());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Disability");

                        if (nontdetails.getTeacheranydisablity().equals("No")) {
                            serializer.startTag(null, "DisabilityType");
                            try {
                                serializer.text("");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DisabilityType");
                        } else {
                            serializer.startTag(null, "DisabilityType");
                            try {
                                serializer.text(nontdetails.getTeachertypedisablity());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "DisabilityType");
                        }


                        /*if (objform.getSchoolStatusDetail().equals(
                                "Student and teaching Staff Absent")
                                || objform.getSchoolStatusDetail()
                                .equals("Teaching Staff Absent")) {

                            serializer.startTag(null, "Status");
                            serializer.text("Absent");
                            serializer.endTag(null, "Status");

                            serializer.startTag(null, "StatusDetails");
                            serializer.text("Un-Authorized");
                            serializer.endTag(null, "StatusDetails");

                        } else {*/
                        serializer.startTag(null, "Status");
                        try {
                            serializer.text(nontdetails.getAttendance());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Status");

                        serializer.startTag(null, "TransferIn");
                        try {
                            serializer.text(tdetails.getAttendanceTrasnferIn());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TransferIn");

                        serializer.startTag(null, "StatusDetails");
                        try {
                            serializer.text(nontdetails.getTeacherattendancedetails());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StatusDetails");


                        serializer.startTag(null, "DateSince");
                        try {
                            serializer.text(nontdetails.getAttendancedatesince());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateSince");

                        serializer.startTag(null, "Transfer_School");
                        try {
                            serializer.text(nontdetails.getAttendancetrasnferschool());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Transfer_School");
                        //}

                        serializer.endTag(null, "NONTEACHER");
                    }

                    for (int i = 0; i < Nonteacherdetailsweb
                            .size(); i++) {
                        Nontdetailsweb = Nonteacherdetailsweb.get(i);
                        serializer.startTag(null, "NONTEACHER");

                        serializer.startTag(null, "Name");
                        try {
                            serializer.text(Nontdetailsweb.getTeachername());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Name");

                        serializer.startTag(null, "FatherName");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "FatherName");

                        serializer.startTag(null, "Gender");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Gender");

                        serializer.startTag(null, "MaritalStatus");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "MaritalStatus");

                        serializer.startTag(null, "BPS");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BPS");

                        serializer.startTag(null, "ContactNo");
                        try {
                            serializer.text(Nontdetailsweb.getTeacherno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ContactNo");

                        serializer.startTag(null, "PersonalNo");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PersonalNo");


                        serializer.startTag(null, "CNIC");
                        try {
                            serializer.text(Nontdetailsweb.getTeachercnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "CNIC");

                        serializer.startTag(null, "DOB");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DOB");

                        serializer.startTag(null, "H_QualificationLevel");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationLevel");

                        serializer.startTag(null, "H_QualificationSubject");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_QualificationSubject");

                        serializer.startTag(null, "DateOf1stAppointment");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateOf1stAppointment");

                        serializer.startTag(null, "District");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "District");

                        serializer.startTag(null, "H_ProfessionalQualification");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "H_ProfessionalQualification");

                        serializer.startTag(null, "DesigAs_1stAppointment");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DesigAs_1stAppointment");

                        serializer.startTag(null, "Designation");
                        try {
                            serializer.text(Nontdetailsweb.getTeachergender());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Designation");

                        serializer.startTag(null, "UC");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UC");

                        serializer.startTag(null, "Disability");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Disability");

                        serializer.startTag(null, "DisabilityType");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DisabilityType");

                        serializer.startTag(null, "Status");
                        try {
                            serializer.text(Nontdetailsweb.getAttendance());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Status");

                        serializer.startTag(null, "TransferIn");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TransferIn");

                        serializer.startTag(null, "StatusDetails");
                        try {
                            serializer.text(Nontdetailsweb.getTeacherattendancedetails());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StatusDetails");


                        serializer.startTag(null, "DateSince");
                        try {
                            serializer.text(Nontdetailsweb.getAttendancedatesince());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "DateSince");

                        serializer.startTag(null, "Transfer_School");
                        try {
                            serializer.text(Nontdetailsweb.getAttendancetrasnferschool());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Transfer_School");
                        serializer.endTag(null, "NONTEACHER");
                    }

                    serializer.endTag(null, "NONTEACHERPRESENCE");


                    ///proxy teacher
                    serializer.startTag(null, "PROXYTEACHERSPRESENCE");


                    for (int i = 0; i < proxyTeacherList.size(); i++) {

                        details = proxyTeacherList.get(i);

                        serializer.startTag(null, "PROXYTEACHER");

                        serializer.startTag(null, "ProxyTeacherName");
                        try {
                            serializer.text(details.getName());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherName");

                        serializer.startTag(null, "ProxyTeacherCNIC");
                        try {
                            serializer.text(details.getCnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherCNIC");

                        serializer.startTag(null, "ProxyForName");
                        try {
                            serializer.text(details.getProxyName());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyForName");

                        serializer.startTag(null, "ProxyForCNIC");
                        try {
                            serializer.text(details.getProxycnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyForCNIC");

                        serializer.startTag(null, "ProxyTeacherForPersoanlNo");
                        try {
                            serializer.text(details.getProxyno());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherForPersoanlNo");

                        serializer.startTag(null, "ProxyTeacherDesignation");
                        try {
                            serializer.text(details.getDesignation());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherDesignation");

                        serializer.startTag(null, "ProxyTeacherTimeSince");
                        try {
                            serializer.text(details.getProxytimeSince());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherTimeSince");
                        serializer.endTag(null, "PROXYTEACHER");
                    }

                    serializer.endTag(null, "PROXYTEACHERSPRESENCE");

                    ///TEACHER APOINTED BY

                    serializer.startTag(null, "PROXYTEACHERBYOO");

                    for (int i = 0; i < appointedby
                            .size(); i++) {
                        detailsAppointed = appointedby.get(i);
                        serializer.startTag(null, "PROXYTEACHEROO");

                        serializer.startTag(null, "ProxyTeacherName");
                        try {
                            serializer.text(detailsAppointed.getName());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherName");

                        serializer.startTag(null, "ProxyTeacherCNIC");
                        try {
                            serializer.text(detailsAppointed.getCnic());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ProxyTeacherCNIC");

                        serializer.startTag(null, "AppointedBy");
                        try {
                            serializer.text(detailsAppointed.getAppointedby());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "AppointedBy");

                        serializer.startTag(null, "ApoointedDate");
                        try {
                            serializer.text(detailsAppointed.getAppointedDate());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ApoointedDate");

                        serializer.endTag(null, "PROXYTEACHEROO");
                    }

                    serializer.endTag(null, "PROXYTEACHERBYOO");


                    //ENROLLMENT ATTENDANCE GAP////

                    serializer.startTag(null, "STUDENTENROLLMENTATTANDANCEGAP");

                    for (int i = 0; i < eagList.size(); i++) {
                        eagdetails = eagList.get(i);
                        serializer.startTag(null, "ENROLLMENT");

                        serializer.startTag(null, "CLASSNAME");
                        try {
                            serializer.text(eagdetails.getClassName());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "CLASSNAME");

                        serializer.startTag(null, "STUDENTAPREGISTER");
                        try {
                            serializer.text(eagdetails.getStudentsEnrolled());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STUDENTAPREGISTER");

                        serializer.startTag(null, "STUDENTAPHEADCOUNT");
                        try {
                            serializer.text(eagdetails.getStudentsPresentHead());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STUDENTAPHEADCOUNT");

                        serializer.startTag(null, "STUDENTAPREGISTERCOUNT");
                        try {
                            serializer.text(eagdetails.getStudentsPresentRegister());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STUDENTAPREGISTERCOUNT");

                        serializer.startTag(null, "GIRLSINBOYSSCHOOL");
                        try {
                            serializer.text(eagdetails.getGirlsEnrolled());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "GIRLSINBOYSSCHOOL");

                        serializer.startTag(null, "BOYSINGIRLSSCHOOL");
                        try {
                            serializer.text(eagdetails.getBoysEnrolled());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BOYSINGIRLSSCHOOL");

                        serializer.endTag(null, "ENROLLMENT");
                    }

                    serializer.endTag(null, "STUDENTENROLLMENTATTANDANCEGAP");


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


                    serializer.startTag(null, "ElectricitySrc");
                    try {
                        serializer.text(srcw);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ElectricitySrc");


                    serializer.startTag(null, "WaterAvailability");
                    try {
                        serializer.text(c);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WaterAvailability");

                    serializer.startTag(null, "ToiletAvailability");
                    try {
                        serializer.text(g);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ToiletAvailability");

                    serializer.startTag(null, "WallAvailability");
                    try {
                        serializer.text(i);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WallAvailability");

                    serializer.startTag(null, "WaterFunctionality");
                    try {
                        serializer.text(d);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WaterFunctionality");

                    serializer.startTag(null, "ToiletFunctionality");
                    try {
                        serializer.text(h);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "ToiletFunctionality");

                    serializer.startTag(null, "WallFunctionality");
                    try {
                        serializer.text(j);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WallFunctionality");

                    serializer.startTag(null, "SourceOfwater");
                    try {
                        serializer.text(ee);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "SourceOfwater");

                    serializer.startTag(null, "WaterDrinkable");
                    try {
                        serializer.text(f);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "WaterDrinkable");

                    serializer.startTag(null, "TeacherToiletAvailable");
                    try {
                        serializer.text(l);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "TeacherToiletAvailable");

                    serializer.startTag(null, "TeacherToiletFunctional");
                    try {
                        serializer.text(o);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "TeacherToiletFunctional");

                    serializer.startTag(null, "StudentToiletAvailable");
                    try {
                        serializer.text(k);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "StudentToiletAvailable");

                    serializer.startTag(null, "StudentToiletFunctional");
                    try {
                        serializer.text(n);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "StudentToiletFunctional");

                    serializer.startTag(null, "CommonToiletAvailable");
                    try {
                        serializer.text(m);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "CommonToiletAvailable");

                    serializer.startTag(null, "CommonToiletFunctional");
                    try {
                        serializer.text(p);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "CommonToiletFunctional");


                    //GRANT
                    try {

                        serializer.startTag(null, "GrantList");

                        for (int i = 0; i < grantlist.size(); i++) {

                            grantdetails = grantlist.get(i);

                            serializer.startTag(null, "Grant");

                            serializer.startTag(null, "Funds_Received");
                            try {
                                serializer.text(grantdetails.getFundsReceived());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Funds_Received");

                            if (grantdetails.getFundsReceived().equals("Yes")) {

                                serializer.startTag(null, "Amount_Equals");
                                try {
                                    serializer.text(grantdetails.getAmmountCorrect());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "Amount_Equals");

                                if (grantdetails.getAmmountCorrect().equals("No")) {
                                    serializer.startTag(null, "Actual_Amount");
                                    try {
                                        serializer.text(grantdetails.getAmmountEnter());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Actual_Amount");
                                }
                                else
                                {

                                }

                                serializer.startTag(null, "Work_completed");
                                try {
                                    serializer.text(grantdetails.getWorkStatus());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "Work_completed");

                                if (grantdetails.getWorkStatus().equals("Yes")) {

                                    serializer.startTag(null, "Start_Date");
                                    try {
                                        serializer.text(grantdetails.getStartDate());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Start_Date");

                                    serializer.startTag(null, "FaceCode");
                                    try {
                                        serializer.text(String.valueOf(grantdetails.getFaceCode()));
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "FaceCode");

                                    serializer.startTag(null, "Type");
                                    try {
                                        serializer.text(grantdetails.getType());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Type");


                                    serializer.startTag(null, "Grand_type");
                                    try {
                                        serializer.text(grantdetails.getGrantType());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Grand_type");


                                    serializer.startTag(null, "Work_Grading");
                                    try {
                                        serializer.text(grantdetails.getWorkGrading());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Work_Grading");

                                    serializer.startTag(null, "Grand_Year");
                                    try {
                                        serializer.text(grantdetails.getYear());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Grand_Year");

                                    serializer.startTag(null, "Grand_Amount");
                                    try {
                                        serializer.text(grantdetails.getAmount());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Grand_Amount");

                                    serializer.startTag(null, "Financial");
                                    try {
                                        serializer.text(grantdetails.getFinancial());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Financial");

                                    serializer.startTag(null, "remarks");
                                    try {
                                        serializer.text(grantdetails.getRemarks());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "remarks");

                                    serializer.startTag(null, "record_Status");
                                    try {
                                        serializer.text(grantdetails.getRecordStatus());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "record_Status");
                                } else {
                                    serializer.startTag(null, "Grand_type");
                                    try {
                                        serializer.text(grantdetails.getGrantType());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Grand_type");

                                    serializer.startTag(null, "Grand_Year");
                                    try {
                                        serializer.text(grantdetails.getYear());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Grand_Year");

                                    serializer.startTag(null, "Grand_Amount");
                                    try {
                                        serializer.text(grantdetails.getAmount());
                                    } catch (Exception e) {
                                    }
                                    serializer.endTag(null, "Grand_Amount");
                                }
                            }

                            else {
                                serializer.startTag(null, "Grand_type");
                                try {
                                    serializer.text(grantdetails.getGrantType());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "Grand_type");

                                serializer.startTag(null, "Grand_Year");
                                try {
                                    serializer.text(grantdetails.getYear());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "Grand_Year");

                                serializer.startTag(null, "Grand_Amount");
                                try {
                                    serializer.text(grantdetails.getAmount());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "Grand_Amount");
                            }

                            serializer.endTag(null, "Grant");
                        }

                        serializer.endTag(null, "GrantList");
                    } catch (Exception e) {

                    }

                    ///VACANT POSITION

                    serializer.startTag(null, "VacantPosition");

                    for (int i = 0; i < vacantpostion
                            .size(); i++) {

                        dt = vacantpostion.get(i);
                        serializer.startTag(null, "Vacant");

                        serializer.startTag(null, "VacantPositionNumber");
                        try {
                            serializer.text(dt.getVacantseats());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "VacantPositionNumber");

                        serializer.startTag(null, "VacantDesignation");
                        try {
                            serializer.text(dt.getVacantdesignation());
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "VacantDesignation");

                        serializer.endTag(null, "Vacant");

                    }

                    serializer.endTag(null, "VacantPosition");


                    serializer.startTag(null, "FlagHoisted");
                    try {
                        serializer.text(flagcheck);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "FlagHoisted");

                    serializer.startTag(null, "GrantBoard");
                    try {
                        serializer.text(grantcheck);
                    } catch (Exception e) {
                    }
                    serializer.endTag(null, "GrantBoard");

                    //School Area

                    if(schoolareaStrScreen.equals("True")) {
                        serializer.startTag(null, "Covered_Area");
                        try {
                            serializer.text(covered);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Covered_Area");

                        serializer.startTag(null, "UnCovered_Area");
                        try {
                            serializer.text(uncovered);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UnCovered_Area");

                        serializer.startTag(null, "UnCovered_Area_Premis");
                        try {
                            serializer.text(SPINNER);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UnCovered_Area_Premis");

                        serializer.startTag(null, "UnCovered_Area_Distance");
                        try {
                            serializer.text(distanceInMeters);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UnCovered_Area_Distance");

                        serializer.startTag(null, "Rooms_Basement");
                        try {
                            serializer.text(brooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Rooms_Basement");

                        serializer.startTag(null, "GroundFloor");
                        try {
                            serializer.text(grooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "GroundFloor");

                        serializer.startTag(null, "FirstFloor");
                        try {
                            serializer.text(frooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "FirstFloor");

                        serializer.startTag(null, "SecondFloor");
                        try {
                            serializer.text(srooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SecondFloor");

                        serializer.startTag(null, "ThirdFloor");
                        try {
                            serializer.text(trooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ThirdFloor");
                    }

                    if (constructionStrScreen.equals("True")) {
                        serializer.startTag(null, "Pakka_NoOfClassrooms");
                        try {
                            serializer.text(coveredd);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Pakka_NoOfClassrooms");

                        serializer.startTag(null, "Pakka_NoOfOtherrooms");
                        try {
                            serializer.text(uncoveredd);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Pakka_NoOfOtherrooms");

                        serializer.startTag(null, "Kacha_NoOfClassrooms");
                        try {
                            serializer.text(broomsd);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Kacha_NoOfClassrooms");

                        serializer.startTag(null, "Kacha_NoOfOtherrooms");
                        try {
                            serializer.text(groomsd);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Kacha_NoOfOtherrooms");

                    }

                    if (buildindconditionStrScreen.equals("True")) {
                        serializer.startTag(null, "Building_condition");
                        try {
                            serializer.text(WHOLENEEDS);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Building_condition");

                        serializer.startTag(null, "NoOfClasses_reconstruction");
                        try {
                            serializer.text(noOfClassrooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfClasses_reconstruction");

                        serializer.startTag(null, "NoOfOtherRooms_reconstruction");
                        try {
                            serializer.text(noOfOtherRooms);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfOtherRooms_reconstruction");

                        serializer.startTag(null, "NoOfClasses_MajorRepair");
                        try {
                            serializer.text(noOfClassroomsMajor);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfClasses_MajorRepair");

                        serializer.startTag(null, "NoOfOtherRooms_MajorRepair");
                        try {
                            serializer.text(noOfOtherroomsMajor);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfOtherRooms_MajorRepair");
                    }
                    //IT LAB

                    if (itlabStrScreen.equals("True")) {
                        serializer.startTag(null, "ITLab");
                        try {
                            serializer.text(ITlABexists);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ITLab");

                        serializer.startTag(null, "ITLabFunctional");
                        try {
                            serializer.text(labFun);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ITLabFunctional");

                        serializer.startTag(null, "ITLabEstablished");
                        try {
                            serializer.text(labEst);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ITLabEstablished");

                        serializer.startTag(null, "ITLabEstablished_PrivateName");
                        try {
                            serializer.text(firmname);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ITLabEstablished_PrivateName");

                        serializer.startTag(null, "NoOfComputersInLab");
                        try {
                            serializer.text(noOfComputers);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfComputersInLab");

                        serializer.startTag(null, "NoOfFunctionalComputers");
                        try {
                            serializer.text(noOfFunctionalComputers);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfFunctionalComputers");

                        serializer.startTag(null, "INTERNETAVAILABLE");
                        try {
                            serializer.text(Internet);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "INTERNETAVAILABLE");
                    }

                    //STUDENT COMMODITIES

                    if(commodititesStrScreen.equals("True")) {
                        serializer.startTag(null, "StudentDesks_TwoSeats_Useable");
                        try {
                            serializer.text(twouse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_TwoSeats_Useable");

                        serializer.startTag(null, "StudentDesks_TwoSeats_UnUseable");
                        try {
                            serializer.text(twounuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_TwoSeats_UnUseable");

                        serializer.startTag(null, "StudentDesks_TwoSeats_NewRequired");
                        try {
                            serializer.text(twonew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_TwoSeats_NewRequired");

                        serializer.startTag(null, "StudentDesks_ThreeSeats_Useable");
                        try {
                            serializer.text(threeuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_ThreeSeats_Useable");

                        serializer.startTag(null, "StudentDesks_ThreeSeats_UnUseable");
                        try {
                            serializer.text(threeunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_ThreeSeats_UnUseable");

                        serializer.startTag(null, "StudentDesks_ThreeSeats_NewRequired");
                        try {
                            serializer.text(threenew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_ThreeSeats_NewRequired");

                        //

                        serializer.startTag(null, "StudentDesks_Benches_Useable");
                        try {
                            serializer.text(benchuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_Benches_Useable");

                        serializer.startTag(null, "StudentDesks_Benches_UnUseable");
                        try {
                            serializer.text(benchunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_Benches_UnUseable");

                        serializer.startTag(null, "StudentDesks_Benches_NewRequired");
                        try {
                            serializer.text(benchnew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_Benches_NewRequired");

                        //

                        serializer.startTag(null, "StudentDesks_StudentChairs_Useable");
                        try {
                            serializer.text(chairuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_StudentChairs_Useable");

                        serializer.startTag(null, "StudentDesks_StudentChairs_UnUseable");
                        try {
                            serializer.text(chairunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_StudentChairs_UnUseable");

                        serializer.startTag(null, "StudentDesks_StudentChairs_NewRequired");
                        try {
                            serializer.text(chairnew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_StudentChairs_NewRequired");

                        //

                        serializer.startTag(null, "StudentDesks_TabletChairs_Useable");
                        try {
                            serializer.text(tabletuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_TabletChairs_Useable");

                        serializer.startTag(null, "StudentDesks_TabletChairs_UnUseable");
                        try {
                            serializer.text(tabletunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_TabletChairs_UnUseable");

                        serializer.startTag(null, "StudentDesks_TabletChairs_NewRequired");
                        try {
                            serializer.text(tabletnew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_TabletChairs_NewRequired");

                        //
                        serializer.startTag(null, "StudentDesks_JuteTats_Useable");
                        try {
                            serializer.text(tatsuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_JuteTats_Useable");

                        serializer.startTag(null, "StudentDesks_JuteTats_UnUseable");
                        try {
                            serializer.text(tatsunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_JuteTats_UnUseable");

                        serializer.startTag(null, "StudentDesks_JuteTats_NewRequired");
                        try {
                            serializer.text(tatsnew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_JuteTats_NewRequired");

                        //
                        serializer.startTag(null, "StudentDesks_Fans_Useable");
                        try {
                            serializer.text(fansuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_Fans_Useable");

                        serializer.startTag(null, "StudentDesks_Fans_UnUseable");
                        try {
                            serializer.text(fansunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_Fans_UnUseable");

                        serializer.startTag(null, "StudentDesks_Fans_NewRequired");
                        try {
                            serializer.text(fansnew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_Fans_NewRequired");

                        //
                        serializer.startTag(null, "StudentDesks_AnyOther1_Name");
                        try {
                            serializer.text(anyother);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther1_Name");

                        serializer.startTag(null, "StudentDesks_AnyOther1_Useable");
                        try {
                            serializer.text(otheruse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther1_Useable");

                        serializer.startTag(null, "StudentDesks_AnyOther1_UnUseable");
                        try {
                            serializer.text(otherunuse);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther1_UnUseable");

                        serializer.startTag(null, "StudentDesks_AnyOther1_NewRequired");
                        try {
                            serializer.text(othernew);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther1_NewRequired");

                        serializer.startTag(null, "StudentDesks_AnyOther2_Name");
                        try {
                            serializer.text(hq);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther2_Name");

                        serializer.startTag(null, "StudentDesks_AnyOther2_Useable");
                        try {
                            serializer.text(hw);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther2_Useable");

                        serializer.startTag(null, "StudentDesks_AnyOther2_UnUseable");
                        try {
                            serializer.text(he);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther2_UnUseable");

                        serializer.startTag(null, "StudentDesks_AnyOther2_NewRequired");
                        try {
                            serializer.text(hr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentDesks_AnyOther2_NewRequired");
                    }

                    //PTC
                    if (ptcStrScreen.equals("True")) {
                        serializer.startTag(null, "PTC_Established");
                        try {
                            serializer.text(ptcestvalue);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PTC_Established");

                        if (ptcestvalue.equals("Yes")) {

                            serializer.startTag(null, "PTC_DOE");
                            try {
                                serializer.text(ptcdoe);
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PTC_DOE");
                        }

                        serializer.startTag(null, "PTC_MembersTrained");
                        try {
                            serializer.text(ptctrainedvalue);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PTC_MembersTrained");

                        serializer.startTag(null, "PTC_ChairpersonName");
                        try {
                            serializer.text(ptcnamee);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PTC_ChairpersonName");

                        serializer.startTag(null, "PTC_ContactNo");
                        try {
                            serializer.text(ptcnoo);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PTC_ContactNo");

                        serializer.startTag(null, "BankName");
                        try {
                            serializer.text(ptcbanknamee);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BankName");

                        serializer.startTag(null, "AccountNo");
                        try {
                            serializer.text(ptcaccno);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "AccountNo");

                        serializer.startTag(null, "Branch");
                        try {
                            serializer.text(ptcbcode);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Branch");

                        serializer.startTag(null, "PTC_LastElectionDate");
                        try {
                            serializer.text(ptclast);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PTC_LastElectionDate");

                        serializer.startTag(null, "BalanceAvailableon30thJune");
                        try {
                            serializer.text(ptcblnc);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BalanceAvailableon30thJune");

                        serializer.startTag(null, "GovtFundRecivncialYearFinnedDu");
                        try {
                            serializer.text(ptcamnt);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "GovtFundRecivncialYearFinnedDu");

                        serializer.startTag(null, "OtherSourcesFundRecivncialYearFinnedDu");
                        try {
                            serializer.text(amntothers);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "OtherSourcesFundRecivncialYearFinnedDu");

                        serializer.startTag(null, "TotaExpindtureIncurrentlYear");
                        try {
                            serializer.text(expendituress);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotaExpindtureIncurrentlYear");

                        serializer.startTag(null, "Presentbalance");
                        try {
                            serializer.text(presentbalancee);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Presentbalance");


                        serializer.startTag(null, "NumberOfMeetingInLAstMonth");
                        try {
                            serializer.text(lastmnthmetng);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NumberOfMeetingInLAstMonth");

                        serializer.startTag(null, "BANKSTATEMENTSHOWN");
                        try {
                            serializer.text(bankstatementshown);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BANKSTATEMENTSHOWN");

                    }

                    //STIPEND
                    if(stipendStrScreen.equals("True")) {
                        serializer.startTag(null, "Stipend_RecordShown");
                        try {
                            serializer.text(rs);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Stipend_RecordShown");

                        serializer.startTag(null, "Stipend_year");
                        try {
                            serializer.text(sy);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Stipend_year");

                        serializer.startTag(null, "MainStipend");
                        serializer.startTag(null, "Stipend");

                        serializer.startTag(null, "STIPENDCLASS");
                        try {
                            serializer.text("Class 6");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STIPENDCLASS");

                        serializer.startTag(null, "SchemeName");
                        try {
                            serializer.text(stipend_schemeName6);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SchemeName");

                        serializer.startTag(null, "TotalEligibleStudents");
                        try {
                            serializer.text(stipend_eligibleStudents6);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalEligibleStudents");

                        serializer.startTag(null, "TotalNonreceivingStudents");
                        try {
                            serializer.text(stipend_EligibleNoStipend6);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalNonreceivingStudents");

                        serializer.startTag(null, "REMARKS");
                        try {
                            serializer.text(stipend_6remarks);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "REMARKS");

                        serializer.endTag(null, "Stipend");

                        serializer.startTag(null, "Stipend");

                        serializer.startTag(null, "STIPENDCLASS");
                        try {
                            serializer.text("Class 7");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STIPENDCLASS");

                        serializer.startTag(null, "SchemeName");
                        try {
                            serializer.text(stipend_SchemeName7);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SchemeName");

                        serializer.startTag(null, "TotalEligibleStudents");
                        try {
                            serializer.text(stipend_eligibleStudents7);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalEligibleStudents");

                        serializer.startTag(null, "TotalNonreceivingStudents");
                        try {
                            serializer.text(stipend_EligibleNoStipend7);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalNonreceivingStudents");

                        serializer.startTag(null, "REMARKS");
                        try {
                            serializer.text(stipend_7remarks);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "REMARKS");

                        serializer.endTag(null, "Stipend");

                        serializer.startTag(null, "Stipend");

                        serializer.startTag(null, "STIPENDCLASS");
                        try {
                            serializer.text("Class 8");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STIPENDCLASS");

                        serializer.startTag(null, "SchemeName");
                        try {
                            serializer.text(stipend_SchemeName8);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SchemeName");

                        serializer.startTag(null, "TotalEligibleStudents");
                        try {
                            serializer.text(stipend_eligibleStudents8);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalEligibleStudents");

                        serializer.startTag(null, "TotalNonreceivingStudents");
                        try {
                            serializer.text(stipend_EligibleNoStipend8);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalNonreceivingStudents");

                        serializer.startTag(null, "REMARKS");
                        try {
                            serializer.text(stipend_8remarks);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "REMARKS");

                        serializer.endTag(null, "Stipend");

                        serializer.startTag(null, "Stipend");

                        serializer.startTag(null, "STIPENDCLASS");
                        try {
                            serializer.text("Class 9");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STIPENDCLASS");

                        serializer.startTag(null, "SchemeName");
                        try {
                            serializer.text(stipend_SchemeName9);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SchemeName");

                        serializer.startTag(null, "TotalEligibleStudents");
                        try {
                            serializer.text(stipend_eligibleStudents9);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalEligibleStudents");

                        serializer.startTag(null, "TotalNonreceivingStudents");
                        try {
                            serializer.text(stipend_EligibleNoStipend9);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalNonreceivingStudents");

                        serializer.startTag(null, "REMARKS");
                        try {
                            serializer.text(stipend_9remarks);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "REMARKS");

                        serializer.endTag(null, "Stipend");

                        serializer.startTag(null, "Stipend");

                        serializer.startTag(null, "STIPENDCLASS");
                        try {
                            serializer.text("Class 10");
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "STIPENDCLASS");

                        serializer.startTag(null, "SchemeName");
                        try {
                            serializer.text(stipend_SchemeName10);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SchemeName");

                        serializer.startTag(null, "TotalEligibleStudents");
                        try {
                            serializer.text(stipend_eligibleStudents10);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalEligibleStudents");

                        serializer.startTag(null, "TotalNonreceivingStudents");
                        try {
                            serializer.text(stipend_EligibleNoStipend10);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "TotalNonreceivingStudents");

                        serializer.startTag(null, "REMARKS");
                        try {
                            serializer.text(stipend_10remarks);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "REMARKS");

                        serializer.endTag(null, "Stipend");
                        serializer.endTag(null, "MainStipend");
                    }

                    //INFRASTRUCTURE
                    if (infrastructureStrScreen.equals("True")) {
                        serializer.startTag(null, "HTOffice");
                        try {
                            serializer.text(htStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "HTOffice");

                        serializer.startTag(null, "ScienceLab");
                        try {
                            serializer.text(scienceStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ScienceLab");

                        serializer.startTag(null, "IT_LAB");
                        try {
                            serializer.text(itLabStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "IT_LAB");

                        serializer.startTag(null, "Staffroom");
                        try {
                            serializer.text(staffStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Staffroom");

                        serializer.startTag(null, "Library");
                        try {
                            serializer.text(libStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Library");

                        serializer.startTag(null, "ClerkRoom");
                        try {
                            serializer.text(clerkStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ClerkRoom");

                        serializer.startTag(null, "ExaminationHall");
                        try {
                            serializer.text(examStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ExaminationHall");

                        serializer.startTag(null, "PlayGround");
                        try {
                            serializer.text(playStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "PlayGround");

                        serializer.startTag(null, "OneScreenWhiteBoard");
                        try {
                            serializer.text(oneStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "OneScreenWhiteBoard");

                        serializer.startTag(null, "AlternativeSourceAvailable");
                        try {
                            serializer.text(alternateStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "AlternativeSourceAvailable");
                    }

                    if (guideStrScreen.equals("True")) {
                        serializer.startTag(null, "UserGuides");
                        try {
                            serializer.text(teachrguide);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "UserGuides");
                    }
                    //SchoolCleanliness
                    if (cleanlinessStrScreen.equals("True")) {
                        serializer.startTag(null, "SchoolCleaniness");
                        try {
                            serializer.text(schCleanN);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SchoolCleaniness");

                        serializer.startTag(null, "StudentCleaniness");
                        try {
                            serializer.text(stdCleanN);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "StudentCleaniness");

                    }
                    //SANCTIONED POSTS TEACHING
                    if (SanctionedStrScreen.equals("True")) {
                        serializer.startTag(null, "SanctionedPosts");

                        for (int i = 0; i < sanctionedPosts
                                .size(); i++) {

                            sp = sanctionedPosts.get(i);
                            serializer.startTag(null, "TeachingStaff");

                            serializer.startTag(null, "PostPositionCode");
                            try {
                                serializer.text(sp.getPositioncode());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PostPositionCode");

                            serializer.startTag(null, "Designation");
                            try {
                                serializer.text(sp.getDesignation());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Designation");

                            if (sp.getDesignation().equals("Senior Subject Specialist") || sp.getDesignation().equals("Subject Specialist")) {
                                serializer.startTag(null, "Subject");
                                try {
                                    serializer.text(sp.getSubject());
                                } catch (Exception e) {
                                }
                                serializer.endTag(null, "Subject");
                            }

                            serializer.startTag(null, "BPS");
                            try {
                                serializer.text(sp.getBPS());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "BPS");

                            serializer.startTag(null, "OTHERS");
                            try {
                                serializer.text(sp.getSpecifyothers());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "OTHERS");

                            serializer.startTag(null, "NoOfSanctionedPosts");
                            try {
                                serializer.text(sp.getNoOfSanctionedPosts());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NoOfSanctionedPosts");

                            serializer.endTag(null, "TeachingStaff");

                        }

                        serializer.endTag(null, "SanctionedPosts");

                        //SanctionedPostNon Teaching

                        serializer.startTag(null, "SanctionedPosts");

                        for (int i = 0; i < nonsanctionedPosts
                                .size(); i++) {

                            nonsp = nonsanctionedPosts.get(i);
                            serializer.startTag(null, "NonTeachingStaff");

                            serializer.startTag(null, "PostPositionCode");
                            try {
                                serializer.text(nonsp.getPositioncode());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PostPositionCode");

                            serializer.startTag(null, "Designation");
                            try {
                                serializer.text(nonsp.getDesignation());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Designation");

                            serializer.startTag(null, "BPS");
                            try {
                                serializer.text(nonsp.getBPS());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "BPS");
                            serializer.startTag(null, "OTHERS");
                            try {
                                serializer.text(nonsp.getSpecifyothers());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "OTHERS");

                            serializer.startTag(null, "NoOfSanctionedPosts");
                            try {
                                serializer.text(nonsp.getNoOfSanctionedPosts());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NoOfSanctionedPosts");

                            serializer.endTag(null, "NonTeachingStaff");

                        }

                        serializer.endTag(null, "SanctionedPosts");
                    }

                    //OtherFacilities
                    if (indicatorStrScreen.equals("True")) {
                        serializer.startTag(null, "Hostel_Availability");
                        try {
                            serializer.text(Facilities_SLevel);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Hostel_Availability");

                        serializer.startTag(null, "Hostel_Capacity");
                        try {
                            serializer.text(Facilities_hcapacity);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Hostel_Capacity");

                        serializer.startTag(null, "Office_Availablity");
                        try {
                            serializer.text(Facilities_officeStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Office_Availablity");

                        serializer.startTag(null, "Store_Availability");
                        try {
                            serializer.text(Facilities_storeStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Store_Availability");

                        serializer.startTag(null, "HomeEconomicsLab_Availability");
                        try {
                            serializer.text(Facilities_labStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "HomeEconomicsLab_Availability");

                        serializer.startTag(null, "Entrance_Access");
                        try {
                            serializer.text(Facilities_EntraceStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Entrance_Access");

                        serializer.startTag(null, "Building_Access");
                        try {
                            serializer.text(Facilities_BuildingStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Building_Access");

                        serializer.startTag(null, "Toilets_Access");
                        try {
                            serializer.text(Facilities_toiletsStr);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "Toilets_Access");
                    }

                    //////EMROLLMENT AGE GROUP BOYS///////
                    if (enrollmentageStrScreen.equals("True")) {
                        serializer.startTag(null, "EnrollmentByAge");

                        for (int i = 0; i < eageboysList.size(); i++) {

                            eagedetails = eageboysList.get(i);
                            serializer.startTag(null, "Enrollment");

                            serializer.startTag(null, "Gender");
                            try {
                                serializer.text("Boys");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Gender");

                            serializer.startTag(null, "Class");
                            try {
                                serializer.text(eagedetails.getClassName());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Class");

                            serializer.startTag(null, "ThreePlus");
                            try {
                                serializer.text(eagedetails.getAge3());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ThreePlus");
                            serializer.startTag(null, "FourPlus");
                            try {
                                serializer.text(eagedetails.getAge4());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FourPlus");

                            serializer.startTag(null, "FivePlus");
                            try {
                                serializer.text(eagedetails.getAge5());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FivePlus");

                            serializer.startTag(null, "SixPlus");
                            try {
                                serializer.text(eagedetails.getAge6());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SixPlus");

                            serializer.startTag(null, "SevenPlus");
                            try {
                                serializer.text(eagedetails.getAge7());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SevenPlus");

                            serializer.startTag(null, "EightPlus");
                            try {
                                serializer.text(eagedetails.getAge8());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "EightPlus");

                            serializer.startTag(null, "NinePlus");
                            try {
                                serializer.text(eagedetails.getAge9());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NinePlus");

                            serializer.startTag(null, "TenPlus");
                            try {
                                serializer.text(eagedetails.getAge10());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TenPlus");

                            serializer.startTag(null, "ElevenPlus");
                            try {
                                serializer.text(eagedetails.getAge11());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ElevenPlus");

                            serializer.startTag(null, "TwelvePlus");
                            try {
                                serializer.text(eagedetails.getAge12());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TwelvePlus");

                            serializer.startTag(null, "ThirteenPlus");
                            try {
                                serializer.text(eagedetails.getAge13());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ThirteenPlus");

                            serializer.startTag(null, "FourteenPlus");
                            try {
                                serializer.text(eagedetails.getAge14());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FourteenPlus");

                            serializer.startTag(null, "FifteenPlus");
                            try {
                                serializer.text(eagedetails.getAge15());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FifteenPlus");

                            serializer.startTag(null, "SixteenPlus");
                            try {
                                serializer.text(eagedetails.getAge16());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SixteenPlus");

                            serializer.startTag(null, "SeventeenPlus");
                            try {
                                serializer.text(eagedetails.getAge17());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SeventeenPlus");
                            serializer.startTag(null, "EighteenPlus");
                            try {
                                serializer.text(eagedetails.getAge18());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "EighteenPlus");
                            serializer.startTag(null, "NineteenPlus");
                            try {
                                serializer.text(eagedetails.getAge19());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NineteenPlus");
                            serializer.startTag(null, "TwentyPlus");
                            try {
                                serializer.text(eagedetails.getAge20());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TwentyPlus");
                            serializer.startTag(null, "TwentyOnePlus");
                            try {
                                serializer.text(eagedetails.getAge21());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TwentyOnePlus");
                            serializer.startTag(null, "Repeaters");
                            try {
                                serializer.text(eagedetails.getRepeaters());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Repeaters");
                            serializer.startTag(null, "NonMuslims");
                            try {
                                serializer.text(eagedetails.getNonmuslims());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NonMuslims");

                            serializer.endTag(null, "Enrollment");

                        }

                        serializer.endTag(null, "EnrollmentByAge");


                        /////Enrollment by Age Group Girls

                        serializer.startTag(null, "EnrollmentByAge");

                        for (int i = 0; i < eagegirlsList.size(); i++) {

                            eagegirlsdetails = eagegirlsList.get(i);
                            serializer.startTag(null, "Enrollment");

                            serializer.startTag(null, "Gender");
                            try {
                                serializer.text("Girls");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Gender");

                            serializer.startTag(null, "Class");
                            try {
                                serializer.text(eagegirlsdetails.getClassName());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Class");

                            serializer.startTag(null, "ThreePlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge3());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ThreePlus");
                            serializer.startTag(null, "FourPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge4());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FourPlus");

                            serializer.startTag(null, "FivePlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge5());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FivePlus");

                            serializer.startTag(null, "SixPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge6());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SixPlus");

                            serializer.startTag(null, "SevenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge7());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SevenPlus");

                            serializer.startTag(null, "EightPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge8());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "EightPlus");

                            serializer.startTag(null, "NinePlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge9());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NinePlus");

                            serializer.startTag(null, "TenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge10());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TenPlus");

                            serializer.startTag(null, "ElevenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge11());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ElevenPlus");

                            serializer.startTag(null, "TwelvePlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge12());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TwelvePlus");

                            serializer.startTag(null, "ThirteenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge13());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "ThirteenPlus");

                            serializer.startTag(null, "FourteenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge14());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FourteenPlus");

                            serializer.startTag(null, "FifteenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge15());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FifteenPlus");

                            serializer.startTag(null, "SixteenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge16());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SixteenPlus");

                            serializer.startTag(null, "SeventeenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge17());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SeventeenPlus");
                            serializer.startTag(null, "EighteenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge18());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "EighteenPlus");
                            serializer.startTag(null, "NineteenPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge19());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NineteenPlus");
                            serializer.startTag(null, "TwentyPlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge20());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TwentyPlus");
                            serializer.startTag(null, "TwentyOnePlus");
                            try {
                                serializer.text(eagegirlsdetails.getAge21());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "TwentyOnePlus");
                            serializer.startTag(null, "Repeaters");
                            try {
                                serializer.text(eagegirlsdetails.getRepeaters());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Repeaters");
                            serializer.startTag(null, "NonMuslims");
                            try {
                                serializer.text(eagegirlsdetails.getNonmuslims());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "NonMuslims");

                            serializer.endTag(null, "Enrollment");

                        }

                        serializer.endTag(null, "EnrollmentByAge");
                    }

                    ////////ENROLLEMTN BY GROUP SECTION//////////


                    //////DISBALED STUDENTS BOYS//////

                    if (disabledStrScreen.equals("True")) {
                        serializer.startTag(null, "DisabledStudents");

                        for (int i = 0; i < specialboysList.size(); i++) {

                            specialboysdetails = specialboysList.get(i);
                            serializer.startTag(null, "Students");

                            serializer.startTag(null, "CLASS");
                            try {
                                serializer.text(specialboysdetails.getClassName());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "CLASS");

                            serializer.startTag(null, "Gender");
                            try {
                                serializer.text("Boys");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Gender");

                            serializer.startTag(null, "FullVisual");
                            try {
                                serializer.text(specialboysdetails.getFullVisual());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FullVisual");
                            serializer.startTag(null, "PartialVisual");
                            try {
                                serializer.text(specialboysdetails.getPartialVisual());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PartialVisual");

                            serializer.startTag(null, "FullHearing");
                            try {
                                serializer.text(specialboysdetails.getFullHearing());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FullHearing");

                            serializer.startTag(null, "PartialHearing");
                            try {
                                serializer.text(specialboysdetails.getPartialHearing());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PartialHearing");

                            serializer.startTag(null, "FullSpeech");
                            try {
                                serializer.text(specialboysdetails.getFullSpeech());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FullSpeech");

                            serializer.startTag(null, "PartialSpeech");
                            try {
                                serializer.text(specialboysdetails.getPartialSpeech());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PartialSpeech");

                            serializer.startTag(null, "HandArm");
                            try {
                                serializer.text(specialboysdetails.getHandarm());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "HandArm");

                            serializer.startTag(null, "LegFoot");
                            try {
                                serializer.text(specialboysdetails.getLegfoot());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "LegFoot");

                            serializer.startTag(null, "MentalPhysical");
                            try {
                                serializer.text(specialboysdetails.getMental());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "MentalPhysical");
                            serializer.endTag(null, "Students");

                        }

                        serializer.endTag(null, "DisabledStudents");


                        //////DISBALED STUDENTS GIRLS//////


                        serializer.startTag(null, "DisabledStudents");

                        for (int i = 0; i < specialgirlsList.size(); i++) {

                            specialgirlsdetails = specialgirlsList.get(i);
                            serializer.startTag(null, "Students");

                            serializer.startTag(null, "CLASS");
                            try {
                                serializer.text(specialgirlsdetails.getClassName());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "CLASS");

                            serializer.startTag(null, "Gender");
                            try {
                                serializer.text("Girls");
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "Gender");

                            serializer.startTag(null, "FullVisual");
                            try {
                                serializer.text(specialgirlsdetails.getFullVisual());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FullVisual");
                            serializer.startTag(null, "PartialVisual");
                            try {
                                serializer.text(specialgirlsdetails.getPartialVisual());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PartialVisual");

                            serializer.startTag(null, "FullHearing");
                            try {
                                serializer.text(specialgirlsdetails.getFullHearing());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FullHearing");

                            serializer.startTag(null, "PartialHearing");
                            try {
                                serializer.text(specialgirlsdetails.getPartialHearing());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PartialHearing");

                            serializer.startTag(null, "FullSpeech");
                            try {
                                serializer.text(specialgirlsdetails.getFullSpeech());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "FullSpeech");

                            serializer.startTag(null, "PartialSpeech");
                            try {
                                serializer.text(specialgirlsdetails.getPartialSpeech());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "PartialSpeech");

                            serializer.startTag(null, "HandArm");
                            try {
                                serializer.text(specialgirlsdetails.getHandarm());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "HandArm");

                            serializer.startTag(null, "LegFoot");
                            try {
                                serializer.text(specialgirlsdetails.getLegfoot());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "LegFoot");

                            serializer.startTag(null, "MentalPhysical");
                            try {
                                serializer.text(specialgirlsdetails.getMental());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "MentalPhysical");
                            serializer.endTag(null, "Students");

                        }

                        serializer.endTag(null, "DisabledStudents");
                    }

                    ////////ENROLLEMTN BY GROUP SECTION//////////

                    if (enrollmentgrpStrScreen.equals("True")) {
                        serializer.startTag(null, "EnrollmentByGroup");
                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 6");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a6groupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b6groupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 7");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a7groupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b7groupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 8");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a8groupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b8groupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 9");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Science");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a9Sgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b9Sgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 9");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Arts");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a9Agroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b9Agroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 9");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Computer Science");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a9Cgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b9Cgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");


                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 10");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Science");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a10Sgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b10Sgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 10");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Arts");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a10Agroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b10Agroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");

                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 10");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Computer Science");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(a10Cgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(b10Cgroupsection);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");
                        serializer.endTag(null, "EnrollmentByGroup");

                        /////////higher seoncary school////////

                        serializer.startTag(null, "EnrollmentHigherSecondary");
                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 11");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("PreMedical");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_11_Medical);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_11_Medical);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 11");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("PreEngineering");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_11_Eng);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_11_Eng);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 11");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Arts");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_11_Arts);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_11_Arts);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 11");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Inter Science");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_11_Inter);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_11_Inter);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 12");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("PreMedical");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_12_Medical);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_12_Medical);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");


                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 12");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("PreEngineering");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_12_Eng);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_12_Eng);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 12");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Arts");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_12_Arts);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_12_Arts);
                        } catch (Exception e) {
                        }
                        ;
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.startTag(null, "Enrollment");
                        serializer.startTag(null, "CLASS");
                        try {
                            serializer.text("Class 12");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "CLASS");
                        serializer.startTag(null, "Group");
                        try {
                            serializer.text("Inter Science");
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Group");

                        serializer.startTag(null, "TotalEnrollment");
                        try {
                            serializer.text(enrol_12_Inter);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "TotalEnrollment");

                        serializer.startTag(null, "Sections");
                        try {
                            serializer.text(sec_12_Inter);
                        } catch (Exception e) {
                        }
                        ;
                        serializer.endTag(null, "Sections");

                        serializer.endTag(null, "Enrollment");

                        serializer.endTag(null, "EnrollmentHigherSecondary");
                    }


                    ////////SECURITY MEASURES/////////

                    if (securityStrScreen.equals("True")) {
                        serializer.startTag(null, "BoundaryWallHeight");
                        try {
                            serializer.text(SM_HEIGHT);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BoundaryWallHeight");

                        serializer.startTag(null, "NoOfSecurityGuards");
                        try {
                            serializer.text(SM_GUARDS);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfSecurityGuards");

                        serializer.startTag(null, "NoOfChowkidar");
                        try {
                            serializer.text(SM_CHOWKIDAR);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfChowkidar");

                        serializer.startTag(null, "NoOfWeapons");
                        try {
                            serializer.text(SM_WEAPONS);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfWeapons");

                        serializer.startTag(null, "NoOfMetalDetectors");
                        try {
                            serializer.text(SM_METAL);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "NoOfMetalDetectors");

                        serializer.startTag(null, "BarbedWire");
                        try {
                            serializer.text(SM_BARBED);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BarbedWire");

                        serializer.startTag(null, "GlassSpikes");
                        try {
                            serializer.text(SM_GLASS);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "GlassSpikes");

                        serializer.startTag(null, "EnteranceBlocks");
                        try {
                            serializer.text(SM_ENTRANCE);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "EnteranceBlocks");

                        serializer.startTag(null, "SOSAvailable");
                        try {
                            serializer.text(SM_SOS);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "SOSAvailable");
                    }

                    /////////////provision OF FREE TEXT BOOKS//////////
                    if (ftbStrScreen.equals("True")) {
                        serializer.startTag(null, "PROVISIONFREEBOOKS");

                        for (int i = 0; i < pbooks.size(); i++) {
                            pdetails = pbooks.get(i);

                            serializer.startTag(null, "FREEBOOKS");

                            serializer.startTag(null, "CLASS");
                            try {
                                serializer.text(pdetails.getClassNo());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "CLASS");

                            serializer.startTag(null, "SUBJECT");
                            try {
                                serializer.text(pdetails.getSubjectName());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SUBJECT");

                            serializer.startTag(null, "BOOKSDEMAND");
                            try {
                                serializer.text(pdetails.getBookDemand());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "BOOKSDEMAND");

                            serializer.startTag(null, "BOOKSRECIEVED");
                            try {
                                serializer.text(pdetails.getBookRecieved());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "BOOKSRECIEVED");

                            serializer.startTag(null, "STUDENTNONFTB");
                            try {
                                serializer.text(pdetails.getStdWithFTB());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "STUDENTNONFTB");

                            serializer.startTag(null, "SURPLUSBOOKS");
                            try {
                                serializer.text(pdetails.getSurplusBooks());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "SURPLUSBOOKS");

                            serializer.startTag(null, "BOOKSRETURN");
                            try {
                                serializer.text(pdetails.getBooksReturnToOffice());
                            } catch (Exception e) {
                            }
                            serializer.endTag(null, "BOOKSRETURN");

                            serializer.endTag(null, "FREEBOOKS");
                        }

                        serializer.endTag(null, "PROVISIONFREEBOOKS");
                    }
                    ///////SCSHOOL FURNITURE/////

                    if (furnitureStrScreen.equals("True")) {
                        serializer.startTag(null, "BLACKGREENBLACKBOARD");
                        try {
                            serializer.text(Fur_BLACK);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "BLACKGREENBLACKBOARD");

                        serializer.startTag(null, "OFFICETABLES");
                        try {
                            serializer.text(Fur_TABLES);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "OFFICETABLES");

                        serializer.startTag(null, "OFFICECHAIRS");
                        try {
                            serializer.text(Fur_CHAIRS);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "OFFICECHAIRS");

                        serializer.startTag(null, "ALMIRAH");
                        try {
                            serializer.text(Fur_ALMIRAH);
                        } catch (Exception e) {
                        }
                        serializer.endTag(null, "ALMIRAH");
                    }


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

                    Toast.makeText(FormSaving.this, "File Saved Successfully",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                    new PostDataAsyncTaskSaveForm(formId).execute();
                    databasehandler.removeRosteremis(emisCode);
                    databasehandler.removeTeacherData(emisCode);
                    databasehandler.removeTeacherData1(emisCode);
                    databasehandler.removeTeacherData2(emisCode);
                    databasehandler.removeTeacherData3(emisCode);
                    databasehandler.removeTeacherData4proxy(emisCode);
                    databasehandler.removeTeacherDatavacant(emisCode);
                    databasehandler.removeTeacherDatavisit(emisCode);
                    databasehandler.removeTeacherDataappointed(emisCode);
                    databasehandler.removeTeacherDatasanctionedT(emisCode);
                    databasehandler.removeTeacherDatasanctionedNT(emisCode);
                    databasehandler.removeTeacherDataenrollmentboys(emisCode);
                    databasehandler.removeTeacherDataenrollmentgirls(emisCode);
                    databasehandler.removeTeacherDataenrollmentgap(emisCode);
                    databasehandler.removeTeacherDataenrollmentspecialboys(emisCode);
                    databasehandler.removeTeacherDataenrollmentspecialgirls(emisCode);
                    SharedPreferences p5 = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                    SharedPreferences.Editor e1 = p5.edit();
                    e1.clear();
                    e1.commit();

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

                    byte[] encryptedText = CryptoUtils.encrypt(key, newxmlfile);

                   // newxmlfile.delete();

                    File encryptfile = new File(newxmlfile, "");
                    try {
                        encryptfile.createNewFile();
                        FileOutputStream fileStream = new FileOutputStream(
                                encryptfile);
                        fileStream.write(encryptedText);

                    } catch (IOException e) {
                        Log.d("Error on create File",
                                "exception in createNewFile() method");
                    }
                    /*FileOutputStream fileStream = new FileOutputStream(
                            encryptfile);
                    fileStream.write(encryptedText);

                    FormSavingGcs.this.finish();*/

                    /*if (isMyServiceRunning(FormService.class)) {

                        Intent i = new Intent(getActivity(), FormService.class);

                        getActivity().stopService(i);

                    }*/

                } catch (Exception e) {
                    Toast.makeText(FormSaving.this, "Error while saving",
                            Toast.LENGTH_LONG).show();
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
