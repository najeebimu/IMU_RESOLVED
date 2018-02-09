package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class TeacherNewDetails {
    private int id;
    private String emisCode;
    private String teachername;
    private String teacherfathername;
    private String teachergender;
    private String teachermarital;
    private String teacherbps;
    private String teacherno;
    private String teacheraccountno;
    private String teachercnic;
    private String teacherdob;
    private String teacherhighestlevel;
    private String teacherhigestsubject;
    private String teacherdateoffirst;
    private String teacherdistrict;
    private String teacherhighestqualification;
    private String teacherdesigasfirst;
    private String designation;
    private String teacheruc;
    private String teacheranydisablity;
    private String teachertypedisablity;
    private String attendance;
    private String attendanceTrasnferIn;
    private String teacherattendancedetails;
    private String attendancedatesince;
    private String attendancetrasnferschool;

    public TeacherNewDetails() {
    }

    public TeacherNewDetails(String emisCode, String teachername, String teacherfathername, String teachergender, String teachermarital,
                             String teacherbps, String teacherno, String teacheraccountno, String teachercnic, String teacherdob,
                             String teacherhighestlevel, String teacherhigestsubject, String teacherdateoffirst, String teacherdistrict,
                             String teacherhighestqualification, String teacherdesigasfirst, String designation, String teacheruc,
                             String teacheranydisablity, String teachertypedisablity, String attendance, String attendanceTrasnferIn, String teacherattendancedetails,
                             String attendancedatesince, String attendancetrasnferschool) {
        this.emisCode = emisCode;
        this.teachername = teachername;
        this.teacherfathername = teacherfathername;
        this.teachergender = teachergender;
        this.teachermarital = teachermarital;
        this.teacherbps = teacherbps;
        this.teacherno = teacherno;
        this.teacheraccountno = teacheraccountno;
        this.teachercnic = teachercnic;
        this.teacherdob = teacherdob;
        this.teacherhighestlevel = teacherhighestlevel;
        this.teacherhigestsubject = teacherhigestsubject;
        this.teacherdateoffirst = teacherdateoffirst;
        this.teacherdistrict = teacherdistrict;
        this.teacherhighestqualification = teacherhighestqualification;
        this.teacherdesigasfirst = teacherdesigasfirst;
        this.designation = designation;
        this.teacheruc = teacheruc;
        this.teacheranydisablity = teacheranydisablity;
        this.teachertypedisablity = teachertypedisablity;
        this.attendance = attendance;
        this.attendanceTrasnferIn = attendanceTrasnferIn;
        this.teacherattendancedetails = teacherattendancedetails;
        this.attendancedatesince = attendancedatesince;
        this.attendancetrasnferschool = attendancetrasnferschool;
    }

    public TeacherNewDetails(int id, String emisCode, String teachername, String teacherfathername, String teachergender, String teachermarital,
                             String teacherbps, String teacherno, String teacheraccountno, String teachercnic, String teacherdob,
                             String teacherhighestlevel, String teacherhigestsubject, String teacherdateoffirst, String teacherdistrict,
                             String teacherhighestqualification, String teacherdesigasfirst, String designation, String teacheruc,
                             String teacheranydisablity, String teachertypedisablity, String attendance, String attendanceTrasnferIn, String teacherattendancedetails,
                             String attendancedatesince, String attendancetrasnferschool) {
        this.id = id;
        this.emisCode = emisCode;
        this.teachername = teachername;
        this.teacherfathername = teacherfathername;
        this.teachergender = teachergender;
        this.teachermarital = teachermarital;
        this.teacherbps = teacherbps;
        this.teacherno = teacherno;
        this.teacheraccountno = teacheraccountno;
        this.teachercnic = teachercnic;
        this.teacherdob = teacherdob;
        this.teacherhighestlevel = teacherhighestlevel;
        this.teacherhigestsubject = teacherhigestsubject;
        this.teacherdateoffirst = teacherdateoffirst;
        this.teacherdistrict = teacherdistrict;
        this.teacherhighestqualification = teacherhighestqualification;
        this.teacherdesigasfirst = teacherdesigasfirst;
        this.designation = designation;
        this.teacheruc = teacheruc;
        this.teacheranydisablity = teacheranydisablity;
        this.teachertypedisablity = teachertypedisablity;
        this.attendance = attendance;
        this.attendanceTrasnferIn = attendanceTrasnferIn;
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

    public String getTeacherfathername() {
        return teacherfathername;
    }

    public void setTeacherfathername(String teacherfathername) {
        this.teacherfathername = teacherfathername;
    }

    public String getTeachergender() {
        return teachergender;
    }

    public void setTeachergender(String teachergender) {
        this.teachergender = teachergender;
    }

    public String getTeachermarital() {
        return teachermarital;
    }

    public void setTeachermarital(String teachermarital) {
        this.teachermarital = teachermarital;
    }

    public String getTeacherbps() {
        return teacherbps;
    }

    public void setTeacherbps(String teacherbps) {
        this.teacherbps = teacherbps;
    }

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno;
    }


    public String getTeacheraccountno() {
        return teacheraccountno;
    }

    public void setTeacheraccountno(String teacheraccountno) {
        this.teacheraccountno = teacheraccountno;
    }


    public String getTeachercnic() {
        return teachercnic;
    }

    public void setTeachercnic(String teachercnic) {
        this.teachercnic = teachercnic;
    }

    public String getTeacherdob() {
        return teacherdob;
    }

    public void setTeacherdob(String teacherdob) {
        this.teacherdob = teacherdob;
    }

    public String getTeacherhighestlevel() {
        return teacherhighestlevel;
    }

    public void setTeacherhighestlevel(String teacherhighestlevel) {
        this.teacherhighestlevel = teacherhighestlevel;
    }

    public String getTeacherhigestsubject() {
        return teacherhigestsubject;
    }

    public void setTeacherhigestsubject(String teacherhigestsubject) {
        this.teacherhigestsubject = teacherhigestsubject;
    }

    public String getTeacherdateoffirst() {
        return teacherdateoffirst;
    }

    public void setTeacherdateoffirst(String teacherdateoffirst) {
        this.teacherdateoffirst = teacherdateoffirst;
    }

    public String getTeacherdistrict() {
        return teacherdistrict;
    }

    public void setTeacherdistrict(String teacherdistrict) {
        this.teacherdistrict = teacherdistrict;
    }

    public String getTeacherhighestqualification() {
        return teacherhighestqualification;
    }

    public void setTeacherhighestqualification(String teacherhighestqualification) {
        this.teacherhighestqualification = teacherhighestqualification;
    }

    public String getTeacherdesigasfirst() {
        return teacherdesigasfirst;
    }

    public void setTeacherdesigasfirst(String teacherdesigasfirst) {
        this.teacherdesigasfirst = teacherdesigasfirst;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTeacheruc() {
        return teacheruc;
    }

    public void setTeacheruc(String teacheruc) {
        this.teacheruc = teacheruc;
    }

    public String getTeacheranydisablity() {
        return teacheranydisablity;
    }

    public void setTeacheranydisablity(String teacheranydisablity) {
        this.teacheranydisablity = teacheranydisablity;
    }


    public String getTeachertypedisablity() {
        return teachertypedisablity;
    }

    public void setTeachertypedisablity(String teachertypedisablity) {
        this.teachertypedisablity = teachertypedisablity;
    }


    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }


    public String getAttendanceTrasnferIn() {
        return attendanceTrasnferIn;
    }

    public void setAttendanceTrasnferIn(String attendanceTrasnferIn) {
        this.attendanceTrasnferIn = attendanceTrasnferIn;
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
