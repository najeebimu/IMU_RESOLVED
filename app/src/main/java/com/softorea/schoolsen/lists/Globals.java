package com.softorea.schoolsen.lists;

/**
 * Created by arsla on 16/10/2017.
 */


public class Globals {

    public static final String GlobalUrl = "http://175.107.63.45:18080";
    public static final String FTP_HOST = "54.84.180.194";
    public static final String FTP_USER = "surveyFTP";
    public static final String FTP_PASS = "SuRvEyFtPNoMoreABC@12436";
    public static final int Port = 21;
    public static final String faild_path = "/.sampledata/" + "failed" + "/";
    public static final int UPDATE_INTERVAL = 1 * 1000;
    public static final int FATEST_INTERVAL = 20;
    public static final int DISPLACEMENT = 1;
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String TickerText = "Form Started";
    public static final String notificationTitleText = "Imu Collect";
    public static final String notificationBodyText = "Form Started";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "SampleDataBase";
    public static final String saveObjPath = "/sdcard/save_object.bin";

    /* urls */
    public static final String Profile = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=getMonitorProfileNew";
    public static final String Post_profile_url = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=postMonitorProfile";
    public static final String update_Saved_Form = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=updateSavedSchools_v1";
    public static final String urlGetRoaster = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=getRoaster";
    public static final String urlGetTeacherInfo = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=getTeacherInformation_new";
    public static final String urlGetNonTeacherInfo = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=getNonTeacherInformation_new";
    public static final String urlGCSTeacherInfo = "http://175.107.63.45/IMUWS/IMU.svc//GetTeacherEEF/";
    public static final String urlGetSchoolInfoTask = "http://175.107.63.45/IMUWS/IMU.svc//GetSchoolInformation/";
    public static final String urlSubject = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=getSubjectInformation";
    public static final String urlGrant = "http://cg.kesp.com.pk/Services/api/Values/PostSchoolAllocation";
    public static final String urlScreenConfig = "http://175.107.63.45/IMUWS/IMU.svc//GetScreenconfiguration/";
    public static final String urlRegisterComplaint = GlobalUrl + "/IMU_WebService/IMU_Servlet?_f=insertComplaints";
    public static final String urlFeedBack = "http://175.107.63.45/IMUWS/IMU.svc/GetReplies/";


}

