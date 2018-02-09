package com.softorea.schoolsen.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.softorea.schoolsen.adapters.Monitor;
import com.softorea.schoolsen.lists.SchoolInformationDetails;
import com.softorea.schoolsen.m_monthly.Grant;
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
import com.softorea.schoolsen.models.DetailsStaff;
import com.softorea.schoolsen.models.DetailsTeacherwebservice;
import com.softorea.schoolsen.models.DetailsVacant;
import com.softorea.schoolsen.models.GcsTeacherDetails;
import com.softorea.schoolsen.models.NonTeacherNewDetails;
import com.softorea.schoolsen.models.ProvisionFreeTextBookModel;
import com.softorea.schoolsen.models.RosterDetails;
import com.softorea.schoolsen.models.ScreenConfig;
import com.softorea.schoolsen.models.SubjectClassModel;
import com.softorea.schoolsen.models.SubjectModel;
import com.softorea.schoolsen.models.TeacherNewDetails;
import com.softorea.schoolsen.models.TrainingRecord;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CensusDb";
    private static final String TableMonitor = "tbl_monitor";
    private static final String TABLE_PROXY_TEACHER = "t_proxyteacher";
    private static final String TABLE_TEACHER_APPOINTEDBY = "t_appointedby";
    private static final String TABLE_STAFF_ADDING = "t_staffadding";
    private static final String TABLE_SCHOOL_VISITYBY = "t_schoolvisitby";
    private static final String TABLE_VACANT = "t_vacant";
    private static final String TABLE_SanctionedTeaching = "t_sanctionedteaching";
    private static final String TABLE_SanctionedNonTeaching = "t_sanctionednonteaching";
    private static final String TableSubjectconfiguration = "Subject_configuration";
    private static final String TableGrant = "Grant";
    private static final String TableGrantwebservice = "Grantwebservice";
    private static final String TableFtp = "Table_Ftp";
    private static final String TableEnrollmentAttendanceGap = "Table_EAG";
    private static final String TableEnrollmentAgeBoys = "Table_EageBoys";
    private static final String TableEnrollmentAgeGirls = "Table_EageGirls";
    private static final String TableEnrollmentSpecialBoys = "Table_ESpecialBoys";
    private static final String TableEnrollmentSpecialGirls = "Table_ESpecialGirls";

    private static final String TABLE_Roster = "t_roster";
    private static final String TableTeacherInfo = "t_teacherInfo";
    private static final String TableTraining = "t_training";
    private static final String TableNonTeacherInfo = "t_nonteacherInfo";
    private static final String TableSchoolInformation = "t_schoolinformation";
    private static final String TableScreenConfig = "t_screenconfig";
    private static final String GCSTeachers = "t_gcsteachers";

    private static final String Teacherwebservice = "Teacherwebservice";
    private static final String NonTeacherwebservice = "NonTeacherwebservice";

    //Table for Proxy Teacher
    private static final String KEY_ID = "id";
    private static final String KEY_Name = "name";
    private static final String KEY_Cnicc = "cnic";
    private static final String KEY_ProxyForName = "proxyname";
    private static final String KEY_ProxyForCnic = "proxycnic";
    private static final String KEY_ProxyForNo = "proxyno";
    private static final String KEY_Designation = "designation";
    private static final String KEY_proxyTimeSince = "proxytimesince";

    ////ENROLLMENT ATTEENDANCE GAP////
    private static final String EAGclassName = "eag_classname";
    private static final String EAGstudentsEnrolled = "eag_stdenrolled";
    private static final String EAGstudentsPresentHead = "eag_stdpresenthead";
    private static final String EAGstudentsPresentRegister = "eag_stdpresentregister";
    private static final String EAGgirlsEnrolled = "eag_girlsenrolled";
    private static final String EAGboysEnrolled = "eag_boysenrolled";

    ////ENROLLMENT Age Boys////
    private static final String EAageboysclassName = "eageboys_classname";
    private static final String EAageboys3 = "eageboys3";
    private static final String EAageboys4 = "eageboys4";
    private static final String EAageboys5 = "eageboys5";
    private static final String EAageboys6 = "eageboys6";
    private static final String EAageboys7 = "eageboys7";
    private static final String EAageboys8 = "eageboys8";
    private static final String EAageboys9 = "eageboys9";
    private static final String EAageboys10 = "eageboys10";
    private static final String EAageboys11 = "eageboys11";
    private static final String EAageboys12 = "eageboys12";
    private static final String EAageboys13 = "eageboys13";
    private static final String EAageboys14 = "eageboys14";
    private static final String EAageboys15 = "eageboys15";
    private static final String EAageboys16 = "eageboys16";
    private static final String EAageboys17 = "eageboys17";
    private static final String EAageboys18 = "eageboys18";
    private static final String EAageboys19 = "eageboys19";
    private static final String EAageboys20 = "eageboys20";
    private static final String EAageboys21 = "eageboys21";
    private static final String EAageboysrepeaters = "eageboysrepeaters";
    private static final String EAageboysnonmuslims = "eageboysnonmuslims";


    ////ENROLLMENT Age Girls////
    private static final String EAagegirlsclassName = "eagegirls_classname";
    private static final String EAagegirls3 = "eagegirls3";
    private static final String EAagegirls4 = "eagegirls4";
    private static final String EAagegirls5 = "eagegirls5";
    private static final String EAagegirls6 = "eagegirls6";
    private static final String EAagegirls7 = "eagegirls7";
    private static final String EAagegirls8 = "eagegirls8";
    private static final String EAagegirls9 = "eagegirls9";
    private static final String EAagegirls10 = "eagegirls10";
    private static final String EAagegirls11 = "eagegirls11";
    private static final String EAagegirls12 = "eagegirls12";
    private static final String EAagegirls13 = "eagegirls13";
    private static final String EAagegirls14 = "eagegirls14";
    private static final String EAagegirls15 = "eagegirls15";
    private static final String EAagegirls16 = "eagegirls16";
    private static final String EAagegirls17 = "eagegirls17";
    private static final String EAagegirls18 = "eagegirls18";
    private static final String EAagegirls19 = "eagegirls19";
    private static final String EAagegirls20 = "eagegirls20";
    private static final String EAagegirls21 = "eagegirls21";
    private static final String EAagegirlsrepeaters = "eagegirlsrepeaters";
    private static final String EAagegirlsnonmuslims = "eagegirlsnonmuslims";


    /////Special boys

    private static final String SpecialBoysClassName = "specialboysclassName";
    private static final String full_visual = "full_visual";
    private static final String partial_visual = "partial_visual";
    private static final String full_hearing = "full_hearing";
    private static final String partial_hearing = "partial_hearing";
    private static final String full_speech = "full_speech";
    private static final String partial_speech = "partial_speech";
    private static final String hand_arm = "hand_arm";
    private static final String leg_foot = "leg_foot";
    private static final String mentalstr = "mentalstr";

    /////Special girls

    private static final String SpecialGirlsClassName = "specialgirlsclassName";
    private static final String Girlsfull_visual = "Girlsfull_visual";
    private static final String Girlspartial_visual = "Girlspartial_visual";
    private static final String Girlsfull_hearing = "Girlsfull_hearing";
    private static final String Girlspartial_hearing = "Girlspartial_hearing";
    private static final String Girlsfull_speech = "Girlsfull_speech";
    private static final String Girlspartial_speech = "Girlspartial_speech";
    private static final String Girlshand_arm = "Girlshand_arm";
    private static final String Girlsleg_foot = "Girlsleg_foot";
    private static final String Girlsmentalstr = "Girlsmentalstr";


    ////GRANT

    private static final String grantImeiCode = "imei_code";
    private static final String grantType = "type";
    private static final String grantYear = "year";
    private static final String grantAmount = "amount";
    private static final String grantId = "grant_id";
    private static final String grantdate = "g_date";
    private static final String granttypespiner = "grant_type";
    private static final String grantfinancial = "g_financial";
    private static final String grantworkstatus = "g_status";
    private static final String grantrecord = "g_record";
    private static final String grantremarks = "g_remarks";
    private static final String grantgrading = "g_grading";

    private static final String grantfundsreceived = "g_fundsreceived";
    private static final String grantammountcorrect = "g_ammountcorrect";
    private static final String grantamountenter = "g_amountenter";

    //Monitor Profile
    private static final String IMEI1 = "IMEI1";
    private static final String LoginName = "LoginName";
    private static final String IMEI2 = "IMEI2";
    private static final String Email = "Email";
    private static final String RecoveryCode = "RecoveryCode";
    private static final String Sex = "Sex";
    private static final String RoleId = "RoleId";
    private static final String Name = "Name";
    private static final String ContactNo = "ContactNo";
    private static final String Education = "Education";
    private static final String Picture = "Picture";
    private static final String CNIC = "CNIC";
    private static final String SessionId = "SessionId";
    private static final String Age = "Age";
    private static final String Password = "Password";
    private static final String Status = "Status";

    //Table for Teacher Appointed By
    private static final String KEY_AppointedName = "appointedname";
    private static final String KEY_AppointedCnic = "appointedcnic";
    private static final String KEY_AppointedBy = "appointedby";
    private static final String KEY_AppointedDate = "appointeddate";
    //private static final String KEY_School = "school";

    //Table for Staff Adding
    private static final String KEY_StaffName = "staffname";
    private static final String KEY_StaffCnic = "staffcnic";
    private static final String KEY_StaffNo = "staffno";
    private static final String KEY_StaffAttendance = "staffattendance";

    //Table for School Visit By
    private static final String KEY_visitDate = "visitdate";
    private static final String KEY_visitBy = "visitby";
    private static final String KEY_visitOther = "visitother";

    //Table for Vacant
    private static final String KEY_vacantdesignation = "vacantdesig";
    private static final String KEY_vacantseats = "vacantseats";

    /**
     * Subject_configuration cloums
     */

    private static final String ClassName = "ClassName";
    private static final String Subjects = "SubjectsName";


    private static final String webgrantImeiCode = "w_imei_code";
    private static final String webgrantType = "w_type";
    private static final String webgrantYear = "w_year";
    private static final String webgrantAmount = "w_amount";
    private static final String webgrantId = "w_grant_id";

    //Table for Sanctioned Teaching
    private static final String KEY_teaching_positioncode = "teaching_positioncode";
    private static final String KEY_teaching_designation = "teaching_designation";
    private static final String KEY_teaching_subject = "teaching_subject";
    private static final String KEY_teaching_bps = "teaching_bps";
    private static final String KEY_teaching_specifyother = "teaching_other";
    private static final String KEY_teaching_noofsanctioned = "teaching_noofsanctioned";

    private static final String KEY_formIdd = "FtpformId";
    private static final String KEY_ClassName = "FtpClassname";
    private static final String KEY_SubjectName = "FtpSubjectName";
    private static final String KEY_bookDemand = "FtpBooksDemand";
    private static final String KEY_bookRecieved = "FtpBooksReceived";
    private static final String KEY_stdWithFTB = "FtpStdWithFtb";
    private static final String KEY_surplusBooks = "FtpSurplusBooks";
    private static final String KEY_booksReturnToOffice = "FtpBooksReturn";


    //Table for Sanctioned Non Teaching
    private static final String KEY_Nonteaching_positioncode = "Nonteaching_positioncode";
    private static final String KEY_Nonteaching_designation = "Nonteaching_designation";
    private static final String KEY_Nonteaching_bps = "Nonteaching_bps";
    private static final String NonKEY_teaching_specifyother = "Nonteaching_other";
    private static final String KEY_Nonteaching_noofsanctioned = "Nonteaching_noofsanctioned";


    //Table for Roster
    private static final String KEY_Emis = "emis";
    private static final String KEY_Visit = "visit";
    private static final String KEY_School = "school";
    private static final String KEY_FormId = "RosterformId";
    private static final String KEY_RosterMonth = "Rostermonth";

    //Table for TeacherNew
    private static final String KEY_Teacher_ID = "teacherID";
    private static final String KEY_TeacherName = "teachername";
    private static final String KEY_TeacherFatherName = "teacherfathername";
    private static final String KEY_TeacherGender = "teacherGender";
    private static final String KEY_TeacherMaritalStatus = "teacherMstatus";
    private static final String KEY_TeacherBps = "teacherbps";
    private static final String KEY_TeacherNo = "teacherno";
    private static final String KEY_TeacherAccountNo = "teacheraccountno";
    private static final String KEY_TeacherCnic = "teachercnic";
    private static final String KEY_TeacherDob = "teacherdob";
    private static final String KEY_TeacherHqualificatinLevel = "teacherhighlevel";
    private static final String KEY_TeacherHqualificatinSubject = "teacherhighlsubject";
    private static final String KEY_TeacherDateofFirst = "teacherdateoffirst";
    private static final String KEY_TeacherDistrict = "teacherdistrict";
    private static final String KEY_TeacherHProfessional = "teacherhprofessional";
    private static final String KEY_TeacherDesignationAs1st = "teacherdesignationasfirst";
    private static final String KEY_TeacherDesignation = "teacherdesignation";
    private static final String KEY_TeacherUC = "teacheruc";
    private static final String KEY_TeacherAnyDisbality = "teacherdisablity";
    private static final String KEY_TeacherTypeDisbality = "teachertypedisablity";
    private static final String KEY_TeacherAttendance = "teacher_attendance";
    private static final String KEY_TeacherAttendanceTransferIn = "teacher_attendancetrasnferin";
    private static final String KEY_TeacherAttendanceDetails = "teacher_attendancedetails";
    private static final String KEY_TeacherAttendanceDatesince = "teacher_attendancedatesince";
    private static final String KEY_TeacherAttendanceTransferSchool = "teacher_attendancetrasnferschool";

    //Table for TrainingRecord
    private static final String KEY_TrainingName = "trainingname";
    private static final String KEY_TrainingCnic = "trainingcnic";
    private static final String KEY_TrainingBackAccountNo = "trainingbackaccountno";
    private static final String KEY_TrainingTitle = "trainingtitle";
    private static final String KEY_TrainingYear = "trainingyear";
    private static final String KEY_TrainingDuration = "trainingduration";
    private static final String KEY_TrainingConductedBy = "trainingconductedby";
    private static final String KEY_TrainingAttendedAs = "trainingattendedas";

    //Table for NonTeacherNew
    private static final String NonKEY_TeacherName = "nonteachername";
    private static final String NonKEY_TeacherFatherName = "nonteacherfathername";
    private static final String NonKEY_TeacherGender = "nonteacherGender";
    private static final String NonKEY_TeacherMaritalStatus = "nonteacherMstatus";
    private static final String NonKEY_TeacherBps = "nonteacherbps";
    private static final String NonKEY_TeacherNo = "nonteacherno";
    private static final String NonKEY_TeacherAccountNo = "nonteacheraccountno";
    private static final String NonKEY_TeacherCnic = "nonteachercnic";
    private static final String NonKEY_TeacherDob = "nonteacherdob";
    private static final String NonKEY_TeacherHqualificatinLevel = "nonteacherhighlevel";
    private static final String NonKEY_TeacherHqualificatinSubject = "nonteacherhighlsubject";
    private static final String NonKEY_TeacherDateofFirst = "nonteacherdateoffirst";
    private static final String NonKEY_TeacherDistrict = "nonteacherdistrict";
    private static final String NonKEY_TeacherHProfessional = "nonteacherhprofessional";
    private static final String NonKEY_TeacherDesignationAs1st = "nonteacherdesignationasfirst";
    private static final String NonKEY_TeacherDesignation = "nonteacherdesignation";
    private static final String NonKEY_TeacherUC = "nonteacheruc";
    private static final String NonKEY_TeacherAnyDisbality = "nonteacherdisablity";
    private static final String NonKEY_TeacherTypeDisbality = "nonteachertypedisablity";
    private static final String NonKEY_TeacherAttendance = "nonteacher_attendance";
    private static final String NonKEY_TeacherAttendanceTransferIn = "nonteacher_attendancetrasnferin";
    private static final String NonKEY_TeacherAttendanceDetails = "nonteacher_attendancedetails";
    private static final String NonKEY_TeacherAttendanceDatesince = "nonteacher_attendancedatesince";
    private static final String NonKEY_TeacherAttendanceTransferSchool = "nonteacher_attendancetrasnferschool";

    //Table for SchoolInformation
    //private static final String KEY_schoolemiscode = "schoolemiscode";
    private static final String KEY_schoolname = "schoolname";
    private static final String KEY_gender = "gender";
    private static final String KEY_schoollevel = "schoollevel";
    private static final String KEY_ddoCode = "ddocode";
    private static final String KEY_district = "district";
    private static final String KEY_tehsil = "tehsil";
    private static final String KEY_ucName = "ucname";
    private static final String KEY_location = "location";
    private static final String KEY_schoolzone = "schoolzone";
    private static final String KEY_naNo = "nano";
    private static final String KEY_pkNo = "pkno";
    private static final String KEY_circleofficename = "circleofficename";
    private static final String KEY_ADOName = "ado_name";
    private static final String KEY_ADONo = "ado_no";

    //Table for ScreenConfig
    private static final String KEY_An_DisabledStudent = "An_DisabledStudent";
    private static final String KEY_An_EnrollmentByAge= "An_EnrollmentByAge";
    private static final String KEY_An_EnrollmentByGroup = "An_EnrollmentByGroup";
    private static final String KEY_An_FTB = "An_FTB";
    private static final String KEY_An_Furniture = "An_Furniture";
    private static final String KEY_An_Indicator = "An_Indicator";
    private static final String KEY_An_SantionedPosts = "An_SantionedPosts";
    private static final String KEY_An_SecurityMeasures = "An_SecurityMeasures";
    private static final String KEY_Bi_BuildingCondition = "Bi_BuildingCondition";
    private static final String KEY_Bi_Cleanliness = "Bi_Cleanliness";
    private static final String KEY_Bi_Commodities = "Bi_Commodities";
    private static final String KEY_Bi_Guides = "Bi_Guides";
    private static final String KEY_Bi_ITLab = "Bi_ITLab";
    private static final String KEY_Bi_Infrastructure = "Bi_Infrastructure";
    private static final String KEY_Bi_NatureOfConstruction = "Bi_NatureOfConstruction";
    private static final String KEY_Bi_PTC= "Bi_PTC";
    private static final String KEY_Bi_SchoolArea= "Bi_SchoolArea";
    private static final String KEY_Bi_Stipend = "Bi_Stipend";
    private static final String KEY_SchoolInformation = "SchoolInformation";

    ////TEACHER WEB SERVICE

    private static final String KEY_TeacherNamewebservice = "TeacherNamewebservice";
    private static final String KEY_TeacherCnicwebservice = "TeacherCnicwebservice";
    private static final String KEY_TeacherNowebservice = "TeacherNowebservice";
    private static final String KEY_TeacherGenderwebservice = "TeacherGenderwebservice";
    private static final String KEY_TeacherAttendancewebservice = "TeacherAttendancewebservice";
    private static final String KEY_TeacherAttdetailswebservice = "TeacherAttdetailswebservice";
    private static final String KEY_TeacherAttendanceDatesinceWeb = "teacher_attendancedatesinceWeb";
    private static final String KEY_TeacherAttendanceTransferSchoolWeb = "teacher_attendancetrasnferschoolWeb";

    ////NON TEACHER WEB SERVICE

    private static final String KEY_NONTeacherNamewebservice = "NonTeacherNamewebservice";
    private static final String KEY_NONTeacherCnicwebservice = "NonTeacherCnicwebservice";
    private static final String KEY_NONTeacherNowebservice = "NonTeacherNowebservice";
    private static final String KEY_NONTeacherGenderwebservice = "NonTeacherGenderwebservice";
    private static final String KEY_NONTeacherAttendancewebservice = "NonTeacherAttendancewebservice";
    private static final String KEY_NONTeacherAttdetailswebservice = "NonTeacherAttdetailswebservice";
    private static final String KEY_NONTeacherAttendanceDatesinceWeb = "NONteacher_attendancedatesinceWeb";
    private static final String KEY_NONTeacherAttendanceTransferSchoolWeb = "NONteacher_attendancetrasnferschoolWeb";

    //Table for GCS Teachers
    //private static final String KEY_schoolemiscode = "schoolemiscode";
    private static final String KEY_gcsteachername = "gcsteachername";
    private static final String KEY_gcsteachercnic = "gcsteachercnic";
    private static final String KEY_gcsteacherno = "gcsteacherno";
    private static final String KEY_gcsteachergender = "gcsteachergender";
    private static final String KEY_gcsteacheratt = "gcsteacheratt";
    private static final String KEY_gcsteacherattdetails = "gcsteacherattdetails";
    private static final String KEY_gcsreplacement = "gcsreplacement";
    private static final String KEY_gcsreplacementname = "gcsreplacementname";
    private static final String KEY_gcsreplacementgender = "gcsreplacementgender";
    private final Context myContext;
    private SQLiteDatabase myDataBase;
    private SQLiteStatement insertStmt;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        this.myDataBase = getWritableDatabase();
    }

    /*public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        } else {

            // By calling this method and empty database will be created into the default system path
            // of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }*/
    /*private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }*/

    /**
     * @throws SQLException
     */
    /*public void openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }*/
    public SQLiteDatabase getDb() {
        return myDataBase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROXY_TEACHER = "CREATE TABLE " + TABLE_PROXY_TEACHER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_Name + " TEXT," + KEY_Cnicc + " TEXT,"
                + KEY_ProxyForName + " TEXT," + KEY_ProxyForCnic + " TEXT," + KEY_ProxyForNo + " TEXT,"
                + KEY_Designation + " TEXT,"
                + KEY_proxyTimeSince + " TEXT" + ");";
        db.execSQL(CREATE_PROXY_TEACHER);

        String CreateTableGrant = "CREATE TABLE "
                + TableGrant
                + "("
                + grantImeiCode
                + " TEXT,"
                + grantType
                + " TEXT,"
                + grantAmount
                + " TEXT,"
                + grantId
                + " INTEGER,"
                + grantYear
                + " TEXT,"
                + grantdate
                + " TEXT,"
                + granttypespiner
                + " TEXT,"
                + grantfinancial
                + " TEXT,"
                + grantworkstatus
                + " TEXT,"
                + grantrecord
                + " TEXT,"
                + grantremarks
                + " TEXT,"
                + grantgrading
                + " TEXT,"
                + grantfundsreceived
                + " TEXT,"
                + grantammountcorrect
                + " TEXT,"
                + grantamountenter
                + " TEXT"
                + ")";
        db.execSQL(CreateTableGrant);

        String CreateTableEAG = "CREATE TABLE "
                + TableEnrollmentAttendanceGap
                + "("
                + KEY_Emis
                + " TEXT,"
                + EAGclassName
                + " TEXT,"
                + EAGstudentsEnrolled
                + " TEXT,"
                + EAGstudentsPresentHead
                + " TEXT,"
                + EAGstudentsPresentRegister
                + " TEXT,"
                + EAGgirlsEnrolled
                + " TEXT,"
                + EAGboysEnrolled
                + " TEXT"
                + ")";
        db.execSQL(CreateTableEAG);


        String CreateTableWebGrant = "CREATE TABLE "
                + TableGrantwebservice
                + "("
                + webgrantImeiCode
                + " TEXT,"
                + webgrantType
                + " TEXT,"
                + webgrantYear
                + " TEXT,"
                + webgrantAmount
                + " TEXT,"
                + webgrantId
                + " INTEGER"
                + ")";
        db.execSQL(CreateTableWebGrant);

        String CreateTableMonitor = "CREATE TABLE "
                + TableMonitor
                + "("
                + Status
                + " TEXT,"
                + IMEI1
                + " TEXT,"
                + LoginName
                + " TEXT,"
                + IMEI2
                + " TEXT,"
                + Email
                + " TEXT,"
                + RecoveryCode
                + " TEXT,"
                + Sex
                + " TEXT,"
                + RoleId
                + " TEXT,"
                + Name
                + " TEXT,"
                + ContactNo
                + " TEXT,"
                + Education
                + " TEXT,"
                + Picture
                + " TEXT,"
                + CNIC
                + " TEXT,"
                + KEY_ID
                + " TEXT,"
                + SessionId
                + " TEXT," + Age + " TEXT," + Password + " TEXT" + ")";
        db.execSQL(CreateTableMonitor);

        String CREATE_TEACHER_APPOINTED_BY = "CREATE TABLE " + TABLE_TEACHER_APPOINTEDBY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_AppointedName + " TEXT," + KEY_AppointedCnic + " TEXT,"
                + KEY_AppointedBy + " TEXT," + KEY_AppointedDate + " TEXT" + ");";
        db.execSQL(CREATE_TEACHER_APPOINTED_BY);

        String CREATE_FTPTable = "CREATE TABLE " + TableFtp + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_formIdd + " TEXT," + KEY_ClassName + " TEXT,"
                + KEY_SubjectName + " TEXT," + KEY_bookDemand + " TEXT," + KEY_bookRecieved + " TEXT," + KEY_stdWithFTB + " TEXT,"
                + KEY_surplusBooks + " TEXT," + KEY_booksReturnToOffice + " TEXT" + ");";
        db.execSQL(CREATE_FTPTable);

        String CREATE_STAFFADDING = "CREATE TABLE " + TABLE_STAFF_ADDING + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_StaffName + " TEXT," + KEY_StaffNo + " TEXT," + KEY_StaffCnic + " TEXT," + KEY_StaffAttendance + " TEXT" + ");";
        db.execSQL(CREATE_STAFFADDING);

        String CREATE_SCHOOLVISITYBY = "CREATE TABLE " + TABLE_SCHOOL_VISITYBY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_visitDate + " TEXT,"
                + KEY_visitBy + " TEXT," + KEY_visitOther + " TEXT" + ");";
        db.execSQL(CREATE_SCHOOLVISITYBY);

        String CREATE_VACANT = "CREATE TABLE " + TABLE_VACANT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_vacantdesignation + " TEXT," + KEY_vacantseats + " TEXT" + ");";
        db.execSQL(CREATE_VACANT);

        String CREATE_SanctionedTeaching = "CREATE TABLE " + TABLE_SanctionedTeaching + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_teaching_positioncode + " TEXT," + KEY_teaching_designation + " TEXT,"
                + KEY_teaching_subject + " TEXT," + KEY_teaching_bps + " TEXT," + KEY_teaching_specifyother + " TEXT," + KEY_teaching_noofsanctioned + " TEXT" + ");";
        db.execSQL(CREATE_SanctionedTeaching);

        String CREATE_SanctionedNonTeaching = "CREATE TABLE " + TABLE_SanctionedNonTeaching + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_Nonteaching_positioncode + " TEXT," + KEY_Nonteaching_designation + " TEXT,"
                + KEY_Nonteaching_bps + " TEXT," + NonKEY_teaching_specifyother + " TEXT," + KEY_Nonteaching_noofsanctioned + " TEXT" + ");";
        db.execSQL(CREATE_SanctionedNonTeaching);

        String CREATE_ROSTER = "CREATE TABLE " + TABLE_Roster + "("
                + KEY_ID + " INTEGER PRIMARY KEY ," + KEY_Emis + " TEXT unique," + KEY_Visit + " TEXT,"
                + KEY_School + " TEXT,"
                + KEY_FormId + " TEXT,"
                + KEY_RosterMonth + " TEXT" + ");";
        db.execSQL(CREATE_ROSTER);

        String CREATE_TEACHERNEW = "CREATE TABLE " + TableTeacherInfo + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_TeacherName + " TEXT," + KEY_TeacherFatherName + " TEXT,"
                + KEY_TeacherGender + " TEXT," + KEY_TeacherMaritalStatus + " TEXT," + KEY_TeacherBps + " TEXT,"
                + KEY_TeacherNo + " TEXT," + KEY_TeacherAccountNo + " TEXT," + KEY_TeacherCnic + " TEXT,"
                + KEY_TeacherDob + " TEXT," + KEY_TeacherHqualificatinLevel + " TEXT," + KEY_TeacherHqualificatinSubject + " TEXT,"
                + KEY_TeacherDateofFirst + " TEXT," + KEY_TeacherDistrict + " TEXT," + KEY_TeacherHProfessional + " TEXT,"
                + KEY_TeacherDesignationAs1st + " TEXT," + KEY_TeacherDesignation + " TEXT," + KEY_TeacherUC + " TEXT,"
                + KEY_TeacherAnyDisbality + " TEXT," + KEY_TeacherTypeDisbality + " TEXT," + KEY_TeacherAttendance + " TEXT," + KEY_TeacherAttendanceTransferIn + " TEXT,"
                + KEY_TeacherAttendanceDetails + " TEXT," + KEY_TeacherAttendanceDatesince + " TEXT,"
                + KEY_TeacherAttendanceTransferSchool + " TEXT" + ");";
        db.execSQL(CREATE_TEACHERNEW);

        String CREATE_TRAINING_RECORD = "CREATE TABLE " + TableTraining + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + KEY_Teacher_ID + " INTEGER," +
                KEY_TrainingName + " TEXT," + KEY_TrainingCnic + " TEXT," +
                KEY_TrainingTitle + " TEXT," + KEY_TrainingYear + " TEXT," + KEY_TrainingDuration + " TEXT," + KEY_TrainingConductedBy + " TEXT,"
                + KEY_TrainingAttendedAs + " TEXT" + ");";
        db.execSQL(CREATE_TRAINING_RECORD);


        String CREATE_NONTEACHERNEW = "CREATE TABLE " + TableNonTeacherInfo + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Emis + " TEXT," + NonKEY_TeacherName + " TEXT," + NonKEY_TeacherFatherName + " TEXT,"
                + NonKEY_TeacherGender + " TEXT," + NonKEY_TeacherMaritalStatus + " TEXT," + NonKEY_TeacherBps + " TEXT,"
                + NonKEY_TeacherNo + " TEXT," + NonKEY_TeacherAccountNo + " TEXT," + NonKEY_TeacherCnic + " TEXT,"
                + NonKEY_TeacherDob + " TEXT," + NonKEY_TeacherHqualificatinLevel + " TEXT," + NonKEY_TeacherHqualificatinSubject + " TEXT,"
                + NonKEY_TeacherDateofFirst + " TEXT," + NonKEY_TeacherDistrict + " TEXT," + NonKEY_TeacherHProfessional + " TEXT,"
                + NonKEY_TeacherDesignationAs1st + " TEXT," + NonKEY_TeacherDesignation + " TEXT," + NonKEY_TeacherUC + " TEXT,"
                + NonKEY_TeacherAnyDisbality + " TEXT," + NonKEY_TeacherTypeDisbality + " TEXT," + NonKEY_TeacherAttendance + " TEXT,"
                + NonKEY_TeacherAttendanceTransferIn + " TEXT," + NonKEY_TeacherAttendanceDetails + " TEXT," + NonKEY_TeacherAttendanceDatesince + " TEXT,"
                + NonKEY_TeacherAttendanceTransferSchool + " TEXT" + ");";
        db.execSQL(CREATE_NONTEACHERNEW);

        String CREATE_SCHOOL_INFORMATION = "CREATE TABLE " + TableSchoolInformation + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Emis + " TEXT," + KEY_schoolname + " TEXT,"
                + KEY_gender + " TEXT," + KEY_schoollevel + " TEXT," + KEY_ddoCode + " TEXT,"
                + KEY_district + " TEXT," + KEY_tehsil + " TEXT," + KEY_ucName + " TEXT,"
                + KEY_location + " TEXT," + KEY_schoolzone + " TEXT," + KEY_naNo + " TEXT," + KEY_pkNo + " TEXT," + KEY_circleofficename
                + " TEXT," + KEY_ADOName + " TEXT," + KEY_ADONo + " TEXT" + ");";
        db.execSQL(CREATE_SCHOOL_INFORMATION);

        String CREATE_GCS_Teachers = "CREATE TABLE " + GCSTeachers + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Emis + " TEXT," + KEY_gcsteachername + " TEXT unique,"
                + KEY_gcsteachercnic + " TEXT," + KEY_gcsteacherno + " TEXT," + KEY_gcsteachergender + " TEXT,"
                + KEY_gcsteacheratt + " TEXT," + KEY_gcsteacherattdetails + " TEXT," + KEY_gcsreplacement + " TEXT,"
                + KEY_gcsreplacementname + " TEXT," + KEY_gcsreplacementgender + " TEXT" + ");";
        db.execSQL(CREATE_GCS_Teachers);


        String CREATE_TeachersWEBSERVICE = "CREATE TABLE " + Teacherwebservice + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Emis + " TEXT," + KEY_TeacherNamewebservice + " TEXT unique,"
                + KEY_TeacherCnicwebservice + " TEXT," + KEY_TeacherNowebservice + " TEXT," + KEY_TeacherGenderwebservice + " TEXT,"
                + KEY_TeacherAttendancewebservice + " TEXT,"
                + KEY_TeacherAttdetailswebservice + " TEXT,"
                + KEY_TeacherAttendanceDatesinceWeb + " TEXT,"
                + KEY_TeacherAttendanceTransferSchoolWeb + " TEXT" + ");";
        db.execSQL(CREATE_TeachersWEBSERVICE);

        String CREATE_NonTeachersWEBSERVICE = "CREATE TABLE " + NonTeacherwebservice + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Emis + " TEXT," + KEY_NONTeacherNamewebservice + " TEXT unique,"
                + KEY_NONTeacherCnicwebservice + " TEXT," + KEY_NONTeacherNowebservice + " TEXT," + KEY_NONTeacherGenderwebservice + " TEXT,"
                + KEY_NONTeacherAttendancewebservice + " TEXT,"
                + KEY_NONTeacherAttdetailswebservice + " TEXT,"
                + KEY_NONTeacherAttendanceDatesinceWeb + " TEXT,"
                + KEY_NONTeacherAttendanceTransferSchoolWeb + " TEXT" + ");";
        db.execSQL(CREATE_NonTeachersWEBSERVICE);

        String CreateTabelSubjectCofig = "CREATE TABLE " + TableSubjectconfiguration + "(" + ClassName + " TEXT," + Subjects + " TEXT" + ");";
        db.execSQL(CreateTabelSubjectCofig);


        String CREATE_EageBoys = "CREATE TABLE " + TableEnrollmentAgeBoys + "("
                + KEY_Emis + " TEXT," + EAageboysclassName + " TEXT," + EAageboys3 + " TEXT,"
                + EAageboys4 + " TEXT," + EAageboys5 + " TEXT," + EAageboys6 + " TEXT,"
                + EAageboys7 + " TEXT," + EAageboys8 + " TEXT," + EAageboys9 + " TEXT,"
                + EAageboys10 + " TEXT," + EAageboys11 + " TEXT," + EAageboys12 + " TEXT,"
                + EAageboys13 + " TEXT," + EAageboys14 + " TEXT," + EAageboys15 + " TEXT,"
                + EAageboys16 + " TEXT," + EAageboys17 + " TEXT," + EAageboys18 + " TEXT,"
                + EAageboys19 + " TEXT," + EAageboys20 + " TEXT," + EAageboys21 + " TEXT,"
                + EAageboysrepeaters + " TEXT,"
                + EAageboysnonmuslims + " TEXT" + ");";
        db.execSQL(CREATE_EageBoys);


        String CREATE_Eagegirls = "CREATE TABLE " + TableEnrollmentAgeGirls + "("
                + KEY_Emis + " TEXT," + EAagegirlsclassName + " TEXT," + EAagegirls3 + " TEXT,"
                + EAagegirls4 + " TEXT," + EAagegirls5 + " TEXT," + EAagegirls6 + " TEXT,"
                + EAagegirls7 + " TEXT," + EAagegirls8 + " TEXT," + EAagegirls9 + " TEXT,"
                + EAagegirls10 + " TEXT," + EAagegirls11 + " TEXT," + EAagegirls12 + " TEXT,"
                + EAagegirls13 + " TEXT," + EAagegirls14 + " TEXT," + EAagegirls15 + " TEXT,"
                + EAagegirls16 + " TEXT," + EAagegirls17 + " TEXT," + EAagegirls18 + " TEXT,"
                + EAagegirls19 + " TEXT," + EAagegirls20 + " TEXT," + EAagegirls21 + " TEXT,"
                + EAagegirlsrepeaters + " TEXT,"
                + EAagegirlsnonmuslims + " TEXT" + ");";
        db.execSQL(CREATE_Eagegirls);

        String CREATE_SpecialBoys = "CREATE TABLE " + TableEnrollmentSpecialBoys + "("
                + KEY_Emis + " TEXT," + SpecialBoysClassName + " TEXT," + full_visual + " TEXT,"
                + partial_visual + " TEXT," + full_hearing + " TEXT," + partial_hearing + " TEXT,"
                + full_speech + " TEXT," + partial_speech + " TEXT," + hand_arm + " TEXT,"
                + leg_foot + " TEXT," + mentalstr + " TEXT" + ");";
        db.execSQL(CREATE_SpecialBoys);

        String CREATE_SpecialGirls = "CREATE TABLE " + TableEnrollmentSpecialGirls + "("
                + KEY_Emis + " TEXT," + SpecialGirlsClassName + " TEXT," + Girlsfull_visual + " TEXT,"
                + Girlspartial_visual + " TEXT," + Girlsfull_hearing + " TEXT," + Girlspartial_hearing + " TEXT,"
                + Girlsfull_speech + " TEXT," + Girlspartial_speech + " TEXT," + Girlshand_arm + " TEXT,"
                + Girlsleg_foot + " TEXT," + Girlsmentalstr + " TEXT" + ");";
        db.execSQL(CREATE_SpecialGirls);


        String CREATE_ScreenConfig = "CREATE TABLE " + TableScreenConfig + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_An_DisabledStudent + " TEXT," + KEY_An_EnrollmentByAge
                + " TEXT," + KEY_An_EnrollmentByGroup + " TEXT,"
                + KEY_An_FTB + " TEXT," + KEY_An_Furniture + " TEXT," + KEY_An_Indicator + " TEXT,"
                + KEY_An_SantionedPosts + " TEXT," + KEY_An_SecurityMeasures+ " TEXT," + KEY_Bi_BuildingCondition + " TEXT,"
                + KEY_Bi_Cleanliness + " TEXT," + KEY_Bi_Commodities + " TEXT," + KEY_Bi_Guides + " TEXT,"
                + KEY_Bi_ITLab + " TEXT," + KEY_Bi_Infrastructure + " TEXT," + KEY_Bi_NatureOfConstruction + " TEXT,"
                + KEY_Bi_PTC + " TEXT," + KEY_Bi_SchoolArea + " TEXT," + KEY_Bi_Stipend + " TEXT,"
                + KEY_SchoolInformation + " TEXT" + ");";
        db.execSQL(CREATE_ScreenConfig);

        /*String CREATE_TEACHER_TABLE = "CREATE TABLE " + TABLE_TInfo + "("
                + KEY_ID + " INTEGER PRIMARY KEY ," + KEY_Name + " TEXT," + KEY_Cnic + " TEXT," + KEY_Pno + " TEXT,"
                + KEY_attendance + " TEXT" + ");";
        db.execSQL(CREATE_TEACHER_TABLE);

        String CREATE_SCHOOLINFO_TABLE = "CREATE TABLE " + TABLE_SchoolInfo + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SchoolName + " TEXT," + KEY_level + " TEXT," +
                KEY_Pkno + " TEXT," + KEY_Nano + " TEXT," + KEY_UCName + " TEXT,"
                + KEY_circleofficeno + " TEXT" + ");";
        db.execSQL(CREATE_SCHOOLINFO_TABLE);*/
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROXY_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER_APPOINTEDBY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF_ADDING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL_VISITYBY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SanctionedTeaching);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SanctionedNonTeaching);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Roster);
        db.execSQL("DROP TABLE IF EXISTS " + TableTeacherInfo);
        db.execSQL("DROP TABLE IF EXISTS " + TableTraining);
        db.execSQL("DROP TABLE IF EXISTS " + TableNonTeacherInfo);
        db.execSQL("DROP TABLE IF EXISTS " + TableSchoolInformation);
        db.execSQL("DROP TABLE IF EXISTS " + TableMonitor);
        db.execSQL("DROP TABLE IF EXISTS " + TableSubjectconfiguration);
        db.execSQL("DROP TABLE IF EXISTS " + TableFtp);
        db.execSQL("DROP TABLE IF EXISTS " + TableGrant);
        db.execSQL("DROP TABLE IF EXISTS " + TableGrantwebservice);
        db.execSQL("DROP TABLE IF EXISTS " + GCSTeachers);
        db.execSQL("DROP TABLE IF EXISTS " + TableEnrollmentAttendanceGap);
        db.execSQL("DROP TABLE IF EXISTS " + TableEnrollmentAgeBoys);
        db.execSQL("DROP TABLE IF EXISTS " + TableEnrollmentAgeGirls);
        db.execSQL("DROP TABLE IF EXISTS " + TableEnrollmentSpecialBoys);
        db.execSQL("DROP TABLE IF EXISTS " + TableEnrollmentSpecialGirls);
        db.execSQL("DROP TABLE IF EXISTS " + TableScreenConfig);
        db.execSQL("DROP TABLE IF EXISTS " + Teacherwebservice);
        db.execSQL("DROP TABLE IF EXISTS " + NonTeacherwebservice);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_TInfo);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SchoolInfo);
        onCreate(db);
    }

    public long createMonitor(Monitor monitor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(KEY_ID, monitor.getId());
        Values.put(Status, monitor.getStatus());
        Values.put(IMEI1, monitor.getiMEI1());
        Values.put(LoginName, monitor.getLoginName());
        Values.put(IMEI2, monitor.getiMEI2());
        Values.put(Email, monitor.getEmail());
        Values.put(RecoveryCode, monitor.getRecoveryCode());
        Values.put(Sex, monitor.getSex());
        Values.put(RoleId, monitor.getRoleId());
        Values.put(Name, monitor.getName());
        Values.put(ContactNo, monitor.getContactNo());
        Values.put(Education, monitor.getEducation());
        Values.put(CNIC, monitor.getCnic());
        Values.put(SessionId, monitor.getSession());
        Values.put(Age, monitor.getAge());
        Values.put(Password, monitor.getPassword());

        return db.insert(TableMonitor, null, Values);

    }

    public Monitor getMonitor(String Username) {

        Monitor monitor = new Monitor();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TableMonitor + " WHERE "
                + LoginName + " = " + "'" + Username + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    String loginName = c.getString(c.getColumnIndex(LoginName));
                    String password = c.getString(c.getColumnIndex(Password));
                    String id = c.getString(c.getColumnIndex(KEY_ID));
                    String name = c.getString(c.getColumnIndex(Name));
                    String roleId = c.getString(c.getColumnIndex(RoleId));
                    String age = c.getString(c.getColumnIndex(Age));
                    String sex = c.getString(c.getColumnIndex(Sex));
                    String education = c.getString(c.getColumnIndex(Education));
                    String imei1 = c.getString(c.getColumnIndex(IMEI1));
                    String imei2 = c.getString(c.getColumnIndex(IMEI2));
                    String status = c.getString(c.getColumnIndex(Status));
                    String cnic = c.getString(c.getColumnIndex(CNIC));
                    String email = c.getString(c.getColumnIndex(Email));
                    String recoveryCode = c.getString(c
                            .getColumnIndex(RecoveryCode));
                    String contactNo = c.getString(c.getColumnIndex(ContactNo));
                    String sessionId = c.getString(c.getColumnIndex(SessionId));

                    monitor.setLoginName(loginName);
                    monitor.setPassword(password);
                    monitor.setId(id);
                    monitor.setName(name);
                    monitor.setRoleId(roleId);
                    monitor.setAge(age);
                    monitor.setSex(sex);
                    monitor.setEducation(education);
                    monitor.setiMEI1(imei1);
                    monitor.setiMEI2(imei2);
                    monitor.setStatus(status);
                    monitor.setCnic(cnic);
                    monitor.setEmail(email);
                    monitor.setRecoveryCode(recoveryCode);
                    monitor.setContactNo(contactNo);
                    monitor.setSession(sessionId);

                } while (c.moveToNext());
            }

            return monitor;

        }

        return monitor;

    }

    public ProvisionFreeTextBookModel getftb(String formId, String classname, String subjectname) {
        ProvisionFreeTextBookModel pbooks = new ProvisionFreeTextBookModel();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableFtp + " WHERE " + KEY_formIdd + " = " + formId + " AND " + KEY_ClassName
                + " = " + "'" + classname + "'" + " AND " + KEY_SubjectName + " = " + "'" + subjectname + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    String booksdemand = c.getString(c.getColumnIndex(KEY_bookDemand));
                    String booksrecceived = c.getString(c.getColumnIndex(KEY_bookRecieved));
                    String stdout = c.getString(c.getColumnIndex(KEY_stdWithFTB));
                    String surplusbooks = c.getString(c.getColumnIndex(KEY_surplusBooks));
                    String booksreturn = c.getString(c.getColumnIndex(KEY_booksReturnToOffice));

                    pbooks.setBookDemand(booksdemand);
                    pbooks.setBookRecieved(booksrecceived);
                    pbooks.setStdWithFTB(stdout);
                    pbooks.setSurplusBooks(surplusbooks);
                    pbooks.setBooksReturnToOffice(booksreturn);

                } while (c.moveToNext());
            }

            return pbooks;

        }

        return pbooks;


    }

    public ArrayList<ProvisionFreeTextBookModel> getftbxml(String formId) {
        ArrayList<ProvisionFreeTextBookModel> teacherList = new ArrayList<ProvisionFreeTextBookModel>();
        ProvisionFreeTextBookModel tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableFtp + " WHERE " + KEY_formIdd + " = " + "'" + formId + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new ProvisionFreeTextBookModel();
                    //int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String classname = c.getString(c.getColumnIndex(KEY_ClassName));
                    String sujectname = c.getString(c.getColumnIndex(KEY_SubjectName));
                    String booksdemand = c.getString(c.getColumnIndex(KEY_bookDemand));
                    String booksrecceived = c.getString(c.getColumnIndex(KEY_bookRecieved));
                    String stdout = c.getString(c.getColumnIndex(KEY_stdWithFTB));
                    String surplusbooks = c.getString(c.getColumnIndex(KEY_surplusBooks));
                    String booksreturn = c.getString(c.getColumnIndex(KEY_booksReturnToOffice));

                    tacherInfo.setClassNo(classname);
                    tacherInfo.setSubjectName(sujectname);
                    tacherInfo.setBookDemand(booksdemand);
                    tacherInfo.setBookRecieved(booksrecceived);
                    tacherInfo.setStdWithFTB(stdout);
                    tacherInfo.setSurplusBooks(surplusbooks);
                    tacherInfo.setBooksReturnToOffice(booksreturn);
                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public void savefTP(ProvisionFreeTextBookModel person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_formIdd, person.getFormiD());
        values.put(KEY_ClassName, person.getClassNo());
        values.put(KEY_SubjectName, person.getSubjectName());
        values.put(KEY_bookDemand, person.getBookDemand());
        values.put(KEY_bookRecieved, person.getBookRecieved());
        values.put(KEY_stdWithFTB, person.getStdWithFTB());
        values.put(KEY_surplusBooks, person.getSurplusBooks());
        values.put(KEY_booksReturnToOffice, person.getBooksReturnToOffice());
        String query = "SELECT * FROM " + TableFtp + " WHERE " + KEY_formIdd + " = " + person.getFormiD() + " AND " + KEY_ClassName
                + " = " + "'" + person.getClassNo() + "'" + " AND " + KEY_SubjectName + " = " + "'" + person.getSubjectName() + "'";
        //values.put(KEY_School, person.getSchool());
        //db.execSQL(query);
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableFtp, values, KEY_formIdd + "=" + person.getFormiD() + " AND " + KEY_ClassName + "=" + "'" + person.getClassNo() + "'" + " AND "
                    + KEY_SubjectName + "=" + "'" + person.getSubjectName() + "'", null);
        } else {
            db.insert(TableFtp, null, values);
        }
        db.close();
    }

    public void saveEAG(DetailsEnrollmentAttendaanceGap person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(EAGclassName, person.getClassName());
        values.put(EAGstudentsEnrolled, person.getStudentsEnrolled());
        values.put(EAGstudentsPresentHead, person.getStudentsPresentHead());
        values.put(EAGstudentsPresentRegister, person.getStudentsPresentRegister());
        values.put(EAGgirlsEnrolled, person.getGirlsEnrolled());
        values.put(EAGboysEnrolled, person.getBoysEnrolled());
        String query = "SELECT * FROM " + TableEnrollmentAttendanceGap + " WHERE " + KEY_Emis + " = " + person.getEmisCode()
                + " AND " + EAGclassName + " = " + "'" + person.getClassName() + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableEnrollmentAttendanceGap, values, KEY_Emis + "=" + person.getEmisCode()
                    + " AND " + EAGclassName + "=" + "'" + person.getClassName() + "'", null);
        } else {
            db.insert(TableEnrollmentAttendanceGap, null, values);
        }
        db.close();
    }


    public void saveEAgeboys(DetailsEnrollmentAgeBoys person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(EAageboysclassName, person.getClassName());
        values.put(EAageboys3, person.getAge3());
        values.put(EAageboys4, person.getAge4());
        values.put(EAageboys5, person.getAge5());
        values.put(EAageboys6, person.getAge6());
        values.put(EAageboys7, person.getAge7());
        values.put(EAageboys8, person.getAge8());
        values.put(EAageboys9, person.getAge9());
        values.put(EAageboys10, person.getAge10());
        values.put(EAageboys11, person.getAge11());
        values.put(EAageboys12, person.getAge12());
        values.put(EAageboys13, person.getAge13());
        values.put(EAageboys14, person.getAge14());
        values.put(EAageboys15, person.getAge15());
        values.put(EAageboys16, person.getAge16());
        values.put(EAageboys17, person.getAge17());
        values.put(EAageboys18, person.getAge18());
        values.put(EAageboys19, person.getAge19());
        values.put(EAageboys20, person.getAge20());
        values.put(EAageboys21, person.getAge21());
        values.put(EAageboysrepeaters, person.getRepeaters());
        values.put(EAageboysnonmuslims, person.getNonmuslims());
        String query = "SELECT * FROM " + TableEnrollmentAgeBoys + " WHERE " + KEY_Emis + " = " + person.getEmisCode()
                + " AND " + EAageboysclassName + " = " + "'" + person.getClassName() + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableEnrollmentAgeBoys, values, KEY_Emis + "=" + person.getEmisCode()
                    + " AND " + EAageboysclassName + "=" + "'" + person.getClassName() + "'", null);
        } else {
            db.insert(TableEnrollmentAgeBoys, null, values);
        }
        db.close();
    }


    public void saveEAgegirls(DetailsEnrollmentAgeGirls person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(EAagegirlsclassName, person.getClassName());
        values.put(EAagegirls3, person.getAge3());
        values.put(EAagegirls4, person.getAge4());
        values.put(EAagegirls5, person.getAge5());
        values.put(EAagegirls6, person.getAge6());
        values.put(EAagegirls7, person.getAge7());
        values.put(EAagegirls8, person.getAge8());
        values.put(EAagegirls9, person.getAge9());
        values.put(EAagegirls10, person.getAge10());
        values.put(EAagegirls11, person.getAge11());
        values.put(EAagegirls12, person.getAge12());
        values.put(EAagegirls13, person.getAge13());
        values.put(EAagegirls14, person.getAge14());
        values.put(EAagegirls15, person.getAge15());
        values.put(EAagegirls16, person.getAge16());
        values.put(EAagegirls17, person.getAge17());
        values.put(EAagegirls18, person.getAge18());
        values.put(EAagegirls19, person.getAge19());
        values.put(EAagegirls20, person.getAge20());
        values.put(EAagegirls21, person.getAge21());
        values.put(EAagegirlsrepeaters, person.getRepeaters());
        values.put(EAagegirlsnonmuslims, person.getNonmuslims());
        String query = "SELECT * FROM " + TableEnrollmentAgeGirls + " WHERE " + KEY_Emis + " = " + person.getEmisCode()
                + " AND " + EAagegirlsclassName + " = " + "'" + person.getClassName() + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableEnrollmentAgeGirls, values, KEY_Emis + "=" + person.getEmisCode()
                    + " AND " + EAagegirlsclassName + "=" + "'" + person.getClassName() + "'", null);
        } else {
            db.insert(TableEnrollmentAgeGirls, null, values);
        }
        db.close();
    }


    public void saveSpecialBoys(DetailsEnrollmentSpecialBoys person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(SpecialBoysClassName, person.getClassName());
        values.put(full_visual, person.getFullVisual());
        values.put(partial_visual, person.getPartialVisual());
        values.put(full_hearing, person.getFullHearing());
        values.put(partial_hearing, person.getPartialHearing());
        values.put(full_speech, person.getFullSpeech());
        values.put(partial_speech, person.getPartialSpeech());
        values.put(hand_arm, person.getHandarm());
        values.put(leg_foot, person.getLegfoot());
        values.put(mentalstr, person.getMental());
        String query = "SELECT * FROM " + TableEnrollmentSpecialBoys+ " WHERE " + KEY_Emis + " = " + person.getEmisCode()
                + " AND " + SpecialBoysClassName + " = " + "'" + person.getClassName() + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableEnrollmentSpecialBoys, values, KEY_Emis + "=" + person.getEmisCode()
                    + " AND " + SpecialBoysClassName + "=" + "'" + person.getClassName() + "'", null);
        } else {
            db.insert(TableEnrollmentSpecialBoys, null, values);
        }
        db.close();
    }

    public void saveSpecialGirls(DetailsEnrollmentSpecialGirls person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(SpecialGirlsClassName, person.getClassName());
        values.put(Girlsfull_visual, person.getFullVisual());
        values.put(Girlspartial_visual, person.getPartialVisual());
        values.put(Girlsfull_hearing, person.getFullHearing());
        values.put(Girlspartial_hearing, person.getPartialHearing());
        values.put(Girlsfull_speech, person.getFullSpeech());
        values.put(Girlspartial_speech, person.getPartialSpeech());
        values.put(Girlshand_arm, person.getHandarm());
        values.put(Girlsleg_foot, person.getLegfoot());
        values.put(Girlsmentalstr, person.getMental());
        String query = "SELECT * FROM " + TableEnrollmentSpecialGirls+ " WHERE " + KEY_Emis + " = " + person.getEmisCode()
                + " AND " + SpecialGirlsClassName + " = " + "'" + person.getClassName() + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableEnrollmentSpecialGirls, values, KEY_Emis + "=" + person.getEmisCode()
                    + " AND " + SpecialGirlsClassName + "=" + "'" + person.getClassName() + "'", null);
        } else {
            db.insert(TableEnrollmentSpecialGirls, null, values);
        }
        db.close();
    }

    public void saveGrant(Grant person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(grantImeiCode, person.getEmisCode());
        values.put(grantType, person.getGrantType());
        values.put(grantYear, person.getYear());
        values.put(grantAmount, person.getAmount());
        values.put(grantId, person.getFaceCode());
        values.put(grantdate, person.getStartDate());
        values.put(granttypespiner, person.getType());
        values.put(grantfinancial, person.getFinancial());
        values.put(grantworkstatus, person.getWorkStatus());
        values.put(grantgrading, person.getWorkGrading());
        values.put(grantremarks, person.getRemarks());
        values.put(grantrecord, person.getRecordStatus());

        values.put(grantfundsreceived, person.getFundsReceived());
        values.put(grantammountcorrect, person.getAmmountCorrect());
        values.put(grantamountenter, person.getAmmountEnter());

        String query = "SELECT * FROM " + TableGrant + " WHERE " + grantImeiCode + "=" + person.getEmisCode()
                + " AND " + grantType + "=" + "'" + person.getGrantType() + "'" + " AND "
                + grantYear + "=" + "'" + person.getYear() + "'" + " AND "
                + grantAmount + "=" + "'" + person.getAmount() + "'" + " AND "
                + grantId + "=" + "'" + person.getFaceCode() + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            db.update(TableGrant, values, "imei_code='" + person.getEmisCode() + "' AND amount='" + person.getAmount()
                    + "' AND year='" + person.getYear() + "' AND grant_id='" + person.getFaceCode()
                    + "' AND type='" + person.getGrantType() + "'", null);
        } else {
            db.insert(TableGrant, null, values);
        }
        db.close();
    }

    public void save(DetailsProxyTeacher person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_Name, person.getName());
        values.put(KEY_Cnicc, person.getCnic());
        values.put(KEY_ProxyForName, person.getProxyName());
        values.put(KEY_ProxyForCnic, person.getProxycnic());
        values.put(KEY_ProxyForNo, person.getProxyno());
        values.put(KEY_Designation, person.getDesignation());
        values.put(KEY_proxyTimeSince, person.getProxytimeSince());
        //values.put(KEY_School, person.getSchool());
        db.insert(TABLE_PROXY_TEACHER, null, values);
        db.close();
    }

    public void createTableGrantt(Grant grant) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(webgrantImeiCode, grant.getEmisCode());
        Values.put(webgrantAmount, grant.getAmount());
        Values.put(webgrantType, grant.getGrantType());
        Values.put(webgrantYear, grant.getYear());
        Values.put(webgrantId, grant.getFaceCode());
        db.insert(TableGrantwebservice, null, Values);
        db.close();


    }

    /*public void createTableGrant(Grant grant) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(grantImeiCode, grant.getEmisCode());
        Values.put(grantAmount, grant.getAmount());
        Values.put(grantType, grant.getGrantType());
        Values.put(grantYear, grant.getYear());
        Values.put(grantId,grant.getFaceCode());
        Values.put(grantdate,grant.getStartDate());
        Values.put(grantfinancial,grant.getFinancial());
        Values.put(granttypespiner,grant.getType());
        Values.put(grantremarks,grant.getRemarks());
        Values.put(grantworkstatus,grant.getWorkStatus());
        Values.put(grantrecord,grant.getRecordStatus());
        Values.put(grantgrading,grant.getWorkGrading());


        String query = "SELECT * FROM " + TableGrant + " WHERE " + grantImeiCode + " = " + "'" + grant.getEmisCode() + "'";
        //values.put(KEY_School, person.getSchool());
        //db.execSQL(query);
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0)
        {
            db.update(TableGrant, Values, grantImeiCode + "="+ "'" + grant.getEmisCode() + "'", null);
        }
        else {
            db.insert(TableGrant, null, Values);
        }
        db.close();

    }*/

    /*public void checkGrantExists(String emis)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableGrant + " WHERE " + grantImeiCode + " = " + emis+ "'";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0)
        {

        }

    }*/

    public void saveAppointedBy(DetailsAppointedBy person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_AppointedName, person.getName());
        values.put(KEY_AppointedCnic, person.getCnic());
        values.put(KEY_AppointedBy, person.getAppointedby());
        values.put(KEY_AppointedDate, person.getAppointedDate());
        db.insert(TABLE_TEACHER_APPOINTEDBY, null, values);
        db.close();
    }

    public void saveStaff(DetailsStaff person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_StaffName, person.getStaffname());
        values.put(KEY_StaffNo, person.getStaffno());
        values.put(KEY_StaffCnic, person.getStaffcnic());
        values.put(KEY_StaffAttendance, person.getStaffdesignation());
        db.insert(TABLE_STAFF_ADDING, null, values);
        db.close();
    }

    public void saveSchoolVisitBy(DetailsSchoolVisit person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_visitDate, person.getVisitdate());
        values.put(KEY_visitBy, person.getVisitby());
        values.put(KEY_visitOther, person.getDesignationother());
        db.insert(TABLE_SCHOOL_VISITYBY, null, values);
        db.close();
    }

    public void createSubjectConfig(SubjectModel subject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(ClassName, subject.getClassName());
        Values.put(Subjects, subject.getSubjectName());

        db.insert(TableSubjectconfiguration, null, Values);
    }

    public void saveVacant(DetailsVacant person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_vacantdesignation, person.getVacantdesignation());
        values.put(KEY_vacantseats, person.getVacantseats());
        db.insert(TABLE_VACANT, null, values);
        db.close();
    }

    public void saveSanctionedTeaching(DetailsSanctionedPosts person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_teaching_positioncode, person.getPositioncode());
        values.put(KEY_teaching_designation, person.getDesignation());
        values.put(KEY_teaching_subject, person.getSubject());
        values.put(KEY_teaching_bps, person.getBPS());
        values.put(KEY_teaching_specifyother, person.getSpecifyothers());
        values.put(KEY_teaching_noofsanctioned, person.getNoOfSanctionedPosts());
        db.insert(TABLE_SanctionedTeaching, null, values);
        db.close();
    }

    public void saveSanctionedNonTeaching(DetailsSanctionedNonteachingPosts person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_Nonteaching_positioncode, person.getPositioncode());
        values.put(KEY_Nonteaching_designation, person.getDesignation());
        values.put(KEY_Nonteaching_bps, person.getBPS());
        values.put(NonKEY_teaching_specifyother, person.getSpecifyothers());
        values.put(KEY_Nonteaching_noofsanctioned, person.getNoOfSanctionedPosts());
        db.insert(TABLE_SanctionedNonTeaching, null, values);
        db.close();
    }

    public void saveRoster(RosterDetails person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmis());
        values.put(KEY_Visit, person.getVisit());
        values.put(KEY_School, person.getSchool());
        values.put(KEY_FormId, person.getFormId());
        values.put(KEY_RosterMonth, person.getMonth());
        db.insert(TABLE_Roster, null, values);
        db.close();
    }

    public void updateMonitor(Monitor monitor, String UserName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = UserName;
        Values.put(Password, monitor.getPassword());
        Values.put(Name, monitor.getName());
        Values.put(Age, monitor.getAge());
        Values.put(Sex, monitor.getSex());
        Values.put(Education, monitor.getEducation());
        Values.put(CNIC, monitor.getCnic());
        Values.put(Email, monitor.getEmail());
        Values.put(ContactNo, monitor.getContactNo());

        db.update(TableMonitor, Values, "LoginName='" + username
                + "'", null);
    }


    public void updateProxyTeacher(DetailsProxyTeacher monitor, String emis, int identifier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = emis;
        //int cnicc = identifier;
        Values.put(KEY_Name, monitor.getName());
        Values.put(KEY_Cnicc, monitor.getCnic());
        Values.put(KEY_ProxyForName, monitor.getProxyName());
        Values.put(KEY_ProxyForCnic, monitor.getProxycnic());
        Values.put(KEY_ProxyForNo, monitor.getProxyno());
        Values.put(KEY_Designation, monitor.getDesignation());
        Values.put(KEY_proxyTimeSince, monitor.getProxytimeSince());
        db.update(TABLE_PROXY_TEACHER, Values, "emis='" + username + "' AND id='" + identifier + "'", null);
        //db.update(TABLE_PROXY_TEACHER, Values, "emis" + " = ? AND " + "cnic" + " = ?",
        //      new String[]{username, cnicc});
        //String[] args = new String[]{username, cnicc};
        //db.update(TABLE_PROXY_TEACHER, Values, "emis=? AND cnic=?", args);
    }

    public void updateVacantSeats(DetailsVacant monitor, String emis, int iden) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = emis;
        Values.put(KEY_vacantdesignation, monitor.getVacantdesignation());
        Values.put(KEY_vacantseats, monitor.getVacantseats());
        db.update(TABLE_VACANT, Values, "emis='" + username + "' AND id='" + iden + "'", null);
    }

    public void updateTeacherAppointedby(DetailsAppointedBy monitor, String emis, int iden) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = emis;
        Values.put(KEY_AppointedName, monitor.getName());
        Values.put(KEY_AppointedCnic, monitor.getCnic());
        Values.put(KEY_AppointedDate, monitor.getAppointedDate());
        Values.put(KEY_AppointedBy, monitor.getAppointedby());
        //db.update(TABLE_TEACHER_APPOINTEDBY, Values, "emis='" + username
        //      + "'", null);
        db.update(TABLE_TEACHER_APPOINTEDBY, Values, "emis='" + username + "' AND id='" + iden + "'", null);
    }

    public void updateschoolVisity(DetailsSchoolVisit monitor, String emis, int identityy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = emis;
        Values.put(KEY_visitDate, monitor.getVisitdate());
        Values.put(KEY_visitBy, monitor.getVisitby());
        Values.put(KEY_visitOther, monitor.getDesignationother());
        db.update(TABLE_SCHOOL_VISITYBY, Values, "emis='" + username + "' AND id='" + identityy + "'", null);
    }

    public void updateSanctionedPosts(DetailsSanctionedPosts monitor, String emis, int iddd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = emis;
        Values.put(KEY_teaching_positioncode, monitor.getPositioncode());
        Values.put(KEY_teaching_designation, monitor.getDesignation());
        Values.put(KEY_teaching_specifyother, monitor.getSpecifyothers());
        Values.put(KEY_teaching_subject, monitor.getSubject());
        Values.put(KEY_teaching_bps, monitor.getBPS());
        Values.put(KEY_teaching_noofsanctioned, monitor.getNoOfSanctionedPosts());
        db.update(TABLE_SanctionedTeaching, Values, "emis='" + username + "' AND id='" + iddd + "'", null);
    }

    public void updateNonSanctionedPosts(DetailsSanctionedNonteachingPosts monitor, String emis, int idde) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String username = emis;
        Values.put(KEY_Nonteaching_positioncode, monitor.getPositioncode());
        Values.put(KEY_Nonteaching_designation, monitor.getDesignation());
        Values.put(NonKEY_teaching_specifyother, monitor.getSpecifyothers());
        Values.put(KEY_Nonteaching_bps, monitor.getBPS());
        Values.put(KEY_Nonteaching_noofsanctioned, monitor.getNoOfSanctionedPosts());
        db.update(TABLE_SanctionedNonTeaching, Values, "emis='" + username + "' AND id='" + idde + "'", null);
    }


    public void updateSchoolInfo(SchoolInformationDetails person, String emis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String username = emis;
        //values.put(KEY_Emis, person.getEmiscode());
        values.put(KEY_schoolname, person.getSchoolname());
        values.put(KEY_gender, person.getGender());
        values.put(KEY_schoollevel, person.getLevel());
        values.put(KEY_ddoCode, person.getDdocode());
        values.put(KEY_district, person.getDistrict());
        values.put(KEY_tehsil, person.getTehsil());
        values.put(KEY_ucName, person.getUcname());
        values.put(KEY_location, person.getLocation());
        values.put(KEY_schoolzone, person.getSchoolzone());
        values.put(KEY_naNo, person.getNano());
        values.put(KEY_pkNo, person.getPkno());
        values.put(KEY_circleofficename, person.getCircleofficename());
        values.put(KEY_ADOName, person.getADOName());
        values.put(KEY_ADONo, person.getADONo());


        db.update(TableSchoolInformation, values, "emis='" + emis
                + "'", null);
    }

    public void saveTeacherNew(TeacherNewDetails person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_TeacherName, person.getTeachername());
        values.put(KEY_TeacherFatherName, person.getTeacherfathername());
        values.put(KEY_TeacherGender, person.getTeachergender());
        values.put(KEY_TeacherMaritalStatus, person.getTeachermarital());
        values.put(KEY_TeacherBps, person.getTeacherbps());
        values.put(KEY_TeacherNo, person.getTeacherno());
        values.put(KEY_TeacherAccountNo, person.getTeacheraccountno());
        values.put(KEY_TeacherCnic, person.getTeachercnic());
        values.put(KEY_TeacherDob, person.getTeacherdob());
        values.put(KEY_TeacherHqualificatinLevel, person.getTeacherhighestlevel());
        values.put(KEY_TeacherHqualificatinSubject, person.getTeacherhigestsubject());
        values.put(KEY_TeacherDateofFirst, person.getTeacherdateoffirst());
        values.put(KEY_TeacherDistrict, person.getTeacherdistrict());
        values.put(KEY_TeacherHProfessional, person.getTeacherhighestqualification());
        values.put(KEY_TeacherDesignationAs1st, person.getTeacherdesigasfirst());
        values.put(KEY_TeacherDesignation, person.getDesignation());
        values.put(KEY_TeacherUC, person.getTeacheruc());
        values.put(KEY_TeacherAnyDisbality, person.getTeacheranydisablity());
        values.put(KEY_TeacherTypeDisbality, person.getTeachertypedisablity());
        values.put(KEY_TeacherAttendance, person.getAttendance());
        values.put(KEY_TeacherAttendanceTransferIn, person.getAttendanceTrasnferIn());
        values.put(KEY_TeacherAttendanceDetails, person.getTeacherattendancedetails());
        values.put(KEY_TeacherAttendanceDatesince, person.getAttendancedatesince());
        values.put(KEY_TeacherAttendanceTransferSchool, person.getAttendancetrasnferschool());
        db.insert(TableTeacherInfo, null, values);
        db.close();
    }

    public void saveTrainingRecord(TrainingRecord person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_Teacher_ID, person.getTeacherid());
        values.put(KEY_TrainingName, person.getName());
        values.put(KEY_TrainingCnic, person.getCnic());
        values.put(KEY_TrainingTitle, person.getTitle());
        values.put(KEY_TrainingYear, person.getYear());
        values.put(KEY_TrainingDuration, person.getDuration());
        values.put(KEY_TrainingConductedBy, person.getConductedby());
        values.put(KEY_TrainingAttendedAs, person.getAttendedas());
        db.insert(TableTraining, null, values);
        db.close();
    }

    public void updateTrainingRecord(TrainingRecord person, String emis, int ident) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String username = emis;
        //values.put(KEY_TrainingBackCnic, person.getBackCnic());
        //values.put(KEY_TrainingBackName, person.getBackName());
        values.put(KEY_TrainingTitle, person.getTitle());
        values.put(KEY_TrainingYear, person.getYear());
        values.put(KEY_TrainingDuration, person.getDuration());
        values.put(KEY_TrainingConductedBy, person.getConductedby());
        values.put(KEY_TrainingAttendedAs, person.getAttendedas());
        db.update(TableTraining, values, "emis='" + username + "' AND id='" + ident + "'", null);
    }

    public void saveNonTeacherNew(NonTeacherNewDetails person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(NonKEY_TeacherName, person.getTeachername());
        values.put(NonKEY_TeacherFatherName, person.getTeacherfathername());
        values.put(NonKEY_TeacherGender, person.getTeachergender());
        values.put(NonKEY_TeacherMaritalStatus, person.getTeachermarital());
        values.put(NonKEY_TeacherBps, person.getTeacherbps());
        values.put(NonKEY_TeacherNo, person.getTeacherno());
        values.put(NonKEY_TeacherAccountNo, person.getTeacheraccountno());
        values.put(NonKEY_TeacherCnic, person.getTeachercnic());
        values.put(NonKEY_TeacherDob, person.getTeacherdob());
        values.put(NonKEY_TeacherHqualificatinLevel, person.getTeacherhighestlevel());
        values.put(NonKEY_TeacherHqualificatinSubject, person.getTeacherhigestsubject());
        values.put(NonKEY_TeacherDateofFirst, person.getTeacherdateoffirst());
        values.put(NonKEY_TeacherDistrict, person.getTeacherdistrict());
        values.put(NonKEY_TeacherHProfessional, person.getTeacherhighestqualification());
        values.put(NonKEY_TeacherDesignationAs1st, person.getTeacherdesigasfirst());
        values.put(NonKEY_TeacherDesignation, person.getDesignation());
        values.put(NonKEY_TeacherUC, person.getTeacheruc());
        values.put(NonKEY_TeacherAnyDisbality, person.getTeacheranydisablity());
        values.put(NonKEY_TeacherTypeDisbality, person.getTeachertypedisablity());
        values.put(NonKEY_TeacherAttendance, person.getAttendance());
        values.put(NonKEY_TeacherAttendanceTransferIn, person.getAttendancetransferIn());
        values.put(NonKEY_TeacherAttendanceDetails, person.getTeacherattendancedetails());
        values.put(NonKEY_TeacherAttendanceDatesince, person.getAttendancedatesince());
        values.put(NonKEY_TeacherAttendanceTransferSchool, person.getAttendancetrasnferschool());
        db.insert(TableNonTeacherInfo, null, values);
        db.close();
    }

    public void updateNonTeacherNew(NonTeacherNewDetails person, String emis, int ident) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String username = emis;
        values.put(NonKEY_TeacherName, person.getTeachername());
        values.put(NonKEY_TeacherFatherName, person.getTeacherfathername());
        values.put(NonKEY_TeacherGender, person.getTeachergender());
        values.put(NonKEY_TeacherMaritalStatus, person.getTeachermarital());
        values.put(NonKEY_TeacherBps, person.getTeacherbps());
        values.put(NonKEY_TeacherNo, person.getTeacherno());
        values.put(NonKEY_TeacherAccountNo, person.getTeacheraccountno());
        values.put(NonKEY_TeacherCnic, person.getTeachercnic());
        values.put(NonKEY_TeacherDob, person.getTeacherdob());
        values.put(NonKEY_TeacherHqualificatinLevel, person.getTeacherhighestlevel());
        values.put(NonKEY_TeacherHqualificatinSubject, person.getTeacherhigestsubject());
        values.put(NonKEY_TeacherDateofFirst, person.getTeacherdateoffirst());
        values.put(NonKEY_TeacherDistrict, person.getTeacherdistrict());
        values.put(NonKEY_TeacherHProfessional, person.getTeacherhighestqualification());
        values.put(NonKEY_TeacherDesignationAs1st, person.getTeacherdesigasfirst());
        values.put(NonKEY_TeacherDesignation, person.getDesignation());
        values.put(NonKEY_TeacherUC, person.getTeacheruc());
        values.put(NonKEY_TeacherAnyDisbality, person.getTeacheranydisablity());
        values.put(NonKEY_TeacherTypeDisbality, person.getTeachertypedisablity());
        values.put(NonKEY_TeacherAttendance, person.getAttendance());
        values.put(NonKEY_TeacherAttendanceTransferIn, person.getAttendancetransferIn());
        values.put(NonKEY_TeacherAttendanceDetails, person.getTeacherattendancedetails());
        values.put(NonKEY_TeacherAttendanceDatesince, person.getAttendancedatesince());
        values.put(NonKEY_TeacherAttendanceTransferSchool, person.getAttendancetrasnferschool());
        db.update(TableNonTeacherInfo, values, "emis='" + username + "' AND id='" + ident + "'", null);
    }

    public void updateTeacherNew(TeacherNewDetails person, String emis, int ident) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String username = emis;
        values.put(KEY_TeacherName, person.getTeachername());
        values.put(KEY_TeacherFatherName, person.getTeacherfathername());
        values.put(KEY_TeacherGender, person.getTeachergender());
        values.put(KEY_TeacherMaritalStatus, person.getTeachermarital());
        values.put(KEY_TeacherBps, person.getTeacherbps());
        values.put(KEY_TeacherNo, person.getTeacherno());
        values.put(KEY_TeacherAccountNo, person.getTeacheraccountno());
        values.put(KEY_TeacherCnic, person.getTeachercnic());
        values.put(KEY_TeacherDob, person.getTeacherdob());
        values.put(KEY_TeacherHqualificatinLevel, person.getTeacherhighestlevel());
        values.put(KEY_TeacherHqualificatinSubject, person.getTeacherhigestsubject());
        values.put(KEY_TeacherDateofFirst, person.getTeacherdateoffirst());
        values.put(KEY_TeacherDistrict, person.getTeacherdistrict());
        values.put(KEY_TeacherHProfessional, person.getTeacherhighestqualification());
        values.put(KEY_TeacherDesignationAs1st, person.getTeacherdesigasfirst());
        values.put(KEY_TeacherDesignation, person.getDesignation());
        values.put(KEY_TeacherUC, person.getTeacheruc());
        values.put(KEY_TeacherAnyDisbality, person.getTeacheranydisablity());
        values.put(KEY_TeacherTypeDisbality, person.getTeachertypedisablity());
        values.put(KEY_TeacherAttendance, person.getAttendance());
        values.put(KEY_TeacherAttendanceTransferIn, person.getAttendanceTrasnferIn());
        values.put(KEY_TeacherAttendanceDetails, person.getTeacherattendancedetails());
        values.put(KEY_TeacherAttendanceDatesince, person.getAttendancedatesince());
        values.put(KEY_TeacherAttendanceTransferSchool, person.getAttendancetrasnferschool());
        db.update(TableTeacherInfo, values, "emis='" + username + "' AND id='" + ident + "'", null);
    }

    public void saveSchoolInformation(SchoolInformationDetails person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmiscode());
        values.put(KEY_schoolname, person.getSchoolname());
        values.put(KEY_gender, person.getGender());
        values.put(KEY_schoollevel, person.getLevel());
        values.put(KEY_ddoCode, person.getDdocode());
        values.put(KEY_district, person.getDistrict());
        values.put(KEY_tehsil, person.getTehsil());
        values.put(KEY_ucName, person.getUcname());
        values.put(KEY_location, person.getLocation());
        values.put(KEY_schoolzone, person.getSchoolzone());
        values.put(KEY_naNo, person.getNano());
        values.put(KEY_pkNo, person.getPkno());
        values.put(KEY_circleofficename, person.getCircleofficename());
        values.put(KEY_ADOName, person.getADOName());
        values.put(KEY_ADONo, person.getADONo());
        db.insert(TableSchoolInformation, null, values);
        db.close();
    }


    public void saveScreenConfig(ScreenConfig person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_An_DisabledStudent, person.getAn_DisabledStudent());
        values.put(KEY_An_EnrollmentByAge, person.getAn_EnrollmentByAge());
        values.put(KEY_An_EnrollmentByGroup, person.getAn_EnrollmentByGroup());
        values.put(KEY_An_FTB, person.getAn_FTB());
        values.put(KEY_An_Furniture, person.getAn_Furniture());
        values.put(KEY_An_Indicator, person.getAn_Indicator());
        values.put(KEY_An_SantionedPosts, person.getAn_SantionedPosts());
        values.put(KEY_An_SecurityMeasures, person.getAn_SecurityMeasures());
        values.put(KEY_Bi_BuildingCondition, person.getBi_BuildingCondition());
        values.put(KEY_Bi_Cleanliness, person.getBi_Cleanliness());
        values.put(KEY_Bi_Commodities, person.getBi_Commodities());
        values.put(KEY_Bi_Guides, person.getBi_Guides());
        values.put(KEY_Bi_ITLab, person.getBi_ITLab());
        values.put(KEY_Bi_Infrastructure, person.getBi_Infrastructure());
        values.put(KEY_Bi_NatureOfConstruction, person.getBi_NatureOfConstruction());
        values.put(KEY_Bi_PTC, person.getBi_PTC());
        values.put(KEY_Bi_SchoolArea, person.getBi_SchoolArea());
        values.put(KEY_Bi_Stipend, person.getBi_Stipend());
        values.put(KEY_SchoolInformation, person.getSchoolInformation());
        db.insert(TableScreenConfig, null, values);
        db.close();
    }

    public void savegcsteachers(GcsTeacherDetails person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_gcsteachername, person.getTeachername());
        values.put(KEY_gcsteachercnic, person.getTeachercnic());
        values.put(KEY_gcsteacherno, person.getTeacherno());
        values.put(KEY_gcsteachergender, person.getTeachergender());
        values.put(KEY_gcsteacheratt, person.getAttendance());
        values.put(KEY_gcsteacherattdetails, person.getTeacherattendancedetails());
        values.put(KEY_gcsreplacement, person.getReplacementavailable());
        values.put(KEY_gcsreplacementname, person.getReplacementname());
        values.put(KEY_gcsreplacementgender, person.getReplacementgender());
        db.insert(GCSTeachers, null, values);
        db.close();
    }

    public void updategcsteachers(GcsTeacherDetails person, String emis, int idde) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_gcsteachername, person.getTeachername());
        values.put(KEY_gcsteachercnic, person.getTeachercnic());
        values.put(KEY_gcsteacherno, person.getTeacherno());
        values.put(KEY_gcsteachergender, person.getTeachergender());
        values.put(KEY_gcsteacheratt, person.getAttendance());
        values.put(KEY_gcsteacherattdetails, person.getTeacherattendancedetails());
        values.put(KEY_gcsreplacement, person.getReplacementavailable());
        values.put(KEY_gcsreplacementname, person.getReplacementname());
        values.put(KEY_gcsreplacementgender, person.getReplacementgender());
        db.update(GCSTeachers, values, "emis='" + emis + "' AND id='" + idde + "'", null);

    }


    public void saveteacherwebservice(DetailsTeacherwebservice person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_TeacherNamewebservice, person.getTeachername());
        values.put(KEY_TeacherCnicwebservice, person.getTeachercnic());
        values.put(KEY_TeacherNowebservice, person.getTeacherno());
        values.put(KEY_TeacherGenderwebservice, person.getTeachergender());
        values.put(KEY_TeacherAttendancewebservice, person.getAttendance());
        values.put(KEY_TeacherAttdetailswebservice, person.getTeacherattendancedetails());
        values.put(KEY_TeacherAttendanceDatesinceWeb, person.getAttendancedatesince());
        values.put(KEY_TeacherAttendanceTransferSchoolWeb, person.getAttendancetrasnferschool());
        db.insert(Teacherwebservice, null, values);
        db.close();
    }

    public void updateteacherwebservice(DetailsTeacherwebservice person, String emis, int idde) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TeacherNamewebservice, person.getTeachername());
        values.put(KEY_TeacherCnicwebservice, person.getTeachercnic());
        values.put(KEY_TeacherNowebservice, person.getTeacherno());
        values.put(KEY_TeacherGenderwebservice, person.getTeachergender());
        values.put(KEY_TeacherAttendancewebservice, person.getAttendance());
        values.put(KEY_TeacherAttdetailswebservice, person.getTeacherattendancedetails());
        values.put(KEY_TeacherAttendanceDatesinceWeb, person.getAttendancedatesince());
        values.put(KEY_TeacherAttendanceTransferSchoolWeb, person.getAttendancetrasnferschool());
        db.update(Teacherwebservice, values, "emis='" + emis + "' AND id='" + idde + "'", null);

    }

    public void saveNonteacherwebservice(DetailsNonTeacherwebservice person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_NONTeacherNamewebservice, person.getTeachername());
        values.put(KEY_NONTeacherCnicwebservice, person.getTeachercnic());
        values.put(KEY_NONTeacherNowebservice, person.getTeacherno());
        values.put(KEY_NONTeacherGenderwebservice, person.getTeachergender());
        values.put(KEY_NONTeacherAttendancewebservice, person.getAttendance());
        values.put(KEY_NONTeacherAttdetailswebservice, person.getTeacherattendancedetails());
        values.put(KEY_NONTeacherAttendanceDatesinceWeb, person.getAttendancedatesince());
        values.put(KEY_NONTeacherAttendanceTransferSchoolWeb, person.getAttendancetrasnferschool());
        db.insert(NonTeacherwebservice, null, values);
        db.close();
    }

    public void updateNonteacherwebservice(DetailsNonTeacherwebservice person, String emis, int idde) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NONTeacherNamewebservice, person.getTeachername());
        values.put(KEY_NONTeacherCnicwebservice, person.getTeachercnic());
        values.put(KEY_NONTeacherNowebservice, person.getTeacherno());
        values.put(KEY_NONTeacherGenderwebservice, person.getTeachergender());
        values.put(KEY_NONTeacherAttendancewebservice, person.getAttendance());
        values.put(KEY_NONTeacherAttdetailswebservice, person.getTeacherattendancedetails());
        values.put(KEY_NONTeacherAttendanceDatesinceWeb, person.getAttendancedatesince());
        values.put(KEY_NONTeacherAttendanceTransferSchoolWeb, person.getAttendancetrasnferschool());
        db.update(NonTeacherwebservice, values, "emis='" + emis + "' AND id='" + idde + "'", null);

    }

    /*public void saveE(TeacherDetails tdetails){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_Name, tdetails.getName());
        values.put(KEY_Cnic, tdetails.getCnic());
        values.put(KEY_Pno, tdetails.getPno());
        values.put(KEY_attendance, tdetails.getAttendance());
        db.insert(TABLE_TInfo, null, values);
        db.close();
    }

    public void schooldata(SchoolNameDetails tdetails){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SchoolName, tdetails.getSchoolname());
        values.put(KEY_level, tdetails.getLevel());
        values.put(KEY_Pkno, tdetails.getPkno());
        values.put(KEY_Nano, tdetails.getNano());
        values.put(KEY_UCName, tdetails.getUcname());
        values.put(KEY_circleofficeno, tdetails.getOfficeno());
        db.insert(TABLE_SchoolInfo, null, values);
        db.close();
    }


    public boolean checkEvent(String schoolname, String level, String pkno)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SchoolInfo,
                new String[] { KEY_SchoolName,KEY_level,KEY_Pkno },
                KEY_SchoolName + " = ? and "+ KEY_level + " = ? and " + KEY_Pkno + " = ?" ,
                new String[] {schoolname,level,pkno},
                null, null, null, null);

        if(cursor.moveToFirst())

            return true; //row exists
        else
            return false;

    }*/


    /*public DetailsProxyTeacher findOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROXY_TEACHER, new String[]{KEY_ID, KEY_Name,KEY_ProxyForName, KEY_Designation},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new DetailsProxyTeacher(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(2),cursor.getString(3));
    }*/

    public List<DetailsProxyTeacher> findAll() {
        List<DetailsProxyTeacher> listperson = new ArrayList<DetailsProxyTeacher>();
        String query = "SELECT * FROM " + TABLE_PROXY_TEACHER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                DetailsProxyTeacher proxydetails = new DetailsProxyTeacher();
                proxydetails.setId(Integer.valueOf(cursor.getString(0)));
                proxydetails.setName(cursor.getString(1));
                proxydetails.setProxyName(cursor.getString(2));
                proxydetails.setDesignation(cursor.getString(3));
                //rosterdetails.setSchool(cursor.getString(3));
                listperson.add(proxydetails);
            } while (cursor.moveToNext());
        }

        return listperson;
    }

    public void update(DetailsProxyTeacher person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, person.getName());
        values.put(KEY_ProxyForName, person.getProxyName());
        values.put(KEY_Designation, person.getDesignation());
        //values.put(KEY_School, person.getSchool());

        db.update(TABLE_PROXY_TEACHER, values, KEY_ID + "=?", new String[]{String.valueOf(person.getId())});
        db.close();
    }

    public void Teacherupdate(TeacherNewDetails person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Emis, person.getEmisCode());
        values.put(KEY_TeacherName, person.getTeachername());
        values.put(KEY_TeacherNo, person.getTeacherno());
        values.put(KEY_TeacherCnic, person.getTeachercnic());
        values.put(KEY_TeacherDesignation, person.getDesignation());
        values.put(KEY_TeacherAttendance, person.getAttendance());
        //values.put(KEY_School, person.getSchool());
        String a = person.getEmisCode();
        String b = person.getTeachername();
        String c = person.getTeacherno();
        String d = person.getTeachercnic();
        String e = person.getDesignation();
        String f = person.getAttendance();

        db.update(TableTeacherInfo, values, KEY_Emis
                        + " = ? AND " + KEY_TeacherName + " = ? AND " + KEY_TeacherNo + " = ? AND " + KEY_TeacherCnic
                        + " = ? AND " + KEY_TeacherDesignation + " = ? AND " + KEY_TeacherAttendance + " = ?"
                , new String[]{a, b, c, d, e, f});
        db.close();
    }

    /*public void schoolinfoupdate(SchoolNameDetails school){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_SchoolName, school.getSchoolname());
        values.put(KEY_level, school.getLevel());
        values.put(KEY_Pkno, school.getPkno());
        values.put(KEY_Nano, school.getNano());
        values.put(KEY_UCName, school.getUcname());
        values.put(KEY_circleofficeno, school.getOfficeno());
        db.insert(TABLE_SchoolInfo, null, values);

        db.update(TABLE_SchoolInfo, values, KEY_ID+"=?", new String[]{String.valueOf(school.getId())});
        db.close();
    }*/

    /*public void delete(DetailsProxyTeacher person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROXY_TEACHER,  + "=?", new String[]{String.valueOf(person.getId())});
        db.close();
    }*/

    public void removeProxyTeacher(int name) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_PROXY_TEACHER + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(name)});
        cursor.moveToFirst();
        database.close();
    }

    public void updateTrainingTeacherID(int teacherID) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "update " + TableTraining + " set " + KEY_Teacher_ID + " = " + teacherID + " WHERE " + KEY_Teacher_ID + " = 0";
        Cursor cursor = database.rawQuery(select, new String[]{});
        cursor.moveToFirst();
        database.close();
    }

    public int getMaxid() {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "SELECT max(id) from " + TableTeacherInfo;
        Cursor cursor = database.rawQuery(select, new String[]{});
        cursor.moveToFirst();
        try {
            int temp = cursor.getInt(0);
            database.close();
            return temp;
        } catch (Exception ex) {
            database.close();
            return 0;
        }
    }

    public void removeSanctionedPost(int identity) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SanctionedTeaching + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(identity)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeNonTeacher(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableNonTeacherInfo + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeTeacher(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableTeacherInfo + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeSanctionedPostNon(int idns) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SanctionedNonTeaching + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(idns)});
        cursor.moveToFirst();
        database.close();
    }

    /*public void removeSchoolvisit(String code, String designation) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SCHOOL_VISITYBY + " WHERE " + KEY_visitDate + " = ? AND " + KEY_visitBy + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{code, designation});
        cursor.moveToFirst();
        database.close();
    }*/
    public void removeSchoolvisit(int code) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SCHOOL_VISITYBY + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(code)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeRoster(String month) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_Roster + " WHERE " + KEY_RosterMonth + " != ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(month)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeTeacherData(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + Teacherwebservice + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherData1(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + NonTeacherwebservice + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherData2(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableTeacherInfo + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherData3(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableNonTeacherInfo + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherData4proxy(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_PROXY_TEACHER + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDataappointed(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_TEACHER_APPOINTEDBY + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDatavisit(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SCHOOL_VISITYBY + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDatavacant(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_VACANT + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDatasanctionedT(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SanctionedTeaching + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDatasanctionedNT(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_SanctionedNonTeaching + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeTeacherDataenrollmentboys(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableEnrollmentAgeBoys + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDataenrollmentgirls(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableEnrollmentAgeGirls + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDataenrollmentgap(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableEnrollmentAttendanceGap + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDataenrollmentspecialboys(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableEnrollmentSpecialBoys + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }
    public void removeTeacherDataenrollmentspecialgirls(String emiscode) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableEnrollmentSpecialGirls + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(emiscode)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeAppointedBy(int ident) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_TEACHER_APPOINTEDBY + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(ident)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeVacant(int ident) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_VACANT + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(ident)});
        cursor.moveToFirst();
        database.close();
    }

    public void removeRosteremis(String emis) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TABLE_Roster + " WHERE " + KEY_Emis + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{emis});
        cursor.moveToFirst();
        database.close();
    }

    public void removeTraining() {
        getWritableDatabase().execSQL("DELETE FROM " + TableTraining + ";");
    }

    public void removeTrainingRecord(int ide) {
        SQLiteDatabase database = this.getWritableDatabase();
        String select = "DELETE FROM " + TableTraining + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = database.rawQuery(select, new String[]{String.valueOf(ide)});
        cursor.moveToFirst();
        database.close();
    }


    public ArrayList<ArrayList<Object>> abc() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_PROXY_TEACHER, new String[]{KEY_Emis, KEY_Name, KEY_Cnicc,
                            KEY_ProxyForName, KEY_ProxyForCnic, KEY_ProxyForNo, KEY_Designation, KEY_proxyTimeSince},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataList.add(cur.getString(6));
                    dataList.add(cur.getString(7));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }


    public ArrayList<ArrayList<Object>> abcAppointedBy() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_TEACHER_APPOINTEDBY, new String[]{KEY_AppointedName, KEY_AppointedCnic, KEY_AppointedBy, KEY_AppointedDate},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcStaff() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_STAFF_ADDING, new String[]{KEY_StaffName, KEY_StaffNo, KEY_StaffCnic, KEY_StaffAttendance},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcSchoolVisitBy() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_SCHOOL_VISITYBY, new String[]{KEY_visitDate, KEY_visitBy},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcVacant() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_VACANT, new String[]{KEY_vacantdesignation, KEY_vacantseats},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcSanctionedTeaching() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_SanctionedTeaching, new String[]{KEY_teaching_positioncode, KEY_teaching_designation,
                            KEY_teaching_bps, KEY_teaching_noofsanctioned},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcSanctionedNonTeaching() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_SanctionedNonTeaching, new String[]{KEY_Nonteaching_positioncode, KEY_Nonteaching_designation,
                            KEY_Nonteaching_bps, KEY_Nonteaching_noofsanctioned},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcRoster() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TABLE_Roster, new String[]{KEY_Emis, KEY_Visit, KEY_School, KEY_FormId},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcTeacherNew() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TableTeacherInfo, new String[]{KEY_Emis, KEY_TeacherName, KEY_TeacherFatherName, KEY_TeacherGender,
                            KEY_TeacherMaritalStatus, KEY_TeacherBps, KEY_TeacherNo, KEY_TeacherAccountNo,
                            KEY_TeacherCnic, KEY_TeacherDob, KEY_TeacherHqualificatinLevel, KEY_TeacherHqualificatinSubject,
                            KEY_TeacherDateofFirst, KEY_TeacherDistrict, KEY_TeacherHProfessional, KEY_TeacherDesignationAs1st,
                            KEY_TeacherDesignation, KEY_TeacherUC, KEY_TeacherAnyDisbality, KEY_TeacherTypeDisbality,
                            KEY_TeacherAttendance, KEY_TeacherAttendanceDetails, KEY_TeacherAttendanceDatesince, KEY_TeacherAttendanceTransferSchool},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataList.add(cur.getString(6));
                    dataList.add(cur.getString(7));
                    dataList.add(cur.getString(8));
                    dataList.add(cur.getString(9));
                    dataList.add(cur.getString(10));
                    dataList.add(cur.getString(11));
                    dataList.add(cur.getString(12));
                    dataList.add(cur.getString(13));
                    dataList.add(cur.getString(14));
                    dataList.add(cur.getString(15));
                    dataList.add(cur.getString(16));
                    dataList.add(cur.getString(17));
                    dataList.add(cur.getString(18));
                    dataList.add(cur.getString(19));
                    dataList.add(cur.getString(20));
                    dataList.add(cur.getString(21));
                    dataList.add(cur.getString(22));
                    dataList.add(cur.getString(23));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcTrainingRecord() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TableTraining, new String[]{KEY_Emis, KEY_TrainingTitle, KEY_TrainingYear, KEY_TrainingDuration, KEY_TrainingConductedBy, KEY_TrainingAttendedAs},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcNonTeacherNew() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TableNonTeacherInfo, new String[]{KEY_Emis, NonKEY_TeacherName, NonKEY_TeacherFatherName, NonKEY_TeacherGender,
                            NonKEY_TeacherMaritalStatus, NonKEY_TeacherBps, NonKEY_TeacherNo, NonKEY_TeacherAccountNo,
                            NonKEY_TeacherCnic, NonKEY_TeacherDob, NonKEY_TeacherHqualificatinLevel, NonKEY_TeacherHqualificatinSubject,
                            NonKEY_TeacherDateofFirst, NonKEY_TeacherDistrict, NonKEY_TeacherHProfessional, NonKEY_TeacherDesignationAs1st,
                            NonKEY_TeacherDesignation, NonKEY_TeacherUC, NonKEY_TeacherAnyDisbality, NonKEY_TeacherTypeDisbality,
                            NonKEY_TeacherAttendance, NonKEY_TeacherAttendanceDetails, NonKEY_TeacherAttendanceDatesince, NonKEY_TeacherAttendanceTransferSchool},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataList.add(cur.getString(6));
                    dataList.add(cur.getString(7));
                    dataList.add(cur.getString(8));
                    dataList.add(cur.getString(9));
                    dataList.add(cur.getString(10));
                    dataList.add(cur.getString(11));
                    dataList.add(cur.getString(12));
                    dataList.add(cur.getString(13));
                    dataList.add(cur.getString(14));
                    dataList.add(cur.getString(15));
                    dataList.add(cur.getString(16));
                    dataList.add(cur.getString(17));
                    dataList.add(cur.getString(18));
                    dataList.add(cur.getString(19));
                    dataList.add(cur.getString(20));
                    dataList.add(cur.getString(21));
                    dataList.add(cur.getString(22));
                    dataList.add(cur.getString(23));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcSchoolInformation() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cur = db.query(TableSchoolInformation, new String[]{KEY_Emis, KEY_schoolname, KEY_gender,
                            KEY_schoollevel, KEY_ddoCode, KEY_district, KEY_tehsil,
                            KEY_ucName, KEY_location, KEY_schoolzone,
                            KEY_naNo, KEY_pkNo, KEY_circleofficename, KEY_ADOName, KEY_ADONo},
                    null, null, null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataList.add(cur.getString(6));
                    dataList.add(cur.getString(7));
                    dataList.add(cur.getString(8));
                    dataList.add(cur.getString(9));
                    dataList.add(cur.getString(10));
                    dataList.add(cur.getString(11));
                    dataList.add(cur.getString(12));
                    dataList.add(cur.getString(13));
                    dataList.add(cur.getString(14));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<TeacherNewDetails> getGcsTeacher(String emisCode) {
        ArrayList<TeacherNewDetails> teacherList = new ArrayList<TeacherNewDetails>();
        TeacherNewDetails tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TableTeacherInfo
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new TeacherNewDetails();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String TeacherNamee = c.getString(c
                            .getColumnIndex(KEY_TeacherName));
                    String fathername = c.getString(c.getColumnIndex(KEY_TeacherFatherName));
                    String Teachergender = c.getString(c.getColumnIndex(KEY_TeacherGender));
                    String Teachermarital = c.getString(c.getColumnIndex(KEY_TeacherMaritalStatus));
                    String Teacherbps = c.getString(c.getColumnIndex(KEY_TeacherBps));
                    String Teacherno = c.getString(c.getColumnIndex(KEY_TeacherNo));
                    String Teacheraccno = c.getString(c.getColumnIndex(KEY_TeacherAccountNo));
                    String Teachercnicno = c.getString(c.getColumnIndex(KEY_TeacherCnic));
                    String Teacherbirth = c.getString(c.getColumnIndex(KEY_TeacherDob));
                    String Teacherlevel = c.getString(c.getColumnIndex(KEY_TeacherHqualificatinLevel));
                    String Teachersubejct = c.getString(c.getColumnIndex(KEY_TeacherHqualificatinSubject));
                    String Teacherdateoffirst = c.getString(c.getColumnIndex(KEY_TeacherDateofFirst));
                    String Teacherdstrc = c.getString(c.getColumnIndex(KEY_TeacherDistrict));
                    String Teacherhighestprof = c.getString(c.getColumnIndex(KEY_TeacherHProfessional));
                    String Teacherdesigassfirst = c.getString(c.getColumnIndex(KEY_TeacherDesignationAs1st));
                    String Teacherdesignation = c.getString(c.getColumnIndex(KEY_TeacherDesignation));
                    String Teacheruc = c.getString(c.getColumnIndex(KEY_TeacherUC));
                    String Teacherdisbality = c.getString(c.getColumnIndex(KEY_TeacherAnyDisbality));
                    String Teachertype = c.getString(c.getColumnIndex(KEY_TeacherTypeDisbality));
                    String status = c.getString(c.getColumnIndex(KEY_TeacherAttendance));
                    String statustrasnfer = c.getString(c.getColumnIndex(KEY_TeacherAttendanceTransferIn));
                    String statusdetails = c.getString(c.getColumnIndex(KEY_TeacherAttendanceDetails));
                    String datesince = c.getString(c.getColumnIndex(KEY_TeacherAttendanceDatesince));
                    String transfer = c.getString(c.getColumnIndex(KEY_TeacherAttendanceTransferSchool));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setTeachername(TeacherNamee);
                    tacherInfo.setTeacherfathername(fathername);
                    tacherInfo.setTeachergender(Teachergender);
                    tacherInfo.setTeachermarital(Teachermarital);
                    tacherInfo.setTeacherbps(Teacherbps);
                    tacherInfo.setTeacherno(Teacherno);
                    tacherInfo.setTeacheraccountno(Teacheraccno);
                    tacherInfo.setTeachercnic(Teachercnicno);
                    tacherInfo.setTeacherdob(Teacherbirth);
                    tacherInfo.setTeacherhighestlevel(Teacherlevel);
                    tacherInfo.setTeacherhigestsubject(Teachersubejct);
                    tacherInfo.setTeacherdateoffirst(Teacherdateoffirst);
                    tacherInfo.setTeacherdistrict(Teacherdstrc);
                    tacherInfo.setTeacherhighestqualification(Teacherhighestprof);
                    tacherInfo.setTeacherdesigasfirst(Teacherdesigassfirst);
                    tacherInfo.setDesignation(Teacherdesignation);
                    tacherInfo.setTeacheruc(Teacheruc);
                    tacherInfo.setTeacheranydisablity(Teacherdisbality);
                    tacherInfo.setTeachertypedisablity(Teachertype);
                    tacherInfo.setAttendance(status);
                    tacherInfo.setAttendanceTrasnferIn(statustrasnfer);
                    tacherInfo.setTeacherattendancedetails(statusdetails);
                    tacherInfo.setAttendancedatesince(datesince);
                    tacherInfo.setAttendancetrasnferschool(transfer);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }


    public ArrayList<GcsTeacherDetails> Gcslistteacher(String emisCode) {
        ArrayList<GcsTeacherDetails> teacherList = new ArrayList<GcsTeacherDetails>();
        GcsTeacherDetails tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + GCSTeachers
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new GcsTeacherDetails();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String TeacherNamee = c.getString(c.getColumnIndex(KEY_gcsteachername));
                    String Teachergender = c.getString(c.getColumnIndex(KEY_gcsteachergender));
                    String Teacherno = c.getString(c.getColumnIndex(KEY_gcsteacherno));
                    String Teachercnicno = c.getString(c.getColumnIndex(KEY_gcsteachercnic));
                    String Teacheratt = c.getString(c.getColumnIndex(KEY_gcsteacheratt));
                    String Teacherattdetails = c.getString(c.getColumnIndex(KEY_gcsteacherattdetails));
                    String Teacherreplacement = c.getString(c.getColumnIndex(KEY_gcsreplacement));
                    String Teacherreplacementname = c.getString(c.getColumnIndex(KEY_gcsreplacementname));
                    String Teacherreplacementgender = c.getString(c.getColumnIndex(KEY_gcsreplacementgender));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setTeachername(TeacherNamee);
                    tacherInfo.setTeachergender(Teachergender);
                    tacherInfo.setTeachercnic(Teachercnicno);
                    tacherInfo.setTeacherno(Teacherno);
                    tacherInfo.setAttendance(Teacheratt);
                    tacherInfo.setTeacherattendancedetails(Teacherattdetails);
                    tacherInfo.setReplacementavailable(Teacherreplacement);
                    tacherInfo.setReplacementname(Teacherreplacementname);
                    tacherInfo.setReplacementgender(Teacherreplacementgender);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<NonTeacherNewDetails> getGcsNonTeacher(String emisCode) {
        ArrayList<NonTeacherNewDetails> teacherList = new ArrayList<NonTeacherNewDetails>();
        NonTeacherNewDetails tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TableNonTeacherInfo
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new NonTeacherNewDetails();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String TeacherNamee = c.getString(c.getColumnIndex(NonKEY_TeacherName));
                    String fathername = c.getString(c.getColumnIndex(NonKEY_TeacherFatherName));
                    String Teachergender = c.getString(c.getColumnIndex(NonKEY_TeacherGender));
                    String Teachermarital = c.getString(c.getColumnIndex(NonKEY_TeacherMaritalStatus));
                    String Teacherbps = c.getString(c.getColumnIndex(NonKEY_TeacherBps));
                    String Teacherno = c.getString(c.getColumnIndex(NonKEY_TeacherNo));
                    String Teacheraccno = c.getString(c.getColumnIndex(NonKEY_TeacherAccountNo));
                    String Teachercnicno = c.getString(c.getColumnIndex(NonKEY_TeacherCnic));
                    String Teacherbirth = c.getString(c.getColumnIndex(NonKEY_TeacherDob));
                    String Teacherlevel = c.getString(c.getColumnIndex(NonKEY_TeacherHqualificatinLevel));
                    String Teachersubejct = c.getString(c.getColumnIndex(NonKEY_TeacherHqualificatinSubject));
                    String Teacherdateoffirst = c.getString(c.getColumnIndex(NonKEY_TeacherDateofFirst));
                    String Teacherdstrc = c.getString(c.getColumnIndex(NonKEY_TeacherDistrict));
                    String Teacherhighestprof = c.getString(c.getColumnIndex(NonKEY_TeacherHProfessional));
                    String Teacherdesigassfirst = c.getString(c.getColumnIndex(NonKEY_TeacherDesignationAs1st));
                    String Teacherdesignation = c.getString(c.getColumnIndex(NonKEY_TeacherDesignation));
                    String Teacheruc = c.getString(c.getColumnIndex(NonKEY_TeacherUC));
                    String Teacherdisbality = c.getString(c.getColumnIndex(NonKEY_TeacherAnyDisbality));
                    String Teachertype = c.getString(c.getColumnIndex(NonKEY_TeacherTypeDisbality));
                    String status = c.getString(c.getColumnIndex(NonKEY_TeacherAttendance));
                    String statustrasnfer = c.getString(c.getColumnIndex(NonKEY_TeacherAttendanceTransferIn));
                    String statusdetails = c
                            .getString(c.getColumnIndex(NonKEY_TeacherAttendanceDetails));
                    String datesince = c.getString(c.getColumnIndex(NonKEY_TeacherAttendanceDatesince));
                    String transfer = c.getString(c.getColumnIndex(NonKEY_TeacherAttendanceTransferSchool));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setTeachername(TeacherNamee);
                    tacherInfo.setTeacherfathername(fathername);
                    tacherInfo.setTeachergender(Teachergender);
                    tacherInfo.setTeachermarital(Teachermarital);
                    tacherInfo.setTeacherbps(Teacherbps);
                    tacherInfo.setTeacherno(Teacherno);
                    tacherInfo.setTeacheraccountno(Teacheraccno);
                    tacherInfo.setTeachercnic(Teachercnicno);
                    tacherInfo.setTeacherdob(Teacherbirth);
                    tacherInfo.setTeacherhighestlevel(Teacherlevel);
                    tacherInfo.setTeacherhigestsubject(Teachersubejct);
                    tacherInfo.setTeacherdateoffirst(Teacherdateoffirst);
                    tacherInfo.setTeacherdistrict(Teacherdstrc);
                    tacherInfo.setTeacherhighestqualification(Teacherhighestprof);
                    tacherInfo.setTeacherdesigasfirst(Teacherdesigassfirst);
                    tacherInfo.setDesignation(Teacherdesignation);
                    tacherInfo.setTeacheruc(Teacheruc);
                    tacherInfo.setTeacheranydisablity(Teacherdisbality);
                    tacherInfo.setTeachertypedisablity(Teachertype);
                    tacherInfo.setAttendance(status);
                    tacherInfo.setAttendancetransferIn(statustrasnfer);
                    tacherInfo.setTeacherattendancedetails(statusdetails);
                    tacherInfo.setAttendancedatesince(datesince);
                    tacherInfo.setAttendancetrasnferschool(transfer);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }


    public ArrayList<TrainingRecord> getTrainingRecord(String emis /*, int teacherId*/) {
        ArrayList<TrainingRecord> teacherList = new ArrayList<TrainingRecord>();
        TrainingRecord tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        /*String selectQuery = "SELECT * " + " FROM " + TableTraining
                + " WHERE " + KEY_Emis + " = " + emisCode +
                " AND " + KEY_TrainingBackCnic + " = " + backCnic +
                " AND " + KEY_TrainingBackName + " = " + backName +
                " AND " + KEY_TrainingBackAccountNo + " = " + backAccno + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);*/
        String select = "SELECT * " + " FROM " + TableTraining
                + " WHERE " /*+ KEY_Emis+ " = ? AND "*/ + KEY_Emis + "=?";
        Cursor c = db.rawQuery(select, new String[]{emis});

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new TrainingRecord();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    int tid = c.getInt(c.getColumnIndex(KEY_Teacher_ID));
                    String emise = c.getString(c.getColumnIndex(KEY_Emis));
                    String name = c.getString(c.getColumnIndex(KEY_TrainingName));
                    String cnic = c.getString(c.getColumnIndex(KEY_TrainingCnic));
                    String title = c.getString(c
                            .getColumnIndex(KEY_TrainingTitle));
                    String year = c
                            .getString(c.getColumnIndex(KEY_TrainingYear));
                    String duration = c.getString(c.getColumnIndex(KEY_TrainingDuration));
                    String conducteddby = c.getString(c.getColumnIndex(KEY_TrainingConductedBy));
                    String attendedaas = c.getString(c.getColumnIndex(KEY_TrainingAttendedAs));
                    tacherInfo.setId(idd);
                    tacherInfo.setTeacherid(tid);
                    tacherInfo.setEmisCode(emise);
                    tacherInfo.setName(name);
                    tacherInfo.setCnic(cnic);
                    tacherInfo.setTitle(title);
                    tacherInfo.setYear(year);
                    tacherInfo.setDuration(duration);
                    tacherInfo.setConductedby(conducteddby);
                    tacherInfo.setAttendedas(attendedaas);
                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<DetailsProxyTeacher> getProxyTeacher(String emisCode) {
        ArrayList<DetailsProxyTeacher> teacherList = new ArrayList<DetailsProxyTeacher>(

        );
        DetailsProxyTeacher tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TABLE_PROXY_TEACHER
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsProxyTeacher();
                    // String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    int id = c.getInt(c
                            .getColumnIndex(KEY_ID));
                    String name = c.getString(c
                            .getColumnIndex(KEY_Name));
                    String cnic = c.getString(c
                            .getColumnIndex(KEY_Cnicc));
                    String proxyforname = c
                            .getString(c.getColumnIndex(KEY_ProxyForName));
                    String proxyforcnic = c
                            .getString(c.getColumnIndex(KEY_ProxyForCnic));
                    String proxyforno = c
                            .getString(c.getColumnIndex(KEY_ProxyForNo));
                    String designation = c.getString(c.getColumnIndex(KEY_Designation));
                    String timeSince = c.getString(c.getColumnIndex(KEY_proxyTimeSince));
                    //tacherInfo.setEmisCode(emis);
                    tacherInfo.setId(id);
                    tacherInfo.setName(name);
                    tacherInfo.setCnic(cnic);
                    tacherInfo.setProxyName(proxyforname);
                    tacherInfo.setProxycnic(proxyforcnic);
                    tacherInfo.setProxyno(proxyforno);
                    tacherInfo.setDesignation(designation);
                    tacherInfo.setProxytimeSince(timeSince);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<DetailsAppointedBy> getAppointedBy(String emisCode) {
        ArrayList<DetailsAppointedBy> teacherList = new ArrayList<DetailsAppointedBy>();
        DetailsAppointedBy tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TABLE_TEACHER_APPOINTEDBY
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsAppointedBy();
                    int id = c.getInt(c
                            .getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String appointedname = c.getString(c
                            .getColumnIndex(KEY_AppointedName));
                    String appointedcnic = c.getString(c
                            .getColumnIndex(KEY_AppointedCnic));
                    String appointedby = c
                            .getString(c.getColumnIndex(KEY_AppointedBy));
                    String appointeddate = c.getString(c
                            .getColumnIndex(KEY_AppointedDate));
                    tacherInfo.setId(id);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setName(appointedname);
                    tacherInfo.setCnic(appointedcnic);
                    tacherInfo.setAppointedby(appointedby);
                    tacherInfo.setAppointedDate(appointeddate);


                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<DetailsSchoolVisit> getVisitBy(String emisCode) {
        ArrayList<DetailsSchoolVisit> teacherList = new ArrayList<DetailsSchoolVisit>();
        DetailsSchoolVisit tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TABLE_SCHOOL_VISITYBY
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsSchoolVisit();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String vistdate = c.getString(c
                            .getColumnIndex(KEY_visitDate));
                    String visitby = c
                            .getString(c.getColumnIndex(KEY_visitBy));

                    String visitothers = c
                            .getString(c.getColumnIndex(KEY_visitOther));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setVisitdate(vistdate);
                    tacherInfo.setVisitby(visitby);
                    tacherInfo.setDesignationother(visitothers);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<DetailsVacant> getVacantPosition(String emisCode) {
        ArrayList<DetailsVacant> teacherList = new ArrayList<DetailsVacant>();
        DetailsVacant tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TABLE_VACANT
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsVacant();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String vacantdesignation = c.getString(c
                            .getColumnIndex(KEY_vacantdesignation));
                    String vacantseats = c
                            .getString(c.getColumnIndex(KEY_vacantseats));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setVacantdesignation(vacantdesignation);
                    tacherInfo.setVacantseats(vacantseats);


                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<DetailsSanctionedPosts> getSanctionedPost(String emisCode) {
        ArrayList<DetailsSanctionedPosts> teacherList = new ArrayList<DetailsSanctionedPosts>();
        DetailsSanctionedPosts tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TABLE_SanctionedTeaching
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsSanctionedPosts();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String code = c.getString(c
                            .getColumnIndex(KEY_teaching_positioncode));
                    String desig = c
                            .getString(c.getColumnIndex(KEY_teaching_designation));
                    String subject = c
                            .getString(c.getColumnIndex(KEY_teaching_subject));
                    String bps = c.getString(c
                            .getColumnIndex(KEY_teaching_bps));
                    String ooother = c.getString(c
                            .getColumnIndex(KEY_teaching_specifyother));
                    String noOf = c
                            .getString(c.getColumnIndex(KEY_teaching_noofsanctioned));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setPositioncode(code);
                    tacherInfo.setDesignation(desig);
                    tacherInfo.setSubject(subject);
                    tacherInfo.setBPS(bps);
                    tacherInfo.setSpecifyothers(ooother);
                    tacherInfo.setNoOfSanctionedPosts(noOf);


                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public ArrayList<DetailsSanctionedNonteachingPosts> getNonTeachingSanctionedPost(String emisCode) {
        ArrayList<DetailsSanctionedNonteachingPosts> teacherList = new ArrayList<DetailsSanctionedNonteachingPosts>();
        DetailsSanctionedNonteachingPosts tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + TABLE_SanctionedNonTeaching
                + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsSanctionedNonteachingPosts();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String code = c.getString(c
                            .getColumnIndex(KEY_Nonteaching_positioncode));
                    String desig = c
                            .getString(c.getColumnIndex(KEY_Nonteaching_designation));
                    String bps = c.getString(c
                            .getColumnIndex(KEY_Nonteaching_bps));
                    String oother = c.getString(c
                            .getColumnIndex(NonKEY_teaching_specifyother));
                    String noOf = c
                            .getString(c.getColumnIndex(KEY_Nonteaching_noofsanctioned));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setPositioncode(code);
                    tacherInfo.setDesignation(desig);
                    tacherInfo.setBPS(bps);
                    tacherInfo.setSpecifyothers(oother);
                    tacherInfo.setNoOfSanctionedPosts(noOf);


                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }

    public DetailsEnrollmentAgeBoys getEAgeboys(String emis, String className) {
        DetailsEnrollmentAgeBoys eageboys = new DetailsEnrollmentAgeBoys();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableEnrollmentAgeBoys + " WHERE " + KEY_Emis + " = " + emis
                + " AND " + EAageboysclassName + " = " + "'" + className + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String age3 = c.getString(c.getColumnIndex(EAageboys3));
                    String age4 = c.getString(c.getColumnIndex(EAageboys4));
                    String age5 = c.getString(c.getColumnIndex(EAageboys5));
                    String age6 = c.getString(c.getColumnIndex(EAageboys6));
                    String age7 = c.getString(c.getColumnIndex(EAageboys7));
                    String age8 = c.getString(c.getColumnIndex(EAageboys8));
                    String age9 = c.getString(c.getColumnIndex(EAageboys9));
                    String age10 = c.getString(c.getColumnIndex(EAageboys10));
                    String age11 = c.getString(c.getColumnIndex(EAageboys11));
                    String age12 = c.getString(c.getColumnIndex(EAageboys12));
                    String age13 = c.getString(c.getColumnIndex(EAageboys13));
                    String age14 = c.getString(c.getColumnIndex(EAageboys14));
                    String age15 = c.getString(c.getColumnIndex(EAageboys15));
                    String age16 = c.getString(c.getColumnIndex(EAageboys16));
                    String age17 = c.getString(c.getColumnIndex(EAageboys17));
                    String age18 = c.getString(c.getColumnIndex(EAageboys18));
                    String age19 = c.getString(c.getColumnIndex(EAageboys19));
                    String age20 = c.getString(c.getColumnIndex(EAageboys20));
                    String age21 = c.getString(c.getColumnIndex(EAageboys21));
                    String agerepeaters = c.getString(c.getColumnIndex(EAageboysrepeaters));
                    String agenonmuslims = c.getString(c.getColumnIndex(EAageboysnonmuslims));

                    eageboys.setAge3(age3);
                    eageboys.setAge4(age4);
                    eageboys.setAge5(age5);
                    eageboys.setAge6(age6);
                    eageboys.setAge7(age7);
                    eageboys.setAge8(age8);
                    eageboys.setAge9(age9);
                    eageboys.setAge10(age10);
                    eageboys.setAge11(age11);
                    eageboys.setAge12(age12);
                    eageboys.setAge13(age13);
                    eageboys.setAge14(age14);
                    eageboys.setAge15(age15);
                    eageboys.setAge16(age16);
                    eageboys.setAge17(age17);
                    eageboys.setAge18(age18);
                    eageboys.setAge19(age19);
                    eageboys.setAge20(age20);
                    eageboys.setAge21(age21);
                    eageboys.setRepeaters(agerepeaters);
                    eageboys.setNonmuslims(agenonmuslims);

                } while (c.moveToNext());
            }
            return eageboys;
        }
        return eageboys;
    }

    public DetailsEnrollmentAgeGirls getEAgegirls(String emis, String className) {
        DetailsEnrollmentAgeGirls eagegirls = new DetailsEnrollmentAgeGirls();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableEnrollmentAgeGirls + " WHERE " + KEY_Emis + " = " + emis
                + " AND " + EAagegirlsclassName + " = " + "'" + className + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String age3 = c.getString(c.getColumnIndex(EAagegirls3));
                    String age4 = c.getString(c.getColumnIndex(EAagegirls4));
                    String age5 = c.getString(c.getColumnIndex(EAagegirls5));
                    String age6 = c.getString(c.getColumnIndex(EAagegirls6));
                    String age7 = c.getString(c.getColumnIndex(EAagegirls7));
                    String age8 = c.getString(c.getColumnIndex(EAagegirls8));
                    String age9 = c.getString(c.getColumnIndex(EAagegirls9));
                    String age10 = c.getString(c.getColumnIndex(EAagegirls10));
                    String age11 = c.getString(c.getColumnIndex(EAagegirls11));
                    String age12 = c.getString(c.getColumnIndex(EAagegirls12));
                    String age13 = c.getString(c.getColumnIndex(EAagegirls13));
                    String age14 = c.getString(c.getColumnIndex(EAagegirls14));
                    String age15 = c.getString(c.getColumnIndex(EAagegirls15));
                    String age16 = c.getString(c.getColumnIndex(EAagegirls16));
                    String age17 = c.getString(c.getColumnIndex(EAagegirls17));
                    String age18 = c.getString(c.getColumnIndex(EAagegirls18));
                    String age19 = c.getString(c.getColumnIndex(EAagegirls19));
                    String age20 = c.getString(c.getColumnIndex(EAagegirls20));
                    String age21 = c.getString(c.getColumnIndex(EAagegirls21));
                    String agerepeaters = c.getString(c.getColumnIndex(EAagegirlsrepeaters));
                    String agenonmuslims = c.getString(c.getColumnIndex(EAagegirlsnonmuslims));

                    eagegirls.setAge3(age3);
                    eagegirls.setAge4(age4);
                    eagegirls.setAge5(age5);
                    eagegirls.setAge6(age6);
                    eagegirls.setAge7(age7);
                    eagegirls.setAge8(age8);
                    eagegirls.setAge9(age9);
                    eagegirls.setAge10(age10);
                    eagegirls.setAge11(age11);
                    eagegirls.setAge12(age12);
                    eagegirls.setAge13(age13);
                    eagegirls.setAge14(age14);
                    eagegirls.setAge15(age15);
                    eagegirls.setAge16(age16);
                    eagegirls.setAge17(age17);
                    eagegirls.setAge18(age18);
                    eagegirls.setAge19(age19);
                    eagegirls.setAge20(age20);
                    eagegirls.setAge21(age21);
                    eagegirls.setRepeaters(agerepeaters);
                    eagegirls.setNonmuslims(agenonmuslims);

                } while (c.moveToNext());
            }
            return eagegirls;
        }
        return eagegirls;
    }


    public DetailsEnrollmentSpecialBoys getSpecialboys(String emis, String className) {
        DetailsEnrollmentSpecialBoys specialboys = new DetailsEnrollmentSpecialBoys();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableEnrollmentSpecialBoys + " WHERE " + KEY_Emis + " = " + emis
                + " AND " + SpecialBoysClassName + " = " + "'" + className + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String fullvisual = c.getString(c.getColumnIndex(full_visual));
                    String partialvisual = c.getString(c.getColumnIndex(partial_visual));
                    String fullhearing = c.getString(c.getColumnIndex(full_hearing));
                    String partialhearing = c.getString(c.getColumnIndex(partial_hearing));
                    String fullspeech = c.getString(c.getColumnIndex(full_speech));
                    String partialspeech = c.getString(c.getColumnIndex(partial_speech));
                    String handarm = c.getString(c.getColumnIndex(hand_arm));
                    String legfoot = c.getString(c.getColumnIndex(leg_foot));
                    String mental_str = c.getString(c.getColumnIndex(mentalstr));

                    specialboys.setFullVisual(fullvisual);
                    specialboys.setPartialVisual(partialvisual);
                    specialboys.setFullHearing(fullhearing);
                    specialboys.setPartialHearing(partialhearing);
                    specialboys.setFullSpeech(fullspeech);
                    specialboys.setPartialSpeech(partialspeech);
                    specialboys.setHandarm(handarm);
                    specialboys.setLegfoot(legfoot);
                    specialboys.setMental(mental_str);

                } while (c.moveToNext());
            }
            return specialboys;
        }
        return specialboys;
    }

    public DetailsEnrollmentSpecialGirls getSpecialgirls(String emis, String className) {
        DetailsEnrollmentSpecialGirls specialgirls = new DetailsEnrollmentSpecialGirls();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableEnrollmentSpecialGirls + " WHERE " + KEY_Emis + " = " + emis
                + " AND " + SpecialGirlsClassName + " = " + "'" + className + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String fullvisual = c.getString(c.getColumnIndex(Girlsfull_visual));
                    String partialvisual = c.getString(c.getColumnIndex(Girlspartial_visual));
                    String fullhearing = c.getString(c.getColumnIndex(Girlsfull_hearing));
                    String partialhearing = c.getString(c.getColumnIndex(Girlspartial_hearing));
                    String fullspeech = c.getString(c.getColumnIndex(Girlsfull_speech));
                    String partialspeech = c.getString(c.getColumnIndex(Girlspartial_speech));
                    String handarm = c.getString(c.getColumnIndex(Girlshand_arm));
                    String legfoot = c.getString(c.getColumnIndex(Girlsleg_foot));
                    String mental_str = c.getString(c.getColumnIndex(Girlsmentalstr));

                    specialgirls.setFullVisual(fullvisual);
                    specialgirls.setPartialVisual(partialvisual);
                    specialgirls.setFullHearing(fullhearing);
                    specialgirls.setPartialHearing(partialhearing);
                    specialgirls.setFullSpeech(fullspeech);
                    specialgirls.setPartialSpeech(partialspeech);
                    specialgirls.setHandarm(handarm);
                    specialgirls.setLegfoot(legfoot);
                    specialgirls.setMental(mental_str);

                } while (c.moveToNext());
            }
            return specialgirls;
        }
        return specialgirls;
    }

    public ArrayList<DetailsEnrollmentAgeBoys> getEAgeboysxml(String emis) {
        ArrayList<DetailsEnrollmentAgeBoys> eageboysList = new ArrayList<DetailsEnrollmentAgeBoys>();
        DetailsEnrollmentAgeBoys eageboys;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableEnrollmentAgeBoys + " WHERE " + KEY_Emis + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    eageboys = new DetailsEnrollmentAgeBoys();
                    String classname = c.getString(c.getColumnIndex(EAageboysclassName));
                    String age3 = c.getString(c.getColumnIndex(EAageboys3));
                    String age4 = c.getString(c.getColumnIndex(EAageboys4));
                    String age5 = c.getString(c.getColumnIndex(EAageboys5));
                    String age6 = c.getString(c.getColumnIndex(EAageboys6));
                    String age7 = c.getString(c.getColumnIndex(EAageboys7));
                    String age8 = c.getString(c.getColumnIndex(EAageboys8));
                    String age9 = c.getString(c.getColumnIndex(EAageboys9));
                    String age10 = c.getString(c.getColumnIndex(EAageboys10));
                    String age11 = c.getString(c.getColumnIndex(EAageboys11));
                    String age12 = c.getString(c.getColumnIndex(EAageboys12));
                    String age13 = c.getString(c.getColumnIndex(EAageboys13));
                    String age14 = c.getString(c.getColumnIndex(EAageboys14));
                    String age15 = c.getString(c.getColumnIndex(EAageboys15));
                    String age16 = c.getString(c.getColumnIndex(EAageboys16));
                    String age17 = c.getString(c.getColumnIndex(EAageboys17));
                    String age18 = c.getString(c.getColumnIndex(EAageboys18));
                    String age19 = c.getString(c.getColumnIndex(EAageboys19));
                    String age20 = c.getString(c.getColumnIndex(EAageboys20));
                    String age21 = c.getString(c.getColumnIndex(EAageboys21));
                    String agerepeaters = c.getString(c.getColumnIndex(EAageboysrepeaters));
                    String agenonmuslims = c.getString(c.getColumnIndex(EAageboysnonmuslims));

                    eageboys.setClassName(classname);
                    eageboys.setAge3(age3);
                    eageboys.setAge4(age4);
                    eageboys.setAge5(age5);
                    eageboys.setAge6(age6);
                    eageboys.setAge7(age7);
                    eageboys.setAge8(age8);
                    eageboys.setAge9(age9);
                    eageboys.setAge10(age10);
                    eageboys.setAge11(age11);
                    eageboys.setAge12(age12);
                    eageboys.setAge13(age13);
                    eageboys.setAge14(age14);
                    eageboys.setAge15(age15);
                    eageboys.setAge16(age16);
                    eageboys.setAge17(age17);
                    eageboys.setAge18(age18);
                    eageboys.setAge19(age19);
                    eageboys.setAge20(age20);
                    eageboys.setAge21(age21);
                    eageboys.setRepeaters(agerepeaters);
                    eageboys.setNonmuslims(agenonmuslims);


                    eageboysList.add(eageboys);

                } while (c.moveToNext());
            }

            return eageboysList;

        }

        return eageboysList;

    }

    public ArrayList<DetailsEnrollmentAgeGirls> getEAgegirlsxml(String emis) {
        ArrayList<DetailsEnrollmentAgeGirls> eagegirlsList = new ArrayList<DetailsEnrollmentAgeGirls>();
        DetailsEnrollmentAgeGirls eagegirls;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableEnrollmentAgeGirls + " WHERE " + KEY_Emis + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    eagegirls = new DetailsEnrollmentAgeGirls();
                    String classname = c.getString(c.getColumnIndex(EAagegirlsclassName));
                    String age3 = c.getString(c.getColumnIndex(EAagegirls3));
                    String age4 = c.getString(c.getColumnIndex(EAagegirls4));
                    String age5 = c.getString(c.getColumnIndex(EAagegirls5));
                    String age6 = c.getString(c.getColumnIndex(EAagegirls6));
                    String age7 = c.getString(c.getColumnIndex(EAagegirls7));
                    String age8 = c.getString(c.getColumnIndex(EAagegirls8));
                    String age9 = c.getString(c.getColumnIndex(EAagegirls9));
                    String age10 = c.getString(c.getColumnIndex(EAagegirls10));
                    String age11 = c.getString(c.getColumnIndex(EAagegirls11));
                    String age12 = c.getString(c.getColumnIndex(EAagegirls12));
                    String age13 = c.getString(c.getColumnIndex(EAagegirls13));
                    String age14 = c.getString(c.getColumnIndex(EAagegirls14));
                    String age15 = c.getString(c.getColumnIndex(EAagegirls15));
                    String age16 = c.getString(c.getColumnIndex(EAagegirls16));
                    String age17 = c.getString(c.getColumnIndex(EAagegirls17));
                    String age18 = c.getString(c.getColumnIndex(EAagegirls18));
                    String age19 = c.getString(c.getColumnIndex(EAagegirls19));
                    String age20 = c.getString(c.getColumnIndex(EAagegirls20));
                    String age21 = c.getString(c.getColumnIndex(EAagegirls21));
                    String agerepeaters = c.getString(c.getColumnIndex(EAagegirlsrepeaters));
                    String agenonmuslims = c.getString(c.getColumnIndex(EAagegirlsnonmuslims));

                    eagegirls.setClassName(classname);
                    eagegirls.setAge3(age3);
                    eagegirls.setAge4(age4);
                    eagegirls.setAge5(age5);
                    eagegirls.setAge6(age6);
                    eagegirls.setAge7(age7);
                    eagegirls.setAge8(age8);
                    eagegirls.setAge9(age9);
                    eagegirls.setAge10(age10);
                    eagegirls.setAge11(age11);
                    eagegirls.setAge12(age12);
                    eagegirls.setAge13(age13);
                    eagegirls.setAge14(age14);
                    eagegirls.setAge15(age15);
                    eagegirls.setAge16(age16);
                    eagegirls.setAge17(age17);
                    eagegirls.setAge18(age18);
                    eagegirls.setAge19(age19);
                    eagegirls.setAge20(age20);
                    eagegirls.setAge21(age21);
                    eagegirls.setRepeaters(agerepeaters);
                    eagegirls.setNonmuslims(agenonmuslims);


                    eagegirlsList.add(eagegirls);

                } while (c.moveToNext());
            }

            return eagegirlsList;

        }

        return eagegirlsList;

    }


    public ArrayList<DetailsEnrollmentSpecialBoys> getspecialboysxml(String emis) {
        ArrayList<DetailsEnrollmentSpecialBoys> specialboysList = new ArrayList<DetailsEnrollmentSpecialBoys>();
        DetailsEnrollmentSpecialBoys specialboys;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableEnrollmentSpecialBoys + " WHERE " + KEY_Emis + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    specialboys = new DetailsEnrollmentSpecialBoys();
                    String classname = c.getString(c.getColumnIndex(SpecialBoysClassName));
                    String fullvisual = c.getString(c.getColumnIndex(full_visual));
                    String partialvisual = c.getString(c.getColumnIndex(partial_visual));
                    String fullhearing = c.getString(c.getColumnIndex(full_hearing));
                    String partialhearing = c.getString(c.getColumnIndex(partial_hearing));
                    String fullspeech = c.getString(c.getColumnIndex(full_speech));
                    String partialspeech = c.getString(c.getColumnIndex(partial_speech));
                    String handarm = c.getString(c.getColumnIndex(hand_arm));
                    String legfoot = c.getString(c.getColumnIndex(leg_foot));
                    String mental_str = c.getString(c.getColumnIndex(mentalstr));

                    specialboys.setClassName(classname);
                    specialboys.setFullVisual(fullvisual);
                    specialboys.setPartialVisual(partialvisual);
                    specialboys.setFullHearing(fullhearing);
                    specialboys.setPartialHearing(partialhearing);
                    specialboys.setFullSpeech(fullspeech);
                    specialboys.setPartialSpeech(partialspeech);
                    specialboys.setHandarm(handarm);
                    specialboys.setLegfoot(legfoot);
                    specialboys.setMental(mental_str);


                    specialboysList.add(specialboys);

                } while (c.moveToNext());
            }

            return specialboysList;

        }

        return specialboysList;

    }

    public ArrayList<DetailsEnrollmentSpecialGirls> getspecialgirlsxml(String emis) {
        ArrayList<DetailsEnrollmentSpecialGirls> specialgirlsList = new ArrayList<DetailsEnrollmentSpecialGirls>();
        DetailsEnrollmentSpecialGirls specialgirls;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableEnrollmentSpecialGirls + " WHERE " + KEY_Emis + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    specialgirls = new DetailsEnrollmentSpecialGirls();
                    String classname = c.getString(c.getColumnIndex(SpecialGirlsClassName));
                    String fullvisual = c.getString(c.getColumnIndex(Girlsfull_visual));
                    String partialvisual = c.getString(c.getColumnIndex(Girlspartial_visual));
                    String fullhearing = c.getString(c.getColumnIndex(Girlsfull_hearing));
                    String partialhearing = c.getString(c.getColumnIndex(Girlspartial_hearing));
                    String fullspeech = c.getString(c.getColumnIndex(Girlsfull_speech));
                    String partialspeech = c.getString(c.getColumnIndex(Girlspartial_speech));
                    String handarm = c.getString(c.getColumnIndex(Girlshand_arm));
                    String legfoot = c.getString(c.getColumnIndex(Girlsleg_foot));
                    String mental_str = c.getString(c.getColumnIndex(Girlsmentalstr));
                    specialgirls.setClassName(classname);
                    specialgirls.setFullVisual(fullvisual);
                    specialgirls.setPartialVisual(partialvisual);
                    specialgirls.setFullHearing(fullhearing);
                    specialgirls.setPartialHearing(partialhearing);
                    specialgirls.setFullSpeech(fullspeech);
                    specialgirls.setPartialSpeech(partialspeech);
                    specialgirls.setHandarm(handarm);
                    specialgirls.setLegfoot(legfoot);
                    specialgirls.setMental(mental_str);


                    specialgirlsList.add(specialgirls);

                } while (c.moveToNext());
            }

            return specialgirlsList;

        }

        return specialgirlsList;

    }



    public DetailsEnrollmentAttendaanceGap getEAG(String emis, String className) {
        DetailsEnrollmentAttendaanceGap eag = new DetailsEnrollmentAttendaanceGap();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableEnrollmentAttendanceGap + " WHERE " + KEY_Emis + " = " + emis
                + " AND " + EAGclassName + " = " + "'" + className + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String stdEnrolled = c.getString(c.getColumnIndex(EAGstudentsEnrolled));
                    String stdperHead = c.getString(c.getColumnIndex(EAGstudentsPresentHead));
                    String stdperRegister = c.getString(c.getColumnIndex(EAGstudentsPresentRegister));
                    String eagGirlsEnrolled = c.getString(c.getColumnIndex(EAGgirlsEnrolled));
                    String eagBoysEnrolled = c.getString(c.getColumnIndex(EAGboysEnrolled));

                    eag.setStudentsEnrolled(stdEnrolled);
                    eag.setStudentsPresentHead(stdperHead);
                    eag.setStudentsPresentRegister(stdperRegister);
                    eag.setGirlsEnrolled(eagGirlsEnrolled);
                    eag.setBoysEnrolled(eagBoysEnrolled);

                } while (c.moveToNext());
            }

            return eag;

        }

        return eag;


    }



    public ArrayList<DetailsEnrollmentAttendaanceGap> geteagxml(String emis) {
        ArrayList<DetailsEnrollmentAttendaanceGap> eagList = new ArrayList<DetailsEnrollmentAttendaanceGap>();
        DetailsEnrollmentAttendaanceGap details;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableEnrollmentAttendanceGap + " WHERE " + KEY_Emis + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    details = new DetailsEnrollmentAttendaanceGap();

                    String className = c.getString(c.getColumnIndex(EAGclassName));
                    String stdenrolled = c.getString(c.getColumnIndex(EAGstudentsEnrolled));
                    String stdhead = c.getString(c.getColumnIndex(EAGstudentsPresentHead));
                    String stdregister = c.getString(c.getColumnIndex(EAGstudentsPresentRegister));

                    String girlsenrolled = c.getString(c.getColumnIndex(EAGgirlsEnrolled));
                    String boysenrolled = c.getString(c.getColumnIndex(EAGboysEnrolled));

                    details.setClassName(className);
                    details.setStudentsEnrolled(stdenrolled);
                    details.setStudentsPresentHead(stdhead);
                    details.setStudentsPresentRegister(stdregister);
                    details.setGirlsEnrolled(girlsenrolled);
                    details.setBoysEnrolled(boysenrolled);


                    eagList.add(details);

                } while (c.moveToNext());
            }

            return eagList;

        }

        return eagList;

    }

    public Grant getgrant(String emis, String type, String year, String amount, int grantIdd) {
        Grant grant = new Grant();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableGrant + " WHERE " + grantImeiCode + " = " + emis + " AND " + grantType
                + " = " + "'" + type + "'" + " AND " + grantYear
                + " = " + "'" + year + "'" + " AND " + grantAmount
                + " = " + "'" + amount + "'" + " AND " + grantId + " = " + "'" + grantIdd + "'";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    String startdate = c.getString(c.getColumnIndex(grantdate));
                    String financial = c.getString(c.getColumnIndex(grantfinancial));
                    String typee = c.getString(c.getColumnIndex(granttypespiner));
                    String workstatus = c.getString(c.getColumnIndex(grantworkstatus));
                    String workgrading = c.getString(c.getColumnIndex(grantgrading));
                    String remarks = c.getString(c.getColumnIndex(grantremarks));
                    String recordshown = c.getString(c.getColumnIndex(grantrecord));

                    String funds = c.getString(c.getColumnIndex(grantfundsreceived));
                    String amountcorrect = c.getString(c.getColumnIndex(grantammountcorrect));
                    String amountenter = c.getString(c.getColumnIndex(grantamountenter));

                    grant.setStartDate(startdate);
                    grant.setFinancial(financial);
                    grant.setType(typee);
                    grant.setWorkStatus(workstatus);
                    grant.setWorkGrading(workgrading);
                    grant.setRemarks(remarks);
                    grant.setRecordStatus(recordshown);
                    grant.setFundsReceived(funds);
                    grant.setAmmountCorrect(amountcorrect);
                    grant.setAmmountEnter(amountenter);

                } while (c.moveToNext());
            }

            return grant;

        }

        return grant;


    }

    public ArrayList<Grant> getgrantxml(String emis) {
        ArrayList<Grant> grantList = new ArrayList<Grant>();
        Grant grantInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableGrant + " WHERE " + grantImeiCode + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(query, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    grantInfo = new Grant();

                    String amount = c.getString(c.getColumnIndex(grantAmount));
                    String year = c.getString(c.getColumnIndex(grantYear));
                    int grantid = c.getInt(c.getColumnIndex(grantId));
                    String type = c.getString(c.getColumnIndex(grantType));

                    String startdate = c.getString(c.getColumnIndex(grantdate));
                    String financial = c.getString(c.getColumnIndex(grantfinancial));
                    String typee = c.getString(c.getColumnIndex(granttypespiner));
                    String workstatus = c.getString(c.getColumnIndex(grantworkstatus));
                    String workgrading = c.getString(c.getColumnIndex(grantgrading));
                    String remarks = c.getString(c.getColumnIndex(grantremarks));
                    String recordshown = c.getString(c.getColumnIndex(grantrecord));

                    String funds = c.getString(c.getColumnIndex(grantfundsreceived));
                    String amountcorrect = c.getString(c.getColumnIndex(grantammountcorrect));
                    String amountenter = c.getString(c.getColumnIndex(grantamountenter));


                    grantInfo.setAmount(amount);
                    grantInfo.setYear(year);
                    grantInfo.setFaceCode(grantid);
                    grantInfo.setGrantType(type);
                    grantInfo.setStartDate(startdate);
                    grantInfo.setFinancial(financial);
                    grantInfo.setType(typee);
                    grantInfo.setWorkStatus(workstatus);
                    grantInfo.setWorkGrading(workgrading);
                    grantInfo.setRemarks(remarks);
                    grantInfo.setRecordStatus(recordshown);

                    grantInfo.setFundsReceived(funds);
                    grantInfo.setAmmountCorrect(amountcorrect);
                    grantInfo.setAmmountEnter(amountenter);


                    grantList.add(grantInfo);

                } while (c.moveToNext());
            }

            return grantList;

        }

        return grantList;

    }


    public List<String> getAllLabels(String emis) {
        List<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + TableTeacherInfo + " WHERE " + KEY_Emis + " = " + "'" + emis + "'";
        ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(2);
                String cnic = cursor.getString(9);
                labels.add(name + " : " + cnic);
                //labels.add(cursor.getString(9));
                //labels.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public String getSubject(String className) {
        ArrayList<SubjectClassModel> teacherList = new ArrayList<SubjectClassModel>();
        SubjectClassModel tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String subject = "Null";
        String selectQuery = "SELECT * " + " FROM " + TableSubjectconfiguration
                + " WHERE " + ClassName + " = " + "'" + className + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new SubjectClassModel();
                    subject = c.getString(c.getColumnIndex(Subjects));
                    tacherInfo.setSubjectName(subject);
                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return subject;

        }

        return subject;

    }

    public ArrayList<DetailsTeacherwebservice> teacherwebserviceList(String emisCode) {
        ArrayList<DetailsTeacherwebservice> teacherList = new ArrayList<DetailsTeacherwebservice>();
        DetailsTeacherwebservice tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + Teacherwebservice + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsTeacherwebservice();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String TeacherNamee = c.getString(c.getColumnIndex(KEY_TeacherNamewebservice));
                    String Teachergender = c.getString(c.getColumnIndex(KEY_TeacherGenderwebservice));
                    String Teacherno = c.getString(c.getColumnIndex(KEY_TeacherNowebservice));
                    String Teachercnicno = c.getString(c.getColumnIndex(KEY_TeacherCnicwebservice));
                    String Teacheratt = c.getString(c.getColumnIndex(KEY_TeacherAttendancewebservice));
                    String Teacherattdetails = c.getString(c.getColumnIndex(KEY_TeacherAttdetailswebservice));
                    String Teacherattdatesince = c.getString(c.getColumnIndex(KEY_TeacherAttendanceDatesinceWeb));
                    String Teacheratttransferout = c.getString(c.getColumnIndex(KEY_TeacherAttendanceTransferSchoolWeb));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setTeachername(TeacherNamee);
                    tacherInfo.setTeachergender(Teachergender);
                    tacherInfo.setTeachercnic(Teachercnicno);
                    tacherInfo.setTeacherno(Teacherno);
                    tacherInfo.setAttendance(Teacheratt);
                    tacherInfo.setTeacherattendancedetails(Teacherattdetails);
                    tacherInfo.setAttendancedatesince(Teacherattdatesince);
                    tacherInfo.setAttendancetrasnferschool(Teacheratttransferout);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }


    public ArrayList<DetailsNonTeacherwebservice> NONteacherwebserviceList(String emisCode) {
        ArrayList<DetailsNonTeacherwebservice> teacherList = new ArrayList<DetailsNonTeacherwebservice>();
        DetailsNonTeacherwebservice tacherInfo;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + NonTeacherwebservice + " WHERE " + KEY_Emis + " = " + "'" + emisCode + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    tacherInfo = new DetailsNonTeacherwebservice();
                    int idd = c.getInt(c.getColumnIndex(KEY_ID));
                    String emis = c.getString(c.getColumnIndex(KEY_Emis));
                    String TeacherNamee = c.getString(c.getColumnIndex(KEY_NONTeacherNamewebservice));
                    String Teachergender = c.getString(c.getColumnIndex(KEY_NONTeacherGenderwebservice));
                    String Teacherno = c.getString(c.getColumnIndex(KEY_NONTeacherNowebservice));
                    String Teachercnicno = c.getString(c.getColumnIndex(KEY_NONTeacherCnicwebservice));
                    String Teacheratt = c.getString(c.getColumnIndex(KEY_NONTeacherAttendancewebservice));
                    String Teacherattdetails = c.getString(c.getColumnIndex(KEY_NONTeacherAttdetailswebservice));
                    String Teacherattdatesince = c.getString(c.getColumnIndex(KEY_NONTeacherAttendanceDatesinceWeb));
                    String Teacheratttransferout = c.getString(c.getColumnIndex(KEY_NONTeacherAttendanceTransferSchoolWeb));
                    tacherInfo.setId(idd);
                    tacherInfo.setEmisCode(emis);
                    tacherInfo.setTeachername(TeacherNamee);
                    tacherInfo.setTeachergender(Teachergender);
                    tacherInfo.setTeachercnic(Teachercnicno);
                    tacherInfo.setTeacherno(Teacherno);
                    tacherInfo.setAttendance(Teacheratt);
                    tacherInfo.setTeacherattendancedetails(Teacherattdetails);
                    tacherInfo.setAttendancedatesince(Teacherattdatesince);
                    tacherInfo.setAttendancetrasnferschool(Teacheratttransferout);

                    teacherList.add(tacherInfo);

                } while (c.moveToNext());
            }

            return teacherList;

        }

        return teacherList;

    }
}

    /*public ArrayList<ArrayList<Object>> def() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db=this.getReadableDatabase();
            cur = db.query(TABLE_SchoolInfo, new String[] { KEY_SchoolName, KEY_level, KEY_Pkno, KEY_Nano, KEY_UCName, KEY_circleofficeno },
                    null, null,null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));
                    dataList.add(cur.getString(5));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }

    public ArrayList<ArrayList<Object>> abcd() {

        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            SQLiteDatabase db=this.getReadableDatabase();
            cur = db.query(TABLE_TInfo, new String[] { KEY_Name, KEY_Cnic, KEY_Pno, KEY_attendance },
                    null, null,null, null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getString(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataArray.add(dataList);
                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;
    }
}*/

