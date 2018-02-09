package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsNonTeacherwebservice {
    private int id;
    private String emisCode;
    private String teachername;
    private String teachergender;
    private String teacherno;
    private String teachercnic;
    private String teacherattendance;
    private String teacherattendancedetails;
    private String attendancedatesince;
    private String attendancetrasnferschool;
    public DetailsNonTeacherwebservice() {
    }

    public DetailsNonTeacherwebservice(String emisCode, String teachername, String teachergender, String teacherno,
                                       String teachercnic, String teacherattendance, String teacherattendancedetails,
                                       String attendancedatesince, String attendancetrasnferschool) {
        this.emisCode = emisCode;
        this.teachername = teachername;
        this.teachergender = teachergender;
        this.teacherno = teacherno;
        this.teachercnic = teachercnic;
        this.teacherattendance = teacherattendance;
        this.teacherattendancedetails = teacherattendancedetails;
        this.attendancedatesince = attendancedatesince;
        this.attendancetrasnferschool = attendancetrasnferschool;
    }

    public DetailsNonTeacherwebservice(int id, String emisCode, String teachername, String teachergender, String teacherno,
                                       String teachercnic, String teacherattendance, String teacherattendancedetails,
                                       String attendancedatesince, String attendancetrasnferschool) {
        this.id = id;
        this.emisCode = emisCode;
        this.teachername = teachername;
        this.teachergender = teachergender;
        this.teacherno = teacherno;
        this.teachercnic = teachercnic;
        this.teacherattendance = teacherattendance;
        this.teacherattendancedetails = teacherattendancedetails;
        this.attendancedatesince = attendancedatesince;
        this.attendancetrasnferschool = attendancetrasnferschool;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmisCode() {
        return emisCode;
    }

    public void setEmisCode(String emisCode) {
        this.emisCode = emisCode;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeachergender() {
        return teachergender;
    }

    public void setTeachergender(String teachergender) {
        this.teachergender = teachergender;
    }

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno;
    }

    public String getTeachercnic() {
        return teachercnic;
    }

    public void setTeachercnic(String teachercnic) {
        this.teachercnic = teachercnic;
    }

    public String getAttendance() {
        return teacherattendance;
    }

    public void setAttendance(String teacherattendance) {
        this.teacherattendance = teacherattendance;
    }

    public String getTeacherattendancedetails() {
        return teacherattendancedetails;
    }

    public void setTeacherattendancedetails(String teacherattendancedetails) {
        this.teacherattendancedetails = teacherattendancedetails;
    }

    public String getAttendancedatesince() {
        return attendancedatesince;
    }

    public void setAttendancedatesince(String attendancedatesince) {
        this.attendancedatesince = attendancedatesince;
    }

    public String getAttendancetrasnferschool() {
        return attendancetrasnferschool;
    }

    public void setAttendancetrasnferschool(String attendancetrasnferschool) {
        this.attendancetrasnferschool = attendancetrasnferschool;
    }
}
