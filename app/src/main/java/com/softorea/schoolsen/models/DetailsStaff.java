package com.softorea.schoolsen.models;

/**
 * Created by Softorea on 5/25/2017.
 */

public class DetailsStaff {
    private int id;
    private String staffname;
    private String staffno;
    private String staffcnic;
    private String staffdesignation;

    public DetailsStaff() {
    }

    public DetailsStaff(String staffname, String staffno, String staffcnic, String staffdesignation) {
        this.staffname = staffname;
        this.staffno = staffno;
        this.staffcnic = staffcnic;
        this.staffdesignation = staffdesignation;
    }

    public DetailsStaff(int id, String staffname, String staffno, String staffcnic, String staffdesignation) {
        this.id = id;
        this.staffname = staffname;
        this.staffno = staffno;
        this.staffcnic = staffcnic;
        this.staffdesignation = staffdesignation;
        //this.school=school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public String getStaffno() {
        return staffno;
    }

    public void setStaffno(String staffno) {
        this.staffno = staffno;
    }

    public String getStaffcnic() {
        return staffcnic;
    }

    public void setStaffcnic(String staffcnic) {
        this.staffcnic = staffcnic;
    }

    public String getStaffdesignation() {
        return staffdesignation;
    }

    public void setStaffdesignation(String staffdesignation) {
        this.staffdesignation = staffdesignation;
    }
    /*public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }*/
}

