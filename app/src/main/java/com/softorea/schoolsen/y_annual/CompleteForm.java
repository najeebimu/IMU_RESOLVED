package com.softorea.schoolsen.y_annual;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.b_annual.B_BuildingCondition;
import com.softorea.schoolsen.b_annual.B_Cleanliness;
import com.softorea.schoolsen.b_annual.B_Commodities;
import com.softorea.schoolsen.b_annual.B_Construction;
import com.softorea.schoolsen.b_annual.B_IT_LabExists;
import com.softorea.schoolsen.b_annual.B_InfraStructure;
import com.softorea.schoolsen.b_annual.B_ParentTeacherCouncil;
import com.softorea.schoolsen.b_annual.B_SchoolArea;
import com.softorea.schoolsen.b_annual.B_Stipend;
import com.softorea.schoolsen.b_annual.B_TeacherGuides;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.dialogs.M_NonTeacherPresenceList;
import com.softorea.schoolsen.dialogs.M_NonTeacherPresenceListNew;
import com.softorea.schoolsen.dialogs.M_ProxyTeacherList;
import com.softorea.schoolsen.dialogs.M_SanctionedPostList;
import com.softorea.schoolsen.dialogs.M_SanctionedPostNonteachingList;
import com.softorea.schoolsen.dialogs.M_SchoolVisitList;
import com.softorea.schoolsen.dialogs.M_TeacherAppointedByList;
import com.softorea.schoolsen.dialogs.M_TeacherPresenceList;
import com.softorea.schoolsen.dialogs.M_TeacherPresenceListNew;
import com.softorea.schoolsen.dialogs.M_TrainingRecordList;
import com.softorea.schoolsen.dialogs.M_VacantPositionsList;
import com.softorea.schoolsen.lists.FormSaving;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.m_monthly.MoreInfo;

/**
 * Created by Softorea on 11/13/2017.
 */

public class CompleteForm extends Activity {
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    TextView Schoolemiscode, Schoolname, Gender, Level, DDOcode, District, Tehsil, UCname, VillageCouncil, VillageCityName, Locality,
            Street, PhoneNo, Location, SchoolZone, Nano, Pkno, CircleOfficename, SDEOoffice, BuildingOwnership, TypeOfUpgradation,
            YearPrimary, YearMiddle, YearHigh, YearHighSecondary, LandAllocated, VisitType, ASDEOname, ContactNo, BuildingAvail,
            OccupiedBy, OccupiedSince, HeadInstitute, HeadDesig, HeadNo, ElecAvail, ElecFunctioning, ElecSource, DrinkingAvail,
            DrinkingFunctioning, DrinkingSource, WaterDrinkable, ToiletsAvail, ToiletsFunc, StdToiletAvail, StdToiletFunc,
            TeacherToiletAvail, TeacherToiletFunc, CommonToiletAvail, CommonToiletFunc, BoundaryWallAvail, BoundaryWallFunc;

    String schoolareaStrScreen,constructionStrScreen,buildindconditionStrScreen,itlabStrScreen,
            commodititesStrScreen,ptcStrScreen,infrastructureStrScreen,guideStrScreen,cleanlinessStrScreen
            ,stipendStrScreen,SanctionedStrScreen,indicatorStrScreen,
            enrollmentageStrScreen,enrollmentgrpStrScreen,disabledStrScreen,securityStrScreen,ftbStrScreen,furnitureStrScreen;

    TextView ptcLastElection, dateOfEstablishMent, isPTCestablished, PtcMemberstrained,
            name, no, bankName, bankAccountNo, bankBranchcode, balance, amountReceived, StipendRecord, StipendYear,
            SchemeName6, eligibleStudents6, EligibleNoStipend6, class6remarks,
            SchemeName7, eligibleStudents7, EligibleNoStipend7, class7remarks,
            SchemeName8, eligibleStudents8, EligibleNoStipend8, class8remarks,
            SchemeName9, eligibleStudents9, EligibleNoStipend9, class9remarks,
            SchemeName10, eligibleStudents10, EligibleNoStipend10, class10remarks, flagcheck, grantcheck;

    TextView amountReceivedOthers,expenditures,presentbalance,lastmonthmeeting,bankstatmentptc;

    TextView uncoveredAreaSpinner, coveredArea, UncoveredArea, roomsInBasement, roomsInGroundFloor, roomsInFirstFloor,
            roomsInSecondFloor, roomsInThirdFloor, DISTANCE, noOfpakkaClassrooms, noOfpakkaOtherrooms,
            noOfkachaClassrooms, noOfkachaOtherrooms, building_condition_spinner, noOfClassrooms, noOfOtherRooms,
            noOfClassroomsMajor, noOfOtherroomsMajor, ItLabExists, LabFunctional, LabEstablished, InternetAvailable,
            noOfComputers, noOfFunctionalComputers;

    TextView anyothername, anyothername2, anyotheruseable2, anyotherunuseable2, anyothernumberOfnew,
            twoUseable, twoUnUseable, twoNewRequired, threeUseable, threeUnUseable, threeNewRequired,
            benchesUseable, benchesUnUseable, benchesNewRequired, chairsUseable, chairsUnUseable, chairsNewRequired,
            tabletchairsUseable, tabletchairsUnUseable, tabletchairsNewRequired, tatsUseable,
            tatsUnUseable, tatsNewRequired, fansUseable, fansUnUseable, fansNewRequired, otherUseable, otherUnUseable, otherNewRequired;

    TextView HToffice, sciencelab, Staffroom, library, clerkroom, examinationhall, playground, onescreen, alternaivesource,
            teacherguide, schCleanN, stdCleanN, HostelExists, Office, Store, Lab, Entance, Building, Toilets;

    TextView boundarywall, HEIGHT, GUARDS, CHOWKIDAR, WEAPONS, METAL, barbed, glass, entrance, sos, BLACK, TABLES, CHAIRS, ALMIRAH;
    Button next, back;

    String column1, column2, column3, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14;

    TextView teacherpresence, trainingdetails, nonteacher, proxyteacher, appointedby, visitby, vacantposition,
            SANCTIONEDPOST, NONSACNTIONEDPOST, grant;

    TextView newteacherpresence,newNonteacherpresence;


    LinearLayout ptcHeading,ptcContent;
    LinearLayout stipendHeading,stipendContent;
    LinearLayout schoolareaHeading,schoolareaContent;
    LinearLayout furnitureLayout,securityLayout,cleanlinessLayout,guideLayout,constructionLayout,conditionLayout,
    itlabLayout,commoditiesLayout,infrastructureLayout,sanctionedLayout,facilitiesLayout,trainingLayout,teacherLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_formview);

        trainingLayout = (LinearLayout) findViewById(R.id.trainingLayout);
        teacherLayout = (LinearLayout) findViewById(R.id.teacherpresenceLayout);

        ptcHeading = (LinearLayout) findViewById(R.id.ptcLayoutHeading);
        ptcContent = (LinearLayout) findViewById(R.id.ptcLayoutContent);

        stipendHeading = (LinearLayout) findViewById(R.id.stipendLayoutHeading);
        stipendContent = (LinearLayout) findViewById(R.id.stipendLayoutContent);

        schoolareaHeading = (LinearLayout) findViewById(R.id.schoolareaLayoutHeading);
        schoolareaContent = (LinearLayout) findViewById(R.id.schoolareaLayoutContent);
        furnitureLayout = (LinearLayout) findViewById(R.id.FurnitureLayout);
        securityLayout = (LinearLayout) findViewById(R.id.securityLayout);
        cleanlinessLayout = (LinearLayout) findViewById(R.id.cleanlinessLayout);
        guideLayout = (LinearLayout) findViewById(R.id.guideLayout);

        constructionLayout = (LinearLayout) findViewById(R.id.construtionLayout);
        conditionLayout = (LinearLayout) findViewById(R.id.conditionLayout);
        itlabLayout = (LinearLayout) findViewById(R.id.itLabLayout);
        commoditiesLayout = (LinearLayout) findViewById(R.id.commoditiesLayout);
        infrastructureLayout = (LinearLayout) findViewById(R.id.infrastructureLayout);
        sanctionedLayout = (LinearLayout) findViewById(R.id.sanctionedLayout);
        facilitiesLayout = (LinearLayout) findViewById(R.id.facilitiesLayout);

        SQLiteOpenHelper databasee = new DatabaseHandler(CompleteForm.this);
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


        if (!ptcStrScreen.equals("True"))
        {
            ptcHeading.setVisibility(View.GONE);
            ptcContent.setVisibility(View.GONE);
        }
        if (!stipendStrScreen.equals("True"))
        {
            stipendHeading.setVisibility(View.GONE);
            stipendContent.setVisibility(View.GONE);
        }
        if (!schoolareaStrScreen.equals("True"))
        {
            schoolareaHeading.setVisibility(View.GONE);
            schoolareaContent.setVisibility(View.GONE);
        }
        if (!constructionStrScreen.equals("True"))
        {
            constructionLayout.setVisibility(View.GONE);
        }
        if (!buildindconditionStrScreen.equals("True"))
        {
            conditionLayout.setVisibility(View.GONE);
        }
        if (!itlabStrScreen.equals("True"))
        {
            itlabLayout.setVisibility(View.GONE);
        }
        if (!commodititesStrScreen.equals("True"))
        {
            commoditiesLayout.setVisibility(View.GONE);
        }
        if (!infrastructureStrScreen.equals("True"))
        {
            infrastructureLayout.setVisibility(View.GONE);
        }
        if (!guideStrScreen.equals("True"))
        {
            guideLayout.setVisibility(View.GONE);
        }
        if (!cleanlinessStrScreen.equals("True"))
        {
            cleanlinessLayout.setVisibility(View.GONE);
        }
        if (!furnitureStrScreen.equals("True"))
        {
            furnitureLayout.setVisibility(View.GONE);
        }
        if (!indicatorStrScreen.equals("True"))
        {
            facilitiesLayout.setVisibility(View.GONE);
        }
        if (!SanctionedStrScreen.equals("True"))
        {
            sanctionedLayout.setVisibility(View.GONE);
            trainingLayout.setVisibility(View.GONE);
        }
        if (!securityStrScreen.equals("True"))
        {
            securityLayout.setVisibility(View.GONE);
        }

        teacherpresence = (TextView) findViewById(R.id.teacherpresence);
        newteacherpresence = (TextView) findViewById(R.id.newteacherpresence);
        newNonteacherpresence = (TextView) findViewById(R.id.newnonteacherpresence);
        trainingdetails = (TextView) findViewById(R.id.trainingdetails);
        nonteacher = (TextView) findViewById(R.id.nonteacherpresence);
        proxyteacher = (TextView) findViewById(R.id.proxyteacher);
        appointedby = (TextView) findViewById(R.id.appointedby);
        visitby = (TextView) findViewById(R.id.visitby);
        vacantposition = (TextView) findViewById(R.id.vacantposition);
        grant = (TextView) findViewById(R.id.grant);
        SANCTIONEDPOST = (TextView) findViewById(R.id.sanctionedteaching);
        NONSACNTIONEDPOST = (TextView) findViewById(R.id.sanctionednonteaching);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String emiscode = preferences.getString("emiscode", "");
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
        SharedPreferences aa = getSharedPreferences("abcd", MODE_PRIVATE);
        String visitTy = aa.getString("visitycheck", "");
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM t_schoolinformation WHERE emis= " + emiscode, null);
        if (c.moveToFirst()) {
            do {
                SharedPreferences qer = PreferenceManager.getDefaultSharedPreferences(CompleteForm.this);
                column1 = c.getString(c.getColumnIndex("schoolname"));
                column2 = c.getString(c.getColumnIndex("gender"));
                column3 = c.getString(c.getColumnIndex("schoollevel"));
                column5 = c.getString(c.getColumnIndex("district"));
                column6 = c.getString(c.getColumnIndex("tehsil"));
                column7 = c.getString(c.getColumnIndex("ucname"));
                column8 = c.getString(c.getColumnIndex("location"));
                column9 = c.getString(c.getColumnIndex("schoolzone"));
                column10 = c.getString(c.getColumnIndex("nano"));
                column11 = c.getString(c.getColumnIndex("pkno"));
                column12 = c.getString(c.getColumnIndex("circleofficename"));
                column13 = c.getString(c.getColumnIndex("ado_name"));
                column14 = c.getString(c.getColumnIndex("ado_no"));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        next = (Button) findViewById(R.id.btn_right);
        back = (Button) findViewById(R.id.btn_left);

        Schoolemiscode = (TextView) findViewById(R.id.c_emis);
        Schoolemiscode.setText(emiscode);
        Schoolname = (TextView) findViewById(R.id.c_schoolname);
        Schoolname.setText(column1);
        Gender = (TextView) findViewById(R.id.c_gender);
        Gender.setText(column2);
        Level = (TextView) findViewById(R.id.c_level);
        Level.setText(column3);
        DDOcode = (TextView) findViewById(R.id.c_ddocode);
        DDOcode.setText(ddo_cde);
        District = (TextView) findViewById(R.id.c_district);
        District.setText(column5);
        Tehsil = (TextView) findViewById(R.id.c_tehsil);
        Tehsil.setText(column6);
        UCname = (TextView) findViewById(R.id.c_ucname);
        UCname.setText(column7);
        VillageCouncil = (TextView) findViewById(R.id.c_villagecouncilname);
        VillageCouncil.setText(vcname);
        VillageCityName = (TextView) findViewById(R.id.villagecityname);
        VillageCityName.setText(vcityname);
        Locality = (TextView) findViewById(R.id.c_localityname);
        Locality.setText(locality);
        Street = (TextView) findViewById(R.id.c_streetname);
        Street.setText(street);
        PhoneNo = (TextView) findViewById(R.id.c_phoneno);
        PhoneNo.setText(schoolphone);
        Location = (TextView) findViewById(R.id.c_location);
        Location.setText(column8);
        SchoolZone = (TextView) findViewById(R.id.c_schoolzone);
        SchoolZone.setText(column9);
        Nano = (TextView) findViewById(R.id.c_nano);
        Nano.setText(column10);
        Pkno = (TextView) findViewById(R.id.c_pkno);
        Pkno.setText(column11);
        CircleOfficename = (TextView) findViewById(R.id.c_circleofficename);
        CircleOfficename.setText(column12);
        SDEOoffice = (TextView) findViewById(R.id.c_sdeoofficename);
        SDEOoffice.setText(sdeo);
        BuildingOwnership = (TextView) findViewById(R.id.c_buildingownership);
        BuildingOwnership.setText(buildingownership);
        TypeOfUpgradation = (TextView) findViewById(R.id.c_typeofupgradation);
        TypeOfUpgradation.setText(upgradationtype);
        YearPrimary = (TextView) findViewById(R.id.c_primary);
        if (!pirm.equals("Select Year"))
        {
            YearPrimary.setText(pirm);
        }
        YearMiddle = (TextView) findViewById(R.id.c_middle);
        if (!midd.equals("Select Year"))
        {
            YearMiddle.setText(midd);
        }
        YearHigh = (TextView) findViewById(R.id.c_high);
        if (!high.equals("Select Year"))
        {
            YearHigh.setText(high);
        }
        YearHighSecondary = (TextView) findViewById(R.id.c_highsecondary);
        if (!highsec.equals("Select Year"))
        {
            YearHighSecondary.setText(highsec);
        }
        LandAllocated = (TextView) findViewById(R.id.c_landdonated);
        LandAllocated.setText(landdonatedd);
        VisitType = (TextView) findViewById(R.id.c_visittype);
        VisitType.setText(visitTy);

        SharedPreferences pref1 = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String asdeoname = pref1.getString("asdeoname", "");
        String asdeocontactno = pref1.getString("asdeocontactno", "");
        ASDEOname = (TextView) findViewById(R.id.c_asdeo);
        ASDEOname.setText(asdeoname);
        ContactNo = (TextView) findViewById(R.id.c_asdeocontact);
        ContactNo.setText(asdeocontactno);
        String illegalvalue = pref1.getString("illegalkey", "");
        String occupationvalue = pref1.getString("occupation_occupied", "");
        String date = pref1.getString("occupation_date", "");

        String illegalvaluee = pref1.getString("headofinstitution", "");
        String typevaluee = pref1.getString("headcellno", "");
        String occupationvaluee = pref1.getString("headdesignation", "");

        BuildingAvail = (TextView) findViewById(R.id.c_buildingavail);
        OccupiedBy = (TextView) findViewById(R.id.c_occupiedby);
        OccupiedSince = (TextView) findViewById(R.id.c_occupiedsince);
        BuildingAvail.setText(illegalvalue);
        OccupiedBy.setText(occupationvalue);
        OccupiedSince.setText(date);

        HeadInstitute = (TextView) findViewById(R.id.c_headofinstitution);
        HeadDesig = (TextView) findViewById(R.id.c_headdesignation);
        HeadNo = (TextView) findViewById(R.id.c_headno);
        HeadInstitute.setText(illegalvaluee);
        HeadDesig.setText(occupationvaluee);
        HeadNo.setText(typevaluee);

        ElecAvail = (TextView) findViewById(R.id.c_electricityavail);
        ElecFunctioning = (TextView) findViewById(R.id.c_electricityfunctioning);
        ElecSource = (TextView) findViewById(R.id.c_electricitysource);
        DrinkingAvail = (TextView) findViewById(R.id.c_drinkingwateravail);
        DrinkingFunctioning = (TextView) findViewById(R.id.c_drikingwaterfunctionality);
        DrinkingSource = (TextView) findViewById(R.id.c_drikingwaterSource);
        WaterDrinkable = (TextView) findViewById(R.id.c_drikingwaterdrinkable);
        ToiletsAvail = (TextView) findViewById(R.id.c_toiletavailable);
        ToiletsFunc = (TextView) findViewById(R.id.c_toiletfunctioning);
        StdToiletAvail = (TextView) findViewById(R.id.c_stdtoiletavailable);
        StdToiletFunc = (TextView) findViewById(R.id.c_stdtoiletfunctioning);
        TeacherToiletAvail = (TextView) findViewById(R.id.c_tchtoiletavailable);
        TeacherToiletFunc = (TextView) findViewById(R.id.c_tchtoiletfunctional);
        CommonToiletAvail = (TextView) findViewById(R.id.c_cmmntoiletavailable);
        CommonToiletFunc = (TextView) findViewById(R.id.c_cmmntoiletfunctional);
        BoundaryWallAvail = (TextView) findViewById(R.id.c_wallavailable);
        BoundaryWallFunc = (TextView) findViewById(R.id.c_wallfunctioning);

        String electricSrc = pref1.getString("electricSrc", "");
        String electriAvailable = pref1.getString("electriAvailable", "");
        String electricFunction = pref1.getString("electricFunction", "");
        String waterAvail = pref1.getString("waterAvail", "");
        String waterFunction = pref1.getString("waterFunction", "");
        String watersource = pref1.getString("watersource", "");
        String drinkable = pref1.getString("drinkable", "");
        String toiletAvail = pref1.getString("toiletAvail", "");
        String toiletFunction = pref1.getString("toiletFunction", "");
        String boundaryAvail = pref1.getString("boundaryAvail", "");
        String boundaryFunction = pref1.getString("boundaryFunction", "");
        String stdToilet = pref1.getString("stdToilet", "");
        String TeachToilet = pref1.getString("TeachToilet", "");
        String comToilet = pref1.getString("comToilet", "");
        String stdToiletFunc = pref1.getString("stdToiletFunc", "");
        String teacherToiletFunc = pref1.getString("teacherToiletFunc", "");
        String commonToiletFunction = pref1.getString("commonToiletFunction", "");

        ElecSource.setText(electricSrc);
        ElecAvail.setText(electriAvailable);
        ElecFunctioning.setText(electricFunction);
        DrinkingAvail.setText(waterAvail);
        DrinkingFunctioning.setText(waterFunction);
        DrinkingSource.setText(watersource);
        WaterDrinkable.setText(drinkable);
        ToiletsAvail.setText(toiletAvail);
        ToiletsFunc.setText(toiletFunction);
        BoundaryWallAvail.setText(boundaryAvail);
        BoundaryWallFunc.setText(boundaryFunction);
        StdToiletAvail.setText(stdToilet);
        TeacherToiletAvail.setText(TeachToilet);
        CommonToiletAvail.setText(comToilet);
        StdToiletFunc.setText(stdToiletFunc);
        TeacherToiletFunc.setText(teacherToiletFunc);
        CommonToiletFunc.setText(commonToiletFunction);

        String last = pref1.getString("PTC_lastelection", "");
        String doe = pref1.getString("PTC_DOE", "");
        String namee = pref1.getString("PTC_name", "");
        String noo = pref1.getString("PTC_no", "");
        String banknamee = pref1.getString("PTC_bakname", "");
        String accno = pref1.getString("PTC_acoountno", "");
        String bcode = pref1.getString("PTC_branchcode", "");
        String blnc = pref1.getString("PTC_balance", "");
        String amnt = pref1.getString("PTC_amount", "");

        String amntothers = pref1.getString("PTC_amountothers", "");
        String expendituress = pref1.getString("PTC_expenditures", "");
        String presentbalancee = pref1.getString("PTC_presentbalance", "");
        String lastmnthmetng = pref1.getString("PTC_lastmonthmeeting", "");

        String estvalue = pref1.getString("PTC_EstablishSpinner", "");
        String trainedvalue = pref1.getString("PTC_MembersTrainedSpinner", "");
        String bankstatementshown = pref1.getString("PTC_BankStatementStr", "");

        ptcLastElection = (TextView) findViewById(R.id.c_ptcelectiondate);
        dateOfEstablishMent = (TextView) findViewById(R.id.c_dateofestablishment);
        isPTCestablished = (TextView) findViewById(R.id.c_ptcestablished);
        PtcMemberstrained = (TextView) findViewById(R.id.c_ptcmemberstrained);
        name = (TextView) findViewById(R.id.c_ptchairpersonname);
        no = (TextView) findViewById(R.id.c_contactno);
        bankName = (TextView) findViewById(R.id.c_bankname);
        bankAccountNo = (TextView) findViewById(R.id.c_bankaccountno);
        bankBranchcode = (TextView) findViewById(R.id.c_bankbranchcode);
        balance = (TextView) findViewById(R.id.c_balance);
        amountReceived = (TextView) findViewById(R.id.c_amountreceived);

        amountReceivedOthers = (TextView) findViewById(R.id.c_amountreceivedothers);
        expenditures = (TextView) findViewById(R.id.c_expenditures);
        presentbalance = (TextView) findViewById(R.id.c_presentbalance);
        lastmonthmeeting = (TextView) findViewById(R.id.c_lastmonthmeeting);
        bankstatmentptc = (TextView) findViewById(R.id.c_bankstatement);

        ptcLastElection.setText(last);
        dateOfEstablishMent.setText(doe);
        isPTCestablished.setText(estvalue);
        PtcMemberstrained.setText(trainedvalue);
        name.setText(namee);
        no.setText(noo);
        bankName.setText(banknamee);
        bankAccountNo.setText(accno);
        bankBranchcode.setText(bcode);
        balance.setText(blnc);
        amountReceived.setText(amnt);

        amountReceivedOthers.setText(amntothers);
        expenditures.setText(expendituress);
        presentbalance.setText(presentbalancee);
        lastmonthmeeting.setText(lastmnthmetng);
        bankstatmentptc.setText(bankstatementshown);


        String rs = pref1.getString("stipend_recordshown", "");
        String sy = pref1.getString("stipend_year", "");
        String stipend_SchemeName6 = pref1.getString("stipend_SchemeName6", "");
        String stipend_eligibleStudents6 = pref1.getString("stipend_eligibleStudents6", "");
        String stipend_EligibleNoStipend6 = pref1.getString("stipend_EligibleNoStipend6", "");
        String stipend_total6 = pref1.getString("stipend_total6", "");
        String stipend_SchemeName7 = pref1.getString("stipend_SchemeName7", "");
        String stipend_eligibleStudents7 = pref1.getString("stipend_eligibleStudents7", "");
        String stipend_EligibleNoStipend7 = pref1.getString("stipend_EligibleNoStipend7", "");
        String stipend_total7 = pref1.getString("stipend_total7", "");
        String stipend_SchemeName8 = pref1.getString("stipend_SchemeName8", "");
        String stipend_eligibleStudents8 = pref1.getString("stipend_eligibleStudents8", "");
        String stipend_EligibleNoStipend8 = pref1.getString("stipend_EligibleNoStipend8", "");
        String stipend_total8 = pref1.getString("stipend_total8", "");
        String stipend_SchemeName9 = pref1.getString("stipend_SchemeName9", "");
        String stipend_eligibleStudents9 = pref1.getString("stipend_eligibleStudents9", "");
        String stipend_EligibleNoStipend9 = pref1.getString("stipend_EligibleNoStipend9", "");
        String stipend_total9 = pref1.getString("stipend_total9", "");
        String stipend_SchemeName10 = pref1.getString("stipend_SchemeName10", "");
        String stipend_eligibleStudents10 = pref1.getString("stipend_eligibleStudents10", "");
        String stipend_EligibleNoStipend10 = pref1.getString("stipend_EligibleNoStipend10", "");
        String stipend_remarks10 = pref1.getString("stipend_remarks", "");

        StipendRecord = (TextView) findViewById(R.id.c_stipendrecordshown);
        SchemeName6 = (TextView) findViewById(R.id.c_six_namescheme);
        eligibleStudents6 = (TextView) findViewById(R.id.c_six_eligiblestudents);
        EligibleNoStipend6 = (TextView) findViewById(R.id.c_six_eligiblewhoreceive);
        class6remarks = (TextView) findViewById(R.id.c_six_remarks);
        SchemeName7 = (TextView) findViewById(R.id.c_seven_namescheme);
        eligibleStudents7 = (TextView) findViewById(R.id.c_seven_eligiblestudents);
        EligibleNoStipend7 = (TextView) findViewById(R.id.c_seven_eligiblewhoreceive);
        class7remarks = (TextView) findViewById(R.id.c_seven_remarks);
        SchemeName8 = (TextView) findViewById(R.id.c_eight_namescheme);
        eligibleStudents8 = (TextView) findViewById(R.id.c_eight_eligiblestudents);
        EligibleNoStipend8 = (TextView) findViewById(R.id.c_eight_eligiblewhoreceive);
        class8remarks = (TextView) findViewById(R.id.c_eight_remarks);
        SchemeName9 = (TextView) findViewById(R.id.c_nine_namescheme);
        eligibleStudents9 = (TextView) findViewById(R.id.c_nine_eligiblestudents);
        EligibleNoStipend9 = (TextView) findViewById(R.id.c_nine_eligiblewhoreceive);
        class9remarks = (TextView) findViewById(R.id.c_nine_remarks);
        SchemeName10 = (TextView) findViewById(R.id.c_ten_namescheme);
        eligibleStudents10 = (TextView) findViewById(R.id.c_ten_eligiblestudents);
        EligibleNoStipend10 = (TextView) findViewById(R.id.c_ten_eligiblewhoreceive);
        class10remarks = (TextView) findViewById(R.id.c_ten_remarks);
        StipendYear = (TextView) findViewById(R.id.c_stipendyear);

        StipendYear.setText(sy);
        StipendRecord.setText(rs);
        SchemeName6.setText(stipend_SchemeName6);
        eligibleStudents6.setText(stipend_eligibleStudents6);
        EligibleNoStipend6.setText(stipend_EligibleNoStipend6);
        class6remarks.setText(stipend_total6);
        SchemeName7.setText(stipend_SchemeName7);
        eligibleStudents7.setText(stipend_eligibleStudents7);
        EligibleNoStipend7.setText(stipend_EligibleNoStipend7);
        class7remarks.setText(stipend_total7);
        SchemeName8.setText(stipend_SchemeName8);
        eligibleStudents8.setText(stipend_eligibleStudents8);
        EligibleNoStipend8.setText(stipend_EligibleNoStipend8);
        class8remarks.setText(stipend_total8);
        SchemeName9.setText(stipend_SchemeName9);
        eligibleStudents9.setText(stipend_eligibleStudents9);
        EligibleNoStipend9.setText(stipend_EligibleNoStipend9);
        class9remarks.setText(stipend_total9);
        SchemeName10.setText(stipend_SchemeName10);
        eligibleStudents10.setText(stipend_eligibleStudents10);
        EligibleNoStipend10.setText(stipend_EligibleNoStipend10);
        class10remarks.setText(stipend_remarks10);

        String flaghosted = pref1.getString("flagcheck", "");
        String grantexist = pref1.getString("grantcheck", "");
        flagcheck = (TextView) findViewById(R.id.c_flaghosted);
        grantcheck = (TextView) findViewById(R.id.c_conditionalgrant);
        flagcheck.setText(flaghosted);
        grantcheck.setText(grantexist);

        uncoveredAreaSpinner = (TextView) findViewById(R.id.c_uncoveredareaspiner);
        DISTANCE = (TextView) findViewById(R.id.c_distanceinmeters);
        coveredArea = (TextView) findViewById(R.id.c_coveredarea);
        UncoveredArea = (TextView) findViewById(R.id.c_uncoveredarea);
        roomsInBasement = (TextView) findViewById(R.id.c_roomsbasement);
        roomsInGroundFloor = (TextView) findViewById(R.id.c_roomsground);
        roomsInFirstFloor = (TextView) findViewById(R.id.c_roomsfirstfloor);
        roomsInSecondFloor = (TextView) findViewById(R.id.c_roomssecond);
        roomsInThirdFloor = (TextView) findViewById(R.id.c_roomsthird);

        String covered = pref1.getString("schoolcoveredaread", "");
        String uncovered = pref1.getString("schooluncoveredaread", "");
        String brooms = pref1.getString("basementrooms", "");
        String grooms = pref1.getString("groundrooms", "");
        String frooms = pref1.getString("firstrooms", "");
        String srooms = pref1.getString("secondrooms", "");
        String trooms = pref1.getString("thirdrooms", "");
        String SPINNER = pref1.getString("unvoeredaaread", "");
        String distanceInMeters = pref1.getString("distanceInMeters", "");

        coveredArea.setText(covered);
        UncoveredArea.setText(uncovered);
        roomsInBasement.setText(brooms);
        roomsInGroundFloor.setText(grooms);
        roomsInFirstFloor.setText(frooms);
        roomsInSecondFloor.setText(srooms);
        roomsInThirdFloor.setText(trooms);
        DISTANCE.setText(distanceInMeters);
        uncoveredAreaSpinner.setText(SPINNER);

        noOfpakkaClassrooms = (TextView) findViewById(R.id.c_pakkarooms);
        noOfpakkaOtherrooms = (TextView) findViewById(R.id.c_pakkaotherrooms);
        noOfkachaClassrooms = (TextView) findViewById(R.id.c_kacharooms);
        noOfkachaOtherrooms = (TextView) findViewById(R.id.c_kachaotherrooms);

        String pakkaclass = pref1.getString("pakkaclass", "");
        String pakkaother = pref1.getString("pakkaother", "");
        String kachaclass = pref1.getString("kachaclass", "");
        String kachaothers = pref1.getString("kachaothers", "");

        noOfpakkaClassrooms.setText(pakkaclass);
        noOfpakkaOtherrooms.setText(pakkaother);
        noOfkachaClassrooms.setText(kachaclass);
        noOfkachaOtherrooms.setText(kachaothers);

        noOfClassrooms = (TextView) findViewById(R.id.c_noofclassroomsrequiringreconstruction);
        noOfOtherRooms = (TextView) findViewById(R.id.c_noofotherroomsrequiringreconstruction);
        noOfClassroomsMajor = (TextView) findViewById(R.id.c_noofclassroomsrequiringmajorrepair);
        noOfOtherroomsMajor = (TextView) findViewById(R.id.c_noofclassroomsrequiringmajorrepair);
        building_condition_spinner = (TextView) findViewById(R.id.c_wholebuilding);

        String noOfClassroomss = pref1.getString("noOfClassrooms", "");
        String noOfOtherRoomss = pref1.getString("noOfOtherRooms", "");
        String noOfClassroomsMajorr = pref1.getString("noOfClassroomsMajor", "");
        String noOfOtherroomsMajorr = pref1.getString("noOfOtherroomsMajor", "");
        String WHOLENEEDS = pref1.getString("WHOLENEEDS", "");

        noOfClassrooms.setText(noOfClassroomss);
        noOfOtherRooms.setText(noOfOtherRoomss);
        noOfClassroomsMajor.setText(noOfClassroomsMajorr);
        noOfOtherroomsMajor.setText(noOfOtherroomsMajorr);
        building_condition_spinner.setText(WHOLENEEDS);

        LabFunctional = (TextView) findViewById(R.id.c_labisfunctional);
        LabEstablished = (TextView) findViewById(R.id.c_labestablishedby);
        InternetAvailable = (TextView) findViewById(R.id.c_internetavailable);
        noOfComputers = (TextView) findViewById(R.id.c_totalnoofcomputers);
        noOfFunctionalComputers = (TextView) findViewById(R.id.c_nooffunctionalcomputers);
        ItLabExists = (TextView) findViewById(R.id.c_itlabexists);

        String SLevelll = pref1.getString("SLevelll", "");
        String InternetFun = pref1.getString("InternetFun", "");
        String LabEst = pref1.getString("LabEst", "");
        String LabFun = pref1.getString("LabFun", "");
        String noOfComputerss = pref1.getString("noOfComputers", "");
        String noOfFunctionalComputerss = pref1.getString("noOfFunctionalComputers", "");
        noOfComputers.setText(noOfComputerss);
        noOfFunctionalComputers.setText(noOfFunctionalComputerss);
        InternetAvailable.setText(InternetFun);
        ItLabExists.setText(SLevelll);
        LabFunctional.setText(LabFun);
        LabEstablished.setText(LabEst);

        anyothername2 = (TextView) findViewById(R.id.c_anyother2name);
        anyotheruseable2 = (TextView) findViewById(R.id.c_anyother2_useable);
        anyotherunuseable2 = (TextView) findViewById(R.id.c_anyother2_unuseable);
        anyothernumberOfnew = (TextView) findViewById(R.id.c_anyother2_newrequired);

        twoUseable = (TextView) findViewById(R.id.c_twoseats_useable);
        twoUnUseable = (TextView) findViewById(R.id.c_twoseats_unuseable);
        twoNewRequired = (TextView) findViewById(R.id.c_twoseats_newrequired);
        threeUseable = (TextView) findViewById(R.id.c_threeseats_useable);
        threeUnUseable = (TextView) findViewById(R.id.c_threeseats_unuseable);
        threeNewRequired = (TextView) findViewById(R.id.c_threeseats_newrequired);
        benchesUseable = (TextView) findViewById(R.id.c_benches_useable);
        benchesUnUseable = (TextView) findViewById(R.id.c_benches_unuseable);
        benchesNewRequired = (TextView) findViewById(R.id.c_benches_newrequired);
        chairsUseable = (TextView) findViewById(R.id.c_chairs_useable);
        chairsUnUseable = (TextView) findViewById(R.id.c_chairs_unuseable);
        chairsNewRequired = (TextView) findViewById(R.id.c_chairs_newrequired);
        tabletchairsUseable = (TextView) findViewById(R.id.c_tabletchairs_useable);
        tabletchairsUnUseable = (TextView) findViewById(R.id.c_tabletchairs_unuseable);
        tabletchairsNewRequired = (TextView) findViewById(R.id.c_tabletchairs_newrequired);
        tatsUseable = (TextView) findViewById(R.id.c_jute_useable);
        tatsUnUseable = (TextView) findViewById(R.id.c_jute_unuseable);
        tatsNewRequired = (TextView) findViewById(R.id.c_jute_newrequired);
        fansUseable = (TextView) findViewById(R.id.c_fans_useable);
        fansUnUseable = (TextView) findViewById(R.id.c_fans_unuseable);
        fansNewRequired = (TextView) findViewById(R.id.c_fans_newrequired);
        anyothername = (TextView) findViewById(R.id.c_anyothername);
        otherUseable = (TextView) findViewById(R.id.c_anyother_useable);
        otherUnUseable = (TextView) findViewById(R.id.c_anyother_unuseable);
        otherNewRequired = (TextView) findViewById(R.id.c_anyother_newrequired);


        String hq = pref1.getString("anyothername2", "");
        String hw = pref1.getString("otherUseable2", "");
        String he = pref1.getString("otherUnUseable2", "");
        String hr = pref1.getString("otherNewRequired2", "");

        String twouse = pref1.getString("twoUseable", "");
        String twounuse = pref1.getString("twoUnUseable", "");
        String twonew = pref1.getString("twoNewRequired", "");
        String threeuse = pref1.getString("threeUseable", "");
        String threeunuse = pref1.getString("threeUnUseable", "");
        String threenew = pref1.getString("threeNewRequired", "");
        String benchuse = pref1.getString("benchesUseable", "");
        String benchunuse = pref1.getString("benchesUnUseable", "");
        String benchnew = pref1.getString("benchesNewRequired", "");
        String chairuse = pref1.getString("chairsUseable", "");
        String chairunuse = pref1.getString("chairsUnUseable", "");
        String chairnew = pref1.getString("chairsNewRequired", "");
        String tabletuse = pref1.getString("tabletchairsUseable", "");
        String tabletunuse = pref1.getString("tabletchairsUnUseable", "");
        String tabletnew = pref1.getString("tabletchairsNewRequired", "");
        String tatsuse = pref1.getString("tatsUseable", "");
        String tatsunuse = pref1.getString("tatsUnUseable", "");
        String tatsnew = pref1.getString("tatsNewRequired", "");
        String fansuse = pref1.getString("fansUseable", "");
        String fansunuse = pref1.getString("fansUnUseable", "");
        String fansnew = pref1.getString("fansNewRequired", "");
        String ayother = pref1.getString("anyothername", "");
        String otheruse = pref1.getString("otherUseable", "");
        String otherunuse = pref1.getString("otherUnUseable", "");
        String othernew = pref1.getString("otherNewRequired", "");

        twoUseable.setText(twouse);
        twoUnUseable.setText(twounuse);
        twoNewRequired.setText(twonew);
        threeUseable.setText(threeuse);
        threeUnUseable.setText(threeunuse);
        threeNewRequired.setText(threenew);
        benchesUseable.setText(benchuse);
        benchesUnUseable.setText(benchunuse);
        benchesNewRequired.setText(benchnew);
        chairsUseable.setText(chairuse);
        chairsUnUseable.setText(chairunuse);
        chairsNewRequired.setText(chairnew);
        tabletchairsUseable.setText(tabletuse);
        tabletchairsUnUseable.setText(tabletunuse);
        tabletchairsNewRequired.setText(tabletnew);
        tatsUseable.setText(tatsuse);
        tatsUnUseable.setText(tatsunuse);
        tatsNewRequired.setText(tatsnew);
        fansUseable.setText(fansuse);
        fansUnUseable.setText(fansunuse);
        fansNewRequired.setText(fansnew);
        anyothername.setText(ayother);
        otherUseable.setText(otheruse);
        otherUnUseable.setText(otherunuse);
        otherNewRequired.setText(othernew);
        anyothername2.setText(hq);
        anyotheruseable2.setText(hw);
        anyotherunuseable2.setText(he);
        anyothernumberOfnew.setText(hr);


        HToffice = (TextView) findViewById(R.id.c_htoffice);
        sciencelab = (TextView) findViewById(R.id.c_sciencelab);
        Staffroom = (TextView) findViewById(R.id.c_staffroom);
        library = (TextView) findViewById(R.id.c_library);
        clerkroom = (TextView) findViewById(R.id.c_clerkroom);
        examinationhall = (TextView) findViewById(R.id.c_examinationhall);
        playground = (TextView) findViewById(R.id.c_playground);
        onescreen = (TextView) findViewById(R.id.c_onescreen);
        alternaivesource = (TextView) findViewById(R.id.c_alternativesource);

        String htStr = pref1.getString("htStr", "");
        String scienceStr = pref1.getString("scienceStr", "");
        String staffStr = pref1.getString("staffStr", "");
        String libStr = pref1.getString("libStr", "");
        String clerkStr = pref1.getString("clerkStr", "");
        String examStr = pref1.getString("examStr", "");
        String playStr = pref1.getString("playStr", "");
        String oneStr = pref1.getString("oneStr", "");
        String alternateStr = pref1.getString("alternateStr", "");

        HToffice.setText(htStr);
        sciencelab.setText(scienceStr);
        Staffroom.setText(staffStr);
        library.setText(libStr);
        clerkroom.setText(clerkStr);
        examinationhall.setText(examStr);
        playground.setText(playStr);
        onescreen.setText(oneStr);
        alternaivesource.setText(alternateStr);

        teacherguide = (TextView) findViewById(R.id.c_teacherguide);
        String Teachrguide = pref1.getString("Teachrguide", "");
        teacherguide.setText(Teachrguide);

        schCleanN = (TextView) findViewById(R.id.c_schoolcleanliness);
        stdCleanN = (TextView) findViewById(R.id.c_stdcleanliness);

        String schCleann = pref1.getString("schClean", "");
        String stdCleann = pref1.getString("stdClean", "");


        HostelExists = (TextView) findViewById(R.id.c_hostel);
        Office = (TextView) findViewById(R.id.c_office);
        Store = (TextView) findViewById(R.id.c_store);
        Lab = (TextView) findViewById(R.id.c_homeeconomicslab);
        Entance = (TextView) findViewById(R.id.c_schoolentrance);
        Building = (TextView) findViewById(R.id.c_schoolbuilding);
        Toilets = (TextView) findViewById(R.id.c_toilets);

        String Facilities_SLevel = pref1.getString("Facilities_SLevel", "");
        String Facilities_officeStr = pref1.getString("Facilities_officeStr", "");
        String Facilities_storeStr = pref1.getString("Facilities_storeStr", "");
        String Facilities_labStr = pref1.getString("Facilities_labStr", "");
        String Facilities_EntraceStr = pref1.getString("Facilities_EntraceStr", "");
        String Facilities_BuildingStr = pref1.getString("Facilities_BuildingStr", "");
        String Facilities_toiletsStr = pref1.getString("Facilities_toiletsStr", "");

        HostelExists.setText(Facilities_SLevel);
        Office.setText(Facilities_officeStr);
        Store.setText(Facilities_storeStr);
        Lab.setText(Facilities_labStr);
        Entance.setText(Facilities_EntraceStr);
        Building.setText(Facilities_BuildingStr);
        Toilets.setText(Facilities_toiletsStr);
        schCleanN.setText(schCleann);
        stdCleanN.setText(stdCleann);


        HEIGHT = (TextView) findViewById(R.id.c_boundarywallheight);
        GUARDS = (TextView) findViewById(R.id.c_noofsecurityguards);
        CHOWKIDAR = (TextView) findViewById(R.id.c_nochowkidar);
        WEAPONS = (TextView) findViewById(R.id.c_noweapons);
        METAL = (TextView) findViewById(R.id.c_nometaldetectors);
        barbed = (TextView) findViewById(R.id.c_barbedwire);
        glass = (TextView) findViewById(R.id.c_glassspiked);
        entrance = (TextView) findViewById(R.id.c_entranceblocks);
        sos = (TextView) findViewById(R.id.c_sos);

        String SM_HEIGHT = pref1.getString("SM_HEIGHT", "");
        String SM_GUARDS = pref1.getString("SM_GUARDS", "");
        String SM_CHOWKIDAR = pref1.getString("SM_CHOWKIDAR", "");
        String SM_WEAPONS = pref1.getString("SM_WEAPONS", "");
        String SM_METAL = pref1.getString("SM_METAL", "");
        String SM_BARBED = pref1.getString("SM_BARBED", "");
        String SM_GLASS = pref1.getString("SM_GLASS", "");
        String SM_ENTRANCE = pref1.getString("SM_ENTRANCE", "");
        String SM_SOS = pref1.getString("SM_SOS", "");

        HEIGHT.setText(SM_HEIGHT);
        GUARDS.setText(SM_GUARDS);
        CHOWKIDAR.setText(SM_CHOWKIDAR);
        WEAPONS.setText(SM_WEAPONS);
        METAL.setText(SM_METAL);
        barbed.setText(SM_BARBED);
        glass.setText(SM_GLASS);
        entrance.setText(SM_ENTRANCE);
        sos.setText(SM_SOS);

        BLACK = (TextView) findViewById(R.id.c_blackgreenboard);
        TABLES = (TextView) findViewById(R.id.c_officetables);
        CHAIRS = (TextView) findViewById(R.id.c_officechairs);
        ALMIRAH = (TextView) findViewById(R.id.c_almirah);

        String Fur_BLACK = pref1.getString("Fur_BLACK", "");
        String Fur_TABLES = pref1.getString("Fur_TABLES", "");
        String Fur_CHAIRS = pref1.getString("Fur_CHAIRS", "");
        String Fur_ALMIRAH = pref1.getString("Fur_ALMIRAH", "");

        BLACK.setText(Fur_BLACK);
        TABLES.setText(Fur_TABLES);
        CHAIRS.setText(Fur_CHAIRS);
        ALMIRAH.setText(Fur_ALMIRAH);


        teacherpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CompleteForm.this, M_TeacherPresenceList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });

        newteacherpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CompleteForm.this, M_TeacherPresenceListNew.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });

        trainingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CompleteForm.this, M_TrainingRecordList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });

        nonteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_NonTeacherPresenceList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        newNonteacherpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_NonTeacherPresenceListNew.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        proxyteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_ProxyTeacherList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        appointedby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_TeacherAppointedByList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        visitby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_SchoolVisitList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        vacantposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_VacantPositionsList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        SANCTIONEDPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_SanctionedPostList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        NONSACNTIONEDPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, M_SanctionedPostNonteachingList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteForm.this, HeadSignature.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CompleteForm.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(CompleteForm.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        schoolareaStr = cursorform.getString(cursorform.getColumnIndex("Bi_SchoolArea"));
                        constructionStr = cursorform.getString(cursorform.getColumnIndex("Bi_NatureOfConstruction"));
                        buildindconditionStr = cursorform.getString(cursorform.getColumnIndex("Bi_BuildingCondition"));
                        itlabStr = cursorform.getString(cursorform.getColumnIndex("Bi_ITLab"));
                        commodititesStr = cursorform.getString(cursorform.getColumnIndex("Bi_Commodities"));
                        ptcStr = cursorform.getString(cursorform.getColumnIndex("Bi_PTC"));
                        infrastructureStr = cursorform.getString(cursorform.getColumnIndex("Bi_Infrastructure"));
                        guideStr = cursorform.getString(cursorform.getColumnIndex("Bi_Guides"));
                        cleanlinessStr = cursorform.getString(cursorform.getColumnIndex("Bi_Cleanliness"));
                        stipendStr = cursorform.getString(cursorform.getColumnIndex("Bi_Stipend"));
                        disabledStr = cursorform.getString(cursorform.getColumnIndex("An_DisabledStudent"));
                        enrollmentageStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByAge"));
                        enrollmentgrpStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByGroup"));
                        ftbStr = cursorform.getString(cursorform.getColumnIndex("An_FTB"));
                        furnitureStr = cursorform.getString(cursorform.getColumnIndex("An_Furniture"));
                        indicatorStr = cursorform.getString(cursorform.getColumnIndex("An_Indicator"));
                        SanctionedStr = cursorform.getString(cursorform.getColumnIndex("An_SantionedPosts"));
                        securityStr = cursorform.getString(cursorform.getColumnIndex("An_SecurityMeasures"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();
                if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, Y_SpecialGirlsMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && hsec.equals("HighSecondarySelected")) {
                    startActivity(new Intent(CompleteForm.this, Y_EnrollmentElevenTwelve.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(CompleteForm.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, Y_EnrollmentAgeGirls.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, com.softorea.schoolsen.m_monthly.M_SanctionedPostNonteachingList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(CompleteForm.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(CompleteForm.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(CompleteForm.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(CompleteForm.this);
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
