package com.softorea.schoolsen.models;

import java.io.Serializable;

public class EnrollmentModel implements Serializable {

    private static final long serialVersionUID = 46543443;

    String classNo = "Null";
    String studentAsPerRegister = "Null";
    String studentPresentHeadCount = "Null";
    String girlsInBoysSchool = "Null";
    String boysInBoysSchool = "Null";


    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getStudentAsPerRegister() {
        return studentAsPerRegister;
    }

    public void setStudentAsPerRegister(String studentAsPerRegister) {
        this.studentAsPerRegister = studentAsPerRegister;
    }

    public String getStudentPresentHeadCount() {
        return studentPresentHeadCount;
    }

    public void setStudentPresentHeadCount(String studentPresentHeadCount) {
        this.studentPresentHeadCount = studentPresentHeadCount;
    }

    public String getGirlsInBoysSchool() {
        return girlsInBoysSchool;
    }

    public void setGirlsInBoysSchool(String girlsInBoysSchool) {
        this.girlsInBoysSchool = girlsInBoysSchool;
    }

    public String getBoysInBoysSchool() {
        return boysInBoysSchool;
    }

    public void setBoysInBoysSchool(String boysInBoysSchool) {
        this.boysInBoysSchool = boysInBoysSchool;
    }


}
